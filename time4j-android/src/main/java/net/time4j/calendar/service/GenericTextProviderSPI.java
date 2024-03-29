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

package net.time4j.calendar.service;

import net.time4j.format.CalendarText;
import net.time4j.format.OutputContext;
import net.time4j.format.TextProvider;
import net.time4j.format.TextWidth;
import net.time4j.i18n.LanguageMatch;
import net.time4j.i18n.PropertyBundle;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;


/**
 * <p>{@code ServiceProvider}-implementation for accessing localized calendrical
 * names in any calendar (but not ISO-8601). </p>
 *
 * <p>The underlying properties files are located in the folder
 * &quot;calendar&quot; relative to class path and are encoded in UTF-8.
 * The basic bundle name corresponds to the choosen calendar type. </p>
 *
 * @author  Meno Hochschild
 */
public final class GenericTextProviderSPI
    implements TextProvider {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final String[] EMPTY_STRINGS = new String[0];

    private static final Set<String> TYPES;
    private static final Set<String> LANGUAGES;
    private static final Set<Locale> LOCALES;

    static {
        PropertyBundle rb =
            PropertyBundle.load(
                "calendar/names/generic/generic",
                Locale.ROOT);

        String[] languages = rb.getString("languages").split(" ");
        Set<String> tmp = new HashSet<String>();
        Collections.addAll(tmp, languages);
        tmp.add("");
        LANGUAGES = Collections.unmodifiableSet(tmp);

        Set<Locale> locs = new HashSet<Locale>();

        for (String lang : LANGUAGES) {
            if (lang.isEmpty()) {
                locs.add(Locale.ROOT);
            } else {
                locs.add(new Locale(lang));
            }
        }

        LOCALES = Collections.unmodifiableSet(locs);

        Set<String> types = new HashSet<String>();
        types.add("buddhist");
        types.add("chinese");
        types.add("coptic");
        types.add("dangi");
        types.add("ethiopic");
        types.add("frenchrev");
        types.add("hindu");
        types.add("generic");
        types.add("hebrew");
        types.add("indian");
        types.add("islamic");
        types.add("japanese");
        types.add("juche");
        types.add("persian");
        types.add("roc");
        types.add("vietnam");
        TYPES = Collections.unmodifiableSet(types);
    }

    //~ Konstruktoren -----------------------------------------------------

    /** For {@code java.util.ServiceLoader}. */
    public GenericTextProviderSPI() {
        super();

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean supportsCalendarType(String calendarType) {

        return TYPES.contains(calendarType);

    }

    @Override
    public boolean supportsLanguage(Locale language) {

        return true; // uses fallback Locale.ROOT if language does not fit

    }

    @Override
    public String[] getSupportedCalendarTypes() {

        return TYPES.toArray(new String[TYPES.size()]);

    }

    @Override
    public Locale[] getAvailableLocales() {

        return LOCALES.toArray(new Locale[LOCALES.size()]);

    }

    @Override
    public String[] months(
        String calendarType,
        Locale locale,
        TextWidth tw,
        OutputContext oc,
        boolean leapForm
    ) {

        if (calendarType.equals("roc") || calendarType.equals("buddhist")) {
            List<String> months = CalendarText.getIsoInstance(locale).getStdMonths(tw, oc).getTextForms();
            return months.toArray(new String[months.size()]);
        } else if (calendarType.equals("japanese")) {
            return new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" };
        } else if (calendarType.equals("dangi") || calendarType.equals("vietnam")) {
            calendarType = "chinese"; // Umleitung
        } else if (calendarType.equals("hindu")) {
            calendarType = "indian"; // Umleitung
        } else if (calendarType.equals("juche")) {
            return CalendarText.getIsoInstance(locale).getStdMonths(tw, oc).getTextForms().toArray(new String[12]);
        }

        PropertyBundle rb = getBundle(calendarType, locale);

        if (tw == TextWidth.SHORT) {
            tw = TextWidth.ABBREVIATED;
        }

        String key =
            getKey(rb, "MONTH_OF_YEAR");
        String[] names =
            lookupBundle(
                rb, calendarType, locale.getLanguage(), countOfMonths(calendarType), key, tw, oc, leapForm, 1);

        // fallback rules as found in CLDR-root-properties via alias paths
        if (names == null) {
            if (oc == OutputContext.STANDALONE) {
                if (tw != TextWidth.NARROW) {
                    names = months(calendarType, locale, tw, OutputContext.FORMAT, leapForm);
                }
            } else {
                if (tw == TextWidth.ABBREVIATED) {
                    names = months(calendarType, locale, TextWidth.WIDE, OutputContext.FORMAT, leapForm);
                } else if (tw == TextWidth.NARROW) {
                    names = months(calendarType, locale, tw, OutputContext.STANDALONE, leapForm);
                }
            }
        }

        if (names == null) {
            throw new MissingResourceException(
                "Cannot find calendar month.",
                GenericTextProviderSPI.class.getName(),
                locale.toString());
        }

        return names;

    }

    @Override
    public String[] quarters(
        String calendarType,
        Locale locale,
        TextWidth tw,
        OutputContext oc
    ) {

        return EMPTY_STRINGS;

    }

    @Override
    public String[] weekdays(
        String calendarType,
        Locale locale,
        TextWidth tw,
        OutputContext oc
    ) {

        return EMPTY_STRINGS;

    }

    @Override
    public String[] eras(
        String calendarType,
        Locale locale,
        TextWidth tw
    ) {

        if (calendarType.equals("chinese") || calendarType.equals("vietnam")) {
            return EMPTY_STRINGS; // special handling in era elements of East Asian calendars
        } else if (calendarType.equals("japanese")) { // special handling in class Nengo !!!
            if (tw == TextWidth.NARROW) {
                return new String[] { "M", "T", "S", "H" };
            } else {
                return new String[] { "Meiji", "Taishō", "Shōwa", "Heisei" };
            }
        } else if (calendarType.equals("dangi") || calendarType.equals("juche")) {
            String[] koreans = this.eras("korean", locale, tw);
            String[] names = new String[1];
            names[0] = (calendarType.equals("dangi") ? koreans[0] : koreans[1]);
            return names;
        }

        PropertyBundle rb = getBundle(calendarType, locale);

        if (tw == TextWidth.SHORT) {
            tw = TextWidth.ABBREVIATED;
        }

        String[] names =
            lookupBundle(
                rb,
                calendarType,
                locale.getLanguage(),
                countOfEras(calendarType),
                getKey(rb, "ERA"),
                tw,
                OutputContext.FORMAT,
                false,
                0);

        if ((names == null) && (tw != TextWidth.ABBREVIATED)) {
            names = eras(calendarType, locale, TextWidth.ABBREVIATED);
        }

        if (names == null) {
            throw new MissingResourceException(
                "Cannot find calendar resource for era.",
                GenericTextProviderSPI.class.getName(),
                locale.toString());
        }

        return names;

    }

    @Override
    public String[] meridiems(
        String calendarType,
        Locale locale,
        TextWidth textWidth,
        OutputContext outputContext
    ) {

        return EMPTY_STRINGS;

    }

    @Override
    public String toString() {

        return "GenericTextProviderSPI";

    }

    /**
     * <p>Gets a resource bundle for given calendar type and locale. </p>
     *
     * @param   calendarType    calendar type (usually non-gregorian)
     * @param   desired         locale (language and/or country)
     * @return  {@code ResourceBundle}
     * @since   3.10/4.7
     */
    static PropertyBundle getBundle(
        String calendarType,
        Locale desired
    ) {

        return PropertyBundle.load(
            "calendar/names/" + calendarType + "/" + calendarType,
            LANGUAGES.contains(LanguageMatch.getAlias(desired)) ? desired : Locale.ROOT);

    }

    private static String[] lookupBundle(
        PropertyBundle rb,
        String calendarType,
        String language,
        int len,
        String elementName,
        TextWidth tw,
        OutputContext oc,
        boolean leapForm,
        int baseIndex
    ) {

        String[] names = new String[len];
        boolean shortKey = (elementName.length() == 1);

        for (int i = 0; i < len; i++) {
            StringBuilder b = new StringBuilder();
            b.append(elementName);
            b.append('(');

            if (shortKey) {
                char c = tw.name().charAt(0);

                if (oc != OutputContext.STANDALONE) {
                    c = Character.toLowerCase(c);
                }

                b.append(c);
            } else {
                b.append(tw.name());

                if (oc == OutputContext.STANDALONE) {
                    b.append('|');
                    b.append(oc.name());
                }
                if (leapForm) {
                    b.append('|');
                    b.append("LEAP");
                }
            }

            b.append(')');
            b.append('_');
            b.append(i + baseIndex);
            if (leapForm && (i == 6) && calendarType.equals("hebrew")) {
                // special case for ADAR-II
                b.append('L');
            }
            String key = b.toString();

            if (rb.containsKey(key)) {
                String s = rb.getString(key);
                if (leapForm && calendarType.equals("chinese")) {
                    s = toLeapForm(s, language, tw, oc);
                }
                names[i] = s;
            } else {
                return null;
            }
        }

        return names;

    }

    private static String toLeapForm(
        String s,
        String language,
        TextWidth tw,
        OutputContext oc
    ) {

        if (language.equals("en")) {
            if (tw == TextWidth.NARROW) {
                s = "i" + s;
            } else {
                s = "(leap) " + s;
            }

        } else if (
            language.equals("de") || language.equals("es") || language.equals("fr")
            || language.equals("it") || language.equals("pt") || language.equals("ro")
        ) {
            if (tw == TextWidth.NARROW) {
                s = "i" + s;
            } else {
                s = "(i) " + s;
            }

        } else if (language.equals("ja")) {
            s = "閏" + s;

        } else if (language.equals("ko")) {
            s = "윤" + s;

        } else if (language.equals("zh")) {
            s = "閏" + s;

        } else if (language.equals("vi")) {
            if (tw == TextWidth.NARROW) {
                s = s + "n";
            } else {
                s = s + ((oc == OutputContext.STANDALONE) ? " Nhuận" : " nhuận");
            }

        } else {
            s = "*" + s;
        }

        return s;

    }

    private static String getKey(
        PropertyBundle bundle,
        String elementName
    ) {

        if (
            bundle.containsKey("useShortKeys")
            && "true".equals(bundle.getString("useShortKeys"))
        ) {

            return elementName.substring(0, 1);

        }

        return elementName;

    }

    private static ClassLoader getDefaultLoader() {

        return GenericTextProviderSPI.class.getClassLoader();

    }

    private static int countOfMonths(String ct) {

        return (
            (ct.equals("coptic") || ct.equals("ethiopic") || ct.equals("generic") || ct.equals("hebrew")) ? 13 : 12);

    }

    private static int countOfEras(String ct) {

        if (ct.equals("hindu")) {
            return 6;
        }

        return (
            (ct.equals("ethiopic") || ct.equals("generic")
                || ct.equals("roc") || ct.equals("buddhist") || ct.equals("korean")) ? 2 : 1);

    }

}
