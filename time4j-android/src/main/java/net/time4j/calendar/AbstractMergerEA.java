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

import net.time4j.ClockUnit;
import net.time4j.Moment;
import net.time4j.PlainTimestamp;
import net.time4j.base.TimeSource;
import net.time4j.calendar.service.GenericDatePatterns;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoMerger;
import net.time4j.engine.Chronology;
import net.time4j.engine.DisplayStyle;
import net.time4j.engine.StartOfDay;
import net.time4j.format.Attributes;
import net.time4j.format.Leniency;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

import java.util.Locale;


/**
 * <p>Standard merger for East Asian calendars. </p>
 *
 * @author  Meno Hochschild
 * @since   3.40/4.35
 */
abstract class AbstractMergerEA<C extends EastAsianCalendar<?, C>>
    implements ChronoMerger<C> {

    //~ Instanzvariablen ------------------------------------------------------

    private final Class<C> chronoType;

    //~ Konstruktoren ---------------------------------------------------------

    AbstractMergerEA(Class<C> chronoType) {
        super();

        this.chronoType = chronoType;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public String getFormatPattern(
        DisplayStyle style,
        Locale locale
    ) {

        return GenericDatePatterns.get("chinese", style, locale); // always redirect to chinese calendar

    }

    @Override
    public C createFrom(
        TimeSource<?> clock,
        AttributeQuery attributes
    ) {

        TZID tzid;

        if (attributes.contains(Attributes.TIMEZONE_ID)) {
            tzid = attributes.get(Attributes.TIMEZONE_ID);
        } else if (attributes.get(Attributes.LENIENCY, Leniency.SMART).isLax()) {
            tzid = Timezone.ofSystem().getID();
        } else {
            return null;
        }

        StartOfDay startOfDay = attributes.get(Attributes.START_OF_DAY, this.getDefaultStartOfDay());
        PlainTimestamp tsp = Moment.from(clock.currentTime()).toZonalTimestamp(tzid);
        int deviation = startOfDay.getDeviation(tsp.getCalendarDate(), tzid);
        tsp = tsp.minus(deviation, ClockUnit.SECONDS);
        return tsp.getCalendarDate().transform(this.chronoType);

    }

    @Override
    public abstract C createFrom(
        ChronoEntity<?> entity,
        AttributeQuery attributes,
        boolean lenient,
        boolean preparsing
    );

    @Override
    public ChronoDisplay preformat(
        C context,
        AttributeQuery attributes
    ) {

        return context;

    }

    @Override
    public Chronology<?> preparser() {

        return null;

    }

    @Override
    public StartOfDay getDefaultStartOfDay() {

        return StartOfDay.MIDNIGHT;

    }

    @Override
    public int getDefaultPivotYear() {

        return 100; // two-digit-years are effectively switched off

    }

}
