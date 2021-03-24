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
import net.time4j.format.TextWidth;

import java.util.Locale;


/**
 * <p>Represents the days of decade used in the French revolutionary calendar. </p>
 *
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Dekadentage, die im franz&ouml;sischen Revolutionskalender Verwendung fanden. </p>
 *
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
public enum DayOfDecade {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>The first day of decade. </p>
     */
    /*[deutsch]
     * <p>Der erste Dekadentag. </p>
     */
    PRIMIDI,

    /**
     * <p>The second day of decade. </p>
     */
    /*[deutsch]
     * <p>Der zweite Dekadentag. </p>
     */
    DUODI,

    /**
     * <p>The third day of decade. </p>
     */
    /*[deutsch]
     * <p>Der dritte Dekadentag. </p>
     */
    TRIDI,

    /**
     * <p>The fourth day of decade. </p>
     */
    /*[deutsch]
     * <p>Der vierte Dekadentag. </p>
     */
    QUARTIDI,

    /**
     * <p>The fifth day of decade. </p>
     */
    /*[deutsch]
     * <p>Der f&uuml;nfte Dekadentag. </p>
     */
    QUINTIDI,

    /**
     * <p>The sixth day of decade. </p>
     */
    /*[deutsch]
     * <p>Der sechste Dekadentag. </p>
     */
    SEXTIDI,

    /**
     * <p>The seventh day of decade. </p>
     */
    /*[deutsch]
     * <p>Der siebte Dekadentag. </p>
     */
    SEPTIDI,

    /**
     * <p>The eigth day of decade. </p>
     */
    /*[deutsch]
     * <p>Der achte Dekadentag. </p>
     */
    OCTIDI,

    /**
     * <p>The ninth day of decade. </p>
     */
    /*[deutsch]
     * <p>Der neunte Dekadentag. </p>
     */
    NONIDI,

    /**
     * <p>The tenth day of decade. </p>
     */
    /*[deutsch]
     * <p>Der zehnte Dekadentag. </p>
     */
    DECADI;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the enum-constant which corresponds to the given numerical value. </p>
     *
     * @param   dayOfDecade     french revolutionary day of decade in the range [1-10]
     * @return  day of decade as enum
     * @throws  IllegalArgumentException if given argument is out of range
     */
    /*[deutsch]
     * <p>Liefert die zum kalendarischen Integer-Wert passende Enum-Konstante. </p>
     *
     * @param   dayOfDecade     french revolutionary day of decade in the range [1-10]
     * @return  day of decade as enum
     * @throws  IllegalArgumentException if given argument is out of range
     */
    public static DayOfDecade valueOf(int dayOfDecade) {

        if ((dayOfDecade < 1) || (dayOfDecade > 10)) {
            throw new IllegalArgumentException("Out of range: " + dayOfDecade);
        }

        return DayOfDecade.values()[dayOfDecade - 1];

    }

    /**
     * <p>Gets the corresponding numerical value. </p>
     *
     * @return  number of day of decade in the range [1-10]
     */
    /*[deutsch]
     * <p>Liefert den korrespondierenden kalendarischen Integer-Wert. </p>
     *
     * @return  number of day of decade in the range [1-10]
     */
    public int getValue() {

        return (this.ordinal() + 1);

    }

    /**
     * <p>Equivalent to {@code getDisplayName(locale, TextWidth.WIDE, OutputContext.FORMAT)}. </p>
     *
     * <p>The usage of the French language is strongly recommended. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text for given locale (never {@code null})
     * @see     Locale#FRENCH
     */
    /*[deutsch]
     * <p>&Auml;quivalent zu {@code getDisplayName(locale, TextWidth.WIDE, OutputContext.FORMAT)}. </p>
     *
     * <p>Es wird empfohlen, die franz&ouml;sische Sprache zu verwenden. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text for given locale (never {@code null})
     * @see     Locale#FRENCH
     */
    public String getDisplayName(Locale locale) {

        return this.getDisplayName(locale, TextWidth.WIDE, OutputContext.FORMAT);

    }

    /**
     * <p>Gets the description text dependent on the locale. </p>
     *
     * <p>The usage of the French language is strongly recommended. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @param   oc          output context
     * @return  descriptive text for given locale (never {@code null})
     * @see     Locale#FRENCH
     */
    /*[deutsch]
     * <p>Liefert den sprachabh&auml;ngigen Beschreibungstext. </p>
     *
     * <p>Es wird empfohlen, die franz&ouml;sische Sprache zu verwenden. </p>
     *
     * @param   locale      language setting
     * @param   width       text width
     * @param   oc          output context
     * @return  descriptive text for given locale (never {@code null})
     * @see     Locale#FRENCH
     */
    public String getDisplayName(
        Locale locale,
        TextWidth width,
        OutputContext oc
    ) {

        CalendarText names = CalendarText.getInstance("frenchrev", locale);
        String key = ((width == TextWidth.NARROW) ? "N" : (oc == OutputContext.FORMAT ? "w" : "W"));
        return names.getTextForms().get("D(" + key + ")_" + this.getValue());

    }

}
