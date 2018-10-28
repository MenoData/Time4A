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
 * <p>Indicates that a chronological rule for an element or
 * a time unit is missing. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Signalisiert das Fehlen einer chronologischen Regel f&uuml;r ein
 * chronologisches Element oder eine Zeiteinheit. </p>
 *
 * @author  Meno Hochschild
 */
public class RuleNotFoundException
    extends ChronoException {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -5638437652574160520L;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * Erzeugt eine neue Instanz von <code>RuleNotFoundException</code>.
     *
     * @param   chronology  chronology a rule was searched for in vain
     * @param   element     element as reference for searched rule
     */
    RuleNotFoundException(
        Chronology<?> chronology,
        ChronoElement<?> element
    ) {
        super(createMessage(chronology, element));

    }

    /**
     * Erzeugt eine neue Instanz von <code>RuleNotFoundException</code>.
     *
     * @param   veto        detailed error message
     */
    RuleNotFoundException(String veto) {
        super(veto);

    }

    /**
     * Erzeugt eine neue Instanz von <code>RuleNotFoundException</code>.
     *
     * @param   chronology  chronology a rule was searched for in vain
     * @param   unit        unit as reference for searched rule
     */
    RuleNotFoundException(
        Chronology<?> chronology,
        Object unit
    ) {
        super(createMessage(chronology, unit));

    }

    //~ Methoden ----------------------------------------------------------

    private static String createMessage(
        Chronology<?> chronology,
        ChronoElement<?> element
    ) {

        return (
            "Cannot find any rule for chronological element \""
            + element.name()
            + "\" in: "
            + chronology.getChronoType().getName());

    }

    private static String createMessage(
        Chronology<?> chronology,
        Object unit
    ) {

        return (
            "Cannot find any rule for chronological unit \""
            + getName(unit)
            + "\" in: "
            + chronology.getChronoType().getName());

    }

    private static String getName(Object unit) {

        if (unit instanceof Enum) {
            return Enum.class.cast(unit).name();
        } else {
            return unit.toString();
        }

    }

}
