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

import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * <p>Abstract calendar system for the Hindu calendar. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
/*[deutsch]
 * <p>Abstraktes Kalendersystem f&uuml;r den Hindu-Kalender. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
abstract class HinduCS
    implements CalendarSystem<HinduCalendar> {

    //~ Statische Felder/Initialisierungen --------------------------------

    static final long KALI_YUGA_EPOCH = -1132959L; // julian-BCE-3102-02-18 (as rata die)

    //~ Instanzvariablen --------------------------------------------------

    final HinduVariant variant;

    //~ Konstruktoren -----------------------------------------------------

    HinduCS(HinduVariant variant) {
        super();

        if (variant == null) {
            throw new NullPointerException();
        }

        this.variant = variant;
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public final HinduCalendar transform(long utcDays) {
        long min = this.getMinimumSinceUTC();
        long max = this.getMaximumSinceUTC();

        if ((utcDays < min) || (utcDays > max)) {
            throw new IllegalArgumentException("Out of range: " + min + " <= " + utcDays + " <= " + max);
        }

        return this.create(utcDays);
    }

    @Override
    public final long transform(HinduCalendar date) {
        return date.getDaysSinceEpochUTC();
    }

    @Override
    public List<CalendarEra> getEras() {
        List<CalendarEra> eras = new ArrayList<CalendarEra>(Arrays.asList(HinduEra.values()));
        return Collections.unmodifiableList(eras);
    }

    // non-validating method to create a Hindu date
    abstract HinduCalendar create(long utcDays);

    // non-validating method to create a Hindu date
    abstract HinduCalendar create(
        int kyYear,
        HinduMonth month,
        HinduDay dom
    );

    // general validation method
    abstract boolean isValid(
        int kyYear,
        HinduMonth month,
        HinduDay dom
    );

    // expunged months are called "kshaya"
    final boolean isExpunged(
        int kyYear,
        HinduMonth month
    ) {
        long utcDays = this.create(kyYear, month, HinduDay.valueOf(15)).getDaysSinceEpochUTC();
        HinduCalendar cal = this.create(utcDays);
        return !cal.getMonth().getValue().equals(month.getValue());
    }

    // expunged days are gaps
    final boolean isExpunged(
        int kyYear,
        HinduMonth month,
        HinduDay dom
    ) {
        long utcDays = this.create(kyYear, month, dom).getDaysSinceEpochUTC();
        HinduCalendar cal = this.create(utcDays);

        return (
            (cal.getExpiredYearOfKaliYuga() != kyYear)
                || !cal.getMonth().equals(month)
                || !cal.getDayOfMonth().equals(dom)
        );
    }

    // used in subclasses
    static double modulo(double x, double y) {
        return x - y * Math.floor(x / y);
    }

}
