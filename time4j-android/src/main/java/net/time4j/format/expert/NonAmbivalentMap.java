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

import java.util.HashMap;
import java.util.Map;


/**
 * <p>Spezialimplementierung einer {@code Map}, die kein &Uuml;berschreiben
 * von gespeicherten Eintr&auml;gen zul&auml;sst, wenn die Werte zu einem
 * Schl&uuml;ssel verschieden sind. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
class NonAmbivalentMap
    extends HashMap<ChronoElement<?>, Object> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = 1245025551222311435L;

    //~ Konstruktoren -----------------------------------------------------

    NonAmbivalentMap(Map<? extends ChronoElement<?>, ?> map) {
        super(map);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Object put(ChronoElement<?> key, Object value) {

        Object obj = super.put(key, value);

        if (
            (key == null)
            || (obj == null)
            || obj.equals(value)
        ) {
            return obj;
        } else {
            throw new AmbivalentValueException(key);
        }

    }

}
