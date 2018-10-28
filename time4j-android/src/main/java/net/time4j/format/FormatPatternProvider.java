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

import java.util.Locale;


/**
 * <p>This <strong>SPI-interface</strong> enables the access to localized gregorian
 * date-, time- or interval patterns according to the CLDR-specifiation and is instantiated via a
 * {@code ServiceLoader}-mechanism. </p>
 *
 * <p>If there is no external {@code FormatPatternProvider} then Time4J will
 * just delegate to the JDK. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   3.10/4.7
 * @see     java.util.ServiceLoader
 * @see     java.text.SimpleDateFormat#toPattern()
 */
/*[deutsch]
 * <p>Dieses <strong>SPI-Interface</strong> erm&ouml;glicht den Zugriff auf gregorianische
 * {@code Locale}-abh&auml;ngige Formatmuster f&uuml;r Datum, Uhrzeit oder Intervalle
 * entsprechend der CLDR-Spezifikation und wird &uuml;ber einen {@code ServiceLoader}-Mechanismus
 * instanziert. </p>
 *
 * <p>Wird kein externer {@code FormatPatternProvider} gefunden, wird intern
 * eine Instanz erzeugt, die an das JDK delegiert. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   3.10/4.7
 * @see     java.util.ServiceLoader
 * @see     java.text.SimpleDateFormat#toPattern()
 */
public interface FormatPatternProvider {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Returns the localized date pattern suitable for formatting of objects
     * of type {@code PlainDate}. </p>
     *
     * @param   mode        display mode
     * @param   locale      language and country setting
     * @return  localized date pattern
     * @see     net.time4j.PlainDate
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Datumsmuster geeignet f&uuml;r
     * die Formatierung von Instanzen des Typs{@code PlainDate}. </p>
     *
     * @param   mode        display mode
     * @param   locale      language and country setting
     * @return  localized date pattern
     * @see     net.time4j.PlainDate
     */
    String getDatePattern(
        DisplayMode mode,
        Locale locale
    );

    /**
     * <p>Returns the localized time pattern suitable for formatting of objects
     * of type {@code PlainTime}. </p>
     *
     * @param   mode        display mode
     * @param   locale      language and country setting
     * @return  localized time pattern
     * @see     net.time4j.PlainTime
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Uhrzeitmuster geeignet f&uuml;r die
     * Formatierung von Instanzen des Typs {@code PlainTime}. </p>
     *
     * @param   mode        display mode
     * @param   locale      language and country setting
     * @return  localized time pattern
     * @see     net.time4j.PlainTime
     */
    String getTimePattern(
        DisplayMode mode,
        Locale locale
    );

    /**
     * <p>Returns the localized date-time pattern suitable for formatting of objects
     * of type {@code Moment} or {@code PlainTimestamp}. </p>
     *
     * <p>Expressions of the form &quot;{0}&quot; will be interpreted as the time component
     * and expressions of the form &quot;{1}&quot; will be interpreted as the date component.
     * All other chars of the pattern will be treated as literals. </p>
     *
     * @param   dateMode    display mode of date part
     * @param   timeMode    display mode of time part
     * @param   locale      language and country setting
     * @return  localized date-time pattern
     * @see     net.time4j.Moment
     * @see     net.time4j.PlainTimestamp
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Datums- und Uhrzeitmuster geeignet f&uuml;r die Formatierung
     * von Instanzen des Typs {@code Moment} oder {@code PlainTimestamp}. </p>
     *
     * <p>Die Ausdr&uuml;cke &quot;{0}&quot; und &quot;{1}&quot; werden als Formathalter f&uuml;r die
     * Uhrzeit- und Datumskomponenten interpretiert. Alle anderen Zeichen des Musters werden wie
     * Literale behandelt. </p>
     *
     * @param   dateMode    display mode of date part
     * @param   timeMode    display mode of time part
     * @param   locale      language and country setting
     * @return  localized date-time pattern
     * @see     net.time4j.Moment
     * @see     net.time4j.PlainTimestamp
     */
    String getDateTimePattern(
        DisplayMode dateMode,
        DisplayMode timeMode,
        Locale locale
    );

    /**
     * <p>Returns the localized interval pattern. </p>
     *
     * <p>Expressions of the form &quot;{0}&quot; will be interpreted as the start boundary format
     * and expressions of the form &quot;{1}&quot; will be interpreted as the end boundary format.
     * All other chars of the pattern will be treated as literals. </p>
     *
     * @param   locale      language and country setting
     * @return  localized interval pattern
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Intervallmuster. </p>
     *
     * <p>Die Ausdr&uuml;cke &quot;{0}&quot; und &quot;{1}&quot; werden als Formathalter f&uuml;r die
     * Start- und End-Intervallgrenzen interpretiert. Alle anderen Zeichen des Musters werden wie
     * Literale behandelt. </p>
     *
     * @param   locale      language and country setting
     * @return  localized interval pattern
     */
    String getIntervalPattern(Locale locale);

}
