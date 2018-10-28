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

package net.time4j.base;


/**
 * <p>Represents any UNIX timestamp. </p>
 *
 * @author  Meno Hochschild
 * @see     net.time4j.scale.TimeScale#POSIX
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine beliebige UNIX-Zeit. </p>
 *
 * @author  Meno Hochschild
 * @see     net.time4j.scale.TimeScale#POSIX
 */
public interface UnixTime {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Counts the seconds elapsed since UNIX epoch
     * [1970-01-01T00:00:00Z] in UTC timezone. </p>
     *
     * @return  count of seconds since UNIX-epoch at [1970-01-01T00:00:00Z]
     *          without leap seconds in the timezone UTC (Greenwich)
     */
    /*[deutsch]
     * <p>Liefert die Zeitkoordinate in Sekunden relativ zur UNIX-Epoche
     * [1970-01-01T00:00:00Z] in der UTC-Zeitzone. </p>
     *
     * @return  count of seconds since UNIX-epoch at [1970-01-01T00:00:00Z]
     *          without leap seconds in the timezone UTC (Greenwich)
     */
    long getPosixTime();

    /**
     * <p>Yields the nanosecond fraction of current second. </p>
     *
     * <p>As time unit, the nanosecond is defined as one billionth part of
     * a second). </p>
     *
     * @return  count of nanoseconds as fraction of last second in the
     *          range {@code 0 - 999.999.999}
     */
    /*[deutsch]
     * <p>Liefert den Nanosekundenbruchteil der letzten Sekunde. </p>
     *
     * <p>Als Zeiteinheit dient die Nanosekunde (1 Milliarde Nanosekunden
     * = 1 Sekunde). </p>
     *
     * @return  count of nanoseconds as fraction of last second in the
     *          range {@code 0 - 999.999.999}
     */
    int getNanosecond();

}
