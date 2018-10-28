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

import static net.time4j.CalendarUnit.DAYS;


/**
 * <p>Rundet auf volle Werte. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 */
final class FullValueOperator
    extends ElementOperator<PlainTime> {

    //~ Statische Felder/Initialisierungen --------------------------------

    static final FullValueOperator ROUNDING_FULL_HOUR =
        new FullValueOperator(ElementOperator.OP_ROUND_FULL_HOUR);
    static final FullValueOperator ROUNDING_FULL_MINUTE =
        new FullValueOperator(ElementOperator.OP_ROUND_FULL_MINUTE);
    static final FullValueOperator NEXT_FULL_HOUR =
        new FullValueOperator(ElementOperator.OP_NEXT_FULL_HOUR);
    static final FullValueOperator NEXT_FULL_MINUTE =
        new FullValueOperator(ElementOperator.OP_NEXT_FULL_MINUTE);

    //~ Instanzvariablen --------------------------------------------------

    private final ChronoOperator<PlainTimestamp> tsop;

    //~ Konstruktoren -----------------------------------------------------

    private FullValueOperator(int type) {
        super(PlainTime.COMPONENT, type);

        this.tsop =
            new ChronoOperator<PlainTimestamp>() {
                @Override
                public PlainTimestamp apply(PlainTimestamp entity) {
                    PlainTime time = doApply(entity.getWallTime());

                    if (time.getHour() == 24) {
                        return PlainTimestamp.of(
                            entity.getCalendarDate().plus(1, DAYS),
                            PlainTime.midnightAtStartOfDay());
                    } else {
                        return entity.with(time);
                    }
                }
            };

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public PlainTime apply(PlainTime entity) {

        return this.doApply(entity);

    }

    @Override
    ChronoOperator<PlainTimestamp> onTimestamp() {

        return this.tsop;

    }

    private PlainTime doApply(PlainTime time) {

        int hour = time.getHour();
        int minute = time.getMinute();

        switch (this.getType()) {
            case OP_ROUND_FULL_HOUR:
                if (minute >= 30) {
                    hour++;
                    if (hour == 25) {
                        hour = 1;
                    }
                }
                return PlainTime.of(hour);
            case OP_ROUND_FULL_MINUTE:
                if (time.getSecond() >= 30) {
                    if (hour == 24) {
                        hour = 0;
                        minute = 1;
                    } else {
                        minute++;
                        if (minute == 60) {
                            hour++;
                            minute = 0;
                        }
                    }
                }
                return PlainTime.of(hour, minute);
            case OP_NEXT_FULL_HOUR:
                hour++;
                if (hour == 25) {
                    hour = 1;
                }
                return PlainTime.of(hour);
            case OP_NEXT_FULL_MINUTE:
                if (hour == 24) {
                    hour = 0;
                    minute = 1;
                } else {
                    minute++;
                    if (minute == 60) {
                        hour++;
                        minute = 0;
                    }
                }
                return PlainTime.of(hour, minute);
            default:
                throw new AssertionError("Unknown: " + this.getType());
        }

    }

}
