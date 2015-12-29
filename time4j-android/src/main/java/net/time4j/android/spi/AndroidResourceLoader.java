/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2015 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (AndroidResourceLoader.java) is part of project Time4J.
 *
 * Time4J is free software: You can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Time4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Time4J. If not, see <http://www.gnu.org/licenses/>.
 * -----------------------------------------------------------------------
 */

package net.time4j.android.spi;

import android.app.Application;
import android.text.format.DateFormat;

import net.time4j.base.ResourceLoader;
import net.time4j.calendar.service.EthiopianExtension;
import net.time4j.calendar.service.GenericTextProviderSPI;
import net.time4j.engine.ChronoExtension;
import net.time4j.format.DisplayMode;
import net.time4j.format.FormatEngine;
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
import net.time4j.i18n.UltimateFormatEngine;
import net.time4j.i18n.UnitPatternProviderSPI;
import net.time4j.i18n.WeekdataProviderSPI;
import net.time4j.scale.LeapSecondProvider;
import net.time4j.scale.TickProvider;
import net.time4j.tz.ZoneProvider;
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
        tmp.put(ZoneProvider.class, new LazyZoneData());
        tmp.put(LeapSecondProvider.class, new LazyLeapseconds());
        tmp.put(ChronoExtension.class, new LazyExtensions());
        tmp.put(FormatEngine.class, Collections.singleton(UltimateFormatEngine.INSTANCE));
        tmp.put(NumberSymbolProvider.class, new LazyNumberSymbols());
        tmp.put(PluralProvider.class, new LazyPluraldata());
        tmp.put(UnitPatternProvider.class, Collections.singleton(new UnitPatternProviderSPI()));
        tmp.put(WeekdataProvider.class, new LazyWeekdata());
        tmp.put(TickProvider.class, Collections.singleton(new AndroidTickerSPI()));
        PROVIDERS = Collections.unmodifiableMap(tmp);

        Set<String> set = new HashSet<String>();
        set.add("i18n");
        set.add("calendar");
        set.add("tzdata");
        MODULES = Collections.unmodifiableSet(set);
    }

    //~ Instanzvariablen --------------------------------------------------

    private Application application = null;
    private List<FormatPatternProvider> patterns = Collections.emptyList();

    //~ Methoden ----------------------------------------------------------

    /**
     * Sets the application context.
     *
     * @param   application     Android app
     */
    public void init(Application application) {

        this.application = application;

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
            } else if (uri.isAbsolute() || (this.application == null)) {
                URLConnection conn = uri.toURL().openConnection();
                conn.setUseCaches(false);
                return conn.getInputStream();
            } else {
                return this.application.getAssets().open(uri.toString());
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

    private static final class LazyTextdata
        implements Iterable<TextProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<TextProvider> iterator() {

            return TextdataHolder.ITERABLE.iterator();

        }

    }

    private static final class TextdataHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<TextProvider> ITERABLE;

        static {
            List<TextProvider> providers =
                Arrays.asList(new IsoTextProviderSPI(), new GenericTextProviderSPI());
            ITERABLE = Collections.unmodifiableList(providers);
        }

    }

    private class AndroidFormatPatterns
        implements ExtendedPatterns {

        //~ Instanzvariablen ----------------------------------------------

        private final ExtendedPatterns delegate;

        //~ Konstruktoren -------------------------------------------------

        AndroidFormatPatterns() {
            super();

            this.delegate = new IsoTextProviderSPI();

        }

        //~ Methoden ------------------------------------------------------

        @Override
        public String getDatePattern(
            DisplayMode mode,
            Locale locale
        ) {

            return this.delegate.getDatePattern(mode, locale);

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

            String pattern = this.delegate.getTimePattern(mode, locale, alt);

            if (Locale.getDefault().equals(locale)) {
                String testPattern = pattern;
                if (mode != DisplayMode.SHORT) { // we assume that short style has no literals
                    testPattern = this.delegate.getTimePattern(DisplayMode.SHORT, locale);
                }
                boolean has24HourFormat = (testPattern.indexOf('a') == -1);
                boolean use24HourFormat = DateFormat.is24HourFormat(application);

                if (use24HourFormat != has24HourFormat) {
                    if (use24HourFormat) {
                        return to24HourFormat(pattern).replace("  ", " ").trim();
                    } else {
                        switch (mode) { // let's choose a format pattern in English style
                            case FULL:
                                return "h:mm:ss a zzzz";
                            case LONG:
                                return "h:mm:ss a z";
                            case MEDIUM:
                                return "h:mm:ss a";
                            default:
                                return "h:mm a";
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

            return this.delegate.getDateTimePattern(dateMode, timeMode, locale);

        }

        @Override
        public String getIntervalPattern(Locale locale) {

            return this.delegate.getIntervalPattern(locale);

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

    }

    private static final class LazyWeekdata
        implements Iterable<WeekdataProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<WeekdataProvider> iterator() {

            return WeekdataHolder.ITERABLE.iterator();

        }

    }

    private static final class WeekdataHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<WeekdataProvider> ITERABLE;

        static {
            WeekdataProvider provider = new WeekdataProviderSPI();
            ITERABLE = Collections.singletonList(provider);
        }

    }

    private static final class LazyZoneData
        implements Iterable<ZoneProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<ZoneProvider> iterator() {

            return ZoneDataHolder.ITERABLE.iterator();

        }

    }

    private static final class ZoneDataHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<ZoneProvider> ITERABLE;

        static {
            ZoneProvider tzdb = new TimezoneRepositoryProviderSPI();
            ZoneProvider tznames = new ZoneNameProviderSPI();
            ITERABLE = Collections.unmodifiableList(Arrays.asList(tzdb, tznames));
        }

    }

    private static final class LazyLeapseconds
        implements Iterable<LeapSecondProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<LeapSecondProvider> iterator() {

            return LeapsecondHolder.ITERABLE.iterator();

        }

    }

    private static final class LeapsecondHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<LeapSecondProvider> ITERABLE;

        static {
            LeapSecondProvider provider = null;
            for (ZoneProvider zp : ZoneDataHolder.ITERABLE) {
                if (zp instanceof LeapSecondProvider) {
                    provider = LeapSecondProvider.class.cast(zp);
                    break;
                }
            }
            if (provider == null) {
                ITERABLE = Collections.emptyList();
            } else {
                ITERABLE = Collections.singleton(provider);
            }
        }

    }

    private static final class LazyPluraldata
        implements Iterable<PluralProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<PluralProvider> iterator() {

            return PluraldataHolder.ITERABLE.iterator();

        }

    }

    private static final class PluraldataHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<PluralProvider> ITERABLE;

        static {
            PluralProvider provider = new PluralProviderSPI();
            ITERABLE = Collections.singleton(provider);
        }

    }

    private static final class LazyNumberSymbols
            implements Iterable<NumberSymbolProvider> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<NumberSymbolProvider> iterator() {

            return NumberSymbolHolder.ITERABLE.iterator();

        }

    }

    private static final class NumberSymbolHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<NumberSymbolProvider> ITERABLE;

        static {
            NumberSymbolProvider provider = new SymbolProviderSPI();
            ITERABLE = Collections.singleton(provider);
        }

    }

    private static final class LazyExtensions
        implements Iterable<ChronoExtension> {

        //~ Methoden ------------------------------------------------------

        @Override
        public Iterator<ChronoExtension> iterator() {

            return ExtensionHolder.ITERABLE.iterator();

        }

    }

    private static final class ExtensionHolder {

        //~ Statische Felder/Initialisierungen ----------------------------

        private static final Iterable<ChronoExtension> ITERABLE;

        static {
            ChronoExtension historic = new HistoricExtension();
            ChronoExtension ethiopic = new EthiopianExtension();
            ITERABLE = Collections.unmodifiableList(Arrays.asList(historic, ethiopic));
        }

    }

}
