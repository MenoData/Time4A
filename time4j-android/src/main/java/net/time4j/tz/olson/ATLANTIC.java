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
 * <p>Contains all standard timezone IDs in Atlantic Ocean. </p>
 */
/*[deutsch]
 * <p>Enth&auml;lt alle Standard-Zeitzonen-IDs im Atlantischen Ozean. </p>
 */
public enum ATLANTIC
    implements StdZoneIdentifier {

    //~ Statische Felder/Initialisierungen --------------------------------

    AZORES("Azores", "PT"),
    BERMUDA("Bermuda", "BM"),
    CANARY("Canary", "ES"),
    CAPE_VERDE("Cape_Verde", "CV"),
    FAROE("Faroe", "FO"),
    MADEIRA("Madeira", "PT"),
    REYKJAVIK("Reykjavik", "IS"),
    SOUTH_GEORGIA("South_Georgia", "GS"),
    ST_HELENA("St_Helena", "SH"),
    STANLEY("Stanley", "FK");

    //~ Instanzvariablen --------------------------------------------------

    private final String id;
    private final String city;
    private final String country;

    //~ Konstruktoren -----------------------------------------------------

    private ATLANTIC(
        String city,
        String country
    ) {

        this.id = "Atlantic/" + city;
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

        return "Atlantic";

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

