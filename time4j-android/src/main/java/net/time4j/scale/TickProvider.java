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
 * <p>This <strong>SPI-interface</strong> describes how nanoseconds since an arbitrary
 * start time are generated. </p>
 *
 * <p>Will be evaluated during loading of the class {@code SystemClock}. The internal standard
 * implementation uses {@code System.nanoTime()}. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must have a public no-arg constructor. </p>
 *
 * @author  Meno Hochschild
 * @since   3.2/4.1
 */
/*[deutsch]
 * <p>Dieses <strong>SPI-Interface</strong> beschreibt, wie Nanosekunden seit einem
 * beliebigen Startzeitpunkt generiert werden. </p>
 *
 * <p>Wird beim Laden der Klasse {@code net.time4j.SystemClock} ausgewertet. Die
 * interne Standardimplementierung basiert auf {@code System.nanoTime()}. </p>
 *
 * <p><strong>Specification:</strong>
 * All implementations must have a public no-arg constructor. </p>
 *
 * @author  Meno Hochschild
 * @since   3.2/4.1
 */
public interface TickProvider {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Determines the name of the platform where this implementation should be used. </p>
     *
     * @return  name of suitable platform which is equivalent to the system property &quot;java.vm.name&quot;
     * @since   3.2/4.1
     */
    /*[deutsch]
     * <p>Gibt den Namen der Plattform an wo diese Implementierung genutzt werden darf. </p>
     *
     * @return  name of suitable platform which is equivalent to the system property &quot;java.vm.name&quot;
     * @since   3.2/4.1
     */
    String getPlatform();

    /**
     * <p>Generates a count of nanoseconds. </p>
     *
     * @return  count of nanosecond ticks since an arbitrary but fixed start time which is typically the boot time
     * @since   3.2/4.1
     */
    /*[deutsch]
     * <p>Generiert eine Anzahl von Nanosekunden. </p>
     *
     * @return  count of nanosecond ticks since an arbitrary but fixed start time which is typically the boot time
     * @since   3.2/4.1
     */
    long getNanos();

}
