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
 * <p>Contains all standard timezone IDs in Europe. </p>
 */
/*[deutsch]
 * <p>Enth&auml;lt alle Standard-Zeitzonen-IDs in Europa. </p>
 */
public enum EUROPE
    implements StdZoneIdentifier {

    //~ Statische Felder/Initialisierungen --------------------------------

    AMSTERDAM("Amsterdam", "NL"),
    ANDORRA("Andorra", "AD"),
    ATHENS("Athens", "GR"),
    BELGRADE("Belgrade", "RS"),
    BERLIN("Berlin", "DE"),
    BRATISLAVA("Bratislava", "SK"), // link to Europe/Prague
    BRUSSELS("Brussels", "BE"),
    BUCHAREST("Bucharest", "RO"),
    BUDAPEST("Budapest", "HU"),
    CHISINAU("Chisinau", "MD"),
    COPENHAGEN("Copenhagen", "DK"),
    DUBLIN("Dublin", "IE"),
    GIBRALTAR("Gibraltar", "GI"),
    GUERNSEY("Guernsey", "GG"), // link to Europe/London
    HELSINKI("Helsinki", "FI"),
    ISLE_OF_MAN("Isle_of_Man", "IM"), // link to Europe/London
    ISTANBUL("Istanbul", "TR"),
    JERSEY("Jersey", "JE"), // link to Europe/London
    KALININGRAD("Kaliningrad", "RU"),
    KIEV("Kiev", "UA"),
    LISBON("Lisbon", "PT"),
    LJUBLJANA("Ljubljana", "SI"), // link to Europe/Belgrade
    LONDON("London", "GB"),
    LUXEMBOURG("Luxembourg", "LU"),
    MADRID("Madrid", "ES"),
    MALTA("Malta", "MT"),
    MARIEHAMN("Mariehamn", "AX"), // link to Europe/Helsinki
    MINSK("Minsk", "BY"),
    MONACO("Monaco", "MC"),
    MOSCOW("Moscow", "RU"),
    OSLO("Oslo", "NO"),
    PARIS("Paris", "FR"),
    PODGORICA("Podgorica", "ME"), // link to Europe/Belgrade
    PRAGUE("Prague", "CZ"),
    RIGA("Riga", "LV"),
    ROME("Rome", "IT"),
    SAMARA("Samara", "RU"),
    SAN_MARINO("San_Marino", "SM"), // link to Europe/Rome
    SARAJEVO("Sarajevo", "BA"), // link to Europe/Belgrade
    SIMFEROPOL("Simferopol", "UA"),
    SOFIA("Sofia", "BG"),
    SKOPJE("Skopje", "MK"), // link to Europe/Belgrade
    STOCKHOLM("Stockholm", "SE"),
    TALLINN("Tallinn", "EE"),
    TIRANE("Tirane", "AL"),
    UZHGOROD("Uzhgorod", "UA"),
    VADUZ("Vaduz", "LI"),
    VATICAN("Vatican", "VA"), // link to Europe/Rome
    VIENNA("Vienna", "AT"),
    VILNIUS("Vilnius", "LT"),
    VOLGOGRAD("Volgograd", "RU"),
    WARSAW("Warsaw", "PL"),
    ZAGREB("Zagreb", "HR"), // link to Europe/Belgrade
    ZAPOROZHYE("Zaporozhye", "UA"),
    ZURICH("Zurich", "CH");

    //~ Instanzvariablen --------------------------------------------------

    private final String id;
    private final String city;
    private final String country;

    //~ Konstruktoren -----------------------------------------------------

    private EUROPE(
        String city,
        String country
    ) {

        this.id = "Europe/" + city;
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

        return "Europe";

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
