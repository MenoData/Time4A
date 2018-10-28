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
 * <p>Defines the width of a formatted output of chronological element values
 * as text. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Definiert die Breite einer formatierten Ausgabe von chronologischen
 * Elementwerten als Text. </p>
 *
 * @author  Meno Hochschild
 */
public enum TextWidth {

    //~ Statische Felder/Initialisierungen --------------------------------

    /** Full text width. */
    /*[deutsch] Volle Textbreite. */
    WIDE,

    /** Abbreviation (long form). */
    /*[deutsch] Abk&uuml;rzung (Langform). */
    ABBREVIATED,

    /**
     * Abbreviation (short form - usually identical with {@link #ABBREVIATED}).
     */
    /*[deutsch]
     * Abk&uuml;rzung (Kurzform - meist identisch mit {@link #ABBREVIATED}).
     */
    SHORT,

    /** Symbol form (typically only one single letter). */
    /*[deutsch] Symbolform (typischerweise nur ein Buchstabe). */
    NARROW;

}
