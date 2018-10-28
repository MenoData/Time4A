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

package net.time4j.android.spi;

import android.content.Context;
import android.text.format.DateFormat;

import net.time4j.android.AssetLocation;
import net.time4j.base.ResourceLoader;
import net.time4j.calendar.service.GenericTextProviderSPI;
import net.time4j.calendar.service.KoreanExtension;
import net.time4j.engine.ChronoExtension;
import net.time4j.format.DisplayMode;
import net.time4j.format.FormatPatternProvider;
import net.time4j.format.NumberSymbolProvider;
import net.time4j.format.PluralProvider;
import net.time4j.format.TextProvider;
import net.time4j.format.UnitPatternProvider;
import net.time4j.format.WeekdataProvider;
import net.time4j.format.internal.ExtendedPatterns;
import net.time4j.i18n.HistoricExtension;
import net.time4j.i18n.IsoTextProviderSPI;
import net.time4j.i18n.PluralProviderSPI;
import net.time4j.i18n.SymbolProviderSPI;
import net.time4j.i18n.UnitPatternProviderSPI;
import net.time4j.i18n.WeekdataProviderSPI;
import net.time4j.scale.LeapSecondProvider;
import net.time4j.scale.TickProvider;
import net.time4j.tz.ZoneModelProvider;
import net.time4j.tz.ZoneNameProvider;
import net.time4j.tz.spi.TimezoneRepositoryProviderSPI;
import net.time4j.tz.spi.ZoneNameProviderSPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;


/**
 * <p>An Android-specific {@code ResourceLoader}-implementation for internal use only. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5
 */
