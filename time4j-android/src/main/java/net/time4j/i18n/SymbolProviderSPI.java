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
        PropertyBundle rb =
            PropertyBundle.load(
                "i18n/numbers/symbol",
                Locale.ROOT);

        String[] languages = rb.getString("locales").split(" ");
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, languages);
        SUPPORTED_LOCALES = Collections.unmodifiableSet(set);
        INSTANCE = new SymbolProviderSPI();

        Map<String, NumberSystem> map = new HashMap<String, NumberSystem>();
        for (NumberSystem numsys : NumberSystem.values()) {
            map.put(numsys.getCode(), numsys);
        }
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

        String cldr = lookup(locale, "numsys", NumberSystem.ARABIC.getCode());
        NumberSystem numsys = CLDR_NAMES.get(cldr);

        if (numsys == null) {
            StringBuilder errmsg = new StringBuilder();
            errmsg.append("Unrecognized number system: ");
            errmsg.append(cldr);
            errmsg.append(" (locale=");
            errmsg.append(locale);
            errmsg.append(')');
            throw new IllegalStateException(errmsg.toString());
        }

        return numsys;

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

        PropertyBundle rb = getBundle(locale);

        if ((rb != null) && rb.containsKey(key)) {
            return rb.getString(key).charAt(0);
        }

        return standard;

    }

    private static String lookup(
        Locale locale,
        String key,
        String standard
    ) {

        PropertyBundle rb = getBundle(locale);

        if ((rb != null) && rb.containsKey(key)) {
            return rb.getString(key);
        }

        return standard;

    }

    private static PropertyBundle getBundle(Locale desired) {

        if (SUPPORTED_LOCALES.contains(LanguageMatch.getAlias(desired))) {
            return PropertyBundle.load(
                "i18n/numbers/symbol",
                desired);
        }

        return null;

    }

}
