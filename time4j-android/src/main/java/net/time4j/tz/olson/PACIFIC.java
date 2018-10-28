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
 * <p>Contains all standard timezone IDs in Pacific Ocean. </p>
 */
/*[deutsch]
 * <p>Enth&auml;lt alle Standard-Zeitzonen-IDs im Pazifischen Ozean. </p>
 */
public enum PACIFIC
    implements StdZoneIdentifier {

    //~ Statische Felder/Initialisierungen --------------------------------

    APIA("Apia", "WS"),
    AUCKLAND("Auckland", "NZ"),
    CHATHAM("Chatham", "NZ"),
    CHUUK("Chuuk", "FM"),
    EASTER("Easter", "CL"),
    EFATE("Efate", "VU"),
    ENDERBURY("Enderbury", "KI"),
    FAKAOFO("Fakaofo", "TK"),
    FIJI("Fiji", "FJ"),
    FUNAFUTI("Funafuti", "TV"),
    GALAPAGOS("Galapagos", "EC"),
    GAMBIER("Gambier", "PF"),
    GUADALCANAL("Guadalcanal", "SB"),
    GUAM("Guam", "GU"),
    HONOLULU("Honolulu", "US"),
    JOHNSTON("Johnston", "UM"),
    KIRITIMATI("Kiritimati", "KI"),
    KOSRAE("Kosrae", "FM"),
    KWAJALEIN("Kwajalein", "MH"),
    MAJURO("Majuro", "MH"),
    MARQUESAS("Marquesas", "PF"),
    MIDWAY("Midway", "UM"),
    NAURU("Nauru", "NR"),
    NIUE("Niue", "NU"),
    NORFOLK("Norfolk", "NF"),
    NOUMEA("Noumea", "NC"),
    PAGO_PAGO("Pago_Pago", "AS"),
    PALAU("Palau", "PW"),
    PITCAIRN("Pitcairn", "PN"),
    POHNPEI("Pohnpei", "FM"),
    PORT_MORESBY("Port_Moresby", "PG"),
    RAROTONGA("Rarotonga", "CK"),
    SAIPAN("Saipan", "MP"),
    TAHITI("Tahiti", "PF"),
    TARAWA("Tarawa", "KI"),
    TONGATAPU("Tongatapu", "TO"),
    WAKE("Wake", "UM"),
    WALLIS("Wallis", "WF");

    //~ Instanzvariablen --------------------------------------------------

    private final String id;
    private final String city;
    private final String country;

    //~ Konstruktoren -----------------------------------------------------

    private PACIFIC(
        String city,
        String country
    ) {

        this.id = "Pacific/" + city;
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

        return "Pacific";

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
