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

import net.time4j.engine.ChronoOperator;


/**
 * <p>Spezialoperator zum Navigieren zu bestimmten Uhrzeiten. </p>
 *
 * @author      Meno Hochschild
 */
final class WallTimeOperator
    extends ElementOperator<PlainTimestamp> {

    //~ Instanzvariablen --------------------------------------------------

    private final int mode;
    private final PlainTime value;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * @param   mode        navigation mode
     * @param   value       target value of navigation
     */
    WallTimeOperator(
        final int mode,
        final PlainTime value
    ) {
        super(PlainTime.COMPONENT, mode);

        if (value == null) {
            throw new NullPointerException("Missing target wall time.");
        }

        this.mode = mode;
        this.value = value;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public PlainTimestamp apply(PlainTimestamp entity) {

        PlainTime oldTime = entity.getWallTime();

        if (this.value.isSimultaneous(oldTime)) {
            return this.handleSameTimes(entity);
        } else if (this.value.getHour() == 24) {
            return this.handleMidnight24(entity);
        } else if (this.value.isAfter(oldTime)) {
            return this.handleLater(entity);
        } else {
            return this.handleEarlier(entity);
        }

    }

    @Override
    ChronoOperator<PlainTimestamp> onTimestamp() {

        return this;

    }

    private PlainTimestamp handleSameTimes(PlainTimestamp entity) {

        switch (this.mode) {
            case ElementOperator.OP_NAV_NEXT:
                return entity.plus(1, CalendarUnit.DAYS);
            case ElementOperator.OP_NAV_PREVIOUS:
                return entity.minus(1, CalendarUnit.DAYS);
            case ElementOperator.OP_NAV_NEXT_OR_SAME:
            case ElementOperator.OP_NAV_PREVIOUS_OR_SAME:
                return entity;
            default:
                throw new AssertionError("Unknown: " + this.mode);
        }

    }

    // entity is never on 24:00 hence following logic is to be applied
    private PlainTimestamp handleMidnight24(PlainTimestamp entity) {

        PlainDate date = entity.getCalendarDate();

        switch (this.mode) {
            case ElementOperator.OP_NAV_NEXT:
            case ElementOperator.OP_NAV_NEXT_OR_SAME:
                return date.plus(1, CalendarUnit.DAYS).atStartOfDay();
            case ElementOperator.OP_NAV_PREVIOUS:
            case ElementOperator.OP_NAV_PREVIOUS_OR_SAME:
                return date.atStartOfDay();
            default:
                throw new AssertionError("Unknown: " + this.mode);
        }

    }

    private PlainTimestamp handleLater(PlainTimestamp entity) {

        switch (this.mode) {
            case ElementOperator.OP_NAV_NEXT:
            case ElementOperator.OP_NAV_NEXT_OR_SAME:
                return entity.with(this.value);
            case ElementOperator.OP_NAV_PREVIOUS:
            case ElementOperator.OP_NAV_PREVIOUS_OR_SAME:
                return entity.minus(1, CalendarUnit.DAYS).with(this.value);
            default:
                throw new AssertionError("Unknown: " + this.mode);
        }

    }

    private PlainTimestamp handleEarlier(PlainTimestamp entity) {

        switch (this.mode) {
            case ElementOperator.OP_NAV_NEXT:
            case ElementOperator.OP_NAV_NEXT_OR_SAME:
                return entity.plus(1, CalendarUnit.DAYS).with(this.value);
            case ElementOperator.OP_NAV_PREVIOUS:
            case ElementOperator.OP_NAV_PREVIOUS_OR_SAME:
                return entity.with(this.value);
            default:
                throw new AssertionError("Unknown: " + this.mode);
        }

    }

}
