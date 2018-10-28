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

package net.time4j.format;

import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoException;

import java.io.IOException;
import java.text.ParsePosition;


/**
 * <p>A chronological element which can be formatted as text or can be parsed
 * from a text. </p>
 *
 * @param   <V> generic type of element values
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Repr&auml;sentiert ein chronologisches Element, das als Text dargestellt
 * und interpretiert werden kann. </p>
 *
 * @param   <V> generic type of element values
 * @author  Meno Hochschild
 */
public interface TextElement<V>
    extends ChronoElement<V> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Converts the element value in given context to a formatted text. </p>
     *
     * <p>Implementation note: The concrete element value is obtainable by the
     * expression {@link ChronoDisplay#get(ChronoElement) context.get(this)}.
     * </p>
     *
     * @param   context     time context with the value of this element
     * @param   buffer      format buffer any text output will be sent to
     * @param   attributes  query for control attributes
     * @throws  IOException if writing to buffer fails
     * @throws  ChronoException if there is no suitable element rule for evaluating the value
     */
    /*[deutsch]
     * <p>Wandelt dieses im angegebenen Zeitwertkontext enthaltene Element zu
     * einem Text um. </p>
     *
     * <p>Implementierungshinweis: Der konkrete Elementwert ist durch den
     * Ausdruck {@link ChronoDisplay#get(ChronoElement) context.get(this)}
     * gegeben. </p>
     *
     * @param   context     time context with the value of this element
     * @param   buffer      format buffer any text output will be sent to
     * @param   attributes  query for control attributes
     * @throws  IOException if writing to buffer fails
     * @throws  ChronoException if there is no suitable element rule for evaluating the value
     */
    void print(
        ChronoDisplay context,
        Appendable buffer,
        AttributeQuery attributes
    ) throws IOException, ChronoException;

    /**
     * <p>Interpretes the given text as element value. </p>
     *
     * <p>Implementation note: Any implementation will start first at the
     * position {@link ParsePosition#getIndex() status.getIndex()} and
     * either set the new position after successful parsing or return
     * {@code null} in case of error. </p>
     *
     * @param   text        text to be parsed
     * @param   status      current parsing position
     * @param   attributes  query for control attributes
     * @return  parsed element value or {@code null} if parsing
     *          was not successful
     */
    /*[deutsch]
     * <p>Interpretiert den angegebenen Text ab einer bestimmten Position
     * als Elementwert. </p>
     *
     * <p>Implementierungshinweis: Eine Implementierung wird den Text
     * erst ab der angegebenen Position {@link ParsePosition#getIndex()
     * status.getIndex()} auswerten und nach erfolgreicher Interpretierung
     * den Index neu setzen oder im Fehlerfall {@code null} zur&uuml;ckgeben.
     * </p>
     *
     * @param   text        text to be parsed
     * @param   status      current parsing position
     * @param   attributes  query for control attributes
     * @return  parsed element value or {@code null} if parsing
     *          was not successful
     */
    V parse(
        CharSequence text,
        ParsePosition status,
        AttributeQuery attributes
    );

}
