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
 * <p>This <strong>SPI-interface</strong> enables the access to localized
 * unit patterns and is instantiated via a {@code ServiceLoader}-mechanism. </p>
 *
 * <p>If there is no external {@code RelativeTimeProvider} then Time4J will use
 * an internal implementation which just offers unit patterns either in
 * english or in scientific notation. </p>
 *
 * <p>Note: This interface enhances the standard {@code UnitPatternProvider} by
 * new methods for short/abbreviated relative times and extra words for
 * &quot;yesterday-today-tomorrow&quot;. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor. </p>
 *
 * @author  Meno Hochschild
 * @since   3.6/4.4
 * @see     java.util.ServiceLoader
 */
/*[deutsch]
 * <p>Dieses <strong>SPI-Interface</strong> erm&ouml;glicht den Zugriff
 * auf {@code Locale}-abh&auml;ngige Zeiteinheitsmuster und wird &uuml;ber
 * einen {@code ServiceLoader}-Mechanismus instanziert. </p>
 *
 * <p>Wird kein externer {@code UnitPatternProvider} gefunden, wird intern
 * eine Instanz erzeugt, die lediglich Zeiteinheitsmuster auf Englisch
 * oder in wissenschaftlicher Notation anbietet. </p>
 *
 * <p>Note: Dieses Interface erweitert den Standard {@code UnitPatternProvider} um
 * neue Methoden f&uuml;r abgek&uuml;rzte relative Zeiten und extra W&ouml;rter f&uuml;r
 * &quot;gestern-heute-morgen&quot;. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor. </p>
 *
 * @author  Meno Hochschild
 * @since   3.6/4.4
 * @see     java.util.ServiceLoader
 */
public interface RelativeTimeProvider
    extends UnitPatternProvider {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of years in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short years in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Jahre in der
     * Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short years in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortYearPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of months in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short months in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Monate in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short months in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortMonthPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of weeks in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short weeks in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Wochen in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short weeks in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortWeekPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of days in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short days in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Tage in der
     * Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short days in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortDayPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of hours in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short hours in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Stunden in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short hours in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortHourPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of minutes in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short minutes in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Minuten in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short minutes in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortMinutePattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with short unit name and a placeholder
     * &quot;{0}&quot; for the count of seconds in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short seconds in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit kurzem Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Sekunden in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for short seconds in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   3.6/4.4
     */
    String getShortSecondPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized word for &quot;yesterday&quot;. </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Wort f&uuml;r den gestrigen Tag. </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   3.6/4.4
     */
    String getYesterdayWord(Locale lang);

    /**
     * <p>Yields the localized word for &quot;today&quot;. </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Wort f&uuml;r den heutigen Tag. </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   3.6/4.4
     */
    String getTodayWord(Locale lang);

    /**
     * <p>Yields the localized word for &quot;tomorrow&quot;. </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Wort f&uuml;r den morgigen Tag. </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   3.6/4.4
     */
    String getTomorrowWord(Locale lang);

}
