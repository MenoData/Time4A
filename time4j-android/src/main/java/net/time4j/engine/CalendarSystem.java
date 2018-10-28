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

import java.util.List;


/**
 * <p>Represents a calendar system which can map a calendar date to a
 * day number corresponding to the count of days elapsed since UTC epoch
 * [1972-01-01]. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must be immutable. </p>
 *
 * @param   <D> generic type of calendar date (subtype of {@code Calendrical} or {@code CalendarVariant})
 * @author  Meno Hochschild
 * @see     Calendrical
 * @see     CalendarVariant
 * @see     net.time4j.engine.EpochDays
 */
/*[deutsch]
 * <p>Repr&auml;sentiert ein Kalendersystem, das Datumsangaben eindeutig auf
 * eine Tagesnummer entsprechend der Anzahl der Tage seit der UTC-Epoche
 * [1972-01-01] abbilden kann. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must be immutable. </p>
 *
 * @param   <D> generic type of calendar date (subtype of {@code Calendrical} or {@code CalendarVariant})
 * @author  Meno Hochschild
 * @see     Calendrical
 * @see     CalendarVariant
 * @see     net.time4j.engine.EpochDays
 */
public interface CalendarSystem<D> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Transforms given day number to a calendar date on the local
     * time line at the reference time of noon. </p>
     *
     * @param   utcDays     count of days since UTC epoch [1972-01-01]
     * @return  new calendar date
     * @throws  IllegalArgumentException if the argument is out of range
     */
    /*[deutsch]
     * <p>Transformiert die angegebene Tagesnummer zu einem Datum auf dem
     * lokalen Zeitstrahl mit der Referenzzeit zu 12 Uhr mittags. </p>
     *
     * @param   utcDays     count of days since UTC epoch [1972-01-01]
     * @return  new calendar date
     * @throws  IllegalArgumentException if the argument is out of range
     */
    D transform(long utcDays);

    /**
     * <p>Transforms given calendar date to a day number on the local
     * time line at the reference time of noon. </p>
     *
     * @param   date        calendar date to be transformed
     * @return  count of days since UTC epoch [1972-01-01]
     * @throws  IllegalArgumentException if the argument is not consistent with this calendar system
     */
    /*[deutsch]
     * <p>Transformiert das angegebene Datum zu einer Tagesnummer auf dem
     * lokalen Zeitstrahl mit der Referenzzeit zu 12 Uhr mittags. </p>
     *
     * @param   date        calendar date to be transformed
     * @return  count of days since UTC epoch [1972-01-01]
     * @throws  IllegalArgumentException if the argument is not consistent with this calendar system
     */
    long transform(D date);

    /**
     * <p>Gets the minimum day number as count of days since the
     * introduction of UTC [1972-01-01]. </p>
     *
     * @return  smallest count of days relative to UTC epoch [1972-01-01]
     * @see     #getMaximumSinceUTC()
     */
    /*[deutsch]
     * <p>Liefert die minimal m&ouml;gliche Tagesnummer als Anzahl der Tage
     * seit der Einf&uuml;hrung von UTC [1972-01-01]. </p>
     *
     * @return  smallest count of days relative to UTC epoch [1972-01-01]
     * @see     #getMaximumSinceUTC()
     */
    long getMinimumSinceUTC();

    /**
     * <p>Gets the maximum day number as count of days since the
     * introduction of UTC [1972-01-01]. </p>
     *
     * @return  largest count of days relative to UTC epoch [1972-01-01]
     * @see     #getMinimumSinceUTC()
     */
    /*[deutsch]
     * <p>Liefert die maximal m&ouml;gliche Tagesnummer als Anzahl der Tage
     * seit der Einf&uuml;hrung von UTC [1972-01-01]. </p>
     *
     * @return  largest count of days relative to UTC epoch [1972-01-01]
     * @see     #getMinimumSinceUTC()
     */
    long getMaximumSinceUTC();

    /**
     * <p>Yields an enumeration of valid eras in ascending order. </p>
     *
     * <p>All ISO-systems only yield an empty list. A gregorian calendar
     * defines the eras {@code BC} and {@code AD} related to Jesu birth
     * however. </p>
     *
     * @return  unmodifiable list of eras (maybe empty)
     */
    /*[deutsch]
     * <p>Zeitlich aufsteigend sortierte Auflistung der f&uuml;r ein
     * Kalendersystem g&uuml;ltigen &Auml;ren. </p>
     *
     * <p>Alle ISO-Systeme liefern nur eine leere Liste. Ein gregorianischer
     * Kalender hingegen definiert die &Auml;ren {@code BC} und {@code AD}
     * bezogen auf Jesu Geburt. </p>
     *
     * @return  unmodifiable list of eras (maybe empty)
     */
    List<CalendarEra> getEras();

}
