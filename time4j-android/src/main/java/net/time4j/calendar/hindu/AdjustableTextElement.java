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

import net.time4j.engine.ChronoOperator;
import net.time4j.format.TextElement;


/**
 * <p>Extends a chronological element by some standard ways of
 * manipulation. </p>
 *
 * @param   <V> generic type of element values, either month or day-of-month
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Erweitert ein chronologisches Element um diverse
 * Standardmanipulationen. </p>
 *
 * @param   <V> generic type of element values, either month or day-of-month
 * @author  Meno Hochschild
 */
public interface AdjustableTextElement<V extends HinduPrimitive>
    extends TextElement<V> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Sets the Hindu calendar date to the minimum of this element. </p>
     *
     * @return  ChronoOperator
     */
    /*[deutsch]
     * <p>Setzt das Hindu-Kalenderdatum auf das Elementminimum. </p>
     *
     * @return  ChronoOperator
     */
    ChronoOperator<HinduCalendar> minimized();

    /**
     * <p>Sets the Hindu calendar date to the maximum of this element. </p>
     *
     * @return  ChronoOperator
     */
    /*[deutsch]
     * <p>Setzt das Hindu-Kalenderdatum auf das Elementmaximum. </p>
     *
     * @return  ChronoOperator
     */
    ChronoOperator<HinduCalendar> maximized();

}
