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
 * <p>Type-safe query for format attributes which control the formatting
 * process. </p>
 *
 * @author  Meno Hochschild
 * @see     ChronoMerger#createFrom(ChronoEntity, AttributeQuery, boolean, boolean)
 */
/*[deutsch]
 * <p>Typsichere Abfrage von Formatattributen zur Steuerung eines
 * Formatier- oder Parse-Vorgangs. </p>
 *
 * @author  Meno Hochschild
 * @see     ChronoMerger#createFrom(ChronoEntity, AttributeQuery, boolean, boolean)
 */
public interface AttributeQuery {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Queries if a format attribute exists for given key. </p>
     *
     * @param   key     attribute key
     * @return  {@code true} if attribute exists else {@code false}
     */
    /*[deutsch]
     * <p>Ermittelt, ob ein Formatattribut zum angegebenen Schl&uuml;ssel
     * existiert. </p>
     *
     * @param   key     attribute key
     * @return  {@code true} if attribute exists else {@code false}
     */
    boolean contains(AttributeKey<?> key);

    /**
     * <p>Yields a format attribute for given key. </p>
     *
     * @param   <A> generic type of attribute value
     * @param   key     attribute key
     * @return  attribute value
     * @throws  java.util.NoSuchElementException if attribute does not exist
     */
    /*[deutsch]
     * <p>Ermittelt ein Formatattribut zum angegebenen Schl&uuml;ssel. </p>
     *
     * @param   <A> generic type of attribute value
     * @param   key     attribute key
     * @return  attribute value
     * @throws  java.util.NoSuchElementException if attribute does not exist
     */
    <A> A get(AttributeKey<A> key);

    /**
     * <p>Yields a format attribute for given key. </p>
     *
     * @param   <A> generic type of attribute value
     * @param   key             attribute key
     * @param   defaultValue    replacement value to be used if attribute does
     *                          not exist
     * @return  attribute value
     */
    /*[deutsch]
     * <p>Ermittelt ein Formatattribut zum angegebenen Schl&uuml;ssel. </p>
     *
     * @param   <A> generic type of attribute value
     * @param   key             attribute key
     * @param   defaultValue    replacement value to be used if attribute does
     *                          not exist
     * @return  attribute value
     */
    <A> A get(
        AttributeKey<A> key,
        A defaultValue
    );

}
