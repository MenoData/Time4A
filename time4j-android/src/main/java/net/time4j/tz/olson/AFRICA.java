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
 * <p>Contains all standard timezone IDs in Africa. </p>
 */
/*[deutsch]
 * <p>Enth&auml;lt alle Standard-Zeitzonen-IDs in Afrika. </p>
 */
public enum AFRICA
    implements StdZoneIdentifier {

    //~ Statische Felder/Initialisierungen --------------------------------

    ABIDJAN("Abidjan", "CI"),
    ACCRA("Accra", "GH"),
    ADDIS_ABABA("Addis_Ababa", "ET"),
    ALGIERS("Algiers", "DZ"),
    ASMARA("Asmara", "ER"),
    BAMAKO("Bamako", "ML"),
    BANGUI("Bangui", "CF"),
    BANJUL("Banjul", "GM"),
    BISSAU("Bissau", "GW"),
    BLANTYRE("Blantyre", "MW"),
    BRAZZAVILLE("Brazzaville", "CG"),
    BUJUMBURA("Bujumbura", "BI"),
    CAIRO("Cairo", "EG"),
    CASABLANCA("Casablanca", "MA"),
    CEUTA("Ceuta", "ES"),
    CONAKRY("Conakry", "GN"),
    DAKAR("Dakar", "SN"),
    DAR_ES_SALAAM("Dar_es_Salaam", "TZ"),
    DJIBOUTI("Djibouti", "DJ"),
    DOUALA("Douala", "CM"),
    EL_AAIUN("El_Aaiun", "EH"),
    FREETOWN("Freetown", "SL"),
    GABORONE("Gaborone", "BW"),
    HARARE("Harare", "ZW"),
    JOHANNESBURG("Johannesburg", "ZA"),
    JUBA("Juba", "SS"),
    KAMPALA("Kampala", "UG"),
    KHARTOUM("Khartoum", "SD"),
    KIGALI("Kigali", "RW"),
    KINSHASA("Kinshasa", "CD"),
    LAGOS("Lagos", "NG"),
    LIBREVILLE("Libreville", "GA"),
    LOME("Lome", "TG"),
    LUANDA("Luanda", "AO"),
    LUBUMBASHI("Lubumbashi", "CD"),
    LUSAKA("Lusaka", "ZM"),
    MALABO("Malabo", "GQ"),
    MAPUTO("Maputo", "MZ"),
    MASERU("Maseru", "LS"),
    MBABANE("Mbabane", "SZ"),
    MOGADISHU("Mogadishu", "SO"),
    MONROVIA("Monrovia", "LR"),
    NAIROBI("Nairobi", "KE"),
    NDJAMENA("Ndjamena", "TD"),
    NIAMEY("Niamey", "NE"),
    NOUAKCHOTT("Nouakchott", "MR"),
    OUAGADOUGOU("Ouagadougou", "BF"),
    PORTO_NOVO("Porto-Novo", "BJ"),
    SAO_TOME("Sao_Tome", "ST"),
    TRIPOLI("Tripoli", "LY"),
    TUNIS("Tunis", "TN"),
    WINDHOEK("Windhoek", "NA");

    //~ Instanzvariablen --------------------------------------------------

    private final String id;
    private final String city;
    private final String country;

    //~ Konstruktoren -----------------------------------------------------

    private AFRICA(
        String city,
        String country
    ) {

        this.id = "Africa/" + city;
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

        return "Africa";

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
