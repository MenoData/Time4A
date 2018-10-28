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


import net.time4j.PlainDate;
import net.time4j.base.GregorianMath;
import net.time4j.engine.EpochDays;

/**
 * <p>Represents a cutover event switching the calendar algorithm at a given date. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
final class CutOverEvent {

    //~ Instanzvariablen --------------------------------------------------

    final long start;
    final CalendarAlgorithm algorithm;
    final HistoricDate dateAtCutOver;
    final HistoricDate dateBeforeCutOver;

    //~ Konstruktoren -----------------------------------------------------

    CutOverEvent(
        long mjd,
        CalendarAlgorithm oldAlgorithm,
        CalendarAlgorithm newAlgorithm
    ) {
        super();

        this.start = mjd;
        this.algorithm = newAlgorithm;

        if (mjd == Long.MIN_VALUE) {
            HistoricDate date = new HistoricDate(HistoricEra.BC, GregorianMath.MAX_YEAR + 1, 1, 1);
            this.dateAtCutOver = date;
            this.dateBeforeCutOver = date;
        } else {
            this.dateAtCutOver = algorithm.fromMJD(mjd);
            this.dateBeforeCutOver = oldAlgorithm.fromMJD(mjd - 1);
        }

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof CutOverEvent) {
            CutOverEvent that = (CutOverEvent) obj;
            return (
                (this.start == that.start)
                && (this.algorithm == that.algorithm)
                && this.dateBeforeCutOver.equals(that.dateBeforeCutOver)
            );
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return (int) (this.start ^ (this.start >>> 32));

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append("[start=");
        sb.append(this.start);
        sb.append(" (");
        sb.append(PlainDate.of(this.start, EpochDays.MODIFIED_JULIAN_DATE));
        sb.append("),algorithm=");
        sb.append(this.algorithm);
        sb.append(",date-before-cutover=");
        sb.append(this.dateBeforeCutOver);
        sb.append(",date-at-cutover=");
        sb.append(this.dateAtCutOver);
        sb.append(']');
        return sb.toString();

    }

}
