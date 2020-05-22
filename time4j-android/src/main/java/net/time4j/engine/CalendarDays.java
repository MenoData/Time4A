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

package net.time4j.engine;

import net.time4j.base.MathUtils;

import java.io.Serializable;


/**
 * <p>Represents a count of calendar days. </p>
 *
 * @author  Meno Hochschild
 * @see     Calendrical#plus(CalendarDays)
 * @see     Calendrical#minus(CalendarDays)
 * @see     CalendarVariant#plus(CalendarDays)
 * @see     CalendarVariant#minus(CalendarDays)
 * @since   3.4/4.3
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine Anzahl von Kalendertagen. </p>
 *
 * @author  Meno Hochschild
 * @see     Calendrical#plus(CalendarDays)
 * @see     Calendrical#minus(CalendarDays)
 * @see     CalendarVariant#plus(CalendarDays)
 * @see     CalendarVariant#minus(CalendarDays)
 * @since   3.4/4.3
 */
public final class CalendarDays
    implements Comparable<CalendarDays>, Serializable {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Represents zero calendar days.
     */
    /*[deutsch]
     * Repr&auml;sentiert null Kalendertage.
     */
    public static final CalendarDays ZERO = new CalendarDays(0);

    /**
     * Represents exactly one calendar day.
     */
    /*[deutsch]
     * Repr&auml;sentiert genau einen Kalendertag.
     */
    public static final CalendarDays ONE = new CalendarDays(1);

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  count of days
     */
    /*[deutsch]
     * @serial  Anzahl der Kalendertage
     */
    private final long days;

    //~ Konstruktoren -----------------------------------------------------

    private CalendarDays(long days) {
        super();

        this.days = days;

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Wraps given count of calendar days. </p>
     *
     * @param   days    count of calendar days
     * @return  new or cached instance of {@code CalendarDays}
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Kapselt die angegebenen Kalendertage als Objekt. </p>
     *
     * @param   days    count of calendar days
     * @return  new or cached instance of {@code CalendarDays}
     * @since   3.4/4.3
     */
    public static CalendarDays of(long days) {

        return ((days == 0) ? CalendarDays.ZERO : ((days == 1) ? CalendarDays.ONE : new CalendarDays(days)));

    }

    /**
     * <p>Yields the calendar days as primitive. </p>
     *
     * @return  count of calendar days, maybe zero or negative
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Liefert die Kalendertage als Java-Primitive. </p>
     *
     * @return  count of calendar days, maybe zero or negative
     * @since   3.4/4.3
     */
    public long getAmount() {

        return this.days;

    }

    /**
     * <p>Is the count of calendar days equal to zero? </p>
     *
     * @return  boolean
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Ist die Anzahl der Kalendertage {@code 0}? </p>
     *
     * @return  boolean
     * @since   3.4/4.3
     */
    public boolean isZero() {

        return (this.days == 0);

    }

    /**
     * <p>Is the count of calendar days smaller than zero? </p>
     *
     * @return  boolean
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Ist die Anzahl der Kalendertage negativ? </p>
     *
     * @return  boolean
     * @since   3.4/4.3
     */
    public boolean isNegative() {

        return (this.days < 0);

    }

    /**
     * <p>Calculates the delta of calendar days between given calendar dates. </p>
     *
     * @param   start   first calendar date (inclusive)
     * @param   end     second calendar date (exclusive)
     * @return  count of calendar days between start and end
     * @since   3.8/4.5
     */
    /*[deutsch]
     * <p>Berechnet die Tagesdifferenz zwischen den angegebenen Datumsobjekten. </p>
     *
     * @param   start   first calendar date (inclusive)
     * @param   end     second calendar date (exclusive)
     * @return  count of calendar days between start and end
     * @since   3.8/4.5
     */
    public static CalendarDays between(
        CalendarDate start,
        CalendarDate end
    ){

        long t1 = start.getDaysSinceEpochUTC();
        long t2 = end.getDaysSinceEpochUTC();
        return CalendarDays.of(MathUtils.safeSubtract(t2, t1));

    }

    /**
     * <p>Yields the absolute value of the represented calendar days. </p>
     *
     * @return  non-negative count of calendar days
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Liefert den absoluten Betrag der repr&auml;sentierten Kalendertage. </p>
     *
     * @return  non-negative count of calendar days
     * @since   3.4/4.3
     */
    public CalendarDays abs() {

        return ((this.days < 0) ? this.inverse() : this);

    }

    /**
     * <p>Negates this duration if not zero. </p>
     *
     * @return  inversed count of calendar days
     * @since   4.0
     */
    /*[deutsch]
     * <p>Negiert diese Dauer, wenn nicht null. </p>
     *
     * @return  inversed count of calendar days
     * @since   4.0
     */
    public CalendarDays inverse() {

        return CalendarDays.of(MathUtils.safeNegate(this.days));

    }

    /**
     * <p>Yields the sum of the represented calendar days of this instance and given argument. </p>
     *
     * @param   other   calendar days to be added
     * @return  sum of calendar days
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Liefert die Summe der Kalendertage dieser Instanz und des angegebenen Arguments. </p>
     *
     * @param   other   calendar days to be added
     * @return  sum of calendar days
     * @since   3.4/4.3
     */
    public CalendarDays plus(CalendarDays other) {

        return CalendarDays.of(MathUtils.safeAdd(this.days, other.days));

    }

    /**
     * <p>Yields the delta of the represented calendar days of this instance and given argument. </p>
     *
     * @param   other   calendar days to be subtracted
     * @return  delta of calendar days
     * @since   3.4/4.3
     */
    /*[deutsch]
     * <p>Liefert die Differenz der Kalendertage dieser Instanz und des angegebenen Arguments. </p>
     *
     * @param   other   calendar days to be subtracted
     * @return  delta of calendar days
     * @since   3.4/4.3
     */
    public CalendarDays minus(CalendarDays other) {

        return CalendarDays.of(MathUtils.safeSubtract(this.days, other.days));

    }

    @Override
    public int compareTo(CalendarDays other) {

        return ((this.days < other.days) ? -1 : ((this.days > other.days) ? 1 : 0));

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof CalendarDays) {
            return (this.days == ((CalendarDays) obj).days);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return (int) (this.days ^ (this.days >>> 32));

    }

    /**
     * <p>Returns an ISO-8601-like duration representation in format &quot;[-]P{n}D&quot;. </p>
     *
     * @return  String
     */
    /*[deutsch]
     * <p>Liefert eine ISO-8601-&auml;hnliche Darstellung im Format &quot;[-]P{n}D&quot;. </p>
     *
     * @return  String
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (this.days < 0) {
            sb.append('-');
        }
        sb.append('P');
        sb.append(Math.abs(this.days));
        sb.append('D');
        return sb.toString();

    }

}
