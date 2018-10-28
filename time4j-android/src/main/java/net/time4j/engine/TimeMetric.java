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
 * <p>Computes temporal distances on a time axis as time spans. </p>
 *
 * @param   <U> generic type of time unit
 * @param   <P> generic type of duration type
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Berechnet Abst&auml;nde auf einer Zeitachse als Zeitspannen. </p>
 *
 * @param   <U> generic type of time unit
 * @param   <P> generic type of duration type
 * @author  Meno Hochschild
 */
public interface TimeMetric<U, P> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Computes the temporal distance between two time points. </p>
     *
     * <p><strong>Important note:</strong> This method might not work in Java 6 under some circumstances.
     * In case of any problem users can use the equivalent method {@code until()} defined in the class
     * {@code TimePoint}. </p>
     *
     * @param   <T> generic type of time point
     * @param   start   first time point
     * @param   end     second time point
     * @return  calculated time span between given time points, will be
     *          negative if {@code start} is after {@code end}
     * @see     TimePoint#until(TimePoint, TimeMetric)
     */
    /*[deutsch]
     * <p>Berechnet den zeitlichen Abstand zwischen zwei Zeitpunkten. </p>
     *
     * <p><strong>Wichtiger Hinweis:</strong> Diese Methode mag in Java 6 unter bestimmten Umst&auml;nden nicht
     * funktionieren. Ist das der Fall, k&ouml;nnen Anwender auf die &auml;quivalente Methode
     * {@code until()} definiert in der Klasse {@code TimePoint} ausweichen. </p>
     *
     * @param   <T> generic type of time point
     * @param   start   first time point
     * @param   end     second time point
     * @return  calculated time span between given time points, will be
     *          negative if {@code start} is after {@code end}
     * @see     TimePoint#until(TimePoint, TimeMetric)
     */
    <T extends TimePoint<? super U, T>> P between(
        T start,
        T end
    );

}
