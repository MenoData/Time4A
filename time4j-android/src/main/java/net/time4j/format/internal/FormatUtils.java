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


/**
 * Small utility class for localization purposes.
 *
 * @author  Meno Hochschild
 * @since   4.0
 */
/*[deutsch]
 * Kleine Hilfsklasse f&uuml;r Lokalisierungszwecke.
 *
 * @author  Meno Hochschild
 * @since   4.0
 */
public class FormatUtils {

    //~ Konstruktoren -----------------------------------------------------

    private FormatUtils() {
        // no instantiation
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * Strips off any timezone symbols in clock time patterns.
     */
    public static String removeZones(String pattern) {

        boolean literal = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 0, n = pattern.length(); i < n; i++) {
            char c = pattern.charAt(i);

            if (c == '\'') {
                if (i + 1 < n && pattern.charAt(i + 1) == '\'') {
                    sb.append(c);
                    i++;
                } else {
                    literal = !literal;
                }
                sb.append(c);
            } else if (literal) {
                sb.append(c);
            } else if (c != 'z' && c != 'Z' && c != 'v' && c != 'V' && c != 'x' && c != 'X') {
                sb.append(c);
            }
        }

        for (int j = 0; j < sb.length(); j++) {
            char c = sb.charAt(j);

            if (c == ' ' && j + 1 < sb.length() && sb.charAt(j + 1) == ' ') {
                sb.deleteCharAt(j);
                j--;
            } else if (c == '[' || c == ']' || c == '(' || c == ')') { // check locales es, fa, ps, uz
                sb.deleteCharAt(j);
                j--;
            }
        }

        String result = sb.toString().trim();

        if (result.endsWith(" '")) { // special case for de, fr_BE
            result = result.substring(0, result.length() - 2) + "'";
        } else if (result.endsWith(",")) { // special case for hy
            result = result.substring(0, result.length() - 1);
        }

        return result;

    }

}
