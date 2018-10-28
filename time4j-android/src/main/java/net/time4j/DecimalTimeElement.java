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

import net.time4j.engine.BasicElement;
import net.time4j.engine.ChronoFunction;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.math.BigDecimal;


/**
 * <p>Ein dezimales Uhrzeitelement. </p>
 *
 * @author      Meno Hochschild
 */
final class DecimalTimeElement
    extends BasicElement<BigDecimal>
    implements ZonalElement<BigDecimal> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -4837430960549551204L;

    //~ Instanzvariablen --------------------------------------------------

    private transient final BigDecimal defaultMax;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Erzeugt eine neue Instanz. </p>
     *
     * @param   name        name of element
     * @param   defaultMax  default maximum
     */
    DecimalTimeElement(
        String name,
        BigDecimal defaultMax
    ) {
        super(name);

        this.defaultMax = defaultMax;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<BigDecimal> getType() {

        return BigDecimal.class;

    }

    @Override
    public BigDecimal getDefaultMinimum() {

        return BigDecimal.ZERO;

    }

    @Override
    public BigDecimal getDefaultMaximum() {

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
    public ChronoFunction<Moment, BigDecimal> inStdTimezone() {

        return this.in(Timezone.ofSystem());

    }

    @Override
    public ChronoFunction<Moment, BigDecimal> inTimezone(TZID tzid) {

        return this.in(Timezone.of(tzid));

    }

    @Override
    public ChronoFunction<Moment, BigDecimal> in(Timezone tz) {

        return new ZonalQuery<BigDecimal>(this, tz);

    }

    @Override
    public ChronoFunction<Moment, BigDecimal> atUTC() {

        return this.at(ZonalOffset.UTC);

    }

    @Override
    public ChronoFunction<Moment, BigDecimal> at(ZonalOffset offset) {

        return new ZonalQuery<BigDecimal>(this, offset);

    }

    @Override
    protected boolean isSingleton() {

        return true;

    }

    private Object readResolve() throws ObjectStreamException {

        Object element = PlainTime.lookupElement(this.name());

        if (element == null) {
            throw new InvalidObjectException(this.name());
        } else {
            return element;
        }

    }

}
