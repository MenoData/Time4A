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

package net.time4j.calendar.bahai;


/**
 * Controls the type of content to be formatted in the Badi calendar.
 *
 * @author  Meno Hochschild
 * @see     BadiMonth
 * @see     BadiIntercalaryDays
 * @see     BadiCalendar#YEAR_OF_VAHID
 * @since   5.3
 */
/*[deutsch]
 * <p>Steuert den Typ des Inhalts von zu formatierenden Elementen im Badi-Kalender. </p>
 *
 * @author  Meno Hochschild
 * @see     BadiMonth
 * @see     BadiIntercalaryDays
 * @see     BadiCalendar#YEAR_OF_VAHID
 * @since   5.3
 */
public enum FormattedContent {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Mandates the usual way of transcription of element content in a given language.
     */
    /*[deutsch]
     * Bestimmt, da&szlig; ein Element in der sprach&uuml;blichen Umschreibung zu formatieren ist.
     */
    TRANSCRIPTION {
        @Override
        String variant() {
            return "t";
        }
    },

    /**
     * Calls for the meaning of element content in a given language.
     */
    /*[deutsch]
     * Bestimmt, da&szlig; die sprachabh&auml;ngige Bedeutung eines Elements zu formatieren ist.
     */
    MEANING {
        @Override
        String variant() {
            return "m";
        }
    },

    /**
     * Mandates the HTML way of transcription of element content in a given language.
     *
     * Only relevant for English.
     */
    /*[deutsch]
     * Bestimmt, da&szlig; ein Element in der HTML-Umschreibung zu formatieren ist.
     *
     * Nur f&uuml;r Englisch relevant.
     */
    HTML {
        @Override
        String variant() {
            return "h";
        }
    };

    //~ Methoden ----------------------------------------------------------

    // obtains the variant key in text resources
    abstract String variant();

}
