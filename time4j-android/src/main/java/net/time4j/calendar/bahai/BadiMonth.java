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
import net.time4j.format.TextAccessor;

import java.util.Locale;


/**
 * <p>Represents the months used in the Badi calendar. </p>
 *
 * @author  Meno Hochschild
 * @see     BadiDivision#comparator()
 * @since   5.3
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Monate, die im Badi-Kalender Verwendung finden. </p>
 *
 * @author  Meno Hochschild
 * @see     BadiDivision#comparator()
 * @since   5.3
 */
public enum BadiMonth
    implements BadiDivision {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>The first month starting at vernal equinox in March. </p>
     */
    /*[deutsch]
     * <p>Der erste Monat beginnend zum Fr&uuml;hlingsanfang im M&auml;rz. </p>
     */
    BAHA,

    /**
     * <p>The second month. </p>
     */
    /*[deutsch]
     * <p>Der zweite Monat. </p>
     */
    JALAL,

    /**
     * <p>The third month. </p>
     */
    /*[deutsch]
     * <p>Der dritte Monat. </p>
     */
    JAMAL,

    /**
     * <p>The fourth month. </p>
     */
    /*[deutsch]
     * <p>Der vierte Monat. </p>
     */
    AZAMAT,

    /**
     * <p>The fifth month. </p>
     */
    /*[deutsch]
     * <p>Der f&uuml;nfte Monat. </p>
     */
    NUR,

    /**
     * <p>The sixth month. </p>
     */
    /*[deutsch]
     * <p>Der sechste Monat. </p>
     */
    RAHMAT,

    /**
     * <p>The seventh month. </p>
     */
    /*[deutsch]
     * <p>Der siebente Monat. </p>
     */
    KALIMAT,

    /**
     * <p>The eight month. </p>
     */
    /*[deutsch]
     * <p>Der achte Monat. </p>
     */
    KAMAL,

    /**
     * <p>The ninth month. </p>
     */
    /*[deutsch]
     * <p>Der neunte Monat. </p>
     */
    ASMA,

    /**
     * <p>The tenth month. </p>
     */
    /*[deutsch]
     * <p>Der zehnte Monat. </p>
     */
    IZZAT,

    /**
     * <p>The eleventh month. </p>
     */
    /*[deutsch]
     * <p>Der elfte Monat. </p>
     */
    MASHIYYAT,

    /**
     * <p>The twelvth month. </p>
     */
    /*[deutsch]
     * <p>Der zw&ouml;lfte Monat. </p>
     */
    ILM,

    /**
     * <p>The thirteenth month. </p>
     */
    /*[deutsch]
     * <p>Der dreizehnte Monat. </p>
     */
    QUDRAT,

    /**
     * <p>The fourteenth month. </p>
     */
    /*[deutsch]
     * <p>Der vierzehnte Monat. </p>
     */
    QAWL,

    /**
     * <p>The fifteenth month. </p>
     */
    /*[deutsch]
     * <p>Der f&uuml;nfzehnte Monat. </p>
     */
    MASAIL,

    /**
     * <p>The sixteenth month. </p>
     */
    /*[deutsch]
     * <p>Der sechzehnte Monat. </p>
     */
    SHARAF,

    /**
     * <p>The seventeenth month. </p>
     */
    /*[deutsch]
     * <p>Der siebzehnte Monat. </p>
     */
    SULTAN,

    /**
     * <p>The eighteenth month. </p>
     */
    /*[deutsch]
     * <p>Der achtzehnte Monat. </p>
     */
    MULK,

    /**
     * <p>The nineteenth month. </p>
     */
    /*[deutsch]
     * <p>Der neunzehnte Monat. </p>
     */
    ALA;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the enum-constant which corresponds to the given numerical value. </p>
     *
     * @param   month   badi month in the range [1-19]
     * @return  badi month of year as enum
     * @throws  IllegalArgumentException if given argument is out of range
     */
    /*[deutsch]
     * <p>Liefert die zum kalendarischen Integer-Wert passende Enum-Konstante. </p>
     *
     * @param   month   badi month in the range [1-19]
     * @return  badi month of year as enum
     * @throws  IllegalArgumentException if given argument is out of range
     */
    public static BadiMonth valueOf(int month) {

        if ((month < 1) || (month > 19)) {
            throw new IllegalArgumentException("Out of range: " + month);
        }

        return BadiMonth.values()[month - 1];

    }

    /**
     * <p>Gets the corresponding numerical value. </p>
     *
     * @return  number of badi month in the range [1-19]
     */
    /*[deutsch]
     * <p>Liefert den korrespondierenden kalendarischen Integer-Wert. </p>
     *
     * @return  number of badi month in the range [1-19]
     */
    public int getValue() {

        return (this.ordinal() + 1);

    }

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

        return accessor(locale, FormattedContent.TRANSCRIPTION).print(this);

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

        return accessor(locale, FormattedContent.MEANING).print(this);

    }

    private static TextAccessor accessor(
        Locale lang,
        FormattedContent fc
    ) {

        CalendarText ct = CalendarText.getInstance("extra/bahai", lang);
        return ct.getTextForms("M", BadiMonth.class, fc.variant());

    }

}
