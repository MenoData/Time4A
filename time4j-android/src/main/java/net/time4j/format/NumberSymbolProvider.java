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

import java.text.DecimalFormatSymbols;
import java.util.Locale;


/**
 * <p>This <strong>SPI-interface</strong> enables the access to localized
 * number properties like zero digits and is instantiated via a
 * {@code ServiceLoader}-mechanism. </p>
 *
 * <p>If there is no external {@code NumberSymbolProvider} then Time4J will
 * just delegate to the internal resources or to the JDK. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   2.1
 * @see     java.util.ServiceLoader
 * @see     java.text.DecimalFormatSymbols#getZeroDigit
 */
/*[deutsch]
 * <p>Dieses <strong>SPI-Interface</strong> erm&ouml;glicht den Zugriff
 * auf {@code Locale}-abh&auml;ngige Zahleigenschaften wie Nullziffern
 * und wird &uuml;ber einen {@code ServiceLoader}-Mechanismus instanziert. </p>
 *
 * <p>Wird kein externer {@code NumberSymbolProvider} gefunden, wird intern
 * eine Instanz erzeugt, die an die internen Ressourcen oder das JDK delegiert. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   2.1
 * @see     java.util.ServiceLoader
 * @see     java.text.DecimalFormatSymbols#getZeroDigit
 */
public interface NumberSymbolProvider {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Default provider which delegates to standard JVM resources. </p>
     *
     * @see     DecimalFormatSymbols
     */
    /*[deutsch]
     * <p>Standardimplementierung, die an die Ressourcen der JVM delegiert. </p>
     *
     * @see     DecimalFormatSymbols
     */
    NumberSymbolProvider DEFAULT =
        new NumberSymbolProvider() {
            @Override
            public Locale[] getAvailableLocales() {
                return DecimalFormatSymbols.getAvailableLocales();
            }

            @Override
            public char getZeroDigit(Locale locale) {
                return getSymbols(locale).getZeroDigit();
            }

            @Override
            public char getDecimalSeparator(Locale locale) {
                return getSymbols(locale).getDecimalSeparator();
            }

            @Override
            public String getPlusSign(Locale locale) {
                if (locale.getLanguage().equals("ar")) {
                    return "\u200F+";
                }
                return String.valueOf('+');
            }

            @Override
            public String getMinusSign(Locale locale) {
                if (locale.getLanguage().equals("ar")) {
                    return "\u200F\u002D";
                }
                return String.valueOf(getSymbols(locale).getMinusSign());
            }

            @Override
            public NumberSystem getDefaultNumberSystem(Locale locale) {
                return NumberSystem.ARABIC;
            }

            private DecimalFormatSymbols getSymbols(Locale loc) {
                return DecimalFormatSymbols.getInstance(loc);
            }
        };

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the supported languages. </p>
     *
     * @return  Locale-array
     */
    /*[deutsch]
     * <p>Gibt die unterst&uuml;tzten Sprachen an. </p>
     *
     * @return  Locale-array
     */
    Locale[] getAvailableLocales();

    /**
     * <p>Returns the localized zero digit. </p>
     *
     * @param   locale      language and country setting
     * @return  zero digit of associated numbering system
     */
    /*[deutsch]
     * <p>Liefert die lokalisierte Nullziffer. </p>
     *
     * @param   locale      language and country setting
     * @return  zero digit of associated numbering system
     */
    char getZeroDigit(Locale locale);

    /**
     * <p>Returns the localized decimal separator. </p>
     *
     * @param   locale      language and country setting
     * @return  localized decimal separator
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Dezimaltrennzeichen. </p>
     *
     * @param   locale      language and country setting
     * @return  localized decimal separator
     */
    char getDecimalSeparator(Locale locale);

    /**
     * <p>Returns the localized plus sign. </p>
     *
     * @param   locale      language and country setting
     * @return  localized plus sign, possibly including RLM- or LRM-markers
     * @since   3.13/4.10
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Plus-Zeichen. </p>
     *
     * @param   locale      language and country setting
     * @return  localized plus sign, possibly including RLM- or LRM-markers
     * @since   3.13/4.10
     */
    String getPlusSign(Locale locale);

    /**
     * <p>Returns the localized minus sign. </p>
     *
     * @param   locale      language and country setting
     * @return  localized minus sign, possibly including RLM- or LRM-markers
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Minus-Zeichen. </p>
     *
     * @param   locale      language and country setting
     * @return  localized minus sign, possibly including RLM- or LRM-markers
     */
    String getMinusSign(Locale locale);

    /**
     * <p>Obtains the standard number system for given locale. </p>
     *
     * @param   locale      language and country setting
     * @return  default number system
     * @throws  IllegalStateException if the number system cannot be determined
     * @since   3.23/4.19
     */
    /*[deutsch]
     * <p>Ermittelt das Standardzahlsystem f&uuml;r die angegebene Sprache. </p>
     *
     * @param   locale      language and country setting
     * @return  default number system
     * @throws  IllegalStateException if the number system cannot be determined
     * @since   3.23/4.19
     */
    NumberSystem getDefaultNumberSystem(Locale locale);

}
