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
 * <p>Contains all standard timezone IDs in Australia. </p>
 */
/*[deutsch]
 * <p>Enth&auml;lt alle Standard-Zeitzonen-IDs in Australien. </p>
 */
public enum AUSTRALIA
    implements StdZoneIdentifier {

    //~ Statische Felder/Initialisierungen --------------------------------

    ADELAIDE("Adelaide"),
    BRISBANE("Brisbane"),
    BROKEN_HILL("Broken_Hill"),
    CURRIE("Currie"),
    DARWIN("Darwin"),
    EUCLA("Eucla"),
    HOBART("Hobart"),
    LINDEMAN("Lindeman"),
    LORD_HOWE("Lord_Howe"),
    MELBOURNE("Melbourne"),
    PERTH("Perth"),
    SYDNEY("Sydney");

    //~ Instanzvariablen --------------------------------------------------

    private final String id;
    private final String city;

    //~ Konstruktoren -----------------------------------------------------

    private AUSTRALIA(String city) {

        this.id = "Australia/" + city;
        this.city = city;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public String canonical() {

        return this.id;

    }

    @Override
    public String getRegion() {

        return "Australia";

    }

    @Override
    public String getCity() {

        return this.city;

    }

    @Override
    public String getCountry() {

        return "AU";

    }

}

