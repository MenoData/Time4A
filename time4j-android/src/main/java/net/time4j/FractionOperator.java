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

import static net.time4j.PlainTime.NANO_OF_SECOND;


/**
 * <p>Spezialoperator f&uuml;r Sekundenbruchteile. </p>
 *
 * @param       <T> generic target type of this operator
 * @author      Meno Hochschild
 */
final class FractionOperator<T extends ChronoEntity<T>>
    implements ChronoOperator<T> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final int KILO = 1000;
    private static final int MIO = 1000000;

    //~ Instanzvariablen --------------------------------------------------

    private final char fraction;
    private final boolean up;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * @param   fraction    count of fractional digits (als char = 3, 6, 9)
     * @param   up          {@code true} if ceiling else {@code false}
     */
    FractionOperator(
        char fraction,
        boolean up
    ) {
        super();

        this.fraction = fraction;
        this.up = up;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public T apply(T entity) {

        if (this.fraction == '9') {
            return entity;
        }

        int nano = entity.get(NANO_OF_SECOND);
        int max = entity.getMaximum(NANO_OF_SECOND);

        switch (this.fraction) {
            case '3':
                nano = (nano / MIO) * MIO + (this.up ? 999999 : 0);
                return entity.with(NANO_OF_SECOND, Math.min(max, nano));
            case '6':
                nano = (nano / KILO) * KILO + (this.up ? 999 : 0);
                return entity.with(NANO_OF_SECOND, Math.min(max, nano));
            default:
                throw new UnsupportedOperationException(
                    "Unknown: " + this.fraction);
        }

    }

}
