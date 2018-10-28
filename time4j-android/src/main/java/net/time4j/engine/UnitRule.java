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
 * <p>Represents a rule for the addition or subtraction associated with
 * a time unit. </p>
 *
 * <p>A unit rule will usually be registered together with an unit for a
 * {@code TimeAxis}, during loading of the concrete {@code TimePoint}-class.
 * For every time axis and every time unit there is exactly one rule
 * instance. Implementations must be always <i>immutable</i>. </p>
 *
 * @param   <T> generic type of time context compatible to {@code TimePoint}
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Additions- und Subtraktionsregel einer
 * Zeiteinheit. </p>
 *
 * <p>Eine Zeiteinheitsregel wird in der {@code TimeAxis} zusammen mit
 * der assoziierten Zeiteinheit registriert, was gew&ouml;hnlich beim Laden
 * der jeweiligen {@code TimePoint}-Klasse geschieht. Pro Chronologie und
 * pro Zeiteinheit gibt es genau eine Regelinstanz. Implementierungen
 * m&uuml;ssen immer <i>immutable</i> sein. </p>
 *
 * @param   <T> generic type of time context compatible to {@code TimePoint}
 * @author  Meno Hochschild
 */
public interface UnitRule<T> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Adds given amount to a time point in the context of an
     * associated time unit. </p>
     *
     * <p>This method is called by the {@code plus()}- and
     * {@code minus()}-methods in the class {@code TimePoint}. If not
     * specified otherwise then a possible range overflow will be
     * resolved such that the last valid time point is choosen. For
     * example the addition of one month to date of 31th of may will
     * yield June, 30th. </p>
     *
     * @param   timepoint   time point
     * @param   amount      count of units to be added to
     * @return  result of addition as changed copy, given time point remains unaffected
     * @throws  IllegalArgumentException if boundary constraints are violated
     * @throws  ArithmeticException in case of numerical overflow
     * @see     TimePoint#plus(long, Object) TimePoint.plus(long, U)
     * @see     TimePoint#minus(long, Object) TimePoint.minus(long, U)
     */
    /*[deutsch]
     * <p>Addiert den angegebenen Betrag zu einem Zeitpunkt im Kontext der
     * assoziierten Zeiteinheit. </p>
     *
     * <p>Wird von den {@code plus()}- und {@code minus()}-Methoden
     * in der Klasse {@code TimePoint} aufgerufen. Wenn nicht besonders
     * spezifiziert, wird ein eventueller &Uuml;berlauf so aufgel&ouml;st,
     * da&szlig; der zuletzt g&uuml;ltige Zeitpunkt gesucht wird. Zum
     * Beispiel wird die Addition eines Monats zum 31. Mai gew&ouml;hnlich
     * den 30. Juni ergeben (weil der 31. Juni nicht existiert). </p>
     *
     * @param   timepoint   time point
     * @param   amount      count of units to be added to
     * @return  result of addition as changed copy, given time point remains unaffected
     * @throws  IllegalArgumentException if boundary constraints are violated
     * @throws  ArithmeticException in case of numerical overflow
     * @see     TimePoint#plus(long, Object) TimePoint.plus(long, U)
     * @see     TimePoint#minus(long, Object) TimePoint.minus(long, U)
     */
    T addTo(T timepoint, long amount);

    /**
     * <p>Queries how many units are between given time points. </p>
     *
     * <p>This method is called by {@code TimePoint.until(T, U)}. The
     * expression {@code start.until(end, unit)} corresponds to
     * {@code ruleForUnit.between(start, end)}. Only full units will
     * be counted. A possible remainder of subtraction will always
     * be truncated. </p>
     *
     * @param   start   start time point
     * @param   end     end time point
     * @return  difference in units (negative if {@code end} is before
     *          {@code start})
     * @throws  ArithmeticException in case of numerical overflow
     * @see     TimePoint#until(TimePoint, Object) TimePoint.until(T, U)
     */
    /*[deutsch]
     * <p>Ermittelt die Differenz zwischen den angegebenen Zeitwertpunkten
     * als Anzahl der assoziierten Zeiteinheit. </p>
     *
     * <p>Wird von der {@code TimePoint.until(T, U)}-Methode aufgerufen,
     * d.h., der Ausdruck {@code start.until(end, unit)} entspricht
     * gerade {@code ruleForUnit.between(start, end)}. Es werden nur
     * volle Zeiteinheiten gez&auml;hlt. Ein Subtraktionsrest wird immer
     * unterschlagen. </p>
     *
     * @param   start   start time point
     * @param   end     end time point
     * @return  difference in units (negative if {@code end} is before
     *          {@code start})
     * @throws  ArithmeticException in case of numerical overflow
     * @see     TimePoint#until(TimePoint, Object) TimePoint.until(T, U)
     */
    long between(T start, T end);

}
