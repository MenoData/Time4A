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

package net.time4j.history.internal;

import net.time4j.engine.ChronoElement;
import net.time4j.format.DisplayElement;

import java.io.ObjectStreamException;


/**
 * <p>Allgemeines verstellbares chronologisches Element auf Integer-Basis. </p>
 *
 * @author  Meno Hochschild
 * @since   3.16/4.13
 */
public class StdHistoricalElement
    extends DisplayElement<Integer> {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Spezialelement zur Identifikation des angezeigten historischen Jahres. </p>
     */
    public static final ChronoElement<Integer> YEAR_OF_DISPLAY =
        new StdHistoricalElement("YEAR_OF_DISPLAY", '\u0000', 1, 9999);

    private static final long serialVersionUID = 1L;

    //~ Instanzvariablen --------------------------------------------------

    private transient final char symbol;
    private transient final Integer defaultMin;
    private transient final Integer defaultMax;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Default constructor. </p>
     *
     * @param   name        element name
     * @param   symbol      format symbol
     * @param   defaultMin  default minimum value
     * @param   defaultMax  default maximum value
     */
    protected StdHistoricalElement(
        String name,
        char symbol,
        int defaultMin,
        int defaultMax
    ) {
        super(name);

        this.symbol = symbol;
        this.defaultMin = Integer.valueOf(defaultMin);
        this.defaultMax = Integer.valueOf(defaultMax);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public final Class<Integer> getType() {

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
    protected boolean isSingleton() {

        return true;

    }

    private Object readResolve() throws ObjectStreamException {

        return YEAR_OF_DISPLAY;

    }

}
