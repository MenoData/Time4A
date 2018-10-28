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
import net.time4j.engine.ChronoFunction;
import net.time4j.engine.ChronoOperator;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.math.BigDecimal;


/**
 * <p>Allgemeines verstellbares chronologisches Element auf Integer-Basis. </p>
 *
 * @author      Meno Hochschild
 */
final class IntegerDateElement
    extends AbstractDateElement<Integer>
    implements ProportionalElement<Integer, PlainDate> {

    //~ Statische Felder/Initialisierungen --------------------------------

    /** Element-Index */
    static final int YEAR = 14;
    /** Element-Index */
    static final int MONTH = 15;
    /** Element-Index */
    static final int DAY_OF_MONTH = 16;
    /** Element-Index */
    static final int DAY_OF_YEAR = 17;
    /** Element-Index */
    static final int DAY_OF_QUARTER = 18;

    private static final long serialVersionUID = -1337148214680014674L;

    //~ Instanzvariablen --------------------------------------------------

    private transient final int index;
    private transient final Integer defaultMin;
    private transient final Integer defaultMax;
    private transient final char symbol;
    private transient final ChronoFunction<ChronoEntity<?>, BigDecimal> rf;

    //~ Konstruktoren -----------------------------------------------------

    private IntegerDateElement(
        String name,
        int index,
        Integer defaultMin,
        Integer defaultMax,
        char symbol
    ) {
        super(name);

        this.index = index;
        this.defaultMin = defaultMin;
        this.defaultMax = defaultMax;
        this.symbol = symbol;
        this.rf = new ProportionalFunction(this, false);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Integer> getType() {

        return Integer.class;

    }

    @Override
    public char getSymbol() {

        return this.symbol;

    }

    @Override
    public Integer getDefaultMinimum() {

        return this.defaultMin;

    }

    @Override
    public Integer getDefaultMaximum() {

        return this.defaultMax;

    }

    @Override
    public boolean isDateElement() {

        return true;

    }

    @Override
    public boolean isTimeElement() {

        return false;

    }

    @Override
    public ChronoFunction<ChronoEntity<?>, BigDecimal> ratio() {

        return this.rf;

    }

    @Override
    public ChronoOperator<PlainDate> roundedUp(int stepwidth) {

        return new RoundingOperator<PlainDate>(
            this,
            Boolean.TRUE,
            stepwidth
        );

    }

    @Override
    public ChronoOperator<PlainDate> roundedHalf(int stepwidth) {

        return new RoundingOperator<PlainDate>(
            this,
            null,
            stepwidth
        );

    }

    @Override
    public ChronoOperator<PlainDate> roundedDown(int stepwidth) {

        return new RoundingOperator<PlainDate>(
            this,
            Boolean.FALSE,
            stepwidth
        );

    }

    @Override
    protected boolean isSingleton() {

        return true; // exists only once per name in PlainDate

    }

    /**
     * <p>Liefert einen Zugriffsindex zur Optimierung der Elementsuche. </p>
     *
     * @return  int
     */
    int getIndex() {

        return this.index;

    }

    /**
     * <p>Erzeugt ein neues Datumselement. </p>
     *
     * @param   name        name of element
     * @param   index       index of element
     * @param   dmin        default minimum
     * @param   dmax        default maximum
     * @param   symbol      format symbol
     * @return  new element instance
     */
    static IntegerDateElement create(
        String name,
        int index,
        int dmin,
        int dmax,
        char symbol
    ) {

        return new IntegerDateElement(
            name,
            index,
            Integer.valueOf(dmin),
            Integer.valueOf(dmax),
            symbol
        );

    }

    private Object readResolve() throws ObjectStreamException {

        Object element = PlainDate.lookupElement(this.name());

        if (element == null) {
            throw new InvalidObjectException(this.name());
        } else {
            return element;
        }

    }

}
