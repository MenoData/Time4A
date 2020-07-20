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

import java.io.ObjectStreamException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;


/**
 * <p>Represents the cyclic year used in East Asian calendars. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
/*[deutsch]
 * <p>Repr&auml;sentiert das zyklische Jahr, das in ostasiatischen Kalendern verwendet wird. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
public final class CyclicYear
    extends SexagesimalName {

    //~ Statische Felder/Initialisierungen ------------------------------------

    private static final CyclicYear[] INSTANCES;

    static {
        CyclicYear[] instances = new CyclicYear[60];
        for (int i = 0; i < 60; i++) {
            instances[i] = new CyclicYear(i + 1);
        }
        INSTANCES = instances;
    }

    private static final long serialVersionUID = 4908662352833192131L;

    //~ Konstruktoren ---------------------------------------------------------

    private CyclicYear(int year) {
        super(year);
    }

    //~ Methoden --------------------------------------------------------------

    /**
     * <p>Obtains an instance of cyclic year. </p>
     *
     * @param   yearOfCycle     year number in the range 1-60
     * @return  CyclicYear
     * @throws  IllegalArgumentException if the parameter is out of range
     */
    /*[deutsch]
     * <p>Liefert eine Instanz eines zyklischen Jahres. </p>
     *
     * @param   yearOfCycle     year number in the range 1-60
     * @return  CyclicYear
     * @throws  IllegalArgumentException if the parameter is out of range
     */
    public static CyclicYear of(int yearOfCycle) {

        if ((yearOfCycle < 1) || (yearOfCycle > 60)) {
            throw new IllegalArgumentException("Out of range: " + yearOfCycle);
        }

        return INSTANCES[yearOfCycle - 1];

    }

    /**
     * <p>Obtains an instance of cyclic year. </p>
     *
     * @param   stem    celestial stem
     * @param   branch  terrestrial branch
     * @return  CyclicYear
     * @throws  IllegalArgumentException if the combination of stem and branch is invalid
     */
    /*[deutsch]
     * <p>Liefert eine Instanz eines zyklischen Jahres. </p>
     *
     * @param   stem    celestial stem
     * @param   branch  terrestrial branch
     * @return  CyclicYear
     * @throws  IllegalArgumentException if the combination of stem and branch is invalid
     */
    public static CyclicYear of(
        Stem stem,
        Branch branch
    ) {

        SexagesimalName sn = SexagesimalName.of(stem, branch);
        return CyclicYear.of(sn.getNumber());

    }

    /**
     * <p>Parses the given localized name as a combination of stem and branch to a cyclic year. </p>
     *
     * <p>The original Chinese names are usually not translatable. A few languages like Korean,
     * Vietnamese and Russian use their own transcriptions. The pinyin-transcription (official
     * Chinese romanization) serves as fallback for other languages. And the root locale will use
     * a simplified version of pinyin without diacritic accents. This method will always expect
     * a minus sign as separator between stem and branch unless the language is Chinese, Korean
     * or Japanese. </p>
     *
     * @param   text        the text to be parsed
     * @param   locale      language
     * @return  parsed cyclic year
     * @throws  ParseException if the text cannot be parsed
     * @see     #getDisplayName(Locale)
     */
    /*[deutsch]
     * <p>Interpretiert den sprachabh&auml;ngigen Namen als Kombination von Himmelsstamm und Erdzweig. </p>
     *
     * <p>Die chinesischen Originalnamen sind normalerweise nicht &uuml;bersetzbar. Einige wenige
     * Sprachen wie Koreanisch, Vietnamesisch und Russisch haben ihre eigenen Transkriptionen. Die
     * Pinyin-Transkription (offizielle chinesische Romanisierung) dient als Ausweichoption f&uuml;r
     * andere Sprachen. Und die {@code Locale.ROOT}-Einstellung wird eine vereinfachte Pinyin-Version
     * ohne diakritische Akzente verwenden. Zwischen Himmelsstamm und Erdzweig wird immer ein Minus-Zeichen
     * erwartet, es sei denn, die Sprache ist Chinesisch, Koreanisch oder Japanisch. </p>
     *
     * @param   text        the text to be parsed
     * @param   locale      language
     * @return  parsed cyclic year
     * @throws  ParseException if the text cannot be parsed
     * @see     #getDisplayName(Locale)
     */
    public static CyclicYear parse(
        String text,
        Locale locale
    ) throws ParseException {

        SexagesimalName sn = SexagesimalName.parse(text, locale);
        return CyclicYear.of(sn.getNumber());

    }

    /**
     * <p>Rolls this cyclic year by given amount. </p>
     *
     * @param   amount  determines how many years/units this instance should be rolled
     * @return  changed copy of this instance
     */
    /*[deutsch]
     * <p>Rollt dieses zyklische Jahr um den angegebenen Betrag. </p>
     *
     * @param   amount  determines how many years/units this instance should be rolled
     * @return  changed copy of this instance
     */
    @Override
    public CyclicYear roll(int amount) {

        if (amount == 0) {
            return this;
        }

        SexagesimalName sn = super.roll(amount);
        return CyclicYear.of(sn.getNumber());

    }

    /**
     * <p>Obtains an unambivalent year reference for given Qing dynasty. </p>
     *
     * <p>Note: The years AD 1662 and AD 1722 have the same cyclic year so an unambivalent year reference
     * cannot be determined. This is because the Kangxi-era was 61 years long. </p>
     *
     * @param   era    the Chinese era representing a historic Qing dynasty
     * @return  EastAsianYear
     * @throws  IllegalArgumentException if the era is not a Qing dynasty or if the combination of this cyclic year
     *          together with the era is ambivalent (rare case in {@code QING_KANGXI_1662_1723})
     */
    /*[deutsch]
     * <p>Liefert eine eindeutige Jahresreferenz zur angegebenen Qing-Dynastie. </p>
     *
     * <p>Hinweis: Die Jahre AD 1662 und AD 1722 haben das gleiche zyklische Jahr, so da&szlig; eine eindeutige
     * Jahresreferenz unm&ouml;glich ist. Das ist durch die L&auml;nge der Kangxi-&Auml;ra von 61 Jahren bedingt. </p>
     *
     * @param   era    the Chinese era representing a historic Qing dynasty
     * @return  EastAsianYear
     * @throws  IllegalArgumentException if the era is not a Qing dynasty or if the combination of this cyclic year
     *          together with the era is ambivalent (rare case in {@code QING_KANGXI_1662_1723})
     */
    public EastAsianYear inQingDynasty(ChineseEra era) {

        if (era.isQingDynasty()) {
            if ((era == ChineseEra.QING_KANGXI_1662_1723) && (this.getNumber() == 39)){
                throw new IllegalArgumentException(
                    "Ambivalent cyclic year in Kangxi-era (1662 or 1722): " + this.getDisplayName(Locale.ROOT));
            } else {
                final int start = era.getStartAsGregorianYear();
                final int delta = this.getNumber() - EastAsianYear.forGregorian(start).getYearOfCycle().getNumber();
                return new EastAsianYear() {
                    @Override
                    public int getElapsedCyclicYears() {
                        return start + delta + ((delta < 0) ? 2696 : 2636);
                    }
                };
            }
        } else {
            throw new IllegalArgumentException("Chinese era must be related to a Qing dynasty.");
        }

    }

    /**
     * <p>Obtains a year reference for given cycle number (technical identifier). </p>
     *
     * @param   cycle   number of sexagesimal year cycle
     * @return  EastAsianYear
     * @throws  IllegalArgumentException if the cycle is smaller than {@code 1}
     */
    /*[deutsch]
     * <p>Liefert eine Jahresreferenz zur angegebenen Jahreszyklusnummer (technisches Kennzeichen). </p>
     *
     * @param   cycle   number of sexagesimal year cycle
     * @return  EastAsianYear
     * @throws  IllegalArgumentException if the cycle is smaller than {@code 1}
     */
    public EastAsianYear inCycle(final int cycle) {

        if (cycle < 1) {
            throw new IllegalArgumentException("Cycle number must not be smaller than 1: " + cycle);
        }

        return new EastAsianYear() {
            @Override
            public int getElapsedCyclicYears() {
                return (cycle - 1) * 60 + getNumber() - 1;
            }
        };

    }

    /**
     * <p>Parses the given localized name to a cyclic year. </p>
     *
     * @param   text        the text to be parsed
     * @param   pp          when to start parsing
     * @param   locale      language
     * @return  parsed cyclic year or {@code null} if the text cannot be parsed
     */
    /*[deutsch]
     * <p>Interpretiert den sprachabh&auml;ngigen Namen als zyklisches Jahr. </p>
     *
     * @param   text        the text to be parsed
     * @param   pp          when to start parsing
     * @param   locale      language
     * @return  parsed cyclic year or {@code null} if the text cannot be parsed
     */
    static CyclicYear parse(
        CharSequence text,
        ParsePosition pp,
        Locale locale,
        boolean lenient
    ) {

        SexagesimalName sn = SexagesimalName.parse(text, pp, locale, lenient);
        return (sn == null) ? null : CyclicYear.of(sn.getNumber());

    }

    /**
     * @serialData  Checks the consistency of deserialized data and ensures singleton semantic
     * @throws      IllegalArgumentException if the year is not in range 1-60
     */
    Object readResolve() throws ObjectStreamException {

        return CyclicYear.of(super.getNumber());

    }

}
