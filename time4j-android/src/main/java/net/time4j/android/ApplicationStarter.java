/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2018 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (ApplicationStarter.java) is part of project Time4J.
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

package net.time4j.android;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import net.time4j.PlainDate;
import net.time4j.SystemClock;
import net.time4j.android.spi.AndroidResourceLoader;
import net.time4j.base.ResourceLoader;
import net.time4j.format.DisplayMode;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * <p>Serves for the registration of a timezone-change-receiver and must be called once
 * during the start of an android application. </p>
 *
 * @author      Meno Hochschild
 * @since       3.2
 */
/*[deutsch]
 * <p>Dient der Registrierung eines {@code BroadcastReceiver} f&uuml;r Zeitzonenauml;nderungen
 * und mu&szlig; einmalig zum Start einer Android-App aufgerufen werden. </p>
 *
 * @author      Meno Hochschild
 * @since       3.2
 */
public class ApplicationStarter {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final String VERSION = "v3.44.4-2018e";
    private static final int RELEASE_YEAR = 2018;
    private static final int RELEASE_MONTH = 9;
    private static final int RELEASE_DAY = 26;
    private static final String TIME4A = "time4a";

    private static final AtomicBoolean PREPARED = new AtomicBoolean(false);
    private static final AtomicBoolean REGISTERED = new AtomicBoolean(false);

    //~ Konstruktoren -----------------------------------------------------

