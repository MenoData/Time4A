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


/**
 * <p>The element for the ordinal weekday in month. </p>
 *
 * <p>An instance can be obtained using the expression
 * {@link PlainDate#WEEKDAY_IN_MONTH}. This interface inherits from
 * {@code AdjustableElement} and offers additional operator for setting
 * the weekday in month. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Das Element f&uuml;r den x-ten Wochentag im Monat. </p>
 *
 * <p>Eine Instanz ist erh&auml;ltlich &uuml;ber den Ausdruck
 * {@link PlainDate#WEEKDAY_IN_MONTH}. Dieses Interface bietet neben
 * den vom Interface {@code AdjustableElement} geerbten Methoden
 * weitere Spezialmethoden zum Setzen des Wochentags im Monat. </p>
 *
 * @author  Meno Hochschild
 */
public interface OrdinalWeekdayElement
    extends AdjustableElement<Integer, PlainDate> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Defines an operator which moves a date to the first given weekday
     * in month. </p>
     *
     * @param   dayOfWeek   first day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den ersten angegebenen
     * Wochentag eines Monats setzt. </p>
     *
     * @param   dayOfWeek   first day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    ElementOperator<PlainDate> setToFirst(Weekday dayOfWeek);

    /**
     * <p>Defines an operator which moves a date to the second given weekday
     * in month. </p>
     *
     * @param   dayOfWeek   second day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den zweiten angegebenen
     * Wochentag eines Monats setzt. </p>
     *
     * @param   dayOfWeek   second day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    ElementOperator<PlainDate> setToSecond(Weekday dayOfWeek);

    /**
     * <p>Defines an operator which moves a date to the third given weekday
     * in month. </p>
     *
     * @param   dayOfWeek   third day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den dritten angegebenen
     * Wochentag eines Monats setzt. </p>
     *
     * @param   dayOfWeek   third day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    ElementOperator<PlainDate> setToThird(Weekday dayOfWeek);

    /**
     * <p>Defines an operator which moves a date to the fourth given weekday
     * in month. </p>
     *
     * @param   dayOfWeek   fourth day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den vierten angegebenen
     * Wochentag eines Monats setzt. </p>
     *
     * @param   dayOfWeek   fourth day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    ElementOperator<PlainDate> setToFourth(Weekday dayOfWeek);

    /**
     * <p>Defines an operator which moves a date to the last given weekday
     * in month. </p>
     *
     * @param   dayOfWeek   last day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    /*[deutsch]
     * <p>Definiert einen Versteller, der ein Datum auf den letzten angegebenen
     * Wochentag eines Monats setzt. </p>
     *
     * @param   dayOfWeek   last day of week in month
     * @return  operator directly applicable also on {@code PlainTimestamp}
     */
    ElementOperator<PlainDate> setToLast(Weekday dayOfWeek);

}
