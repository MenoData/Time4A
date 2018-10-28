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

package net.time4j.calendar;

import net.time4j.engine.CalendarEra;
import net.time4j.format.CalendarText;
import net.time4j.format.TextWidth;

import java.util.Locale;


/**
 * <p>The Minguo calendar supports two eras related to the year 1912. </p>
 *
 * @author  Meno Hochschild
 * @since   3.13/4.10
 */
/*[deutsch]
 * <p>Der Minguo-Kalender unterst&uuml;tzt zwei &Auml;ras, die sich auf das Jahr 1912 beziehen. </p>
 *
 * @author  Meno Hochschild
 * @since   3.13/4.10
 */
public enum MinguoEra
    implements CalendarEra {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Used for dates before 1912-01-01.
     */
    /*[deutsch]
     * Verwendet f&uuml;r Datumsangaben vor 1912-01-01.
     */
    BEFORE_ROC,

    /**
     * Used for dates from 1912-01-01 or later.
     */
    /*[deutsch]
     * Verwendet f&uuml;r Datumsangaben ab 1912-01-01.
     */
    ROC;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Equivalent to the expression {@code getDisplayName(locale, TextWidth.WIDE)}. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (long form, never {@code null})
     * @see     #getDisplayName(Locale, TextWidth)
     * @since   3.5/4.3
     */
    /*[deutsch]
     * <p>Entspricht dem Ausdruck {@code getDisplayName(locale, TextWidth.WIDE)}. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (long form, never {@code null})
     * @see     #getDisplayName(Locale, TextWidth)
     * @since   3.5/4.3
     */
    public String getDisplayName(Locale locale) {

        return this.getDisplayName(locale, TextWidth.WIDE);

    }

    /**
     * <p>Gets the description text dependent on the locale and style
     * parameters. </p>
     *
     * <p>The second argument controls the width of description. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @return  descriptive text for given locale and style (never {@code null})
     * @since   3.5/4.3
     */
    /*[deutsch]
     * <p>Liefert den sprachabh&auml;ngigen Beschreibungstext. </p>
     *
     * <p>&Uuml;ber das zweite Argument kann gesteuert werden, ob eine kurze
     * oder eine lange Form des Beschreibungstexts ausgegeben werden soll. Das
     * ist besonders sinnvoll in Benutzeroberfl&auml;chen, wo zwischen der
     * Beschriftung und der detaillierten Erl&auml;uterung einer graphischen
     * Komponente unterschieden wird. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @return  descriptive text for given locale and style (never {@code null})
     * @since   3.5/4.3
     */
    public String getDisplayName(
        Locale locale,
        TextWidth width
    ) {

        CalendarText names = CalendarText.getInstance("roc", locale);
        return names.getEras(width).print(this);

    }

}
