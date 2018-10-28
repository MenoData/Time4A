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
 * <p>Represents any kind of clock as source of current world time. </p>
 *
 * @param   <T> generic type of current time
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine beliebige Uhr als Quelle der aktuellen
 * Weltzeit. </p>
 *
 * @param   <T> generic type of current time
 * @author  Meno Hochschild
 */
public interface TimeSource<T extends UnixTime> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the current time. </p>
     *
     * @return  current time in seconds (as {@code UnixTime} object or derivate)
     */
    /*[deutsch]
     * <p>Liefert die aktuelle Zeit. </p>
     *
     * @return  current time in seconds (as {@code UnixTime} object or derivate)
     */
    T currentTime();

}
