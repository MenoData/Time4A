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

package net.time4j.tz;


/**
 * <p>Defines the style of a timezone name. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Definiert den Stil eines Zeitzonennamens. </p>
 *
 * @author  Meno Hochschild
 */
public enum NameStyle {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Abbreviation in winter time
     * (usually standard time, Ireland is an exception however). </p>
     *
     * <p>Example: CET </p>
     */
    /*[deutsch]
     * <p>Abk&uuml;rzung in der Winterzeit
     * (meistens als Normalzeit bekannt, eine Ausnahme ist Irland). </p>
     *
     * <p>Beispiel: MEZ </p>
     */
    SHORT_STANDARD_TIME,

    /**
     * <p>Long name in winter time
     * (usually standard time, Ireland is an exception however). </p>
     *
     * <p>Example: Central European Time </p>
     */
    /*[deutsch]
     * <p>Langer Name in der Winterzeit
     * (meistens als Normalzeit bekannt, eine Ausnahme ist Irland). </p>
     *
     * <p>Beispiel: Mitteleurop&auml;ische Zeit </p>
     */
    LONG_STANDARD_TIME,

    /**
     * <p>Abbreviation in summer time (daylight saving). </p>
     *
     * <p>Example: CEST </p>
     */
    /*[deutsch]
     * <p>Abk&uuml;rzung in der Sommerzeit. </p>
     *
     * <p>Beispiel: MESZ </p>
     */
    SHORT_DAYLIGHT_TIME,

    /**
     * <p>Long name in summer time (daylight saving). </p>
     *
     * <p>Example: Central European Summer Time </p>
     */
    /*[deutsch]
     * <p>Langer Name in der Sommerzeit. </p>
     *
     * <p>Beispiel: Mitteleurop&auml;ische Sommerzeit </p>
     */
    LONG_DAYLIGHT_TIME;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Does this style denote an abbreviation? </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Liegt eine Abk&uuml;rzung vor? </p>
     *
     * @return  boolean
     */
    public boolean isAbbreviation() {

        return (
            (this == SHORT_STANDARD_TIME)
            || (this == SHORT_DAYLIGHT_TIME)
        );

    }

    /**
     * <p>Does this style denote a daylight saving time? </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Liegt eine Sommerzeitform vor? </p>
     *
     * @return  boolean
     */
    public boolean isDaylightSaving() {

        return (
            (this == SHORT_DAYLIGHT_TIME)
            || (this == LONG_DAYLIGHT_TIME)
        );

    }

}
