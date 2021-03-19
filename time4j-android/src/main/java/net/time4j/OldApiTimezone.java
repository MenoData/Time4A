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

package net.time4j;

import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZonalTransition;

import java.util.Date;
import java.util.List;


/**
 * <p>Spezialimplementierung, die die Daten und Regeln einer Time4J-Zeitzone im Gewand des alten API bewahrt. </p>
 *
 * @author      Meno Hochschild
 * @since       3.37/4.32
 * @serial      include
 */
final class OldApiTimezone
    extends java.util.TimeZone {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -6919910650419401271L;

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  the underlying Time4J-zone
     */
    private final Timezone tz;

    //~ Konstruktoren -----------------------------------------------------

    OldApiTimezone(Timezone tz) {
        super();

        this.tz = tz;

        this.setID(tz.getID().canonical());
        assert (tz.getHistory() != null);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public int getOffset(long date) {

        return this.tz.getOffset(
            TemporalType.MILLIS_SINCE_UNIX.translate(Long.valueOf(date))
        ).getIntegralAmount() * 1000;

    }

    @Override
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {

        if (milliseconds < 0 || milliseconds >= 86400000) {
            throw new IllegalArgumentException("Milliseconds out of range: " + milliseconds);
        }

        if (era == java.util.GregorianCalendar.BC) {
            year = 1 - year;
        } else if (era != java.util.GregorianCalendar.AD) {
            throw new IllegalArgumentException("Unknown era: " + era);
        } else if (dayOfWeek < 1 || dayOfWeek > 7) {
            throw new IllegalArgumentException("Day-of-week out of range: " + dayOfWeek);
        }

        return this.tz.getOffset(
            PlainDate.of(year, month + 1, day), // input month is zero-based
            PlainTime.midnightAtStartOfDay().plus(milliseconds, ClockUnit.MILLIS)
        ).getIntegralAmount() * 1000;

    }

    @Override
    public void setRawOffset(int offsetMillis) {

        // manipulation of raw offsets not supported => no-op

    }

    @Override
    public int getRawOffset() {

        return this.tz.getStandardOffset(SystemClock.currentMoment()).getIntegralAmount() * 1000;

    }

    @Override
    public int getDSTSavings() {

        TransitionHistory history = this.tz.getHistory();
        int previousDST = Integer.MIN_VALUE;

        if (history != null) {
            List<ZonalTransition> transitions = history.getStdTransitions();
            for (int i = transitions.size() - 1; i >= 0; i--) {
                int currentDST = transitions.get(i).getDaylightSavingOffset();
                if (previousDST == Integer.MIN_VALUE) {
                    previousDST = currentDST;
                } else if (previousDST != currentDST) {
                    return Math.max(previousDST, currentDST) * 1000;
                }
            }
        }

        return (previousDST == Integer.MIN_VALUE) ? 0 : (previousDST * 1000);

    }

    @Override
    public boolean useDaylightTime() {

        if (!this.tz.isFixed()) {
            TransitionHistory history = this.tz.getHistory();

            if (history != null) {
                List<ZonalTransition> transitions = history.getStdTransitions();
                int previousDST = Integer.MIN_VALUE;
                for (int i = transitions.size() - 1; i >= 0; i--) {
                    int currentDST = transitions.get(i).getDaylightSavingOffset();
                    if (previousDST == Integer.MIN_VALUE) {
                        previousDST = currentDST;
                    } else if (previousDST != currentDST) {
                        return true; // change of dst-offset
                    }
                }
            }
        }

        return false;

    }

    @Override
    public boolean inDaylightTime(Date date) {

        return this.tz.isDaylightSaving(TemporalType.JAVA_UTIL_DATE.translate(date));

    }

    @Override
    public boolean hasSameRules(java.util.TimeZone other) {

        if (this == other) {
            return true;
        } else if (other instanceof OldApiTimezone) {
            OldApiTimezone that = (OldApiTimezone) other;
            return this.tz.getHistory().equals(that.tz.getHistory());
        }

        return false;

    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof OldApiTimezone) {
            OldApiTimezone that = (OldApiTimezone) obj;
            return this.tz.equals(that.tz);
        }

        return false;

    }

    @Override
    public int hashCode() {

        return this.tz.hashCode();

    }

    @Override
    public String toString() {

        return this.getClass().getName() + "@" + this.tz.toString();

    }

    Timezone getDelegate() {

        return this.tz;

    }

}
