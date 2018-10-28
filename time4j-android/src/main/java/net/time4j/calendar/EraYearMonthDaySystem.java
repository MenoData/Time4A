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

import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;


/**
 * <p>Month-based calendar system abstraction. </p>
 *
 * @param   <D> generic type of calendar date (subtype of {@code Calendrical} or {@code CalendarVariant})
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
/*[deutsch]
 * <p>Monatsbasierte Kalendersystemabstraktion. </p>
 *
 * @param   <D> generic type of calendar date (subtype of {@code Calendrical} or {@code CalendarVariant})
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
interface EraYearMonthDaySystem<D>
    extends CalendarSystem<D> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Validates the given calendar date parameters. </p>
     *
     * @param   era         calendar era
     * @param   yearOfEra   calendar year
     * @param   monthOfYear calendar month
     * @param   dayOfMonth  calendar day of month
     * @return  {@code true} if valid else {@code false}
     * @since   3.5/4.3
     */
    /*[deutsch]
     * <p>Sind die angegebenen Datumsparameter g&uuml;ltig? </p>
     *
     * @param   era         calendar era
     * @param   yearOfEra   calendar year
     * @param   monthOfYear calendar month
     * @param   dayOfMonth  calendar day of month
     * @return  {@code true} if valid else {@code false}
     * @since   3.5/4.3
     */
    boolean isValid(
        CalendarEra era,
        int yearOfEra,
        int monthOfYear,
        int dayOfMonth
    );

    /**
     * <p>Yields the length of given  month. </p>
     *
     * @param   era         calendar era
     * @param   yearOfEra   calendar year
     * @param   monthOfYear calendar month
     * @return  length of month in days
     * @throws  IllegalArgumentException if any parameter is wrong or out of bounds
     * @since   3.5/4.3
     */
    /*[deutsch]
     * <p>Bestimmt die L&auml;nge des angegebenen Monats. </p>
     *
     * @param   era         calendar era
     * @param   yearOfEra   calendar year
     * @param   monthOfYear calendar month
     * @return  length of month in days
     * @throws  IllegalArgumentException if any parameter is wrong or out of bounds
     * @since   3.5/4.3
     */
    int getLengthOfMonth(
        CalendarEra era,
        int yearOfEra,
        int monthOfYear
    );

    /**
     * <p>Yields the length of given  year. </p>
     *
     * @param   era         calendar era
     * @param   yearOfEra   calendar year
     * @return  length of year in days
     * @throws  IllegalArgumentException if any parameter is wrong or out of bounds
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Bestimmt die L&auml;nge des angegebenen Jahres. </p>
     *
     * @param   era         calendar era
     * @param   yearOfEra   calendar year
     * @return  length of year in days
     * @throws  IllegalArgumentException if any parameter is wrong or out of bounds
     * @since   3.6/4.4
     */
    int getLengthOfYear(
        CalendarEra era,
        int yearOfEra
    );

}
