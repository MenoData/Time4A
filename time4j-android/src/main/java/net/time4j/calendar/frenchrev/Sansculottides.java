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

import net.time4j.format.CalendarText;
import net.time4j.format.OutputContext;

import java.util.Locale;


/**
 * <p>Represents complementary days of the French revolutionary calendar. </p>
 *
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Erg&auml;nzungstage des franz&ouml;sischen Revolutionskalenders. </p>
 *
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
public enum Sansculottides {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>The first complementary day, also called &quot;jour de la vertu&quot;. </p>
     */
    /*[deutsch]
     * <p>Der erste Erg&auml;nzungstag, auch &quot;jour de la vertu&quot; genannt. </p>
     */
    COMPLEMENTARY_DAY_1,

    /**
     * <p>The second complementary day, also called &quot;jour du g&eacute;nie&quot;. </p>
     */
    /*[deutsch]
     * <p>Der zweite Erg&auml;nzungstag, auch &quot;jour du g&eacute;nie&quot; genannt. </p>
     */
    COMPLEMENTARY_DAY_2,

    /**
     * <p>The third complementary day, also called &quot;jour du travail&quot;. </p>
     */
    /*[deutsch]
     * <p>Der dritte Erg&auml;nzungstag, auch &quot;jour du travail&quot; genannt. </p>
     */
    COMPLEMENTARY_DAY_3,

    /**
     * <p>The fourth complementary day, also called &quot;jour de l&#39;opinion&quot;. </p>
     */
    /*[deutsch]
     * <p>Der vierte Erg&auml;nzungstag, auch &quot;jour de l&#39;opinion&quot; genannt. </p>
     */
    COMPLEMENTARY_DAY_4,

    /**
     * <p>The fifth complementary day, also called &quot;jour des r&eacute;compenses&quot;. </p>
     */
    /*[deutsch]
     * <p>Der f&uuml;nfte Erg&auml;nzungstag, auch &quot;jour des r&eacute;compenses&quot; genannt. </p>
     */
    COMPLEMENTARY_DAY_5,

    /**
     * <p>The sixth complementary day (only in leap years), also called &quot;jour de la r&eacute;volution&quot;. </p>
     */
    /*[deutsch]
     * <p>Der sechste Erg&auml;nzungstag (nur in Schaltjahren),
     * auch &quot;jour de la r&eacute;volution&quot; genannt. </p>
     */
    LEAP_DAY;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the enum-constant which corresponds to the given numerical value. </p>
     *
     * @param   num     number of sansculottide day in the range [1-6]
     * @return  sansculottide day as enum
     * @throws  IllegalArgumentException if given argument is out of range
     */
    /*[deutsch]
     * <p>Liefert die zum kalendarischen Integer-Wert passende Enum-Konstante. </p>
     *
     * @param   num     number of sansculottide day in the range [1-6]
     * @return  sansculottide day as enum
     * @throws  IllegalArgumentException if given argument is out of range
     */
    public static Sansculottides valueOf(int num) {

        if ((num < 1) || (num > 6)) {
            throw new IllegalArgumentException("Out of range: " + num);
        }

        return Sansculottides.values()[num - 1];

    }

    /**
     * <p>Gets the corresponding numerical value. </p>
     *
     * @return  number of sansculottide day in the range [1-6]
     */
    /*[deutsch]
     * <p>Liefert den korrespondierenden kalendarischen Integer-Wert. </p>
     *
     * @return  number of sansculottide day in the range [1-6]
     */
    public int getValue() {

        return (this.ordinal() + 1);

    }

    /**
     * <p>Gets the description text dependent on the locale. </p>
     *
     * <p>The French language is the best choice for most applications. If chosen then the output context
     * will determine the capitalization. </p>
     *
     * @param   locale      language setting
     * @param   oc          output context
     * @return  descriptive text for given locale (never {@code null})
     * @see     Locale#FRENCH
     */
    /*[deutsch]
     * <p>Liefert den sprachabh&auml;ngigen Beschreibungstext. </p>
     *
     * <p>Es wird empfohlen, die franz&ouml;sische Sprache zu benutzen. In diesem Fall wird der
     * Ausgabekontext auch die Kapitalisierung festlegen. </p>
     *
     * @param   locale      language setting
     * @param   oc          output context
     * @return  descriptive text for given locale (never {@code null})
     * @see     Locale#FRENCH
     */
    public String getDisplayName(
        Locale locale,
        OutputContext oc
    ) {

        CalendarText names = CalendarText.getInstance("frenchrev", locale);
        String key = "S(" + (oc == OutputContext.STANDALONE ? "W" : "w") + ")_" + this.getValue();
        String desc = names.getTextForms().get(key);

        if ((desc == null) && (oc == OutputContext.STANDALONE)) {
            key = "S(w)_" + this.getValue();
            desc = names.getTextForms().get(key);
        }

        return desc;

    }

}
