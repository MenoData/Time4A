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

package net.time4j.tz.olson;


/**
 * <p>Contains all standard timezone IDs in Asia. </p>
 */
/*[deutsch]
 * <p>Enth&auml;lt alle Standard-Zeitzonen-IDs in Asien. </p>
 */
public enum ASIA
    implements StdZoneIdentifier {

    //~ Statische Felder/Initialisierungen --------------------------------

    ADEN("Aden", "YE"),
    ALMATY("Almaty", "KZ"),
    AMMAN("Amman", "JO"),
    ANADYR("Anadyr", "RU"),
    AQTAU("Aqtau", "KZ"),
    AQTOBE("Aqtobe", "KZ"),
    ASHGABAT("Ashgabat", "TM"),
    BAGHDAD("Baghdad", "IQ"),
    BAHRAIN("Bahrain", "BH"),
    BAKU("Baku", "AZ"),
    BANGKOK("Bangkok", "TH"),
    BEIRUT("Beirut", "LB"),
    BISHKEK("Bishkek", "KG"),
    BRUNEI("Brunei", "BN"),
    CHOIBALSAN("Choibalsan", "MN"),
    CHONGQING("Chongqing", "CN"),
    COLOMBO("Colombo", "LK"),
    DAMASCUS("Damascus", "SY"),
    DHAKA("Dhaka", "BD"),
    DILI("Dili", "TL"),
    DUBAI("Dubai", "AE"),
    DUSHANBE("Dushanbe", "TJ"),
    GAZA("Gaza", "PS"),
    HARBIN("Harbin", "CN"),
    HEBRON("Hebron", "PS"),
    HO_CHI_MINH("Ho_Chi_Minh", "VN"),
    HONG_KONG("Hong_Kong", "HK"),
    HOVD("Hovd", "MN"),
    IRKUTSK("Irkutsk", "RU"),
    JAKARTA("Jakarta", "ID"),
    JAYAPURA("Jayapura", "ID"),
    JERUSALEM("Jerusalem", "IL"),
    KABUL("Kabul", "AF"),
    KAMCHATKA("Kamchatka", "RU"),
    KARACHI("Karachi", "PK"),
    KASHGAR("Kashgar", "CN"),
    KATHMANDU("Kathmandu", "NP"),
    KHANDYGA("Khandyga", "RU"),
    KOLKATA("Kolkata", "IN"),
    KRASNOYARSK("Krasnoyarsk", "RU"),
    KUALA_LUMPUR("Kuala_Lumpur", "MY"),
    KUCHING("Kuching", "MY"),
    KUWAIT("Kuwait", "KW"),
    MACAU("Macau", "MO"),
    MAGADAN("Magadan", "RU"),
    MAKASSAR("Makassar", "ID"),
    MANILA("Manila", "PH"),
    MUSCAT("Muscat", "OM"),
    NICOSIA("Nicosia", "CY"),
    NOVOKUZNETSK("Novokuznetsk", "RU"),
    NOVOSIBIRSK("Novosibirsk", "RU"),
    OMSK("Omsk", "RU"),
    ORAL("Oral", "KZ"),
    PHNOM_PENH("Phnom_Penh", "KH"),
    PONTIANAK("Pontianak", "ID"),
    PYONGYANG("Pyongyang", "KP"),
    QATAR("Qatar", "QA"),
    QYZYLORDA("Qyzylorda", "KZ"),
    RANGOON("Rangoon", "MM"),
    RIYADH("Riyadh", "SA"),
    SAKHALIN("Sakhalin", "RU"),
    SAMARKAND("Samarkand", "UZ"),
    SEOUL("Seoul", "KR"),
    SHANGHAI("Shanghai", "CN"),
    SINGAPORE("Singapore", "SG"),
    TAIPEI("Taipei", "TW"),
    TASHKENT("Tashkent", "UZ"),
    TBILISI("Tbilisi", "GE"),
    TEHRAN("Tehran", "IR"),
    THIMPHU("Thimphu", "BT"),
    TOKYO("Tokyo", "JP"),
    ULAANBAATAR("Ulaanbaatar", "MN"),
    URUMQI("Urumqi", "CN"),
    UST_NERA("Ust-Nera", "RU"),
    VIENTIANE("Vientiane", "LA"),
    VLADIVOSTOK("Vladivostok", "RU"),
    YAKUTSK("Yakutsk", "RU"),
    YEKATERINBURG("Yekaterinburg", "RU"),
    YEREVAN("Yerevan", "AM");

    //~ Instanzvariablen --------------------------------------------------

    private final String id;
    private final String city;
    private final String country;

    //~ Konstruktoren -----------------------------------------------------

    private ASIA(
        String city,
        String country
    ) {

        this.id = "Asia/" + city;
        this.city = city;
        this.country = country;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public String canonical() {

        return this.id;

    }

    @Override
    public String getRegion() {

        return "Asia";

    }

    @Override
    public String getCity() {

        return this.city;

    }

    @Override
    public String getCountry() {

        return this.country;

    }

}
