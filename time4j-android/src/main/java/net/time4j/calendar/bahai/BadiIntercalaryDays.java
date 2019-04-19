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

import net.time4j.format.CalendarText;

import java.util.Locale;
import java.util.Map;


/**
 * <p>Represents the intercalary days of the Badi calendar. </p>
 *
 * @author  Meno Hochschild
 * @see     BadiDivision#comparator()
 * @since   5.3
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die eingeschobenen Tage des Badi-Kalenders. </p>
 *
 * @author  Meno Hochschild
 * @see     BadiDivision#comparator()
 * @since   5.3
 */
public enum BadiIntercalaryDays
    implements BadiDivision {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>The singleton instance. </p>
     */
    /*[deutsch]
     * <p>Die <i>singleton</i>-Instanz. </p>
     */
    AYYAM_I_HA;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the description text dependent on the locale. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text for given locale (never {@code null})
     */
    /*[deutsch]
     * <p>Liefert den sprachabh&auml;ngigen Beschreibungstext. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text for given locale (never {@code null})
     */
    public String getDisplayName(Locale locale) {

        return CalendarText.getInstance("extra/bahai", locale).getTextForms().get("A");

    }

    /**
     * <p>Gets the meaning dependent on the locale. </p>
     *
     * <p>If a meaning is unavailable then this method will fall back to the transcription given by
     * {@link #getDisplayName(Locale)}. </p>
     *
     * @param   locale      language setting
     * @return  meaning for given locale (never {@code null})
     */
    /*[deutsch]
     * <p>Liefert die sprachabh&auml;ngige Bedeutung. </p>
     *
     * <p>Wenn die Bedeutung nicht verf&uuml;gbar ist, wird diese Methode lediglich die Transkription gegeben
     * durch {@link #getDisplayName(Locale)} liefern. </p>
     *
     * @param   locale      language setting
     * @return  meaning for given locale (never {@code null})
     */
    public String getMeaning(Locale locale) {

        Map<String, String> names = CalendarText.getInstance("extra/bahai", locale).getTextForms();
        String meaning = names.get("a");
        return (meaning == null) ? names.get("A") : meaning;

    }

}
