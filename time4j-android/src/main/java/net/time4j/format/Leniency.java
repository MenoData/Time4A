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

package net.time4j.format;


/**
 * <p>Defines how lenient parsing of chronological texts should be. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Nachsichtigkeitsmodus beim Parsen von Text zu chronologischen Typen. </p>
 *
 * @author  Meno Hochschild
 */
public enum Leniency {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Ensures that all range limits and other validity constraints will
     * be strictly controlled. </p>
     *
     * <p>Exceeding the possibly context-dependent range of an element value
     * will always throw an exception. </p>
     *
     * <p>A consistency check will be performed, too. That means all
     * parsed informations must be consistent (for example the right
     * weekday for a given calendar date). In parsing, the given limits
     * for minimum and maximum count of chars to be interpreted will
     * be checked, too. </p>
     */
    /*[deutsch]
     * <p>Stellt das strikte Einhalten von Wertbereichsgrenzen und anderen
     * G&uuml;ltigkeitseinschr&auml;nkungen sicher. </p>
     *
     * <p>Wertbereichs&uuml;berschreitungen werden immer mit einer Ausnahme
     * quittiert. </p>
     *
     * <p>Auch findet eine Konsistenzpr&uuml;fung statt, die pr&uuml;ft,
     * da&szlig; alle gegebenen Informationen zueinander passen m&uuml;ssen
     * (z.B. der richtige Wochentag passend zu einem Datum). Beim Parsen werden
     * nur hier die angegebenen Grenzen f&uuml;r die minimale und die maximale
     * Anzahl von zu interpretierenden Zeichen genau gepr&uuml;ft. </p>
     */
    STRICT,

    /**
     * <p>This default mode tries to be a compromise between a pedantic
     * and a lax strategy by paying attention to value range constraints
     * but neglecting some constraints like the width of numerical
     * elements. </p>
     *
     * <p>There is no consistency check like in strict mode. For example
     * a wrong weekday will be ignored and the calendar date will just
     * be interpreted on the base of year, month and day of month. </p>
     */
    /*[deutsch]
     * <p>Mit dieser Standardvorgabe wird versucht, einen Mittelweg zwischen
     * einer pedantischen und laxen Strategie zu w&auml;hlen, indem zwar auf
     * Wertbereichs&uuml;berschreitungen geachtet wird, aber bestimmte
     * Einstellungen wie die Breite von numerischen Elementen tolerant
     * gehandhabt werden. </p>
     *
     * <p>Eine Konsistenzpr&uuml;fung wie im strikten Modus findet nicht
     * statt. So wird ein falscher Wochentag ignoriert und das Datum eher
     * auf Basis von Jahr, Monat und Tag des Monats interpretiert. </p>
     */
    SMART,

    /**
     * <p>The parsed data will be interpreted without any consistency
     * check or validation of range limits. </p>
     *
     * <p>This mode even tolerates values like the wall time &quot;T25:00&quot;
     * or the invalid calendar date &quot;2014-02-31&quot; which will be
     * interpreted with the suitable day overflow as &quot;2014-03-03&quot;. </p>
     */
    /*[deutsch]
     * <p>Die Daten werden beim ersten passenden Treffer ohne weitere
     * Konsistenzpr&uuml;fung interpretiert. </p>
     *
     * <p>Auch aus dem definierten Wertbereich fallende Werte wie die
     * Uhrzeit &quot;T25:00:00&quot; oder das Datum &quot;2014-02-31&quot;
     * werden akzeptiert und mit &Uuml;berlauf uminterpretiert. </p>
     */
    LAX;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Is this leniency mode strict? </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Ist dieser Nachsichtigkeitsmodus strikt? </p>
     *
     * @return  boolean
     */
    public boolean isStrict() {

        return (this == STRICT);

    }

    /**
     * <p>Is this leniency mode <i>smart</i>? </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Ist dieser Nachsichtigkeitsmodus <i>smart</i>? </p>
     *
     * @return  boolean
     */
    public boolean isSmart() {

        return (this == SMART);

    }

    /**
     * <p>Is this leniency mode lax? </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Ist dieser Nachsichtigkeitsmodus lax? </p>
     *
     * @return  boolean
     */
    public boolean isLax() {

        return (this == LAX);

    }

}
