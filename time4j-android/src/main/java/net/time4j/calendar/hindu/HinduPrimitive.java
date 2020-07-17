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

package net.time4j.calendar.hindu;

import net.time4j.calendar.EastAsianMonth;
import net.time4j.engine.AttributeKey;


/**
 * <p>Abstract super class of Hindu months or days which can carry a leap status in the lunisolar
 * variants of the Hindu calendar. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
/*[deutsch]
 * <p>Abstrakte Superklasse von Hindumonaten oder Hindutagen, die im lunisolaren Kalender
 * im Schaltzustand vorliegen k&ouml;nnen. </p>
 *
 * @author  Meno Hochschild
 * @since   4.7
 */
public abstract class HinduPrimitive {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Format attribute which defines a symbol character for leap months or leap days. </p>
     *
     * <p>This format attribute is mainly used in numerical formatting or when months shall be printed
     * in an abbreviated style. Otherwise the localized word for &quot;adhika&quot; will be printed. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#with(AttributeKey, char)
     * @see     #ADHIKA_IS_TRAILING
     */
    /*[deutsch]
     * <p>Formatattribut, das ein Symbolzeichen f&uuml;r Schaltmonate oder Schalttage definiert. </p>
     *
     * <p>Dieses Formatattribut wird vorwiegend bei numerischer Formatierung oder dann verwendet, wenn
     * Monate in abgek&uuml;rzter Form ausgegeben werden sollen. Sonst wird das lokalisierte Wort f&uuml;r
     * &quot;adhika&quot; ausgegeben. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#with(AttributeKey, char)
     * @see     #ADHIKA_IS_TRAILING
     */
    public static final AttributeKey<Character> ADHIKA_INDICATOR = EastAsianMonth.LEAP_MONTH_INDICATOR;

    /**
     * <p>Format attribute which defines if the symbol character for leap months or leap days should be printed
     * after the element (default is {@code false} for most languages). </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#with(AttributeKey, boolean)
     * @see     #ADHIKA_INDICATOR
     */
    /*[deutsch]
     * <p>Formatattribut, das angibt, ob das Symbolzeichen f&uuml;r Schaltmonate oder Schalttage nach dem
     * Element angezeigt werden soll (Standard ist f&uuml;r die meisten Sprachen {@code false}). </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#with(AttributeKey, boolean)
     * @see     #ADHIKA_INDICATOR
     */
    public static final AttributeKey<Boolean> ADHIKA_IS_TRAILING = EastAsianMonth.LEAP_MONTH_IS_TRAILING;

    //~ Konstruktoren -----------------------------------------------------

    // package private - no external instantiation
    HinduPrimitive() {
        super();

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Determines if this value primitive is in leap state (intercalated). </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Bestimmt, ob dieser Wert im Schaltzustand ist, also ein eingeschobener Monat oder Tag. </p>
     *
     * @return  boolean
     */
    public abstract boolean isLeap();

}
