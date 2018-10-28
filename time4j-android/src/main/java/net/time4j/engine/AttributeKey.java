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
 * <p>Defines a key for a format attribute as type-safe accessor. </p>
 *
 * <p>Attributes are constrained to types which are <i>immutable</i> and
 * serializable. If there are predefined attributes then the associated
 * keys are to be used via the corresponding constants in the class
 * {@link net.time4j.format.Attributes} instead of creating new ones. </p>
 *
 * @param   <A> generic immutable type of attribute value
 * @author  Meno Hochschild
 * @see     AttributeQuery
 */
/*[deutsch]
 * <p>Definiert einen Attributschl&uuml;ssel zum typsicheren Zugriff auf ein
 * Formatattribut. </p>
 *
 * <p>Attribute sind auf Typen beschr&auml;nkt, die <i>immutable</i> und
 * serialisierbar sind. Gibt es vordefinierte Attribute, so sind deren
 * Schl&uuml;ssel &uuml;ber die entsprechenden Konstanten in der Klasse
 * {@link net.time4j.format.Attributes} zu verwenden, nicht neue zu
 * erzeugen. </p>
 *
 * @param   <A> generic immutable type of attribute value
 * @author  Meno Hochschild
 * @see     AttributeQuery
 */
public interface AttributeKey<A> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Name of associated format attribute. </p>
     *
     * @return  String
     */
    /*[deutsch]
     * <p>Name des assoziierten Formatattributs. </p>
     *
     * @return  String
     */
    String name();

    /**
     * <p>Type of associated format attribute. </p>
     *
     * @return  Class
     */
    /*[deutsch]
     * <p>Typ des assoziierten Formatattributs. </p>
     *
     * @return  Class
     */
    Class<A> type();

}
