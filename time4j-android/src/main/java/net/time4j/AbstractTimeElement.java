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

import net.time4j.engine.ChronoFunction;
import net.time4j.format.DisplayElement;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;


/**
 * <p>Abstrakte Basisklasse f&uuml;r Uhrzeit-Elemente, die bereits alle
 * Methoden des Interface {@code AdjustableElement} vordefiniert. </p>
 *
 * @param   <V> generic type of element values
 * @author  Meno Hochschild
 */
abstract class AbstractTimeElement<V extends Comparable<V>>
    extends DisplayElement<V>
    implements AdjustableElement<V, PlainTime> {

    //~ Instanzvariablen --------------------------------------------------

    private transient final ElementOperator<PlainTime> minimizer;
    private transient final ElementOperator<PlainTime> maximizer;

    //~ Konstruktoren -----------------------------------------------------

    AbstractTimeElement(String name) {
        super(name);

        this.minimizer = new TimeOperator(this, ElementOperator.OP_MINIMIZE);
        this.maximizer = new TimeOperator(this, ElementOperator.OP_MAXIMIZE);

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public ElementOperator<PlainTime> newValue(V value) {

        return new TimeOperator(this, ElementOperator.OP_NEW_VALUE, value);

    }

    @Override
    public ElementOperator<PlainTime> minimized() {

        return this.minimizer;

    }

    @Override
    public ElementOperator<PlainTime> maximized() {

        return this.maximizer;

    }

    @Override
    public ElementOperator<PlainTime> decremented() {

        return new TimeOperator(this, ElementOperator.OP_DECREMENT);

    }

    @Override
    public ElementOperator<PlainTime> incremented() {

        return new TimeOperator(this, ElementOperator.OP_INCREMENT);

    }

    @Override
    public ElementOperator<PlainTime> atFloor() {

        return new TimeOperator(this, ElementOperator.OP_FLOOR);

    }

    @Override
    public ElementOperator<PlainTime> atCeiling() {

        return new TimeOperator(this, ElementOperator.OP_CEILING);

    }

    public ElementOperator<PlainTime> setLenient(V value) {

        return new TimeOperator(this, ElementOperator.OP_LENIENT, value);

    }

    @Override
    public ChronoFunction<Moment, V> inStdTimezone() {

        return this.in(Timezone.ofSystem());

    }

    @Override
    public ChronoFunction<Moment, V> inTimezone(TZID tzid) {

        return this.in(Timezone.of(tzid));

    }

    @Override
    public ChronoFunction<Moment, V> in(Timezone tz) {

        return new ZonalQuery<V>(this, tz);

    }

    @Override
    public ChronoFunction<Moment, V> atUTC() {

        return this.at(ZonalOffset.UTC);

    }

    @Override
    public ChronoFunction<Moment, V> at(ZonalOffset offset) {

        return new ZonalQuery<V>(this, offset);

    }

}
