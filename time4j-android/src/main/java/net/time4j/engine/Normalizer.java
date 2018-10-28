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
 * <p>Normalizes a time span. </p>
 *
 * @param   <U> generic type of time unit compatible to {@link ChronoUnit}
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Normalisiert eine Zeitspanne. </p>
 *
 * @param   <U> generic type of time unit compatible to {@link ChronoUnit}
 * @author  Meno Hochschild
 */
public interface Normalizer<U> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Normalizes given time span such that the amounts of associated
     * time units are converted in a specific way. </p>
     *
     * @param   timespan    time span to be normalized
     * @return  normalized copy of argument which itself remains unaffected
     * @see     net.time4j.Duration#with(Normalizer)
     */
    /*[deutsch]
     * <p>Normalisiert die angegebene Zeitspanne so, da&szlig; die Betr&auml;ge
     * der enthaltenen Zeiteinheiten ineinander nach einem spezifischen
     * Verfahren umgerechnet werden. </p>
     *
     * @param   timespan    time span to be normalized
     * @return  normalized copy of argument which itself remains unaffected
     * @see     net.time4j.Duration#with(Normalizer)
     */
    TimeSpan<U> normalize(TimeSpan<? extends U> timespan);

}
