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
import net.time4j.engine.ChronoEntity;


/**
 * <p>A chronological element which allows a numerical representation. </p>
 *
 * <p>This element interface is only relevant for enum-like elements. </p>
 *
 * @param   <V> generic type of element values (will be adjusted to enum-supertype in next major release)
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Repr&auml;sentiert ein chronologisches Element, das eine numerische
 * Darstellung erlaubt. </p>
 *
 * <p>Dieses Element-Interface ist nur f&uuml;r Enum-Elemente relevant. </p>
 *
 * @param   <V> generic type of element values (will be adjusted to enum-supertype in next major release)
 * @author  Meno Hochschild
 */
public interface NumericalElement<V> // TODO: change V to V extends Enum<V> in next major release
    extends ChronoElement<V> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Translates given element value to a numerical integer. </p>
     *
     * <p>Will be called by the default methods {@link #parseFromInt(ChronoEntity, int)}
     * or {@link #printToInt(Object, ChronoDisplay, AttributeQuery)}. The integer {@code Integer.MIN_VALUE}
     * must be avoided in this conversion. </p>
     *
     * @param   value       value to be converted to int
     * @return  Integer-representation of given value
     */
    /*[deutsch]
     * <p>Ermittelt eine numerische Darstellung des angegebenen Werts. </p>
     *
     * <p>Wird von den Standardimplementierungen der Methoden {@link #parseFromInt(ChronoEntity, int)}
     * oder {@link #printToInt(Object, ChronoDisplay, AttributeQuery)} aufgerufen. Der Integerwert
     * {@code Integer.MIN_VALUE} mu&szlig; vermieden werden. </p>
     *
     * @param   value       value to be converted to int
     * @return  Integer-representation of given value
     */
    int numerical(V value);

    /**
     * <p>Translates given element value to a numerical integer. </p>
     *
     * <p>Will be called when element values need to be printed as numbers. The default
     * implementation just delegates to {@link #numerical(Object) numerical(V)}. </p>
     *
     * @param   value       value to be converted to int
     * @param   context     the object to be formatted
     * @param   attributes  format attributes
     * @return  Integer-representation of given value
     * @since   3.37/4.32
     */
    /*[deutsch]
     * <p>Ermittelt eine numerische Darstellung des angegebenen Werts. </p>
     *
     * <p>Wird aufgerufen, wenn Elementwerte als Zahlen ausgegeben werden m&uuml;ssen. Die
     * Standardimplementierung delegiert einfach an {@link #numerical(Object) numerical(V)}. </p>
     *
     * @param   value       value to be converted to int
     * @param   context     the object to be formatted
     * @param   attributes  format attributes
     * @return  Integer-representation of given value
     * @since   3.37/4.32
     */
    int printToInt(
        V value,
        ChronoDisplay context,
        AttributeQuery attributes
    );

    /**
     * <p>Converts and stores given integer into the result buffer. </p>
     *
     * <p>Will be called when int values need to be interpreted as enums. The default
     * implementation just delegates to {@link #numerical(Object) numerical(V)}. </p>
     *
     * @param   entity  mutable result buffer for parsed values
     * @param   value   parsed integer
     * @return  {@code true} if the integer can be interpreted else {@code false}
     * @since   3.37/4.32
     */
    /*[deutsch]
     * <p>Konvertiert und speichert den angegebenen Integerwert in den Ergebnispuffer. </p>
     *
     * <p>Wird aufgerufen, wenn int-Werte als Enums interpretiert werden m&uuml;ssen. Die
     * Standardimplementierung delegiert einfach an {@link #numerical(Object) numerical(V)}. </p>
     *
     * @param   entity  mutable result buffer for parsed values
     * @param   value   parsed integer
     * @return  {@code true} if the integer can be interpreted else {@code false}
     * @since   3.37/4.32
     */
    boolean parseFromInt(
        ChronoEntity<?> entity,
        int value
    );

}
