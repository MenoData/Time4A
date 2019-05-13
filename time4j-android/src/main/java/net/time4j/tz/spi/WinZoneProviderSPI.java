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

import net.time4j.base.ResourceLoader;
import net.time4j.tz.NameStyle;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZoneModelProvider;
import net.time4j.tz.ZoneNameProvider;
import net.time4j.tz.other.WindowsZone;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


/**
 * <p>SPI-implementation for support of Windows timezones. </p>
 *
 * @author  Meno Hochschild
 * @since   3.1
 */
public class WinZoneProviderSPI
    implements ZoneModelProvider, ZoneNameProvider {

    //~ Statische Felder/Initialisierungen --------------------------------

    // Map<country, Map<tzid, name>>
    private static final Map<String, Map<String, String>> REPOSITORY;

    // Map<country, Set<tzid>>
    private static final Map<String, Set<String>> PREFERRED_KEYS;

    // Map<name, Map<country, Set<tzid>>>
    public static final Map<String, Map<String, Set<TZID>>> NAME_BASED_MAP;

    // Version of windowsZones.xml
    public static final String WIN_NAME_VERSION;

    private static final String VKEY = "VERSION";

    static {
        Map<String, Map<String, String>> map = loadData();
        WIN_NAME_VERSION = map.get(VKEY).keySet().iterator().next();
        map.remove(VKEY);
        REPOSITORY = Collections.unmodifiableMap(map);
        PREFERRED_KEYS = prepareSmartMode();
        NAME_BASED_MAP = prepareResolvers();
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Set<String> getAvailableIDs() {

        Set<String> zones = new HashSet<String>();

        for (TZID tzid : Timezone.getAvailableIDs("DEFAULT")) {
            zones.add("WINDOWS~" + tzid.canonical());
        }

        return Collections.unmodifiableSet(zones);

    }

    @Override
    public Map<String, String> getAliases() {

        return Collections.emptyMap();

    }

    @Override
    public String getFallback() {

        return "DEFAULT";

    }

    @Override
    public String getName() {

        return "WINDOWS";

    }

    @Override
    public String getLocation() {

        return "";

    }

    @Override
    public String getVersion() {

        return "";

    }

    @Override
    public ZoneNameProvider getSpecificZoneNameRepository() {

        return this;

    }

    @Override
    public TransitionHistory load(String zoneID) {

        return null; // uses fallback

    }

    @Override
    public Set<String> getPreferredIDs(
        Locale locale,
        boolean smart
    ) {

        return getPreferredIDs(locale.getCountry(), smart);

    }

    @Override
    public String getDisplayName(
        String tzid,
        NameStyle style, // unused
        Locale locale
    ) {

        if (tzid.isEmpty()) {
            return "";
        }

        Map<String, String> map = idsToNames(locale.getCountry());
        String name = map.get("WINDOWS~" + tzid);
        return ((name == null) ? "" : name);

    }

    @Override
    public String getStdFormatPattern(
        boolean zeroOffset,
        Locale locale
    ) {

        return (zeroOffset ? "GMT" : "GMT\u00B1hh:mm");

    }

    private static Map<String, String> idsToNames(String country) {

        Map<String, String> map = REPOSITORY.get(country);

        if (map == null) {
            return Collections.emptyMap();
        } else {
            return Collections.unmodifiableMap(map);
        }

    }

    private static Set<String> getPreferredIDs(
        String country,
        boolean smart
    ) {

        return (
            smart
                ? getPreferences(country)
                : idsToNames(country).keySet());

    }

    private static Set<String> getPreferences(String country) {

        Set<String> preferences = PREFERRED_KEYS.get(country);

        if (preferences == null) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableSet(preferences);
        }

    }

    private static Map<String, Map<String, String>> loadData() {

        ObjectInputStream ois = null;

        try {
            String source = "data/winzone.ser";
            URI uri = ResourceLoader.getInstance().locate("olson", WinZoneProviderSPI.class, source);
            InputStream is = ResourceLoader.getInstance().load(uri, true);
            if (is == null) {
                is = WinZoneProviderSPI.class.getClassLoader().getResourceAsStream(source);
            }
            ois = new ObjectInputStream(is);
            String version = ois.readUTF();
            Map<String, Map<String, String>> data = cast(ois.readObject());
            Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>(data);
            map.put(VKEY, Collections.singletonMap(version, version));
            return map;
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException(ex);
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj) {

        return (T) obj;

    }

    private static Map<String, Set<String>> prepareSmartMode() {

        Map<String, Set<String>> preferredKeys = new HashMap<String, Set<String>>();

        for (String country : REPOSITORY.keySet()) {
            Map<String, String> map = idsToNames(country);
            Set<String> keys = map.keySet();

            if (keys.size() >= 2) {
                keys = new HashSet<String>();
                Set<String> names = new HashSet<String>(map.values());

                for (String name : names) {
                    for (Map.Entry<String, String> e : getFallbackSet()) {
                        if (e.getValue().equals(name)) {
                            keys.add(e.getKey());
                        }
                    }
                }
            }

            preferredKeys.put(country, keys);
        }

        return Collections.unmodifiableMap(preferredKeys);

    }

    private static Set<Map.Entry<String, String>> getFallbackSet() {

        return idsToNames("001").entrySet();

    }

    private static Map<String, Map<String, Set<TZID>>> prepareResolvers() {

        Map<String, Map<String, Set<TZID>>> nameBasedMap =
            new HashMap<String, Map<String, Set<TZID>>>();

        for (String country : REPOSITORY.keySet()) {
            Map<String, String> idsToNames = REPOSITORY.get(country);

            for (Map.Entry<String, String> e : idsToNames.entrySet()) {
                String id = e.getKey();
                String name = e.getValue();

                Map<String, Set<TZID>> countryToIds = nameBasedMap.get(name);
                if (countryToIds == null) {
                    countryToIds = new HashMap<String, Set<TZID>>();
                    nameBasedMap.put(name, countryToIds);
                }

                Set<TZID> ids = countryToIds.get(country);
                if (ids == null) {
                    ids = new HashSet<TZID>();
                    countryToIds.put(country, ids);
                }

                ids.add(new WinZoneID(id));
            }
        }

        return Collections.unmodifiableMap(nameBasedMap);

    }

}
