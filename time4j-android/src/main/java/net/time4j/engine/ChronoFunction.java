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
 * <p>Represents any temporal query using the strategy pattern approach. </p>
 *
 * @param   <T> generic type of source
 * @param   <R> generic type of result
 * @author  Meno Hochschild
 * @see     ChronoEntity#get(ChronoFunction)
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine beliebige zeitliche Abfrage entsprechend
 * dem Strategie-Entwurfsmuster. </p>
 *
 * @param   <T> generic type of source
 * @param   <R> generic type of result
 * @author  Meno Hochschild
 * @see     ChronoEntity#get(ChronoFunction)
 */
public interface ChronoFunction<T, R> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Reads and evaluates given time value context to a specific result
     * of type R. </p>
     *
     * <p>Will be called by {@link ChronoEntity#get(ChronoFunction)}.
     * Concrete implementations must document if they rather yield
     * {@code null} or throw an exception in case of undefined results. </p>
     *
     * @param   context     time context to be evaluated
     * @return  result of query or {@code null} if undefined
     * @throws  ChronoException if this query is not executable
     */
    /*[deutsch]
     * <p>Liest und interpretiert den angegebenen Zeitwertkontext. </p>
     *
     * <p>Wird von {@link ChronoEntity#get(ChronoFunction)} aufgerufen.
     * Konkrete Implementierungen m&uuml;ssen dokumentieren, ob
     * sie im Fall von undefinierten Ergebnissen eher {@code null}
     * zur&uuml;ckgeben oder stattdessen eine Ausnahme werfen. </p>
     *
     * @param   context     time context to be evaluated
     * @return  result of query or {@code null} if undefined
     * @throws  ChronoException if this query is not executable
     */
    R apply(T context);

}
