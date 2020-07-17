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

package net.time4j.calendar.hindu;

import net.time4j.base.MathUtils;
import net.time4j.engine.CalendarEra;
import net.time4j.format.CalendarText;
import net.time4j.format.TextWidth;

import java.util.Locale;


/**
 * <p>The Hindu calendar supports several eras in different regions of Indian subcontinent. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
/*[deutsch]
 * <p>Der Hindu-Kalender unterst&uuml;tzt mehrere &Auml;ras in verschiedenen Regionen des
 * indischen Subkontinents. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
public enum HinduEra
    implements CalendarEra {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * The onset of this ancient era (iron age) is 3179 years before Saka.
     */
    /*[deutsch]
     * Der Beginn dieser historischen &Auml;ra (eisernes Zeitalter) liegt 3179 Jahre vor Saka.
     */
    KALI_YUGA,

    /**
     * The onset of this era mainly used in Nepal is 955 years before Saka.
     */
    /*[deutsch]
     * Der Beginn dieser vorwiegend in Nepal verwendeten &Auml;ra liegt 955 Jahre vor Saka.
     */
    NEPALESE,

    /**
     * The onset of this era mainly used in Kerala (part of Malayalam calendar) is 900 years before Saka.
     */
    /*[deutsch]
     * Der Beginn dieser vorwiegend in Kerala verwendeten &Auml;ra (Bestandteil des Malayalam-Kalenders)
     * liegt 900 Jahre vor Saka.
     */
    KOLLAM,

    /**
     * The onset of this era mainly used in Northern India is 135 years before Saka.
     */
    /*[deutsch]
     * Der Beginn dieser vorwiegend in Nordindien verwendeten &Auml;ra liegt 135 Jahre vor Saka.
     */
    VIKRAMA,

    /**
     * The onset of this era is in gregorian year +78.
     */
    /*[deutsch]
     * Der Beginn dieser &Auml;ra liegt im gregorianischen Jahr +78.
     */
    SAKA,

    /**
     * The onset of this era mainly used in West Bengal is 515 years after Saka.
     */
    /*[deutsch]
     * Der Beginn dieser vorwiegend in Westbengalen verwendeten &Auml;ra liegt 515 Jahre nach Saka.
     */
    BENGAL;

    private static final int[] SAKA_OFFSETS = {3179, 955, 900, 135, 0, -515};

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
        CalendarText names = CalendarText.getInstance("extra/hindu", locale);
        return names.getEras(width).print(this);
    }

    /**
     * <p>Scales given year of era to another year related to this era. </p>
     *
     * @param   era         era reference of given year
     * @param   yearOfEra   year reckoned in given era
     * @return  year related to this era
     * @throws  IllegalArgumentException if numerical overflow occurs
     */
    /*[deutsch]
     * <p>Skaliert das angegebene Jahr der &Auml;ra zu einem anderen Jahreswert bezogen auf diese &Auml;ra. </p>
     *
     * @param   era         era reference of given year
     * @param   yearOfEra   year reckoned in given era
     * @return  year related to this era
     * @throws  IllegalArgumentException if numerical overflow occurs
     */
    public int yearOfEra(
        HinduEra era,
        int yearOfEra
    ) {
        try {
            return MathUtils.safeSubtract(
                MathUtils.safeAdd(yearOfEra, SAKA_OFFSETS[this.ordinal()]),
                SAKA_OFFSETS[era.ordinal()]);
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException("Out of range: " + yearOfEra);
        }
    }

}
