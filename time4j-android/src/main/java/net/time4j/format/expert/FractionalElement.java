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

package net.time4j.format.expert;

import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;

import java.math.BigDecimal;
import java.util.Locale;


/**
 * <p>Spezialelement f&uuml;r fraktional formatierte Elemente, um einen
 * geparsten Dezimalwert zu speichern. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
enum FractionalElement
    implements ChronoElement<BigDecimal> {

    //~ Statische Felder/Initialisierungen --------------------------------

    FRACTION;

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<BigDecimal> getType() {
        return BigDecimal.class;
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
        return o1.get(this).compareTo(o2.get(this));
    }

    @Override
    public BigDecimal getDefaultMinimum() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getDefaultMaximum() {
        return BigDecimal.ONE;
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
