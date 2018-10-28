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

import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * <p>Ermittelt eine Verh&auml;ltniszahl. </p>
 *
 * @author      Meno Hochschild
 */
final class ProportionalFunction
    implements ChronoFunction<ChronoEntity<?>, BigDecimal> {

    //~ Instanzvariablen --------------------------------------------------

    private final ChronoElement<? extends Number> element;
    private final boolean extendedRange;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Erzeugt eine neue Abfrage. </p>
     *
     * @param   element         element this query is related to
     * @param   extendedRange   is the range extended due to T24:00?
     */
    ProportionalFunction(
        ChronoElement<? extends Number> element,
        boolean extendedRange
    ) {
        super();

        this.element = element;
        this.extendedRange = extendedRange;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public BigDecimal apply(ChronoEntity<?> context) {

        long value = context.get(this.element).longValue();
        long min = context.getMinimum(this.element).longValue();
        long max = context.getMaximum(this.element).longValue();

        if (value > max) {
            value = max; // Schutz gegen Anomalien
        }

        if (value == min) {
            return BigDecimal.ZERO;
        }

        if (
            this.extendedRange
            && (context instanceof PlainTime)
            && !PlainTime.class.cast(context).hasReducedRange(this.element)
        ) {
            if (value == max) {
                return BigDecimal.ONE;
            }
            max--;
        }

        BigDecimal count = new BigDecimal(value - min).setScale(15);
        BigDecimal divisor = new BigDecimal(max - min + 1);

        return count.divide(divisor, RoundingMode.HALF_UP).stripTrailingZeros();

    }

}
