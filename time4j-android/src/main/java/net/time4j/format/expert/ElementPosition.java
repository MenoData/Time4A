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

import net.time4j.engine.ChronoElement;


/**
 * <p>Represents a position information of a chronological element
 * with a formatted text. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine Positionsinformation eines chronologischen
 * Elements in einem formatierten Text. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
public final class ElementPosition {

    //~ Instanzvariablen --------------------------------------------------

    private final ChronoElement<?> element;
    private final int startIndex;
    private final int endIndex;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Creates a new instance of an {@code ElementPosition}. </p>
     *
     * <p>The section {@code text.substring(startIndex, endIndex)} of the
     * formatted text referring to given element denotes the
     * element-specific text. </p>
     *
     * @param   element     chronological element
     * @param   startIndex  index in formatted text which indicates the
     *                      starting position of associated element (inclusive)
     * @param   endIndex    index in formatted text which indicates the
     *                      end position of associated element (exclusive)
     * @throws  IllegalArgumentException if the start index is negative or
     *          after the end index
     */
    /*[deutsch]
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * <p>Im formatierten Text, der dem angegebenen Element entspricht, ist der
     * Abschnitt {@code text.substring(startIndex, endIndex)} der zugeordnete
     * Text. </p>
     *
     * @param   element     chronological element
     * @param   startIndex  index in formatted text which indicates the
     *                      starting position of associated element (inclusive)
     * @param   endIndex    index in formatted text which indicates the
     *                      end position of associated element (exclusive)
     * @throws  IllegalArgumentException if the start index is negative or
     *          after the end index
     */
    public ElementPosition(
        ChronoElement<?> element,
        int startIndex,
        int endIndex
    ) {
        super();

        if (element == null) {
            throw new NullPointerException("Missing chronological element.");
        } else if (startIndex < 0) {
            throw new IllegalArgumentException(
                "Negative start index: " + startIndex
                + " (" + element.name() + ")");
        } else if (endIndex <= startIndex) {
            throw new IllegalArgumentException(
                "End index " + endIndex
                + " must be greater than start index " + startIndex
                + " (" + element.name() + ")");
        }

        this.element = element;
        this.startIndex = startIndex;
        this.endIndex = endIndex;

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the formatted chronolocial element. </p>
     *
     * @return  {@code ChronoElement}
     */
    /*[deutsch]
     * <p>Liefert das formatierte chronologische Element. </p>
     *
     * @return  {@code ChronoElement}
     */
    public ChronoElement<?> getElement() {

        return this.element;

    }

    /**
     * <p>Yields the start index of associated formatted text. </p>
     *
     * @return  int
     */
    /*[deutsch]
     * <p>Liefert den Startindex des assoziierten formatierten Texts. </p>
     *
     * @return  int
     */
    public int getStartIndex() {

        return this.startIndex;

    }

    /**
     * <p>Yields the end index of associated formatted text. </p>
     *
     * @return  int
     */
    /*[deutsch]
     * <p>Liefert den Endindex des assoziierten formatierten Texts. </p>
     *
     * @return  int
     */
    public int getEndIndex() {

        return this.endIndex;

    }

    /**
     * <p>Compares element, start index and end index. </p>
     */
    /*[deutsch]
     * <p>Vergleicht Element, Start- und Endindex. </p>
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof ElementPosition) {
            ElementPosition that = (ElementPosition) obj;
            return (
                this.element.equals(that.element)
                && (this.startIndex == that.startIndex)
                && (this.endIndex == that.endIndex)
            );
        } else {
            return false;
        }

    }

    /*[deutsch]
     * <p>Berechnet den Hash-Code. </p>
     */
    @Override
    public int hashCode() {

        return (
            this.element.hashCode()
            + 37 * (this.startIndex | (this.endIndex << 16)));

    }

    /**
     * <p>For debugging purposes. </p>
     */
    /*[deutsch]
     * <p>F&uuml;r Debugging-Zwecke. </p>
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(80);
        sb.append(this.getClass().getName());
        sb.append("[element=");
        sb.append(this.element.name());
        sb.append(",start-index=");
        sb.append(this.startIndex);
        sb.append(",end-index=");
        sb.append(this.endIndex);
        sb.append(']');
        return sb.toString();

    }

}
