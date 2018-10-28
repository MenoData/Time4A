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
 * <p>If there is no external {@code UnitPatternProvider} then Time4J will use
 * an internal implementation which just offers unit patterns either in
 * english or in scientific notation. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
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
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 * @see     java.util.ServiceLoader
 */
public interface UnitPatternProvider {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of years. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for years
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Jahre. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for years
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getYearPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of months. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for months
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Monate. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for months
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getMonthPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of weeks. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for weeks
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Wochen. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for weeks
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getWeekPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of days. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for days
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Tage. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for days
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getDayPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of hours. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for hours
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Stunden. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for hours
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getHourPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of minutes. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for minutes
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Minuten. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for minutes
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getMinutePattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of seconds. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for seconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Sekunden. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for seconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getSecondPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of milliseconds. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for milliseconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext
     * und einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der
     * Millisekunden. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for milliseconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getMilliPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of microseconds. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for microseconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext
     * und einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der
     * Mikrosekunden. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for microseconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getMicroPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of nanoseconds. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for nanoseconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext
     * und einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der
     * Nanosekunden. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   category    plural category
     * @return  unit pattern for nanoseconds
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getNanoPattern(
        Locale lang,
        TextWidth width,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of years in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for years in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Jahre in der
     * Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for years in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getYearPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of months in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for months in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Monate in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for months in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getMonthPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of weeks in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for weeks in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Wochen in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for weeks in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getWeekPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of days in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for days in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Tage in der
     * Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for days in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getDayPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of hours in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for hours in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Stunden in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for hours in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getHourPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of minutes in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for minutes in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Minuten in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for minutes in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getMinutePattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized unit pattern with unit name and a placeholder
     * &quot;{0}&quot; for the count of seconds in the past or future. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for seconds in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Zeiteinheitsmuster mit Zeiteinheitstext und
     * einem Platzhalter &quot;{0}&quot; f&uuml;r die Anzahl der Sekunden in
     * der Vergangenheit oder Zukunft. </p>
     *
     * @param   lang        language setting
     * @param   future      use future or past form
     * @param   category    plural category
     * @return  unit pattern for seconds in the past or future
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getSecondPattern(
        Locale lang,
        boolean future,
        PluralCategory category
    );

    /**
     * <p>Yields the localized word for the current time (now). </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Wort f&uuml;r die aktuelle Zeit
     * (jetzt). </p>
     *
     * @param   lang    language setting
     * @return  String
     * @throws  java.util.MissingResourceException if not found
     * @since   1.2
     */
    String getNowWord(Locale lang);

    /**
     * <p>Constructs a localized list pattern suitable for the use in
     * {@link java.text.MessageFormat#format(String, Object[])}. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   size        count of list items
     * @return  message format pattern with placeholders {0}, {1}, ..., {x}, ...
     * @throws  IllegalArgumentException if size is smaller than 2
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    /*[deutsch]
     * <p>Konstruiert ein lokalisiertes Listenformat geeignet f&uuml;r
     * {@link java.text.MessageFormat#format(String, Object[])}. </p>
     *
     * @param   lang        language setting
     * @param   width       text width (ABBREVIATED as synonym for SHORT)
     * @param   size        count of list items
     * @return  message format pattern with placeholders {0}, {1}, ..., {x}, ...
     * @throws  IllegalArgumentException if size is smaller than 2
     * @throws  java.util.MissingResourceException if no pattern was found
     * @since   1.2
     */
    String getListPattern(
        Locale lang,
        TextWidth width,
        int size
    );

}
