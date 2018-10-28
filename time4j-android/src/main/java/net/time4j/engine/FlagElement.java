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


import java.util.Locale;

/**
 * <p>A specialized element for indicating special state during parsing. </p>
 *
 * @author  Meno Hochschild
 * @since   3.32/4.27
 */
/*[deutsch]
 * <p>Ein Spezialelement, das einen besonderen Zustand w&auml;hrend eines
 * Interpretationsvorgangs anzeigen kann. </p>
 *
 * @author  Meno Hochschild
 * @since   3.32/4.27
 */
public enum FlagElement
    implements ChronoElement<Boolean> {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Identifies the existence of a leap second in any parsed chronological entity. </p>
     */
    /*[deutsch]
     * <p>Markiert die Existenz einer Schaltsekunde in einer gegebenen chronologischen Entit&auml;t. </p>
     */
    LEAP_SECOND,

    /**
     * <p>Identifies a summer or winter time information in any parsed chronological entity. </p>
     */
    /*[deutsch]
     * <p>Markiert eine Sommer- oder Winterzeitinformation in einer gegebenen chronologischen Entit&auml;t. </p>
     */
    DAYLIGHT_SAVING;

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Boolean> getType() {

        return Boolean.class;

    }

    @Override
    public char getSymbol() {

        return '\u0000';

    }

    @Override
    public int compare(
        ChronoDisplay o1,
        ChronoDisplay o2
    ) {

        boolean b1 = o1.contains(this);
        boolean b2 = o2.contains(this);
        return ((b1 == b2) ? 0 : (b1 ? 1 : -1));

    }

    @Override
    public Boolean getDefaultMinimum() {

        return Boolean.FALSE;

    }

    @Override
    public Boolean getDefaultMaximum() {

        return Boolean.TRUE;

    }

    @Override
    public boolean isDateElement() {

        return false;

    }

    @Override
    public boolean isTimeElement() {

        return false;

    }

    @Override
    public boolean isLenient() {

        return false;

    }

    @Override
    public String getDisplayName(Locale language) {

        return this.name();

    }

}
