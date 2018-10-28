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

package net.time4j;

import net.time4j.engine.ChronoOperator;


/**
 * <p>Spezial-Operator f&uuml;r das Setzen von Werten. </p>
 *
 * @author  Meno Hochschild
 * @param   <T> generic target type (usually {@code PlainTimestamp})
 */
final class ValueOperator<T>
    implements ChronoOperator<T> {

    //~ Instanzvariablen --------------------------------------------------

    private final ChronoOperator<T> delegate;
    private final Object value;

    //~ Konstruktoren -----------------------------------------------------

    private ValueOperator(
        ChronoOperator<T> delegate,
        Object value
    ) {
        super();

        this.delegate = delegate;
        this.value = value;

    }

    //~ Methoden ----------------------------------------------------------

    static <T> ValueOperator of(
        ChronoOperator<T> delegate,
        Object value
    ) {

        return new ValueOperator<T>(delegate, value);

    }

    @Override
    public T apply(T entity) {

        return this.delegate.apply(entity);

    }

    /**
     * <p>Liefert den Wert, der neu gesetzt werden soll. </p>
     *
     * @return  new value which shall be set in standard or lenient mode
     */
    Object getValue() {

        return this.value;

    }

}
