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

package net.time4j.engine;


/**
 * <p>Abstract time unit class which can define its own rule. </p>
 *
 * @see     TimePoint#plus(long, Object) TimePoint.plus(long, U)
 * @see     TimePoint#minus(long, Object) TimePoint.minus(long, U)
 * @see     TimePoint#until(TimePoint, Object) TimePoint.until(T, U)
 */
/*[deutsch]
 * <p>Abstrakte Zeiteinheitsklasse, die ihre eigene Regel definieren kann. </p>
 *
 * @see     TimePoint#plus(long, Object) TimePoint.plus(long, U)
 * @see     TimePoint#minus(long, Object) TimePoint.minus(long, U)
 * @see     TimePoint#until(TimePoint, Object) TimePoint.until(T, U)
 */
public abstract class BasicUnit
    implements ChronoUnit {

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Subclasses usually define a singleton and have no public
     * constructors. </p>
     */
    /*[deutsch]
     * <p>Subklassen werden normalerweise ein Singleton definieren und
     * keine &ouml;ffentlichen Konstruktoren anbieten. </p>
     */
    protected BasicUnit() {
        super();

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean isCalendrical() {

        return (Double.compare(this.getLength(), 86400.0) >= 0);
        
    }

    /**
     * <p>Derives an optional unit rule for given chronology. </p>
     *
     * <p>This implementation yields {@code null} so that a chronology
     * must register this unit. Specialized subclasses can define a rule
     * however if the chronology has not registered this unit. </p>
     *
     * @param   <T> generic type of time context
     * @param   chronology  chronology an unit rule is searched for
     * @return  unit rule or {@code null} if given chronology is
     *          not supported
     */
    /*[deutsch]
     * <p>Leitet eine optionale Einheitsregel f&uuml;r die angegebene
     * Chronologie ab. </p>
     *
     * <p>Diese Implementierung liefert {@code null}, so da&szlig; eine
     * gegebene Chronologie diese Einheit registrieren mu&szlig;.
     * Spezialisierte Subklassen k&ouml;nnen jedoch eine Regel definieren,
     * wenn eine Chronologie diese Einheit nicht registriert hat. </p>
     *
     * @param   <T> generic type of time context
     * @param   chronology  chronology an unit rule is searched for
     * @return  unit rule or {@code null} if given chronology is
     *          not supported
     */
    protected <T extends ChronoEntity<T>> UnitRule<T> derive(Chronology<T> chronology) {

        return null;

    }

    /**
     * <p>Derives an optional unit rule for given entity. </p>
     *
     * @param   <T> generic type of time context
     * @param   entity  the entity referencing a chronology
     * @return  unit rule or {@code null} if the underlying chronology is not supported
     * @since   3.21/4.17
     */
    /*[deutsch]
     * <p>Leitet eine optionale Einheitsregel f&uuml;r die angegebene Entit&auml;t ab. </p>
     *
     * @param   <T> generic type of time context
     * @param   entity  the entity referencing a chronology
     * @return  unit rule or {@code null} if the underlying chronology is not supported
     * @since   3.21/4.17
     */
    protected final <T extends ChronoEntity<T>> UnitRule<T> derive(T entity) {

        return this.derive(entity.getChronology());

    }

}
