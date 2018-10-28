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

package net.time4j.format.internal;

import net.time4j.engine.AttributeKey;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.format.Attributes;
import net.time4j.format.NumberSystem;
import net.time4j.format.TextElement;

import java.io.IOException;
import java.text.ParsePosition;


/**
 * <p>Marks an integer-based element which can be text, too. </p>
 *
 * @author  Meno Hochschild
 * @since   3.32/4.27
 */
public interface DualFormatElement // ehemals: net.time4j.history.internal.HistorizedElement
    extends TextElement<Integer> {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Special format attribute for text elements which need to know the original pattern length. </p>
     */
    /*[deutsch]
     * <p>Spezialformatattribut f&uuml;r Textelemente, die die urspr&uuml;ngliche Symboll&auml;nge im Formatmuster
     * kennen m&uuml;ssen. </p>
     */
    AttributeKey<Integer> COUNT_OF_PATTERN_SYMBOLS = Attributes.createKey("COUNT_OF_PATTERN_SYMBOLS", Integer.class);

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Converts the element value in given context to a formatted text. </p>
     *
     * @param   context     time context with the value of this element
     * @param   buffer      format buffer any text output will be sent to
     * @param   attributes  query for control attributes
     * @param   numsys      number system
     * @param   zeroChar    zero digit
     * @param   minDigits   minimum count of digits
     * @param   maxDigits   maximum count of digits
     * @throws  IOException if writing to buffer fails
     * @throws  ChronoException if there is no suitable element rule for evaluating the value
     * @since   3.15/4.12
     */
    /*[deutsch]
     * <p>Wandelt dieses im angegebenen Zeitwertkontext enthaltene Element zu
     * einem Text um. </p>
     *
     * @param   context     time context with the value of this element
     * @param   buffer      format buffer any text output will be sent to
     * @param   attributes  query for control attributes
     * @param   numsys      number system
     * @param   zeroChar    zero digit
     * @param   minDigits   minimum count of digits
     * @param   maxDigits   maximum count of digits
     * @throws  IOException if writing to buffer fails
     * @throws  ChronoException if there is no suitable element rule for evaluating the value
     * @since   3.15/4.12
     */
    void print(
        ChronoDisplay context,
        Appendable buffer,
        AttributeQuery attributes,
        NumberSystem numsys,
        char zeroChar,
        int minDigits,
        int maxDigits
    ) throws IOException, ChronoException;

    /**
     * <p>Like {@link #parse(CharSequence, ParsePosition, AttributeQuery)} but can create an additional entry
     * in parsed values. </p>
     *
     * @param   text            text to be parsed
     * @param   status          current parsing position
     * @param   attributes      query for control attributes
     * @param   parsedResult    optional container for parsed values
     * @return  parsed element value or {@code null} if parsing was not successful
     * @since   3.16/4.13
     */
    /*[deutsch]
     * <p>Wie {@link #parse(CharSequence, ParsePosition, AttributeQuery)}, kann aber einen extra Eintrag
     * in den interpretierten Werten erzeugen. </p>
     *
     * @param   text            text to be parsed
     * @param   status          current parsing position
     * @param   attributes      query for control attributes
     * @param   parsedResult    optional container for parsed values
     * @return  parsed element value or {@code null} if parsing was not successful
     * @since   3.16/4.13
     */
    Integer parse(
        CharSequence text,
        ParsePosition status,
        AttributeQuery attributes,
        ChronoEntity<?> parsedResult
    );

}
