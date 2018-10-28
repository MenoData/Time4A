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

package net.time4j.history;


/**
 * <p>Calculator engine for historic dates. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
interface Calculus {

    //~ Methoden ----------------------------------------------------------

    /**
     * Converts given historic date tuple to a modified julian date.
     *
     * @param   date    historic date
     * @return  modified julian date
     * @throws  IllegalArgumentException if argument is out of range
     */
    long toMJD(HistoricDate date);

    /**
     * Converts given modified julian date to a historic date tuple.
     *
     * @param   mjd     modified julian date
     * @return  historic date
     * @throws  IllegalArgumentException if argument is out of range
     */
    HistoricDate fromMJD(long mjd);

    /**
     * Checks if given historic date tuple is valid.
     *
     * @param   date    historic date
     * @return  {@code true} if valid else {@code false}
     */
    boolean isValid(HistoricDate date);

    /**
     * Determines the length of associated month of given historic date tuple.
     *
     * @param   date    historic date
     * @return  int
     * @throws  IllegalArgumentException if argument is out of range
     */
    int getMaximumDayOfMonth(HistoricDate date);

}
