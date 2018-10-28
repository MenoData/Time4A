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
import net.time4j.engine.ChronoOperator;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.math.BigDecimal;


/**
 * <p>Repr&auml;sentiert ein Uhrzeitelement vom long-Typ. </p>
 *
 * @author      Meno Hochschild
 */
final class LongElement
    extends AbstractTimeElement<Long>
    implements ProportionalElement<Long, PlainTime> {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Zeiger auf den Tages&uuml;berlauf.
     */
    static final ChronoElement<Long> DAY_OVERFLOW = new LongElement();

    private static final long serialVersionUID = 5930990958663061693L;

    //~ Instanzvariablen --------------------------------------------------

    private transient final Long defaultMin;
    private transient final Long defaultMax;
    private transient final ChronoFunction<ChronoEntity<?>, BigDecimal> rf;

    //~ Konstruktoren -----------------------------------------------------

    private LongElement() {
        this("DAY_OVERFLOW", Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private LongElement(
        String name,
        long defaultMin,
        long defaultMax
    ) {
        super(name);

        this.defaultMin = Long.valueOf(defaultMin);
        this.defaultMax = Long.valueOf(defaultMax);

        this.rf = new ProportionalFunction(this, true);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Long> getType() {

        return Long.class;

    }

    @Override
    public Long getDefaultMinimum() {

        return this.defaultMin;

    }

    @Override
    public Long getDefaultMaximum() {

        return this.defaultMax;

    }

    @Override
    public boolean isDateElement() {

        return false;

    }

    @Override
    public boolean isTimeElement() {

        return true;

    }

    @Override
    public ChronoFunction<ChronoEntity<?>, BigDecimal> ratio() {

        return this.rf;

    }


    @Override
    public ChronoOperator<PlainTime> roundedUp(int stepwidth) {

        return new RoundingOperator<PlainTime>(
            this,
            Boolean.TRUE,
            stepwidth
        );

    }

    @Override
    public ChronoOperator<PlainTime> roundedHalf(int stepwidth) {

        return new RoundingOperator<PlainTime>(
            this,
            null,
            stepwidth
        );

    }

    @Override
    public ChronoOperator<PlainTime> roundedDown(int stepwidth) {

        return new RoundingOperator<PlainTime>(
            this,
            Boolean.FALSE,
            stepwidth
        );

    }

    /**
     * <p>Erzeugt ein neues Uhrzeitelement ohne Formatsymbol. </p>
     *
     * @param   name        name of element
     * @param   defaultMin  default minimum
     * @param   defaultMax  default maximum
     */
    static LongElement create(
        String name,
        long defaultMin,
        long defaultMax
    ) {

        return new LongElement(name, defaultMin, defaultMax);

    }

    private Object readResolve() throws ObjectStreamException {

        Object element = PlainTime.lookupElement(this.name());

        if (element == null) {
            if (this.name().equals("DAY_OVERFLOW")) {
                return DAY_OVERFLOW;
            } else {
                throw new InvalidObjectException(this.name());
            }
        } else {
            return element;
        }

    }

}