public class AndroidResourceLoader
    extends ResourceLoader {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final Map<Class<?>, Iterable<?>> PROVIDERS;
    private static final Set<String> MODULES;

    static {
        Map<Class<?>, Iterable<?>> tmp = new HashMap<Class<?>, Iterable<?>>();
        tmp.put(TextProvider.class, new LazyTextdata());
        tmp.put(ZoneModelProvider.class, new LazyZoneRules());
        tmp.put(ZoneNameProvider.class, new LazyZoneNames());
        tmp.put(LeapSecondProvider.class, new LazyLeapseconds());
        tmp.put(ChronoExtension.class, new LazyExtensions());
        tmp.put(NumberSymbolProvider.class, new LazyNumberSymbols());
        tmp.put(PluralProvider.class, new LazyPluraldata());
        tmp.put(UnitPatternProvider.class, Collections.singleton(new UnitPatternProviderSPI()));
        tmp.put(WeekdataProvider.class, new LazyWeekdata());
        tmp.put(TickProvider.class, Collections.singleton(new AndroidTickerSPI()));
        PROVIDERS = Collections.unmodifiableMap(tmp);

        Set<String> set = new HashSet<String>();
        set.add("i18n");
        set.add("calendar");
        set.add("olson");
        set.add("tzdata");
        MODULES = Collections.unmodifiableSet(set);
    }

    //~ Instanzvariablen --------------------------------------------------

    private Context context = null;
    private AssetLocation assetLocation = null;
    private List<FormatPatternProvider> patterns = Collections.emptyList();

    //~ Methoden ----------------------------------------------------------

    /**
     * Sets the application context and the asset environment.
     *
     * @param   context         Android app or context
     * @param   assetLocation   asset environment (optional)
     */
    public void init(
        Context context,
        AssetLocation assetLocation
    ) {

        if (context == null) {
            throw new NullPointerException("Missing Android-context.");
        }

        this.context = context;
        this.assetLocation = assetLocation;

        FormatPatternProvider p = new AndroidFormatPatterns();
        this.patterns = Collections.singletonList(p);

    }

    @Override
    public URI locate(
        String moduleName,
        Class<?> moduleRef,
        String path
    ) {

        try {
            if (MODULES.contains(moduleName)) {
                StringBuilder sb = new StringBuilder();
                sb.append("net/time4j/");
                sb.append(moduleName);
                sb.append('/');
                sb.append(path);
                return new URI(sb.toString());
            }

            // last try - ask the class loader
            URL url = moduleRef.getClassLoader().getResource(path);
            if (url != null) {
                return url.toURI();
            }
        } catch (URISyntaxException e) {
            // now we give up
        }

        return null;

    }

    @Override
    public InputStream load(
        URI uri,
        boolean noCache
    ) {

        try {
            if (uri == null) {
                return null;
            } else if (uri.isAbsolute()) {
                URLConnection conn = uri.toURL().openConnection();
                conn.setUseCaches(false);
                return conn.getInputStream();
            } else if (this.assetLocation != null) {
                return this.assetLocation.open(uri.toString());
            } else if (this.context == null) {
                throw new IllegalStateException(
                    "'ApplicationStarter.initialize(context)' must be called first at app start.");
            } else {
                return this.context.getAssets().open(uri.toString());
            }
        } catch (IOException ioe) {
            return null;
        }

    }

    @Override
    public <S> Iterable<S> services(Class<S> serviceInterface) {

        Iterable<?> ret = PROVIDERS.get(serviceInterface);

        if (ret == null) {
            if (serviceInterface == FormatPatternProvider.class) {
                ret = this.patterns;
            } else {
                return ServiceLoader.load(serviceInterface, serviceInterface.getClassLoader());
            }
        }

        return cast(ret);

    }

    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj) {

        return (T) obj;

    }

    //~ Innere Klassen ----------------------------------------------------

    private class AndroidFormatPatterns
        implements ExtendedPatterns {

        //~ Methoden ------------------------------------------------------

        @Override
        public String getDatePattern(
            DisplayMode mode,
            Locale locale
        ) {

            return this.getDelegate().getDatePattern(mode, locale);

        }

        @Override
        public String getTimePattern(
            DisplayMode mode,
            Locale locale
        ) {

            return this.getTimePattern(mode, locale, false);

        }

        @Override
        public String getTimePattern(
            DisplayMode mode,
            Locale locale,
            boolean alt
        ) {

            String pattern = this.getDelegate().getTimePattern(mode, locale, alt);

            if (Locale.getDefault().equals(locale)) {
                String testPattern = pattern;
                if (mode != DisplayMode.SHORT) { // we assume that short style has no literals
                    testPattern = this.getDelegate().getTimePattern(DisplayMode.SHORT, locale);
                }
                boolean has24HourFormat = (testPattern.indexOf('a') == -1);
                boolean use24HourFormat = DateFormat.is24HourFormat(context);

                if (use24HourFormat != has24HourFormat) {
                    if (use24HourFormat) {
                        return to24HourFormat(pattern).replace("  ", " ").trim();
                    } else {
                        String dpSymbol = (locale.getLanguage().equals("en") ? "b" : "B");
                        switch (mode) {
                            case FULL:
                                return "h:mm:ss " + dpSymbol + " zzzz";
                            case LONG:
                                return "h:mm:ss " + dpSymbol + " z";
                            case MEDIUM:
                                return "h:mm:ss " + dpSymbol;
                            default:
                                return "h:mm " + dpSymbol;
                        }
                    }
                }
            }

            return pattern;

        }

        @Override
        public String getDateTimePattern(
            DisplayMode dateMode,
            DisplayMode timeMode,
            Locale locale
        ) {

            return this.getDelegate().getDateTimePattern(dateMode, timeMode, locale);

        }

        @Override
        public String getIntervalPattern(Locale locale) {

            return this.getDelegate().getIntervalPattern(locale);

        }

        private String to24HourFormat(String pattern) {

            StringBuilder sb = new StringBuilder();
            boolean literal = false;

            for (int i = 0, n = pattern.length(); i < n; i++) {
                char c = pattern.charAt(i);

                if (c == '\'') {
                    sb.append(c);
                    i++;

                    while (i < n) {
                        c = pattern.charAt(i);
                        if (c == '\'') {
                            sb.append(c);
                            if (
                                (i + 1 < n)
                                && (pattern.charAt(i + 1) == '\'')
                            ) {
                                i++;
                            } else {
                                break;
                            }
                        }
                        sb.append(c);
                        i++;
                    }
                } else if (c == 'h') {
                    sb.append('H');
                } else if (c != 'a') {
                    sb.append(c);
                }
            }

            return sb.toString();

        }

        private ExtendedPatterns getDelegate() {

            return I18nDataHolder.ISODATA;

        }

    }

    private static final class LazyNumberSymbols
        implements Iterable<NumberSymbolProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<NumberSymbolProvider> iterator() {

            return I18nDataHolder.SYMBOLS.iterator();

        }

    }

    private static final class LazyWeekdata
        implements Iterable<WeekdataProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<WeekdataProvider> iterator() {

            return I18nDataHolder.WEEKDATA.iterator();

        }

    }

    private static final class LazyTextdata
        implements Iterable<TextProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<TextProvider> iterator() {

            return I18nDataHolder.TEXTDATA.iterator();

        }

    }

    private static final class LazyZoneRules
        implements Iterable<ZoneModelProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<ZoneModelProvider> iterator() {

            return ZoneDataHolder.RULES.iterator();

        }

    }

    private static final class LazyZoneNames
        implements Iterable<ZoneNameProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<ZoneNameProvider> iterator() {

            return ZoneDataHolder.NAMES.iterator();

        }

    }

    private static final class LazyLeapseconds
        implements Iterable<LeapSecondProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<LeapSecondProvider> iterator() {

            return ZoneDataHolder.LEAPSECONDS.iterator();

        }

    }

    private static final class LazyPluraldata
        implements Iterable<PluralProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<PluralProvider> iterator() {

            return StatelessIterables.PLURALS.iterator();

        }

    }

    private static final class LazyExtensions
        implements Iterable<ChronoExtension> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<ChronoExtension> iterator() {

            return StatelessIterables.EXTENSIONS.iterator();

        }

    }

    // lazy class loading finally triggers disc access for i18n-resources here
    private static final class I18nDataHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final IsoTextProviderSPI ISODATA;
        private static final Iterable<NumberSymbolProvider> SYMBOLS;
        private static final Iterable<WeekdataProvider> WEEKDATA;
        private static final Iterable<TextProvider> TEXTDATA;

        static {
            ISODATA = new IsoTextProviderSPI();

            NumberSymbolProvider symbolProvider = SymbolProviderSPI.INSTANCE;
            SYMBOLS = Collections.singleton(symbolProvider);

            WeekdataProvider weekdataProvider = new WeekdataProviderSPI();
            WEEKDATA = Collections.singletonList(weekdataProvider);

            List<TextProvider> textProviders = Arrays.asList(ISODATA, new GenericTextProviderSPI());
            TEXTDATA = Collections.unmodifiableList(textProviders);
        }

    }

    // lazy class loading finally triggers disc access for tz repository here
    private static final class ZoneDataHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<ZoneModelProvider> RULES;
        private static final Iterable<ZoneNameProvider> NAMES;
        private static final Iterable<LeapSecondProvider> LEAPSECONDS;

        static {
            ZoneModelProvider tzdb = new TimezoneRepositoryProviderSPI();
            RULES = Collections.singleton(tzdb);

            ZoneNameProvider provider = new ZoneNameProviderSPI();
            NAMES = Collections.singleton(provider);

            LeapSecondProvider leapSecondProvider = null;
            for (ZoneModelProvider zp : RULES) {
                if (zp instanceof LeapSecondProvider) {
                    leapSecondProvider = LeapSecondProvider.class.cast(zp);
                    break;
                }
            }
            if (leapSecondProvider == null) {
                LEAPSECONDS = Collections.emptyList();
            } else {
                LEAPSECONDS = Collections.singleton(leapSecondProvider);
            }
        }

    }

    private static final class StatelessIterables {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<PluralProvider> PLURALS;
        private static final Iterable<ChronoExtension> EXTENSIONS;

        static {
            PluralProvider pluralProvider = new PluralProviderSPI();
            PLURALS = Collections.singleton(pluralProvider);

            EXTENSIONS = Arrays.asList(new HistoricExtension(), new KoreanExtension());
        }

    }

}
