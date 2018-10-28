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

package net.time4j.tz;

import net.time4j.base.GregorianDate;
import net.time4j.base.WallTime;


/**
 * <p>Serves for resolving of local timestamps to a global UNIX timestamp,
 * escpecially if there are conflicts due to gaps or overlaps on the local
 * timeline. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must be immutable, thread-safe and serializable. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Dient der Aufl&ouml;sung von lokalen Zeitangaben zu einer UTC-Weltzeit,
 * wenn wegen L&uuml;cken oder &Uuml;berlappungen auf dem lokalen Zeitstrahl
 * Konflikte auftreten. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must be immutable, thread-safe and serializable. </p>
 *
 * @author  Meno Hochschild
 */
public interface TransitionStrategy {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Calculates a suitable global timestamp for given local timestamp. </p>
     *
     * <p>The nanosecond fraction of given wall time will not be taken
     * into account. </p>
     *
     * @param   localDate   local calendar date in given timezone
     * @param   localTime   local wall time in given timezone
     * @param   timezone    timezone data containing offset history
     * @return  global timestamp as full seconds since UNIX epoch (posix time)
     * @since   1.2.1
     * @see     net.time4j.scale.TimeScale#POSIX
     * @see     net.time4j.PlainTimestamp#in(Timezone)
     * @see     Timezone#with(TransitionStrategy)
     */
    /*[deutsch]
     * <p>Bestimmt einen geeigneten globalen Zeitstempel f&uuml;r eine
     * lokale Zeitangabe. </p>
     *
     * <p>Der Nanosekundenteil der angegebenen Uhrzeit bleibt
     * unber&uuml;cksichtigt. </p>
     *
     * @param   localDate   local calendar date in given timezone
     * @param   localTime   local wall time in given timezone
     * @param   timezone    timezone data containing offset history
     * @return  global timestamp as full seconds since UNIX epoch (posix time)
     * @since   1.2.1
     * @see     net.time4j.scale.TimeScale#POSIX
     * @see     net.time4j.PlainTimestamp#in(Timezone)
     * @see     Timezone#with(TransitionStrategy)
     */
    long resolve(
        GregorianDate localDate,
        WallTime localTime,
        Timezone timezone
    );

    /**
     * <p>Calculates a suitable offset for given local timestamp. </p>
     *
     * @param   localDate   local calendar date in given timezone
     * @param   localTime   local wall time in given timezone
     * @param   timezone    timezone data containing offset history
     * @return  ZonalOffset
     * @since   1.2.1
     * @see     net.time4j.PlainTimestamp#in(Timezone)
     * @see     Timezone#with(TransitionStrategy)
     */
    /*[deutsch]
     * <p>Bestimmt einen geeigneten Offset f&uuml;r eine lokale Zeitangabe. </p>
     *
     * @param   localDate   local calendar date in given timezone
     * @param   localTime   local wall time in given timezone
     * @param   timezone    timezone data containing offset history
     * @return  ZonalOffset
     * @since   1.2.1
     * @see     net.time4j.PlainTimestamp#in(Timezone)
     * @see     Timezone#with(TransitionStrategy)
     */
    ZonalOffset getOffset(
        GregorianDate localDate,
        WallTime localTime,
        Timezone timezone
    );

    /**
     * <p>Tries to change the overlap handling. </p>
     *
     * @param   resolver    strategy how to handle ambivalent mappings during an offset overlap
     * @return  possibly adjusted transition strategy
     * @since   3.32/4.27
     */
    /*[deutsch]
     * <p>Versucht, die &Uuml;bergangsstrategie f&uuml;r &Uuml;berlappungen auf dem lokalen Zeitstrahl
     * zu &auml;ndern. </p>
     *
     * @param   resolver    strategy how to handle ambivalent mappings during an offset overlap
     * @return  possibly adjusted transition strategy
     * @since   3.32/4.27
     */
    TransitionStrategy using(OverlapResolver resolver);

}
