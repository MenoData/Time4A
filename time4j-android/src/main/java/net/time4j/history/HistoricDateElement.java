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

package net.time4j.history;

import net.time4j.PlainDate;
import net.time4j.base.GregorianMath;
import net.time4j.engine.BasicElement;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.Chronology;
import net.time4j.engine.ElementRule;

import java.io.ObjectStreamException;


/**
 * <p>Element f&uuml;r ein historisches Datumstupel. </p>
 *
 * @author  Meno Hochschild
 * @since   3.15/4.12
 */
final class HistoricDateElement
    extends BasicElement<HistoricDate> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -5386613740709845550L;

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  associated chronological history
     */
    private final ChronoHistory history;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert ein neues Element mit den angegebenen Details. </p>
     *
     * @param   history     associated chronological history
     */
    HistoricDateElement(ChronoHistory history) {
        super("HISTORIC_DATE");

        this.history = history;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<HistoricDate> getType() {

        return HistoricDate.class;

    }

    @Override
    public HistoricDate getDefaultMinimum() {

        return HistoricDate.of(HistoricEra.BC, 45, 1, 1);

    }

    @Override
    public HistoricDate getDefaultMaximum() {

        return HistoricDate.of(HistoricEra.AD, 9999, 12, 31);

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
    protected <T extends ChronoEntity<T>> ElementRule<T, HistoricDate> derive(Chronology<T> chronology) {

        if (chronology.isRegistered(PlainDate.COMPONENT)) {
            return new Rule<T>(this.history);
        }

        return null;

    }

    @Override
    protected boolean doEquals(BasicElement<?> obj) {

        return this.history.equals(((HistoricDateElement) obj).history);

    }

    private Object readResolve() throws ObjectStreamException {

        return this.history.date();

    }

    //~ Innere Klassen ----------------------------------------------------

    private static class Rule<C extends ChronoEntity<C>>
        implements ElementRule<C, HistoricDate> {

        //~ Instanzvariablen ----------------------------------------------

        private final ChronoHistory history;

        //~ Konstruktoren -------------------------------------------------

        Rule(ChronoHistory history) {
            super();

            this.history = history;

        }

        //~ Methoden ------------------------------------------------------

        @Override
        public HistoricDate getValue(C context) {

            try {
                return this.history.convert(context.get(PlainDate.COMPONENT));
            } catch (IllegalArgumentException iae) {
                throw new ChronoException(iae.getMessage(), iae);
            }

        }

        @Override
        public HistoricDate getMinimum(C context) {

            if (this.history == ChronoHistory.PROLEPTIC_BYZANTINE) {
                return HistoricDate.of(HistoricEra.BYZANTINE, 0, 9, 1);
            } else if (this.history == ChronoHistory.PROLEPTIC_JULIAN) {
                return HistoricDate.of(HistoricEra.BC, ChronoHistory.JULIAN_YMAX + 1, 1, 1);
            } else if (this.history == ChronoHistory.PROLEPTIC_GREGORIAN) {
                return HistoricDate.of(HistoricEra.BC, GregorianMath.MAX_YEAR + 1, 1, 1);
            } else {
                return HistoricDate.of(HistoricEra.BC, 45, 1, 1);
            }

        }

        @Override
        public HistoricDate getMaximum(C context) {

            if (this.history == ChronoHistory.PROLEPTIC_BYZANTINE) {
                return HistoricDate.of(HistoricEra.BYZANTINE, ChronoHistory.BYZANTINE_YMAX, 8, 31);
            } else if (this.history == ChronoHistory.PROLEPTIC_JULIAN) {
                return HistoricDate.of(HistoricEra.AD, ChronoHistory.JULIAN_YMAX, 12, 31);
            } else if (this.history == ChronoHistory.PROLEPTIC_GREGORIAN) {
                return HistoricDate.of(HistoricEra.AD, GregorianMath.MAX_YEAR, 12, 31);
            } else {
                return HistoricDate.of(HistoricEra.AD, 9999, 12, 31);
            }

        }

        @Override
        public boolean isValid(
            C context,
            HistoricDate value
        ) {

            return this.history.isValid(value);

        }

        @Override
        public C withValue(
            C context,
            HistoricDate value,
            boolean lenient
        ) {

            if (value != null) {
                PlainDate date = this.history.convert(value);
                return context.with(PlainDate.COMPONENT, date);
            }

            throw new IllegalArgumentException("Missing historic date.");

        }

        @Override
        public ChronoElement<?> getChildAtFloor(C context) {

            throw new UnsupportedOperationException("Never called.");

        }

        @Override
        public ChronoElement<?> getChildAtCeiling(C context) {

            throw new UnsupportedOperationException("Never called.");

        }

    }

}
