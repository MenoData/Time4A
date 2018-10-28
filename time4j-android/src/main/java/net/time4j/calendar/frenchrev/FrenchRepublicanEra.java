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

package net.time4j.calendar.frenchrev;

import net.time4j.engine.CalendarEra;
import net.time4j.format.CalendarText;
import net.time4j.format.TextWidth;

import java.util.Locale;


/**
 * <p>The French revolutionary calendar only supports one single era which is related
 * to the proclamation of the French republic in year 1792. </p>
 *
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
/*[deutsch]
 * <p>Der franz&ouml;sische Revolutionskalender unterst&uuml;tzt nur eine einzige &Auml;ra, die sich auf
 * die Ausrufung der franz&ouml;sischen Republik im Jahre 1792 bezieht. </p>
 *
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
public enum FrenchRepublicanEra
    implements CalendarEra {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Singleton instance (starting 22nd September 1792 as year one).
     */
    /*[deutsch]
     * Singleton-Instanz (beginnt am 22&#46; September 1792 als Jahr 1).
     */
    REPUBLICAN;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the description text dependent on the locale. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @return  descriptive text for given locale and width (never {@code null})
     */
    /*[deutsch]
     * <p>Liefert den sprachabh&auml;ngigen Beschreibungstext. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @return  descriptive text for given locale and width (never {@code null})
     */
    public String getDisplayName(
        Locale locale,
        TextWidth width
    ) {

        CalendarText names = CalendarText.getInstance("extra/frenchrev", locale);
        return names.getEras(width).print(this);

    }

}