    private ApplicationStarter() {
        // no instantiation
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Equivalent to {@code initialize(application, false)}. </p>
     *
     * @param   application     Android app
     * @since   3.5
     */
    /*[deutsch]
     * <p>&Auml;quivalent zu {@code initialize(application, false)}. </p>
     *
     * @param   application     Android app
     * @since   3.5
     */
    public static void initialize(Application application) {

        initialize(application, false);

    }

    /**
     * <p>General purpose initialization. </p>
     *
     * @param   context     Android context
     * @param   prefetch    Getting standard data on background thread?
     * @since   3.18
     */
    /*[deutsch]
     * <p>Allgemein verwendbare Initialisierung. </p>
     *
     * @param   context     Android context
     * @param   prefetch    Getting standard data on background thread?
     * @since   3.18
     */
    public static void initialize(
        Context context,
        boolean prefetch
    ) {

//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(
//                new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()
//                .penaltyLog()
//                .build());
//        }

        long start = System.nanoTime();

        prepareAssets(context, null); // initialize AndroidResourceLoader (must be called first)
        registerReceiver(context.getApplicationContext()); // includes NPE-check

        // ensures that class PlainDate is loaded before any further initialization
        PlainDate releaseDate = PlainDate.of(RELEASE_YEAR, RELEASE_MONTH, RELEASE_DAY);
        Log.i(TIME4A, "Starting Time4A (" + VERSION + " published on " + releaseDate +")");

        if (prefetch) {
            final long start2 = System.nanoTime();
            Runnable runnable =
                new Runnable() {
                    @Override
                    public void run() {
                        DisplayMode style = DisplayMode.FULL;
                        TZID tzid = Timezone.ofSystem().getID();
                        Locale sysloc = Locale.getDefault();
                        Log.i(TIME4A, "System time zone at start: [" + tzid.canonical() + "]");
                        Log.i(TIME4A, "System locale at start: [" + sysloc.toString() + "]");
                        try {
                            String currentTime =
                                ChronoFormatter.ofMomentStyle(
                                    style,
                                    style,
                                    sysloc,
                                    tzid
                                ).format(SystemClock.currentMoment());
                            Log.i(TIME4A, currentTime);
                        } catch (RuntimeException re) {
                            Log.e(
                                TIME4A,
                                "Error on prefetch thread with: time zone="
                                    + tzid.canonical()
                                    + ", locale=" + sysloc + "!",
                                re);
                            throw re; // rethrow for Google Play Store etc.
                        }
                        long delta = (System.nanoTime() - start2) / 1000000;
                        Log.i(TIME4A, "Prefetch thread consumed (in ms): " + delta);
                    }
                };
            Executors.defaultThreadFactory().newThread(runnable).start();
        }

        long delta = (System.nanoTime() - start) / 1000000;
        Log.i(TIME4A, "Main-Thread consumed in ms: " + delta);

    }

    /**
     * <p>Prepares and optimizes the access to all internal resource files. </p>
     *
     * <p>The application parameter should be specified otherwise Time4A will try to
     * find the assets via the classloader and is likely to fail. </p>
     *
     * @param   application     Android app
     * @since   3.5
     * @deprecated  Use {@link #prepareAssets(Context, AssetLocation)}
     */
    /*[deutsch]
     * <p>Bereitet den allgemeinen Ressourcenzugriff vor und optimiert ihn. </p>
     *
     * <p>Der Anwendungskontext sollte angegeben werden, sonst versucht Time4A
     * die Ressourcen &uuml;ber den {@code Classloader} zu finden, was wahrscheinlich
     * scheitert. </p>
     *
     * @param   application     Android app
     * @since   3.5
     * @deprecated  Use {@link #prepareAssets(Context, AssetLocation)}
     */
    @Deprecated
    public static void prepareResources(Application application) {

        prepareAssets(application, null);

    }

    /**
     * <p>Prepares and optimizes the access to all internal asset files. </p>
     *
     * <p>The context parameter must be specified while the second parameter
     * is optional. </p>
     *
     * @param   context         Android context
     * @param   assetLocation   environment of assets (optional, maybe {@code null})
     * @since   3.28
     */
    /*[deutsch]
     * <p>Bereitet den allgemeinen <i>asset</i>-Zugriff vor und optimiert ihn. </p>
     *
     * <p>Der Android-Kontext mu&szlig; angegeben werden, w&auml;hrend der zweite
     * Parameter optional ist. </p>
     *
     * @param   context         Android context
     * @param   assetLocation   environment of assets (optional, maybe {@code null})
     * @since   3.28
     */
    public static void prepareAssets(
        Context context,
        AssetLocation assetLocation
    ) {

        if (!PREPARED.getAndSet(true)) {
            System.setProperty(
                "net.time4j.base.ResourceLoader",
                "net.time4j.android.spi.AndroidResourceLoader");
            ((AndroidResourceLoader) ResourceLoader.getInstance()).init(context, assetLocation);
        }

    }

    /**
     * <p>Registers a timezone-change-receiver on given app context. </p>
     *
     * <p>If the context parameter is {@code null} then this method does nothing.
     * In this case, any change of the system timezone will not be tracked. </p>
     *
     * @param   context     application context
     */
    /*[deutsch]
     * <p>Registriert einen {@code BroadcastReceiver} f&uuml;r Zeitzonen&auml;nderungen
     * auf dem angegebenen Anwendungskontext. </p>
     *
     * <p>Wenn der &uuml;bergebene context-Parameter {@code null} ist, macht diese
     * Methode nichts. Eine &Auml;nderung der System-Zeitzone wird dann nicht
     * &uuml;berwacht. </p>
     *
     * @param   context     application context
     */
    public static void registerReceiver(Context context) {

        if ((context != null) && !REGISTERED.getAndSet(true)) {
            System.setProperty(
                "net.time4j.allow.system.tz.override",
                "true");
            context.registerReceiver(
                new TimezoneChangedReceiver(),
                new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED));
        }

    }

    //~ Innere Klassen ----------------------------------------------------

    private static class TimezoneChangedReceiver extends BroadcastReceiver {

        //~ Methoden ------------------------------------------------------

        @Override
        public void onReceive(Context context, Intent intent) {

            // makes Timezone.ofSystem() sensible for updates
            Timezone.Cache.refresh();

            // logging
            Log.i(
                TIME4A,
                "Event ACTION_TIMEZONE_CHANGED received, system timezone changed to: ["
                    + Timezone.ofSystem().getID().canonical()
                    + "]. Original tz-id reported by Android: ["
                    + intent.getStringExtra("time-zone")
                    + "]");

        }

    }

}
