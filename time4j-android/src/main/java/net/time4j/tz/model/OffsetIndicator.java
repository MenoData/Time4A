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

package net.time4j.tz.model;


/**
 * <p>Helps to interprete a timestamp relative to an timezone offset. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     java.util.SimpleTimeZone#UTC_TIME
 * @see     java.util.SimpleTimeZone#STANDARD_TIME
 * @see     java.util.SimpleTimeZone#WALL_TIME
 */
/*[deutsch]
 * <p>Hilft einen Zeitstempel relativ zu einem Zeitzonen-Offset zu
 * interpretieren. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     java.util.SimpleTimeZone#UTC_TIME
 * @see     java.util.SimpleTimeZone#STANDARD_TIME
 * @see     java.util.SimpleTimeZone#WALL_TIME
 */
public enum OffsetIndicator {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * Reference to UTC-offset.
     */
    /*[deutsch]
     * Referenz zum UTC-Offset.
     */
    UTC_TIME() {
        @Override
        public char getSymbol() {
            return 'u';
        }
    },

    /**
     * Local standard time (UTC + standard-offset).
     */
    /*[deutsch]
     * Lokale Standardzeit (= UTC + Standard-Offset).
     */
    STANDARD_TIME() {
        @Override
        public char getSymbol() {
            return 's';
        }
    },

    /**
     * Local time (UTC + standard-offset + dst-offset).
     */
    /*[deutsch]
     * Lokale Zeit (= UTC + Standard-Offset + DST-Offset).
     */
    WALL_TIME() {
        @Override
        public char getSymbol() {
            return 'w';
        }
    };

    // Cache
    static final OffsetIndicator[] VALUES = OffsetIndicator.values();

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Interpretes given symbol as indicator. </p>
     *
     * <p>The TZDB-repository recognizes following letters: </p>
     *
     * <ul>
     *  <li>u, g, z - {@link #UTC_TIME}</li>
     *  <li>s - {@link #STANDARD_TIME}</li>
     *  <li>w - {@link #WALL_TIME}</li>
     * </ul>
     *
     * @param   symbol  symbol letter to be parsed as found in TZDB-data
     * @return  offset indicator
     * @since   2.2
     * @throws  IllegalArgumentException if the letter is not supported
     */
    /*[deutsch]
     * <p>Interpretiert das angegebene Symbol als Indikator. </p>
     *
     * <p>Das TZDB-Repositorium kennt folgende Symbole: </p>
     *
     * <ul>
     *  <li>u, g, z - {@link #UTC_TIME}</li>
     *  <li>s - {@link #STANDARD_TIME}</li>
     *  <li>w - {@link #WALL_TIME}</li>
     * </ul>
     *
     * @param   symbol  symbol letter to be parsed as found in TZDB-data
     * @return  offset indicator
     * @since   2.2
     * @throws  IllegalArgumentException if the letter is not supported
     */
    public static OffsetIndicator parseSymbol(char symbol) {

        switch (symbol) {
            case 'u':
            case 'g':
            case 'z':
                return UTC_TIME;
            case 's':
                return STANDARD_TIME;
            case 'w':
                return WALL_TIME;
            default:
                throw new IllegalArgumentException(
                    "Unknown offset indicator: " + symbol);
        }

    }

    /**
     * <p>Yields the symbol for this indicator as used in tzdb-repository. </p>
     *
     * @return  char (&quot;u&quot;, &quot;s&quot; or &quot;w&quot;)
     */
    /*[deutsch]
     * <p>Liefert das Symbol dieses Indikators wie im TZDB-Repositorium
     * benutzt. </p>
     *
     * @return  char (&quot;u&quot;, &quot;s&quot; or &quot;w&quot;)
     */
    public char getSymbol() {

        throw new AbstractMethodError();

    }

}
