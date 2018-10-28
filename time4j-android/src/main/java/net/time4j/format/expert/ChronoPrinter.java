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
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoFunction;

import java.io.IOException;


/**
 * <p>Prints a chronological entity. </p>
 *
 * @param   <T> generic type of chronological entity to be formatted
 * @author  Meno Hochschild
 * @since   3.0
 * @see     net.time4j.engine.ChronoEntity
 */
/*[deutsch]
 * <p>Erzeugt eine formatierte Ausgabe einer Entit&auml;t. </p>
 *
 * @param   <T> generic type of chronological entity to be formatted
 * @author  Meno Hochschild
 * @since   3.0
 * @see     net.time4j.engine.ChronoEntity
 */
public interface ChronoPrinter<T> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Creates a text output and writes it into given buffer. </p>
     *
     * <p>Note: Implementations have to call {@code query.apply(...)}
     * at the end to return a possibly meaningful result. An example
     * would be a query which produces just the identical input so
     * the result of printing a {@code Moment} will be the formatted
     * form of the original {@code Moment}. </p>
     *
     * @param   <R> generic result type
     * @param   formattable chronological entity to be formatted
     * @param   buffer      format buffer any text output will be sent to
     * @param   attributes  control attributes
     * @param   query       custom query returning any kind of result
     * @return  result of query
     * @throws  IllegalArgumentException if the object is not formattable
     * @throws  IOException if writing into buffer fails
     */
    /*[deutsch]
     * <p>Erzeugt eine Textausgabe und schreibt sie in den angegebenen
     * Puffer. </p>
     *
     * <p>Notiz: Implementierungen m&uuml;ssen schlie&szlig;lich
     * {@code query.apply(...)} aufrufen, um ein Ergebnis
     * zur&uuml;ckzugeben. Ein Beispiel w&auml;re eine Abfrage, die
     * einfach die identische Eingabe zur&uuml;ckgibt, so da&szlig;
     * das Ergebnis dieser Methode angewandt auf einen {@code Moment}
     * die vorformatierte Form des urspr&uuml;nglichen {@code Moment}
     * sein wird.</p>
     *
     * @param   <R> generic result type
     * @param   formattable chronological entity to be formatted
     * @param   buffer      format buffer any text output will be sent to
     * @param   attributes  control attributes
     * @param   query       custom query returning any kind of result
     * @return  result of query
     * @throws  IllegalArgumentException if the object is not formattable
     * @throws  IOException if writing into buffer fails
     */
    <R> R print(
        T formattable,
        Appendable buffer,
        AttributeQuery attributes,
        ChronoFunction<ChronoDisplay, R> query
    ) throws IOException;

}
