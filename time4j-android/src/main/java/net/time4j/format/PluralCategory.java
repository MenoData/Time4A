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

package net.time4j.format;


/**
 * <p>Enumeration of CLDR plural categories. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 * @see     PluralRules
 */
/*[deutsch]
 * <p>Aufz&auml;hlung der CLDR-Pluralkategorien. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 * @see     PluralRules
 */
public enum PluralCategory {

    //~ Statische Felder/Initialisierungen --------------------------------

    ZERO,

    ONE,

    TWO,

    FEW,

    MANY,

    /**
     * <p>This category serves as fallback if any other category cannot be found
     * in CLDR-data. </p>
     */
    /*[deutsch]
     * <p>Diese Kategorie dient als Ausweichoption, wenn irgendeine andere
     * Kategorie nicht in den CLDR-Daten verf&uuml;gbar ist. </p>
     */
    OTHER;

}
