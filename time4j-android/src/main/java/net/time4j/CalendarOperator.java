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

import net.time4j.base.GregorianMath;
import net.time4j.engine.ChronoOperator;



/**
 * <p>F&uuml;hrt kombinierte Kalendermanipulationen aus. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 */
final class CalendarOperator
    extends ElementOperator<PlainDate> {

    //~ Statische Felder/Initialisierungen --------------------------------

    static final CalendarOperator FIRST_DAY_OF_NEXT_MONTH =
        new CalendarOperator(ElementOperator.OP_FIRST_DAY_OF_NEXT_MONTH);
    static final CalendarOperator FIRST_DAY_OF_NEXT_QUARTER =
        new CalendarOperator(ElementOperator.OP_FIRST_DAY_OF_NEXT_QUARTER);
    static final CalendarOperator FIRST_DAY_OF_NEXT_YEAR =
        new CalendarOperator(ElementOperator.OP_FIRST_DAY_OF_NEXT_YEAR);

    static final CalendarOperator LAST_DAY_OF_PREVIOUS_MONTH =
        new CalendarOperator(ElementOperator.OP_LAST_DAY_OF_PREVIOUS_MONTH);
    static final CalendarOperator LAST_DAY_OF_PREVIOUS_QUARTER =
        new CalendarOperator(ElementOperator.OP_LAST_DAY_OF_PREVIOUS_QUARTER);
    static final CalendarOperator LAST_DAY_OF_PREVIOUS_YEAR =
        new CalendarOperator(ElementOperator.OP_LAST_DAY_OF_PREVIOUS_YEAR);

    //~ Instanzvariablen --------------------------------------------------

    private final ChronoOperator<PlainTimestamp> tsop;

    //~ Konstruktoren -----------------------------------------------------

    private CalendarOperator(int type) {
        super(PlainDate.COMPONENT, type);

        this.tsop =
            new ChronoOperator<PlainTimestamp>() {
                @Override
                public PlainTimestamp apply(PlainTimestamp entity) {
                    PlainDate date = doApply(entity.getCalendarDate());
                    return entity.with(date);
                }
            };

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public PlainDate apply(PlainDate entity) {

        return this.doApply(entity);

    }

    @Override
    ChronoOperator<PlainTimestamp> onTimestamp() {

        return this.tsop;

    }

    private PlainDate doApply(PlainDate date) {

        int year = date.getYear();
        int month = date.getMonth();

        switch (this.getType()) {
            case OP_FIRST_DAY_OF_NEXT_MONTH:
                month++;
                if (month >= 13) {
                    year++;
                    month = 1;
                }
                return PlainDate.of(year, month, 1);
            case OP_FIRST_DAY_OF_NEXT_QUARTER:
                Quarter q1 = Month.valueOf(month).getQuarterOfYear();
                Quarter q2 = q1.next();
                month = Month.atStartOfQuarterYear(q2).getValue();
                if (q1 == Quarter.Q4) {
                    year++;
                }
                return PlainDate.of(year, month, 1);
            case OP_FIRST_DAY_OF_NEXT_YEAR:
                year++;
                return PlainDate.of(year, 1, 1);
            case OP_LAST_DAY_OF_PREVIOUS_MONTH:
                month--;
                if (month <= 0) {
                    year--;
                    month = 12;
                }
                return PlainDate.of(
                    year,
                    month,
                    GregorianMath.getLengthOfMonth(year, month));
            case OP_LAST_DAY_OF_PREVIOUS_QUARTER:
                Quarter p1 = Month.valueOf(month).getQuarterOfYear();
                Quarter p2 = p1.previous();
                month = Month.atEndOfQuarterYear(p2).getValue();
                if (p2 == Quarter.Q4) {
                    year--;
                    return PlainDate.of(year, month, 31); // december
                } else if (p2 == Quarter.Q1) {
                    return PlainDate.of(year, month, 31); // march
                }
                return PlainDate.of(year, month, 30); // june or september
            case OP_LAST_DAY_OF_PREVIOUS_YEAR:
                year--;
                return PlainDate.of(year, 12, 31);
            default:
                throw new AssertionError("Unknown: " + this.getType());
        }

    }

}
