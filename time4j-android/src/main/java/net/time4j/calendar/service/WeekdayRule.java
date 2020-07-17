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

package net.time4j.calendar.service;

import net.time4j.Weekday;
import net.time4j.Weekmodel;
import net.time4j.base.MathUtils;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ElementRule;


/**
 * Generic element rule for weekdays.
 *
 * @author  Meno Hochschild
 * @since   5.6
 */
/*[deutsch]
 * Generische Elementregel f&uuml;r Wochentage.
 *
 * @author  Meno Hochschild
 * @since   5.6
 */
public class WeekdayRule<D extends CalendarDate>
    implements ElementRule<D, Weekday> {

    //~ Instanzvariablen --------------------------------------------------

    private final Weekmodel stdWeekmodel;
    private final Java8Function<D, CalendarSystem<D>> calsysFunc;

    //~ Konstruktoren -----------------------------------------------------

    public WeekdayRule(
        Weekmodel stdWeekmodel,
        Java8Function<D, CalendarSystem<D>> calsysFunc
    ) {
        super();

        this.stdWeekmodel = stdWeekmodel;
        this.calsysFunc = calsysFunc;
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Weekday getValue(D context) {
        return getWeekday(context.getDaysSinceEpochUTC());
    }

    @Override
    public Weekday getMinimum(D context) {
        CalendarSystem<D> cs = this.calsysFunc.apply(context);
        int oldNum = this.getValue(context).getValue(this.stdWeekmodel);

        if (context.getDaysSinceEpochUTC() + 1 - oldNum < cs.getMinimumSinceUTC()) {
            return getWeekday(cs.getMinimumSinceUTC());
        }

        return this.stdWeekmodel.getFirstDayOfWeek();
    }

    @Override
    public Weekday getMaximum(D context) {
        CalendarSystem<D> cs = this.calsysFunc.apply(context);
        int oldNum = this.getValue(context).getValue(this.stdWeekmodel);

        if (context.getDaysSinceEpochUTC() + 7 - oldNum > cs.getMaximumSinceUTC()) {
            return getWeekday(cs.getMaximumSinceUTC());
        }

        return this.stdWeekmodel.getFirstDayOfWeek().roll(6);
    }

    @Override
    public boolean isValid(
        D context,
        Weekday value
    ) {
        if (value == null) {
            return false;
        }

        int oldValue = this.getValue(context).getValue(this.stdWeekmodel);
        int newValue = value.getValue(this.stdWeekmodel);
        long utcDays = context.getDaysSinceEpochUTC() + newValue - oldValue;
        CalendarSystem<D> cs = this.calsysFunc.apply(context);
        return (utcDays >= cs.getMinimumSinceUTC()) && (utcDays <= cs.getMaximumSinceUTC());
    }

    @Override
    public D withValue(
        D context,
        Weekday value,
        boolean lenient
    ) {
        if (value == null) {
            throw new IllegalArgumentException("Missing weekday.");
        }

        int oldValue = this.getValue(context).getValue(this.stdWeekmodel);
        int newValue = value.getValue(this.stdWeekmodel);
        long utcDays = context.getDaysSinceEpochUTC() + newValue - oldValue;
        CalendarSystem<D> cs = this.calsysFunc.apply(context);

        if ((utcDays >= cs.getMinimumSinceUTC()) && (utcDays <= cs.getMaximumSinceUTC())) {
            return cs.transform(utcDays);
        } else {
            throw new IllegalArgumentException("New day out of supported range.");
        }
    }

    @Override
    public ChronoElement<?> getChildAtFloor(D context) {
        return null;
    }

    @Override
    public ChronoElement<?> getChildAtCeiling(D context) {
        return null;
    }

    private static Weekday getWeekday(long utcDays) {
        return Weekday.valueOf(MathUtils.floorModulo(utcDays + 5, 7) + 1);
    }

}
