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

import net.time4j.Weekday;
import net.time4j.engine.ChronoOperator;


/**
 * <p>The element for the ordinal weekday in month. </p>
 *
 * <p>This interface offers additional operators for setting the weekday in month. </p>
 *
 * @param   <T> generic type of calendar chronology
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
/*[deutsch]
 * <p>Das Element f&uuml;r den x-ten Wochentag im Monat. </p>
 *
 * <p>Dieses Interface bietet weitere Spezialmethoden zum Setzen des Wochentags im Monat. </p>
 *
 * @param   <T> generic type of calendar chronology
 * @author  Meno Hochschild
 * @since   3.33/4.28
 */
public interface OrdinalWeekdayElement<T>
    extends StdCalendarElement<Integer, T> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Defines an operator which moves a date to the first given weekday in month. </p>
     *
     * <p>Equivalent to {@link #setTo(int, Weekday) setTo(1, dayOfWeek)}. </p>
     *
     * @param   dayOfWeek   first day of week in month
     * @return  lenient operator
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den ersten angegebenen Wochentag eines Monats setzt. </p>
     *
     * <p>&Auml;quivalent zu {@link #setTo(int, Weekday) setTo(1, dayOfWeek)}. </p>
     *
     * @param   dayOfWeek   first day of week in month
     * @return  lenient operator
     */
    ChronoOperator<T> setToFirst(Weekday dayOfWeek);

    /**
     * <p>Defines an operator which moves a date to the last given weekday in month. </p>
     *
     * <p>Equivalent to {@link #setTo(int, Weekday) setTo(Integer.MAX_VALUE, dayOfWeek)}.
     * The new day will never be in next month. </p>
     *
     * @param   dayOfWeek   last day of week in month
     * @return  lenient operator
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den letzten angegebenen Wochentag eines Monats setzt. </p>
     *
     * <p>&Auml;quivalent zu {@link #setTo(int, Weekday) setTo(Integer.MAX_VALUE, dayOfWeek)}.
     * Der neue Tag wird niemals im n&auml;chsten Monat liegen. </p>
     *
     * @param   dayOfWeek   last day of week in month
     * @return  lenient operator
     */
    ChronoOperator<T> setToLast(Weekday dayOfWeek);

    /**
     * <p>Defines an operator which moves a date to the given ordinal weekday in month. </p>
     *
     * <p>If given ordinal number is {@code Integer.MAX_VALUE} then the last weekday in month
     * will be determined. This operator behaves in a lenient way, any overflow of the ordinal
     * number will be transferred to another month. Note that this behaviour will also happen
     * if the current month might be shorter than a full calendar week as exotic edge case. </p>
     *
     * @param   ordinal     ordinal number
     * @param   dayOfWeek   last day of week in month
     * @return  lenient operator
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den x-ten angegebenen Wochentag eines Monats setzt. </p>
     *
     * <p>Wenn die angegebene Ordnungsnummer {@code Integer.MAX_VALUE} ist, wird der letzte
     * Wochentag des Monats bestimmt. Dieser Operator verh&auml;lt sich nachsichtig, d. h., jedweder
     * &Uuml;berlauf der Ordnungsnummer wird auf einen anderen Monat &uuml;bertragen. Zu beachten,
     * dieses Verhalten wird auch auftreten, wenn als exotischer Randfall der aktuelle Monat
     * k&uuml;rzer als eine Kalenderwoche sein sollte. </p>
     *
     * @param   ordinal     ordinal number
     * @param   dayOfWeek   last day of week in month
     * @return  lenient operator
     */
    ChronoOperator<T> setTo(
        int ordinal,
        Weekday dayOfWeek
    );

}
