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
 * <p>Repr&auml;sentiert eine Uhrzeitkomponente. </p>
 *
 * @author      Meno Hochschild
 */
final class TimeElement
    extends BasicElement<PlainTime>
	implements WallTimeElement {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Singleton-Instanz.
     */
    static final TimeElement INSTANCE = new TimeElement();

    private static final long serialVersionUID = -3712256393866098916L;

    //~ Konstruktoren -----------------------------------------------------

    private TimeElement() {
        super("WALL_TIME");

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<PlainTime> getType() {

        return PlainTime.class;

    }

    @Override
    public PlainTime getDefaultMinimum() {

        return PlainTime.MIN;

    }

    @Override
    public PlainTime getDefaultMaximum() {

        return PlainTime.of(23, 59, 59, 999999999);

    }

    @Override
    public boolean isDateElement() {

        return false;

    }

    @Override
    public boolean isTimeElement() {

        return true;

    }

	@Override
	public ElementOperator<?> setToNext(PlainTime v) {

		return new WallTimeOperator(ElementOperator.OP_NAV_NEXT, v);

	}

	@Override
	public ElementOperator<?> setToPrevious(PlainTime v) {

		return new WallTimeOperator(ElementOperator.OP_NAV_PREVIOUS, v);

	}

	@Override
	public ElementOperator<?> setToNextOrSame(PlainTime v) {

		return new WallTimeOperator(ElementOperator.OP_NAV_NEXT_OR_SAME, v);

	}

	@Override
	public ElementOperator<?> setToPreviousOrSame(PlainTime v) {

		return new WallTimeOperator(
            ElementOperator.OP_NAV_PREVIOUS_OR_SAME,
            v);

	}

    @Override
    public ElementOperator<PlainTime> roundedToFullHour() {

        return FullValueOperator.ROUNDING_FULL_HOUR;

    }

    @Override
    public ElementOperator<PlainTime> roundedToFullMinute() {

        return FullValueOperator.ROUNDING_FULL_MINUTE;

    }

    @Override
    public ElementOperator<PlainTime> setToNextFullHour() {

        return FullValueOperator.NEXT_FULL_HOUR;

    }

    @Override
    public ElementOperator<PlainTime> setToNextFullMinute() {

        return FullValueOperator.NEXT_FULL_MINUTE;

    }

    @Override
    public ChronoFunction<Moment, PlainTime> inStdTimezone() {

        return this.in(Timezone.ofSystem());

    }

    @Override
    public ChronoFunction<Moment, PlainTime> inTimezone(TZID tzid) {

        return this.in(Timezone.of(tzid));

    }

    @Override
    public ChronoFunction<Moment, PlainTime> in(Timezone tz) {

        return new ZonalQuery<PlainTime>(this, tz);

    }

    @Override
    public ChronoFunction<Moment, PlainTime> atUTC() {

        return this.at(ZonalOffset.UTC);

    }

    @Override
    public ChronoFunction<Moment, PlainTime> at(ZonalOffset offset) {

        return new ZonalQuery<PlainTime>(this, offset);

    }

    @Override
    protected boolean isSingleton() {

        return true;

    }

    private Object readResolve() throws ObjectStreamException {

        return INSTANCE;

    }

}
