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

package net.time4j.format.internal;

import net.time4j.format.NumberSystem;


/**
 * <p>Utility class. </p>
 *
 * @author  Meno Hochschild
 * @since   4.5
 */
public class DualFormatHelper {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Prints given number as numeral. </p>
     *
     * @param   numsys      number system to be applied
     * @param   zeroDigit   relevant for decimal number systems
     * @param   number      the number to be printed
     * @return  numeral string
     */
    /*[deutsch]
     * <p>Formatiert die angegebene Zahl als Numeral. </p>
     *
     * @param   numsys      number system to be applied
     * @param   zeroDigit   relevant for decimal number systems
     * @param   number      the number to be printed
     * @return  numeral string
     */
    public static String toNumeral(
        NumberSystem numsys,
        char zeroDigit,
        int number
    ) {
        if (numsys.isDecimal()) {
            int delta = zeroDigit - '0';
            String standard = Integer.toString(number);

            if (delta == 0) {
                return standard;
            }

            StringBuilder numeral = new StringBuilder();

            for (int i = 0, n = standard.length(); i < n; i++) {
                int codepoint = standard.charAt(i) + delta;
                numeral.append((char) codepoint);
            }

            return numeral.toString();
        } else {
            return numsys.toNumeral(number);
        }
    }

}
