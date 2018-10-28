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

package net.time4j.calendar.service;

import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoOperator;


/**
 * <p>General integer-based date element. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
/*[deutsch]
 * <p>Allgemeines Integer-basiertes Datumselement. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
public class StdIntegerDateElement<T extends ChronoEntity<T>>
    extends StdDateElement<Integer, T> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -4975173343610190782L;

    //~ Instanzvariablen --------------------------------------------------

    private transient final int min;
    private transient final int max;
    private transient final ChronoOperator<T> decrementor;
    private transient final ChronoOperator<T> incrementor;

    //~ Konstruktoren -----------------------------------------------------

    public StdIntegerDateElement(
        String name,
        Class<T> chrono,
        int min,
        int max,
        char symbol
    ) {
        super(name, chrono, symbol, true);

        this.min = min;
        this.max = max;
        this.decrementor = null;
        this.incrementor = null;

    }

    public StdIntegerDateElement(
        String name,
        Class<T> chrono,
        int min,
        int max,
        char symbol,
        ChronoOperator<T> decrementor,
        ChronoOperator<T> incrementor
    ) {
        super(name, chrono, symbol, false);

        this.min = min;
        this.max = max;
        this.decrementor = decrementor;
        this.incrementor = incrementor;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Integer> getType() {

        return Integer.class;

    }

    @Override
    public Integer getDefaultMinimum() {

        return Integer.valueOf(this.min);

    }

    @Override
    public Integer getDefaultMaximum() {

        return Integer.valueOf(this.max);

    }

    @Override
    public ChronoOperator<T> decremented() {

        if (this.decrementor != null) {
            return this.decrementor;
        }

        return super.decremented();

    }

    @Override
    public ChronoOperator<T> incremented() {

        if (this.incrementor != null) {
            return this.incrementor;
        }

        return super.incremented();

    }

}
