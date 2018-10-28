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

package net.time4j.format.expert;

import net.time4j.engine.AttributeQuery;


/**
 * <p>Interpretes a text as chronological entity. </p>
 *
 * @param   <T> generic type of chronological entity to be parsed
 * @author  Meno Hochschild
 * @since   3.0
 * @see     net.time4j.engine.ChronoEntity
 */
/*[deutsch]
 * <p>Interpretiert einen Text als Entit&auml;t. </p>
 *
 * @param   <T> generic type of chronological entity to be parsed
 * @author  Meno Hochschild
 * @since   3.0
 * @see     net.time4j.engine.ChronoEntity
 */
public interface ChronoParser<T> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Interpretes given text starting at the position defined in
     * parse-log. </p>
     *
     * <p>Implementation note: Any implementation will parse the text first
     * at the position {@code status.getPosition()} and then set the new
     * position in the parse log if successful. In case of error the
     * error index in the parse log will be updated instead. </p>
     *
     * @param   text        text to be parsed
     * @param   status      parser information (always as new instance)
     * @param   attributes  control attributes
     * @return  result or {@code null} if parsing does not work
     * @throws  IndexOutOfBoundsException if the start position is at end of
     *          text or even behind
     */
    /*[deutsch]
     * <p>Interpretiert den angegebenen Text ab der angegebenen Position. </p>
     *
     * <p>Implementierungshinweis: Eine Implementierung wird den Text erst
     * ab der angegebenen Position {@code status.getPosition()} auswerten und
     * dann in der Statusinformation nach einer erfolgreichen Interpretierung
     * die aktuelle Position neu setzen oder im Fehlerfall den Fehlerindex auf
     * die fehlerhafte Stelle im Text setzen. </p>
     *
     * @param   text        text to be parsed
     * @param   status      parser information (always as new instance)
     * @param   attributes  control attributes
     * @return  result or {@code null} if parsing does not work
     * @throws  IndexOutOfBoundsException if the start position is at end of
     *          text or even behind
     */
    T parse(
        CharSequence    text,
        ParseLog        status,
        AttributeQuery  attributes
    );

}
