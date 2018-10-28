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

package net.time4j.scale;


/**
 * <p>Erweiterung des Ereignis-Interface um UTC-Methoden. </p>
 *
 * @author  Meno Hochschild
 */
interface ExtendedLSE
    extends LeapSecondEvent {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Definiert die Sekunde des Schaltsekundenereignisses
     * als UTC-Zeit mit Schaltsekunden. </p>
     *
     * @return  elapsed UTC time in SI-seconds relative to UTC epoch 1972-01-01
     *          including leap seconds
     */
    long utc();

    /**
     * <p>Definiert die Sekunde des Schaltsekundenereignisses
     * als UTC-Zeit ohne Schaltsekunden. </p>
     *
     * @return  elapsed UTC time in SI-seconds relative to UTC epoch 1972-01-01
     *          without leap seconds
     */
    long raw();

}
