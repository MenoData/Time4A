/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2015 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (TimezoneRepositoryProviderSPI.java) is part of project Time4J.
 *
 * Time4J is free software: You can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Time4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Time4J. If not, see <http://www.gnu.org/licenses/>.
 * -----------------------------------------------------------------------
 */

package net.time4j.tz.spi;

import net.time4j.PlainDate;
import net.time4j.base.GregorianDate;
import net.time4j.base.ResourceLoader;
import net.time4j.scale.LeapSecondProvider;
import net.time4j.tz.NameStyle;
import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZoneProvider;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;


/**
 * <p>Reads timezone repository-files compiled by the class
 * {@code net.time4j.tool.TimezoneRepositoryCompiler}. </p>
 *
 * @author  Meno Hochschild
 * @since   1.0
 */
public class TimezoneRepositoryProviderSPI
    implements ZoneProvider, LeapSecondProvider {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final Set<String> JDK_NAME_REFS;

    static {
        Set<String> jdkNameRefs = new HashSet<String>();
        Collections.addAll(jdkNameRefs, TimeZone.getAvailableIDs());
        JDK_NAME_REFS = Collections.unmodifiableSet(jdkNameRefs);
    }

    private static final ZoneProvider NAME_PROVIDER = new ZoneNameProviderSPI();

    //~ Instanzvariablen --------------------------------------------------

    private final String version;
    private final String location;
    private final Map<String, byte[]> data;
    private final Map<String, String> aliases;
    private final PlainDate expires;
    private final Map<GregorianDate, Integer> leapsecs;

    //~ Konstruktoren -----------------------------------------------------

    public TimezoneRepositoryProviderSPI() {
        super();

        URI uri = null;
        InputStream is = null;
        DataInputStream dis = null;

        String tmpVersion = "";
        String tmpLocation = "";
        PlainDate tmpExpires = PlainDate.axis().getMinimum();

        Map<String, byte[]> tmpData = new HashMap<String, byte[]>();
        Map<String, String> tmpAliases = new HashMap<String, String>();

        boolean noLeaps =
            (System.getProperty("net.time4j.scale.leapseconds.path") != null);
        if (noLeaps) {
            this.leapsecs = Collections.emptyMap();
        } else {
            this.leapsecs = new LinkedHashMap<GregorianDate, Integer>(50);
        }

        String repositoryPath =
            System.getProperty("net.time4j.tz.repository.path");
        String repositoryVersion =
            System.getProperty("net.time4j.tz.repository.version");
        String file;

        if (repositoryVersion == null) {
            file = "tzdata.repository";
        } else {
            file = "tzdata" + repositoryVersion + ".repository";
        }

        try {
            String path = null;

            if (repositoryPath != null) {
                File f = new File(repositoryPath, file);

                if (f.isAbsolute()) {
                    if (f.exists()) {
                        uri = f.toURI();
                    } else {
                        throw new FileNotFoundException(
                            "Path to tz-repository not found: " + f);
                    }
                } else {
                    path = f.toString();
                    uri = ResourceLoader.getInstance().locate("tzdata", getReference(), path);
                }
            } else {
                path = "tzrepo/" + file;
                uri = ResourceLoader.getInstance().locate("tzdata", getReference(), path);
            }

            if (uri != null) {
                is = ResourceLoader.getInstance().load(uri, true);
                dis = new DataInputStream(is);
                tmpLocation = uri.toString();
                checkMagicLabel(dis, tmpLocation);
                String v = dis.readUTF();
                int sizeOfZones = dis.readInt();

                List<String> zones = new ArrayList<String>(sizeOfZones);

                for (int i = 0; i < sizeOfZones; i++) {
                    String zoneID = dis.readUTF();
                    int dataLen = dis.readInt();
                    byte[] dataBuf = new byte[dataLen];
                    int dataRead = 0;

                    do {
                        dataRead += dis.read(dataBuf, dataRead, dataLen - dataRead);
                        if (dataRead == -1) {
                            throw new EOFException("Incomplete data: " + zoneID);
                        }
                    } while (dataLen > dataRead);

                    zones.add(zoneID);
                    tmpData.put(zoneID, dataBuf);
                }

                int sizeOfLinks = dis.readShort();

                for (int i = 0; i < sizeOfLinks; i++) {
                    String alias = dis.readUTF();
                    String id = zones.get(dis.readShort());
                    tmpAliases.put(alias, id);
                }

                if (!noLeaps) {
                    int sizeOfLeaps = dis.readShort();

                    for (int i = 0; i < sizeOfLeaps; i++) {
                        int year = dis.readShort();
                        int month = dis.readByte();
                        int dom = dis.readByte();
                        int shift = dis.readByte();

                        this.leapsecs.put(
                            PlainDate.of(year, month, dom),
                            Integer.valueOf(shift));
                    }

                    int year = dis.readShort();
                    int month = dis.readByte();
                    int dom = dis.readByte();
                    tmpExpires = PlainDate.of(year, month, dom);
                }

                tmpVersion = v; // here all is okay, so let us set the version
            }

        } catch (IOException ioe) {
            System.out.println("Warning: TZ-repository not available. => " + ioe.getMessage());
            ioe.printStackTrace(System.err);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    // ignored
                }
            }
        }

        this.version = tmpVersion;
        this.location = tmpLocation;
        this.data = Collections.unmodifiableMap(tmpData);
        this.aliases = Collections.unmodifiableMap(tmpAliases);
        this.expires = tmpExpires;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Set<String> getAvailableIDs() {

        return this.data.keySet();

    }

    @Override
    public Set<String> getPreferredIDs(
        Locale locale,
        boolean smart
    ) {

        return NAME_PROVIDER.getPreferredIDs(locale, smart);

    }

    @Override
    public String getDisplayName(
        String tzid,
        NameStyle style,
        Locale locale
    ) {


        if (JDK_NAME_REFS.contains(tzid)) {
            Timezone tz = Timezone.of("java.util.TimeZone~" + tzid);
            return tz.getDisplayName(style, locale);
        }

        return tzid; // fallback if jdk-name-data are too old

    }

    @Override
    public Map<String, String> getAliases() {

        return this.aliases;

    }

    @Override
    public TransitionHistory load(String zoneID) {

        try {
            byte[] bytes = this.data.get(zoneID);
            if (bytes != null) {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                return (TransitionHistory) ois.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public String getFallback() {

        return "";

    }

    @Override
    public String getName() {

        return "TZDB";

    }

    @Override
    public String getLocation() {

        return this.location;

    }

    @Override
    public String getVersion() {

        return this.version;

    }

    @Override
    public Map<GregorianDate, Integer> getLeapSecondTable() {

        return Collections.unmodifiableMap(this.leapsecs);

    }

    @Override
    public boolean supportsNegativeLS() {

        return !this.leapsecs.isEmpty();

    }

    @Override
    public PlainDate getDateOfEvent(
        int year,
        int month,
        int dayOfMonth
    ) {

        return PlainDate.of(year, month, dayOfMonth);

    }

    @Override
    public PlainDate getDateOfExpiration() {

        return this.expires;

    }

    @Override
    public String toString() {

        return "TZ-REPOSITORY(" + this.version + ")";

    }

    private static void checkMagicLabel(
        DataInputStream dis,
        String location
    ) throws IOException {

        int b1 = dis.readByte();
        int b2 = dis.readByte();
        int b3 = dis.readByte();
        int b4 = dis.readByte();
        int b5 = dis.readByte();
        int b6 = dis.readByte();

        if (
            (b1 != 't')
            || (b2 != 'z')
            || (b3 != 'r')
            || (b4 != 'e')
            || (b5 != 'p')
            || (b6 != 'o')
        )  {
            throw new IOException("Invalid tz-repository: " + location);
        }

    }

    private static Class<?> getReference() {

        if (Boolean.getBoolean("test.environment")) {
            try {
                return Class.forName("net.time4j.tz.repository.RepositoryTest");
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }

        return TimezoneRepositoryProviderSPI.class;

    }

}
