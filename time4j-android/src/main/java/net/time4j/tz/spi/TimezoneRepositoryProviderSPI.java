/*
 * Licensed by the author of Time4J-project.
 *
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership. The copyright owner
 * licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.time4j.tz.spi;

import net.time4j.PlainDate;
import net.time4j.base.GregorianDate;
import net.time4j.base.ResourceLoader;
import net.time4j.scale.LeapSecondProvider;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZoneModelProvider;
import net.time4j.tz.ZoneNameProvider;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p>Reads timezone repository-files compiled by the class
 * {@code net.time4j.tool.TimezoneRepositoryCompiler}. </p>
 *
 * @author  Meno Hochschild
 * @since   1.0
 */
public class TimezoneRepositoryProviderSPI
    implements ZoneModelProvider, LeapSecondProvider {

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
        IllegalStateException ise = null;

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
            String path = "tzrepo/" + file;

            if (repositoryPath != null) {
                File f = new File(repositoryPath, file);

                if (f.isAbsolute()) {
                    if (f.exists()) {
                        uri = f.toURI();
                    } else {
                        throw new FileNotFoundException("Path to tz-repository not found: " + f);
                    }
                } else {
                    uri = ResourceLoader.getInstance().locate("tzdata", getReference(), f.toString());
                }
            } else {
                uri = ResourceLoader.getInstance().locate("tzdata", getReference(), path);
            }

            if (uri != null) {
                is = ResourceLoader.getInstance().load(uri, true);
                tmpLocation = uri.toString();
            }

            if (is == null) {
                // fallback if something has gone wrong (maybe invalid uri from protection domain etc.)
                URL url = getReference().getClassLoader().getResource(path);
                if (url == null) {
                    throw new FileNotFoundException("Classloader cannot access tz-repository: " + path);
                } else {
                    URLConnection conn = url.openConnection();
                    conn.setUseCaches(false);
                    conn.connect(); // explicit for clarity
                    is = conn.getInputStream();
                    tmpLocation = url.toString();
                }
            }

            DataInputStream dis = new DataInputStream(is);
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

        } catch (IOException ioe) {
            ise = new IllegalStateException("[ERROR] TZ-repository not available. => " + ioe.getMessage(), ioe);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    // ignored
                }
            }
        }

        if (ise != null) {
            throw ise;
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
    public ZoneNameProvider getSpecificZoneNameRepository() {

        return null;

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
                return Class.forName("net.time4j.tz.spi.RepositoryTest");
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }

        return TimezoneRepositoryProviderSPI.class;

    }

}
