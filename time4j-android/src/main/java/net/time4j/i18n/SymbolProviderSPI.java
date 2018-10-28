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

package net.time4j.i18n;

import net.time4j.format.NumberSymbolProvider;
import net.time4j.format.NumberSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;


/**
 * <p>Internal standard access to localized number symbols. </p>
 *
 * <p>The underlying properties files are located in the folder
 * &quot;numbers&quot; relative to class path and are encoded in UTF-8.
 * The basic bundle name is &quot;symbol&quot;. If a locale is not found
 * then the JDK serves as fallback. </p>
 *
 * @author  Meno Hochschild
 */
public final class SymbolProviderSPI
    implements NumberSymbolProvider {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final Locale[] EMPTY_ARRAY = new Locale[0];

    public static final Set<String> SUPPORTED_LOCALES;
    public static final SymbolProviderSPI INSTANCE;

    private static final Map<String, NumberSystem> CLDR_NAMES;

    static {
        ResourceBundle rb =
            ResourceBundle.getBundle(
                "numbers/symbol",
                Locale.ROOT,
                getLoader(),
                UTF8ResourceControl.SINGLETON);

        String[] languages = rb.getString("locales").split(" ");
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, languages);
        SUPPORTED_LOCALES = Collections.unmodifiableSet(set);
        INSTANCE = new SymbolProviderSPI();

        Map<String, NumberSystem> map = new HashMap<String, NumberSystem>();
        map.put("latn", NumberSystem.ARABIC);
        map.put("arab", NumberSystem.ARABIC_INDIC);
        map.put("arabext", NumberSystem.ARABIC_INDIC_EXT);
        map.put("deva", NumberSystem.DEVANAGARI);
        map.put("mymr", NumberSystem.MYANMAR);
        CLDR_NAMES = Collections.unmodifiableMap(map);
    }

    //~ Konstruktoren -----------------------------------------------------

    /**
     * For service loader only.
     */
    public SymbolProviderSPI() {
        super();

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Locale[] getAvailableLocales() {

        return EMPTY_ARRAY; // ok because this class only serves as fallback in SPI-mechanism

    }

    @Override
    public char getZeroDigit(Locale locale) {

        return lookup(
            locale,
            "zero",
            NumberSymbolProvider.DEFAULT.getZeroDigit(locale));

    }

    @Override
    public char getDecimalSeparator(Locale locale) {

        return lookup(
            locale,
            "separator",
            NumberSymbolProvider.DEFAULT.getDecimalSeparator(locale));

    }

    @Override
    public String getPlusSign(Locale locale) {

        return lookup(
            locale,
            "plus",
            NumberSymbolProvider.DEFAULT.getPlusSign(locale));

    }

    @Override
    public String getMinusSign(Locale locale) {

        return lookup(
            locale,
            "minus",
            NumberSymbolProvider.DEFAULT.getMinusSign(locale));

    }

    @Override
    public NumberSystem getDefaultNumberSystem(Locale locale) {

        String cldr = lookup(locale, "numsys", "latn");
        return CLDR_NAMES.get(cldr);

    }

    @Override
    public String toString() {

        return "SymbolProviderSPI";

    }

    private static char lookup(
        Locale locale,
        String key,
        char standard
    ) {

        ResourceBundle rb = getBundle(locale);

        if (
            (rb != null)
            && rb.containsKey(key)
        ) {
            return rb.getString(key).charAt(0);
        }

        return standard;

    }

    private static String lookup(
        Locale locale,
        String key,
        String standard
    ) {

        ResourceBundle rb = getBundle(locale);

        if (
            (rb != null)
            && rb.containsKey(key)
        ) {
            return rb.getString(key);
        }

        return standard;

    }

    private static ResourceBundle getBundle(Locale desired) {

        if (SUPPORTED_LOCALES.contains(LanguageMatch.getAlias(desired))) {
            return ResourceBundle.getBundle(
                "numbers/symbol",
                desired,
                getLoader(),
                UTF8ResourceControl.SINGLETON);
        }

        return null;

    }

	private static ClassLoader getLoader() {

		return SymbolProviderSPI.class.getClassLoader();

	}

}
