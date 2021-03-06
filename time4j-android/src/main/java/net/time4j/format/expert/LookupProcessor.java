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
import net.time4j.format.Attributes;

import java.io.IOException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


/**
 * <p>Text-Formatierung eines chronologischen Elements mit Hilfe von
 * String-Ressourcen. </p>
 *
 * @param   <V> generic type of element values
 * @author  Meno Hochschild
 * @since   3.0
 */
final class LookupProcessor<V>
    implements FormatProcessor<V> {

    //~ Instanzvariablen ----------------------------------------------

    private final ChronoElement<V> element;
    private final Map<V, String> resources;

    // quick path optimization
    private final int protectedLength;
    private final boolean caseInsensitive;
    private final Locale locale;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * @param   element     element to be formatted
     * @param   resources   text resources
     * @throws  IllegalArgumentException if there not enough text resources to match all values of an enum element type
     */
    LookupProcessor(
        ChronoElement<V> element,
        Map<V, String> resources
    ) {
        super();

        Map<V, String> tmp;
        Class<V> keyType = element.getType();

        if (keyType.isEnum()) {
            if (resources.size() < keyType.getEnumConstants().length) {
                throw new IllegalArgumentException("Not enough text resources defined for enum: " + keyType.getName());
            }
            tmp = createMap(keyType);
        } else {
            tmp = new HashMap<V, String>(resources.size());
        }

        tmp.putAll(resources);
        this.element = element;
        this.resources = Collections.unmodifiableMap(tmp);

        this.protectedLength = 0;
        this.caseInsensitive = true;
        this.locale = Locale.getDefault();

    }

    private LookupProcessor(
        ChronoElement<V> element,
        Map<V, String> resources,
        int protectedLength,
        boolean caseInsensitive,
        Locale locale
    ) {
        super();

        this.element = element;
        this.resources = resources;

        // quick path members
        this.protectedLength = protectedLength;
        this.caseInsensitive = caseInsensitive;
        this.locale = locale;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public int print(
        ChronoDisplay formattable,
        Appendable buffer,
        AttributeQuery attributes,
        Set<ElementPosition> positions,
        boolean quickPath
    ) throws IOException {

        if (buffer instanceof CharSequence) {
            CharSequence cs = (CharSequence) buffer;
            int offset = cs.length();
            int printed = this.print(formattable, buffer);
            if (positions != null) {
                positions.add(new ElementPosition(this.element, offset, cs.length()));
            }
            return printed;
        } else {
            return this.print(formattable, buffer);
        }

    }

    @Override
    public void parse(
        CharSequence text,
        ParseLog status,
        AttributeQuery attributes,
        ParsedEntity<?> parsedResult,
        boolean quickPath
    ) {

        int start = status.getPosition();
        int len = text.length();
        int protectedChars = (quickPath ? this.protectedLength : attributes.get(Attributes.PROTECTED_CHARACTERS, 0));

        if (protectedChars > 0) {
            len -= protectedChars;
        }

        if (start >= len) {
            status.setError(start, "Missing chars for: " + this.element.name());
            status.setWarning();
            return;
        }

        boolean ignoreCase = (
            quickPath
                ? this.caseInsensitive
                : attributes.get(Attributes.PARSE_CASE_INSENSITIVE, Boolean.TRUE).booleanValue());
        Locale loc = (quickPath ? this.locale : attributes.get(Attributes.LANGUAGE, Locale.getDefault()));
        int maxCount = len - start;

        for (V value : this.resources.keySet()) {
            String test = this.getString(value);

            if (ignoreCase) {
                String upper = test.toUpperCase(loc);
                int count = test.length();

                if (count <= maxCount) {
                    String s = text.subSequence(start, start + count).toString().toUpperCase(loc);

                    if (upper.equals(s)) {
                        parsedResult.put(this.element, value);
                        status.setPosition(start + count);
                        return;
                    }
                }
            } else {
                int count = test.length();

                if (count <= maxCount) {
                    CharSequence cs = text.subSequence(start, start + count);

                    if (test.equals(cs.toString())) {
                        parsedResult.put(this.element, value);
                        status.setPosition(start + count);
                        return;
                    }
                }
            }
        }

        status.setError(start, "Element value could not be parsed: " + this.element.name());

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof LookupProcessor) {
            LookupProcessor<?> that = (LookupProcessor) obj;
            return (
                this.element.equals(that.element)
                && this.resources.equals(that.resources)
            );
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return (
            7 * this.element.hashCode()
            + 31 * this.resources.hashCode()
        );

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(512);
        sb.append(this.getClass().getName());
        sb.append("[element=");
        sb.append(this.element.name());
        sb.append(", resources=");
        sb.append(this.resources);
        sb.append(']');
        return sb.toString();

    }

    @Override
    public ChronoElement<V> getElement() {

        return this.element;

    }

    @Override
    public FormatProcessor<V> withElement(ChronoElement<V> element) {

        if (this.element == element) {
            return this;
        }

        return new LookupProcessor<V>(element, this.resources);

    }

    @Override
    public boolean isNumerical() {

        return false;

    }

    @Override
    public FormatProcessor<V> quickPath(
        ChronoFormatter<?> formatter,
        AttributeQuery attributes,
        int reserved
    ) {

        return new LookupProcessor<V>(
            this.element,
            this.resources,
            attributes.get(Attributes.PROTECTED_CHARACTERS, Integer.valueOf(0)).intValue(),
            attributes.get(Attributes.PARSE_CASE_INSENSITIVE, Boolean.TRUE).booleanValue(),
            attributes.get(Attributes.LANGUAGE, Locale.getDefault())
        );

    }

    private int print(
        ChronoDisplay formattable,
        Appendable buffer
    ) throws IOException {

        V value = formattable.get(this.element);
        String text = this.getString(value);
        buffer.append(text);
        return text.length();

    }

    private String getString(V value) {

        String test = this.resources.get(value);

        if (test == null) { // Ersatzwert, wenn keine Ressource da ist
            test = value.toString();
        }

        return test;

    }

    @SuppressWarnings("unchecked")
    private static <V, K extends Enum<K>> Map<V, String> createMap(Class<V> keyType) {

        Class<K> clazz = (Class<K>) keyType;
        return (Map<V, String>) new EnumMap<K, String>(clazz);

    }

}
