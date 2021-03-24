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

import net.time4j.engine.CalendarEra;
import net.time4j.format.CalendarText;
import net.time4j.format.TextAccessor;
import net.time4j.format.TextWidth;

import java.util.Locale;


/**
 * <p>The Badi calendar only supports one single era which is related
 * to the gregorian date of 21st of March in year 1844. </p>
 *
 * @author  Meno Hochschild
 * @since   5.3
 */
/*[deutsch]
 * <p>Der Badi-Kalender unterst&uuml;tzt nur eine einzige &Auml;ra, die sich auf
 * das gregorianische Datum 1844-03-21 bezieht. </p>
 *
 * @author  Meno Hochschild
 * @since   5.3
 */
public enum BadiEra
    implements CalendarEra {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Singleton instance.
     */
    /*[deutsch]
     * Singleton-Instanz.
     */
    BAHAI;

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

        return accessor(locale, width).print(this);

    }

    // also called by era element
    static TextAccessor accessor(
        Locale locale,
        TextWidth width
    ) {

        String variant;

        switch (width) {
            case WIDE:
                variant = "w";
                break;
            case ABBREVIATED:
            case SHORT:
                variant = "a";
                break;
            case NARROW:
                variant = "n";
                break;
            default:
                throw new UnsupportedOperationException(width.name());
        }

        CalendarText ct = CalendarText.getInstance("bahai", locale);
        return ct.getTextForms("E", BadiEra.class, variant);

    }

}
