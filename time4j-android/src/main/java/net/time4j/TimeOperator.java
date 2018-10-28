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
import net.time4j.engine.ChronoOperator;
import net.time4j.engine.StdOperator;


/**
 * <p>Definiert eine Manipulation von Uhrzeitobjekten nach
 * dem Strategy-Entwurfsmuster. </p>
 *
 * @author      Meno Hochschild
 */
final class TimeOperator
    extends ElementOperator<PlainTime> {

    //~ Instanzvariablen --------------------------------------------------

    private final Object opDelegate;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * @param   element         element an operator will be applied on
     * @param   type            type of operator
     */
    TimeOperator(
        ChronoElement<?> element,
        int type
    ) {
        this(element, type, null);

    }

    /**
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * @param   element         element an operator will be applied on
     * @param   type            operator type
     * @param   value           lenient or new value of element
     */
    TimeOperator(
        final ChronoElement<?> element,
        final int type,
        final Object value // optional
    ) {
        super(element, type);

        switch (type) {
            case OP_NEW_VALUE:
                this.opDelegate = newValue(element, value);
                break;
            case OP_MINIMIZE:
                this.opDelegate = StdOperator.minimized(element);
                break;
            case OP_MAXIMIZE:
                this.opDelegate = StdOperator.maximized(element);
                break;
            case OP_DECREMENT:
                this.opDelegate = StdOperator.decremented(element);
                break;
            case OP_INCREMENT:
                this.opDelegate = StdOperator.incremented(element);
                break;
            case OP_FLOOR:
                this.opDelegate = child(element, false);
                break;
            case OP_CEILING:
                this.opDelegate = child(element, true);
                break;
            case OP_LENIENT:
                this.opDelegate = lenient(element, value);
                break;
            default:
                throw new AssertionError("Unknown: " + this.getType());
        }

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public PlainTime apply(PlainTime entity) {

        ChronoOperator<PlainTime> operator = (ChronoOperator<PlainTime>) this.opDelegate;
        return operator.apply(entity);

    }

    @Override
    @SuppressWarnings("unchecked")
    ChronoOperator<PlainTimestamp> onTimestamp() {

        return (ChronoOperator<PlainTimestamp>) this.opDelegate;

    }

    private static <V> Object newValue(
        ChronoElement<V> element,
        Object value
    ) {

        V v = element.getType().cast(value);
        return ValueOperator.of(StdOperator.newValue(v, element), value);

    }

    private static <V> Object lenient(
        ChronoElement<V> element,
        Object value
    ) {

        V v = element.getType().cast(value);
        return ValueOperator.of(StdOperator.setLenient(v, element), value);

    }

    private static <V, T extends ChronoEntity<T>> ChronoOperator<T> child(
        ChronoElement<V> element,
        boolean up
    ) {

        String compare = element.name();

        if (
            compare.equals("MILLI_OF_SECOND")
            || compare.equals("MILLI_OF_DAY")
        ) {
            return new FractionOperator<T>('3', up);
        } else if (
            compare.equals("MICRO_OF_SECOND")
            || compare.equals("MICRO_OF_DAY")
        ) {
            return new FractionOperator<T>('6', up);
        } else if (
            compare.equals("NANO_OF_SECOND")
            || compare.equals("NANO_OF_DAY")
        ) {
            return new FractionOperator<T>('9', up);
        }

        if (up) {
            return StdOperator.atCeiling(element);
        } else {
            return StdOperator.atFloor(element);
        }

    }

}
