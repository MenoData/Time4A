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

import java.util.Comparator;


/**
 * <p>Represents a time axis where a point in time can be moved forward or
 * backward. </p>
 *
 * <p>As step width, the associated time axis will usually use the smallest
 * registered time unit. </p>
 *
 * @param   <T> generic type of time points
 * @author  Meno Hochschild
 * @since   2.0
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine Zeitachse, entlang der ein Zeitpunkt schrittweise
 * vorw&auml;rts oder zur&uuml;ck gesetzt werden kann. </p>
 *
 * <p>Als Schrittweite wird die zugeh&ouml;rige Zeitachse gew&ouml;hnlich die
 * kleinste registrierte Zeiteinheit verwenden. </p>
 *
 * @param   <T> generic type of time points
 * @author  Meno Hochschild
 * @since   2.0
 */
public interface TimeLine<T>
    extends Comparator<T> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Move given point in time forward by one step. </p>
     *
     * @param   timepoint       point in time to be moved forward
     * @return  new point in time one step after given argument
     *          or {@code null} if applied on the maximum of timeline
     * @since   2.0
     */
    /*[deutsch]
     * <p>Setzt den angegebenen Zeitpunkt einen Schritt vorw&auml;rts. </p>
     *
     * @param   timepoint       point in time to be moved forward
     * @return  new point in time one step after given argument
     *          or {@code null} if applied on the maximum of timeline
     * @since   2.0
     */
    T stepForward(T timepoint);

    /**
     * <p>Move given point in time backwards by one step. </p>
     *
     * @param   timepoint       point in time to be moved backwards
     * @return  new point in time one step before given argument
     *          or {@code null} if applied on the minimum of timeline
     * @since   2.0
     */
    /*[deutsch]
     * <p>Setzt den angegebenen Zeitpunkt einen Schritt
     * r&uuml;ckw&auml;rts. </p>
     *
     * @param   timepoint       point in time to be moved backwards
     * @return  new point in time one step before given argument
     *          or {@code null} if applied on the minimum of timeline
     * @since   2.0
     */
    T stepBackwards(T timepoint);

    /**
     * Determines if this timeline is calendrical or not.
     *
     * @return  boolean
     * @see     CalendarDate
     * @since   3.36/4.31
     */
    /*[deutsch]
     * Ermittelt, ob diese Zeitachse kalendarisch ist oder nicht.
     *
     * @return  boolean
     * @see     CalendarDate
     * @since   3.36/4.31
     */
    boolean isCalendrical();

}
