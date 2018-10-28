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

import net.time4j.engine.BasicElement;
import net.time4j.engine.ChronoFunction;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;

import java.io.ObjectStreamException;


/**
 * <p>Repr&auml;sentiert eine Datumskomponente. </p>
 *
 * @author      Meno Hochschild
 */
final class DateElement
    extends BasicElement<PlainDate>
    implements CalendarDateElement {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Singleton-Instanz.
     */
    static final DateElement INSTANCE = new DateElement();

    private static final long serialVersionUID = -6519899440006935829L;

    //~ Konstruktoren -----------------------------------------------------

    private DateElement() {
        super("CALENDAR_DATE");

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<PlainDate> getType() {

        return PlainDate.class;

    }

    @Override
    public PlainDate getDefaultMinimum() {

        return PlainDate.MIN;

    }

    @Override
    public PlainDate getDefaultMaximum() {

        return PlainDate.MAX;

    }

    @Override
    public boolean isDateElement() {

        return true;

    }

    @Override
    public boolean isTimeElement() {

        return false;

    }

    @Override
    public ElementOperator<PlainDate> firstDayOfNextMonth() {

        return CalendarOperator.FIRST_DAY_OF_NEXT_MONTH;

    }

    @Override
    public ElementOperator<PlainDate> firstDayOfNextQuarter() {

        return CalendarOperator.FIRST_DAY_OF_NEXT_QUARTER;

    }

    @Override
    public ElementOperator<PlainDate> firstDayOfNextYear() {

        return CalendarOperator.FIRST_DAY_OF_NEXT_YEAR;

    }

    @Override
    public ElementOperator<PlainDate> lastDayOfPreviousMonth() {

        return CalendarOperator.LAST_DAY_OF_PREVIOUS_MONTH;

    }

    @Override
    public ElementOperator<PlainDate> lastDayOfPreviousQuarter() {

        return CalendarOperator.LAST_DAY_OF_PREVIOUS_QUARTER;

    }

    @Override
    public ElementOperator<PlainDate> lastDayOfPreviousYear() {

        return CalendarOperator.LAST_DAY_OF_PREVIOUS_YEAR;

    }

    @Override
    public ChronoFunction<Moment, PlainDate> inStdTimezone() {

        return this.in(Timezone.ofSystem());

    }

    @Override
    public ChronoFunction<Moment, PlainDate> inTimezone(TZID tzid) {

        return this.in(Timezone.of(tzid));

    }

    @Override
    public ChronoFunction<Moment, PlainDate> in(Timezone tz) {

        return new ZonalQuery<PlainDate>(this, tz);

    }

    @Override
    public ChronoFunction<Moment, PlainDate> atUTC() {

        return this.at(ZonalOffset.UTC);

    }

    @Override
    public ChronoFunction<Moment, PlainDate> at(ZonalOffset offset) {

        return new ZonalQuery<PlainDate>(this, offset);

    }

    @Override
    protected boolean isSingleton() {

        return true;

    }

    private Object readResolve() throws ObjectStreamException {

        return INSTANCE;

    }

}
