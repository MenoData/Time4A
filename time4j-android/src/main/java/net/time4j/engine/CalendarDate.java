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

package net.time4j.engine;


/**
 * <p>Represents a general calendar date. </p>
 *
 * @author  Meno Hochschild
 * @since   3.8/4.5
 */
/*[deutsch]
 * <p>Repr&auml;sentiert ein allgemeines Kalenderdatum. </p>
 *
 * @author  Meno Hochschild
 * @since   3.8/4.5
 */
public interface CalendarDate
    extends Temporal<CalendarDate> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Counts the elapsed days since UTC epoch. </p>
     *
     * @return  count of days relative to UTC epoch [1972-01-01]
     * @see     EpochDays#UTC
     * @since   3.8/4.5
     */
    /*[deutsch]
     * <p>Z&auml;hlt die seit der UTC-Epoche verstrichenen Tage. </p>
     *
     * @return  count of days relative to UTC epoch [1972-01-01]
     * @see     EpochDays#UTC
     * @since   3.8/4.5
     */
    long getDaysSinceEpochUTC();

}
