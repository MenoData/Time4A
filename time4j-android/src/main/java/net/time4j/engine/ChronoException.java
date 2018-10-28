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
 * <p>Indicates a chronological error situation. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Signalisiert eine chronologische Fehlersituation. </p>
 *
 * @author  Meno Hochschild
 */
public class ChronoException
    extends RuntimeException {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -6646794951280971956L;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * Creates a new instanceo of <code>ChronoException</code>
     * with given error message.
     *
     * @param   msg     detailed error message
     */
    /*[deutsch]
     * Erzeugt eine neue Instanz von <code>ChronoException</code>
     * mit der angegebenen Detailmeldung.
     *
     * @param   msg     detailed error message
     */
    public ChronoException(String msg) {
        super(msg);

    }

    /**
     * Creates a new instanceo of <code>ChronoException</code>
     * with given error message and the cause.
     *
     * @param   msg     detailed error message
     * @param   ex      cause
     */
    /*[deutsch]
     * Erzeugt eine neue Instanz von <code>ChronoException</code>
     * mit der angegebenen Detailmeldung und der Ursache.
     *
     * @param   msg     detailed error message
     * @param   ex      cause
     */
    public ChronoException(
        String msg,
        Exception ex
    ) {
        super(msg, ex);

    }

}
