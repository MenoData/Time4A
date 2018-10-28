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
 * <p>The Indian national calendar only supports one single era called &quot;Saka&quot;. </p>
 *
 * @author  Meno Hochschild
 * @since   3.32/4.27
 */
/*[deutsch]
 * <p>Der indische Nationalkalender unterst&uuml;tzt nur eine einzige &Auml;ra, die &quot;Saka&quot;
 * genannt wird. </p>
 *
 * @author  Meno Hochschild
 * @since   3.32/4.27
 */
public enum IndianEra
    implements CalendarEra {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Singleton instance (starting in 78 AD as year zero).
     */
    /*[deutsch]
     * Singleton-Instanz (beginnt im Jahre 78 AD als Jahr 0).
     */
    SAKA;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Equivalent to the expression {@code getDisplayName(locale, TextWidth.WIDE)}. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (long form, never {@code null})
     * @see     #getDisplayName(Locale, TextWidth)
     */
    /*[deutsch]
     * <p>Entspricht dem Ausdruck {@code getDisplayName(locale, TextWidth.WIDE)}. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (long form, never {@code null})
     * @see     #getDisplayName(Locale, TextWidth)
     */
    public String getDisplayName(Locale locale) {

        return this.getDisplayName(locale, TextWidth.WIDE);

    }

    /**
     * <p>Gets the description text dependent on the locale and style parameters. </p>
     *
     * <p>The second argument controls the width of description. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @return  descriptive text for given locale and style (never {@code null})
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
     */
    public String getDisplayName(
        Locale locale,
        TextWidth width
    ) {

        CalendarText names = CalendarText.getInstance("indian", locale);
        return names.getEras(width).print(this);

    }

}
