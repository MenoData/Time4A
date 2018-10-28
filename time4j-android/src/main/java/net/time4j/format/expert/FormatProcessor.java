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
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoException;

import java.io.IOException;
import java.util.Set;


/**
 * <p>F&uuml;hrt das elementweise Parsen und Formatieren aus. </p>
 *
 * <p><strong>Specification:</strong> Implementations must be immutable. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
interface FormatProcessor<V> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Erzeugt eine Textausgabe und speichert sie im angegebenen Puffer. </p>
     *
     * @param   formattable     object to be formatted
     * @param   buffer          format buffer any text output will be sent to
     * @param   attributes      control attributes including sectional attributes
     * @param   positions       positions of elements in text (optional)
     * @param   quickPath       hint for using quick path
     * @return  count of printed characters or {@code Integer.MAX_VALUE} if unknown or {@code -1} if not successful
     * @throws  IllegalArgumentException if the object is not formattable
     * @throws  ChronoException if the object does not contain the element in question
     * @throws  IOException if writing into buffer fails
     */
    int print(
        ChronoDisplay formattable,
        Appendable buffer,
        AttributeQuery attributes,
        Set<ElementPosition> positions,
        boolean quickPath
    ) throws IOException;

    /**
     * <p>Interpretiert den angegebenen Text. </p>
     *
     * <p>Implementierungshinweis: Eine Implementierung wird den Text erst
     * ab der angegebenen Position {@code status.getPosition()} auswerten und
     * dann in der Statusinformation nach einer erfolgreichen Interpretierung
     * die aktuelle Position neu setzen oder im Fehlerfall den Fehlerindex auf
     * die fehlerhafte Stelle im Text setzen. </p>
     *
     * @param   text            text to be parsed
     * @param   status          parser information (always as new instance)
     * @param   attributes      control attributes including sectional attributes
     * @param   parsedResult    result buffer for parsed values
     * @param   quickPath       hint for using quick path
     */
    void parse(
        CharSequence text,
        ParseLog status,
        AttributeQuery attributes,
        ParsedEntity<?> parsedResult,
        boolean quickPath
    );

    /**
     * <p>Liefert das zugeh&ouml;rige chronologische Element,
     * falls definiert. </p>
     *
     * @return  element or {@code null} if not relevant
     */
    ChronoElement<V> getElement();

    /**
     * <p>Liefert eine Kopie mit dem neuen chronologischen Element. </p>
     *
     * @param   element     chronological element
     * @return  copy of this processor maybe modified
     */
    FormatProcessor<V> withElement(ChronoElement<V> element);

    /**
     * <p>Werden nur Zahlen verarbeitet? </p>
     *
     * @return  {@code true} if only digit processing happens else {@code false}
     */
    boolean isNumerical();

    /**
     * <p>Dient der Internalisierung der angegebenen Attribute, um ihren Zugriff durch den auf
     * Java-primitives zu ersetzen. </p>
     *
     * <p>Prozessoren, die keine Internalisierung vorgesehen haben, sondern die Attribute zur Laufzeit
     * abfragen, liefern einfach sich selbst zur&uuml;ck (this-pointer). Diese Methode wird nur nach dem
     * <i>build</i> des Formatierers oder bei Attribut&auml;nderungen als finaler Schritt aufgerufen. </p>
     *
     * @param   formatter       formatter holding global attributes
     * @param   attributes      control attributes including sectional attributes
     * @param   reserved        count of reserved characters (only relevant for numerical processors)
     * @return  copy of this processor maybe modified
     */
    FormatProcessor<V> quickPath(
        ChronoFormatter<?> formatter,
        AttributeQuery attributes,
        int reserved
    );

}
