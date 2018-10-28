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

import net.time4j.calendar.StdCalendarElement;
import net.time4j.engine.BasicElement;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoOperator;
import net.time4j.engine.Chronology;
import net.time4j.engine.EpochDays;
import net.time4j.engine.StdOperator;
import net.time4j.format.DisplayElement;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;


/**
 * <p>General date element. </p>
 *
 * <p>All operator methods are based on {@link StdOperator}. Concrete calendars will
 * just instantiate a subclass once and then assign the instance to a static constant
 * and finally register a rule for this element in a given chronology. Applications
 * will usually not directly use this class. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
/*[deutsch]
 * <p>Allgemeines Datumselement. </p>
 *
 * <p>Alle Operator-Methoden basieren auf {@link StdOperator}. Konkrete Kalender werden
 * einfach eine Subklasse einmalig instanzieren und dann die Instanz einer statischen
 * Konstanten zuweisen und schlie&szlig;lich eine Regel f&uuml;r dieses Element in
 * einer vorgegebenen Chronologie registrieren. Anwendungen werden normalerweise diese
 * Klasse nicht direkt verwenden. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
public abstract class StdDateElement<V extends Comparable<V>, T extends ChronoEntity<T>>
    extends DisplayElement<V>
    implements StdCalendarElement<V, T> {

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  associated chronological type which has registered this element
     */
    /*[deutsch]
     * @serial  assoziierter chronologischer Typ, der dieses Element registriert hat
     */
    private final Class<T> chrono;

    private transient final char symbol;
    private transient final boolean daywise;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>For subclasses. </p>
     *
     * @param   name        element name
     * @param   chrono      chronological type which registers this element
     * @param   symbol      format pattern symbol
     * @param   daywise     Is it a day-based element?
     */
    /*[deutsch]
     * <p>F&uuml;r Subklassen. </p>
     *
     * @param   name        element name
     * @param   chrono      chronological type which registers this element
     * @param   symbol      format pattern symbol
     * @param   daywise     Is it a day-based element?
     */
    public StdDateElement(
        String name,
        Class<T> chrono,
        char symbol,
        boolean daywise
    ) {
        super(name);

        this.chrono = chrono;
        this.symbol = symbol;
        this.daywise = daywise;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public char getSymbol() {

        return this.symbol;

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
    public ChronoOperator<T> minimized() {

        return StdOperator.minimized(this);

    }

    @Override
    public ChronoOperator<T> maximized() {

        return StdOperator.maximized(this);

    }

    @Override
    public ChronoOperator<T> decremented() {

        if (this.daywise) {
            return new DayOperator<T>(true);
        }

        return StdOperator.decremented(this);

    }

    @Override
    public ChronoOperator<T> incremented() {

        if (this.daywise) {
            return new DayOperator<T>(false);
        }

        return StdOperator.incremented(this);

    }

    @Override
    public ChronoOperator<T> atFloor() {

        return StdOperator.atFloor(this);

    }

    @Override
    public ChronoOperator<T> atCeiling() {

        return StdOperator.atCeiling(this);

    }

    @Override
    protected boolean doEquals(BasicElement<?> obj) {

        StdDateElement<?, ?> that = (StdDateElement<?, ?>) obj;
        return (this.chrono == that.chrono);

    }

    /**
     * @serialData  preserves singleton semantic
     * @return      resolved singleton
     * @throws      ObjectStreamException if resolving fails
     */
    protected Object readResolve() throws ObjectStreamException {

        String comp = this.name();

        for (ChronoElement<?> element : Chronology.lookup(this.chrono).getRegisteredElements()) {
            if (element.name().equals(comp)) {
                return element;
            }
        }

        throw new InvalidObjectException(comp);

    }

    protected Class<T> getChronoType() {

        return this.chrono;

    }

    //~ Innere Klassen ----------------------------------------------------

    private static class DayOperator<T extends ChronoEntity<T>>
        implements ChronoOperator<T> {

        //~ Instanzvariablen ----------------------------------------------

        private final boolean backwards;

        //~ Konstruktoren -------------------------------------------------

        DayOperator(boolean backwards) {
            super();

            this.backwards = backwards;

        }

        //~ Methoden ------------------------------------------------------

        public T apply(T entity) {

            long e = entity.get(EpochDays.UTC);
            if (this.backwards) {
                e--;
            } else {
                e++;
            }
            return entity.with(EpochDays.UTC, e);

        }

    }

}
