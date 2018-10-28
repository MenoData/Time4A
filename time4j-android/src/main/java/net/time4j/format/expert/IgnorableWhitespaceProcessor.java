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

import java.io.IOException;
import java.util.Set;


/**
 * <p>Verarbeitet ignorierbare nicht-anzeigbare Zeichen. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
enum IgnorableWhitespaceProcessor
    implements FormatProcessor<Void> {

    //~ Statische Felder/Initialisierungen --------------------------------

    SINGLETON;

    //~ Methoden ----------------------------------------------------------

    @Override
    public int print(
        ChronoDisplay formattable,
        Appendable buffer,
        AttributeQuery attributes,
        Set<ElementPosition> positions,
        boolean quickPath
    ) throws IOException {

        buffer.append(' ');
        return 1;

    }

    @Override
    public void parse(
        CharSequence text,
        ParseLog status,
        AttributeQuery attributes,
        ParsedEntity<?> parsedResult,
        boolean quickPath
    ) {

        int offset = status.getPosition();

        while (offset < text.length()) {
            char c = text.charAt(offset);

            if (Character.isWhitespace(c)) {
                offset++;
            } else {
                break;
            }
        }

        status.setPosition(offset);

    }

    @Override
    public String toString() {

        return "{IGNORABLE_WHITE_SPACE}";

    }

    // optional
    @Override
    public ChronoElement<Void> getElement() {

        return null;

    }

    @Override
    public FormatProcessor<Void> withElement(ChronoElement<Void> element) {

        return this;

    }

    @Override
    public boolean isNumerical() {

        return false;

    }

    @Override
    public FormatProcessor<Void> quickPath(
        ChronoFormatter<?> formatter,
        AttributeQuery attributes,
        int reserved
    ) {

        return this;

    }

}
