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
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoOperator;


/**
 * <p>Spezialoperator zum Navigieren zu bestimmten Elementwerten. </p>
 *
 * @param       <V> generic enum type of element values
 * @author      Meno Hochschild
 */
final class NavigationOperator<V extends Enum<V>>
    extends ElementOperator<PlainDate> {

    //~ Instanzvariablen --------------------------------------------------

    private final V value;
    private final int len;
    private final ChronoOperator<PlainTimestamp> navTS;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert eine neue Instanz. </p>
     *
     * @param   element     reference element
     * @param   mode        navigation mode
     * @param   value       target value of navigation
     */
    NavigationOperator(
        ChronoElement<V> element,
        int mode,
        V value
    ) {
        super(element, mode);

        if (value == null) {
            throw new NullPointerException("Missing value.");
        }

        this.value = value;
        this.len = element.getType().getEnumConstants().length;

        this.navTS =
            new ChronoOperator<PlainTimestamp>() {
                @Override
                public PlainTimestamp apply(PlainTimestamp entity) {
                    return doApply(entity);
                }
            };

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public PlainDate apply(PlainDate entity) {

        return this.doApply(entity);

    }

    @Override
    ChronoOperator<PlainTimestamp> onTimestamp() {

        return this.navTS;

    }

    private <T extends ChronoEntity<T>> T doApply(T entity) {

        if (entity.contains(PlainDate.CALENDAR_DATE)) {
            PlainDate date = entity.get(PlainDate.CALENDAR_DATE);
            Object enumValue = date.get(this.getElement());
            int oldOrdinal = Enum.class.cast(enumValue).ordinal();
            int newOrdinal = this.delta(oldOrdinal);

            if (newOrdinal == oldOrdinal) {
                return entity;
            } else {
                return entity.with(
                    PlainDate.CALENDAR_DATE,
                    date.plus(
                        (newOrdinal - oldOrdinal),
                        date.getChronology().getBaseUnit(this.getElement()))
                );
            }
        }

        String navigation;

        switch (this.getType()) {
            case OP_NAV_NEXT:
                navigation = "setToNext";
                break;
            case OP_NAV_PREVIOUS:
                navigation = "setToPrevious";
                break;
            case OP_NAV_NEXT_OR_SAME:
                navigation = "setToNextOrSame";
                break;
            case OP_NAV_PREVIOUS_OR_SAME:
                navigation = "setToPreviousOrSame";
                break;
            default:
                throw new AssertionError("Unknown: " + this.getType());
        }

        throw new ChronoException(
            navigation
            + "()-operation not supported on: "
            + this.getElement().name());

    }

    private int delta(int oldOrdinal) {

        int newOrdinal = this.value.ordinal();

        switch (this.getType()) {
            case OP_NAV_NEXT:
                if (newOrdinal <= oldOrdinal) {
                    newOrdinal += this.len;
                }
                break;
            case OP_NAV_PREVIOUS:
                if (newOrdinal >= oldOrdinal) {
                    newOrdinal -= this.len;
                }
                break;
            case OP_NAV_NEXT_OR_SAME:
                if (newOrdinal < oldOrdinal) {
                    newOrdinal += this.len;
                }
                break;
            case OP_NAV_PREVIOUS_OR_SAME:
                if (newOrdinal > oldOrdinal) {
                    newOrdinal -= this.len;
                }
                break;
            default:
                throw new AssertionError("Unknown: " + this.getType());
        }

        return newOrdinal;

    }

}
