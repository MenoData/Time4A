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

import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoOperator;


/**
 * <p>Rundungsoperator. </p>
 *
 * @author      Meno Hochschild
 */
final class RoundingOperator<T extends ChronoEntity<T>>
    implements ChronoOperator<T> {

    //~ Instanzvariablen --------------------------------------------------

    private final ProportionalElement<?, T> element;
    private final Boolean up;
    private final double stepwidth;
    private final boolean longBased;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruktor. </p>
     *
     * @param   element     referencing element
     * @param   up          {@code Boolean.TRUE} if ceiling mode,
     *                      {@code null} if half rounding
     *                      {@code Boolean.FALSE} if floor mode
     * @param   stepwidth   controls limits of rounding
     */
    RoundingOperator(
        final ProportionalElement<?, T> element,
        Boolean up,
        int stepwidth
    ) {
        super();

        this.element = element;
        this.up = up;
        this.stepwidth = stepwidth;
        this.longBased = element.getType().equals(Long.class);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public T apply(T entity) {

        double value = entity.get(this.element).doubleValue();
        double nv;

        if (this.up == null) {
            double high = Math.ceil(value / this.stepwidth) * this.stepwidth;
            double low = Math.floor(value / this.stepwidth) * this.stepwidth;
            nv = ((value - low < high - value) ? low : high);
        } else if (this.up.booleanValue()) {
            nv = Math.ceil(value / this.stepwidth) * this.stepwidth;
        } else {
            nv = Math.floor(value / this.stepwidth) * this.stepwidth;
        }

        Number num;

        if (this.longBased) {
            num = Long.valueOf((long) nv);
        } else {
            num = Integer.valueOf((int) nv);
        }

        return entity.with(lenient(this.element, num));

    }

    private static <V extends Number, T extends ChronoEntity<T>>
    ChronoOperator<T> lenient(
        ProportionalElement<V, T> element,
        Number num
    ) {

        return element.setLenient(element.getType().cast(num));

    }

}
