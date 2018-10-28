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

import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * <p>Das Element f&uuml;r die Genauigkeit einer Uhrzeitangabe. </p>
 *
 * @author  Meno Hochschild
 */
class PrecisionElement<U extends Comparable<U>>
    implements ChronoElement<U> {

    //~ Statische Felder/Initialisierungen --------------------------------

    static final ChronoElement<ClockUnit> CLOCK_PRECISION =
        new PrecisionElement<ClockUnit>(ClockUnit.class, ClockUnit.HOURS, ClockUnit.NANOS);
    static final ChronoElement<TimeUnit> TIME_PRECISION =
        new PrecisionElement<TimeUnit>(TimeUnit.class, TimeUnit.DAYS, TimeUnit.NANOSECONDS);

    //~ Instanzvariablen --------------------------------------------------

    private final Class<U> type;
    private transient final U min;
    private transient final U max;

    //~ Konstruktoren -----------------------------------------------------

    private PrecisionElement(
        Class<U> type,
        U min,
        U max
    ) {
        super();

        this.type = type;
        this.min = min;
        this.max = max;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public String name() {

        return "PRECISION";

    }

    @Override
    public Class<U> getType() {

        return this.type;

    }

    @Override
    public U getDefaultMinimum() {

        return this.min;

    }

    @Override
    public U getDefaultMaximum() {

        return this.max;

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
    public int compare(
        ChronoDisplay o1,
        ChronoDisplay o2
    ) {

        U u1 = o1.get(this);
        U u2 = o2.get(this);

        if (this.type == ClockUnit.class) {
            return u1.compareTo(u2);
        } else {
            return u2.compareTo(u1);
        }

    }

    @Override
    public String getDisplayName(Locale language) {

        return this.name();

    }

    @Override
    public char getSymbol() {

        return '\u0000';

    }

    @Override
    public boolean isLenient() {

        return false;

    }

    /**
     * @serialData  serves for singleton semantic
     * @return      replacement object
     */
    private Object readResolve() {

        return ((this.type == ClockUnit.class) ? CLOCK_PRECISION : TIME_PRECISION);

    }

}
