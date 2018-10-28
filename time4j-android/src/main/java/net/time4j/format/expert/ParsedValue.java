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
import net.time4j.engine.ChronoException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * <p>Definiert eine aktualisierbare Wertquelle mit nur einem Ergebniswert. </p>
 *
 * @author  Meno Hochschild
 * @since   3.26/4.22
 */
class ParsedValue
    extends ParsedEntity<ParsedValue> {

    //~ Instanzvariablen --------------------------------------------------

    private Object result;

    // other element-value-pairs which don't exist by default but can be used in extensions or mergers
    private Map<ChronoElement<?>, Object> map = null;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * Standard-Konstruktor.
     */
    ParsedValue() {
        super();

        this.result = null;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean contains(ChronoElement<?> element) {

        if ((element != null) && (this.map != null)) {
            return this.map.containsKey(element);
        }

        return false;

    }

    @Override
    public <V> V get(ChronoElement<V> element) {

        if (element == null) {
            throw new NullPointerException();
        }

        Map<ChronoElement<?>, Object> m = this.map;

        if ((m != null) && m.containsKey(element)) {
            return element.getType().cast(m.get(element));
        }

        throw new ChronoException("No value found for: " + element.name());

    }

    @Override
    public int getInt(ChronoElement<Integer> element) {

        if (element == null) {
            throw new NullPointerException();
        }

        Map<ChronoElement<?>, Object> m = this.map;

        if ((m != null) && m.containsKey(element)) {
            return element.getType().cast(m.get(element)).intValue();
        }

        return Integer.MIN_VALUE;

    }

    @Override
    public Set<ChronoElement<?>> getRegisteredElements() {

        if (this.map == null) {
            return Collections.emptySet();
        }

        return Collections.unmodifiableSet(this.map.keySet());

    }

    // called by format processors
    void put(ChronoElement<?> element, int v) {

        if (element == null) {
            throw new NullPointerException();
        }

        Map<ChronoElement<?>, Object> m = this.map;
        if (m == null) {
            m = new HashMap<ChronoElement<?>, Object>();
            this.map = m;
        }
        m.put(element, Integer.valueOf(v));

    }

    // called by format processors
    void put(ChronoElement<?> element, Object v) {

        if (element == null) {
            throw new NullPointerException();
        }

        if (v == null) { // removal
            if (this.map != null) {
                this.map.remove(element);
                if (this.map.isEmpty()) {
                    this.map = null;
                }
            }
        } else {
            Map<ChronoElement<?>, Object> m = this.map;
            if (m == null) {
                m = new HashMap<ChronoElement<?>, Object>();
                this.map = m;
            }
            m.put(element, v);
        }

    }

    @Override
    void setResult(Object entity) {

        this.result = entity;

    }

    @SuppressWarnings("unchecked")
    @Override
    <E> E getResult() {

        return (E) this.result;

    }

}
