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

package net.time4j.history;

import net.time4j.PlainDate;
import net.time4j.engine.EpochDays;


/**
 * <p>Provides procedures how to determine Easter Sunday. </p>
 *
 * <p>The algorithms are based on the work of Heiner Lichtenberg in 1997. </p>
 *
 * @author  Meno Hochschild
 * @since   3.16/4.13
 */
/*[deutsch]
 * <p>Stellt Verfahren zur Berechnung des Osterfests bereit. </p>
 *
 * <p>Die hier verwendeten Algorithmen fu&szlig;en auf der Arbeit von Heiner Lichtenberg 1997. </p>
 *
 * @author  Meno Hochschild
 * @since   3.16/4.13
 */
public enum Computus {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Applies the Julian/Gregorian calendar and is used by catholic and protestantic churches. </p>
     */
    /*[deutsch]
     * <p>Wendet den julianisch/gregorianischen Kalender an und wird von der katholischen Kirche
     * und den protestantischen Kirchen verwendet. </p>
     */
    WESTERN,

    /**
     * <p>Applies the Julian calendar and is used by Orthodox church in East Europe. </p>
     */
    /*[deutsch]
     * <p>Wendet den julianischen Kalender an und wird von der orthodoxen Kirche in Osteuropa verwendet. </p>
     */
    EASTERN;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Calculates the date of Easter Sunday. </p>
     *
     * @param annoDomini calendar year related to era AD
     * @return calendar date of Easter Sunday (ISO-8601)
     * @throws IllegalArgumentException if given year is before AD 532 (the first table from Beda Venerabilis)
     * @since 3.16/4.13
     */
    /*[deutsch]
     * <p>Berechnet das Datum des Ostersonntags. </p>
     *
     * @param   annoDomini      calendar year related to era AD
     * @return  calendar date of Easter Sunday (ISO-8601)
     * @throws  IllegalArgumentException if given year is before AD 532 (the first table from Beda Venerabilis)
     * @since   3.16/4.13
     */
    public PlainDate easterSunday(int annoDomini) {

        int dom = marchDay(annoDomini);
        int month = 3;

        if (dom > 31) {
            month++;
            dom -= 31;
        }

        return (
            ((this == WESTERN) && (annoDomini > 1582))
            ? PlainDate.of(annoDomini, month, dom)
            : PlainDate.of(JulianMath.toMJD(annoDomini, month, dom), EpochDays.MODIFIED_JULIAN_DATE));

    }

    // auch benutzt von NewYearRule
    int marchDay(int annoDomini) {

        if (annoDomini < 532) {
            throw new IllegalArgumentException("Out of range: " + annoDomini);
        }

        int k = annoDomini / 100;
        int m = 15;
        int s = 0;

        if ((this == WESTERN) && (annoDomini > 1582)) {
            m += ((3 * k + 3) / 4);
            m -= ((8 * k + 13) / 25);
            s = 2 - ((3 * k + 3) / 4);
        }

        int a = annoDomini % 19;
        int d = (19 * a + m) % 30;
        int r = (d / 29) + ((d / 28) - (d / 29)) * (a / 11);
        int og = 21 + d - r;
        int sz = 7 - ((annoDomini + (annoDomini / 4) + s) % 7);
        int oe = 7 - ((og - sz) % 7);

        return og + oe;

    }

}
