/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2016 Meno Hochschild, <http://www.menodata.de/>
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

    private static final String VERSION = "v3.24-2016i";
    private static final int RELEASE_YEAR = 2016;
    private static final int RELEASE_MONTH = 11;
    private static final int RELEASE_DAY = 3;
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
        Context ctx = context.getApplicationContext();
        prepareAssets(ctx); // initialize the class AndroidResourceLoader (must be called first)

        // ensures that class PlainDate is loaded before any further initialization
        PlainDate releaseDate = PlainDate.of(RELEASE_YEAR, RELEASE_MONTH, RELEASE_DAY);
        Log.i(TIME4A, "Starting Time4A (" + VERSION + " published on " + releaseDate +")");

        registerReceiver(ctx);

        if (prefetch) {
            final long start2 = System.nanoTime();
            Runnable runnable =
                new Runnable() {
                    @Override
                    public void run() {
                        DisplayMode style = DisplayMode.FULL;
                        TZID tzid = Timezone.ofSystem().getID();
                        Log.i(
                            TIME4A,
                            ChronoFormatter.ofMomentStyle(
                                style,
                                style,
                                Locale.getDefault(),
                                tzid
                            ).format(SystemClock.currentMoment())
                        );
                        long delta = (System.nanoTime() - start2) / 1000000;
                        Log.i(TIME4A, "Prefetch-Thread consumed in ms: " + delta);
                    }
                };
            Executors.defaultThreadFactory().newThread(runnable).start();
        }

        long delta = (System.nanoTime() - start) / 1000000;
        Log.i(TIME4A, "Main-Thread consumed in ms: " + delta);

    }

    /**
     * <p>Registers a timezone-change-receiver on given app context. </p>
     *
     * @param   context     application context
     */
    /*[deutsch]
     * <p>Registriert einen {@code BroadcastReceiver} f&uuml;r Zeitzonen&auml;nderungen
     * auf dem angegebenen Anwendungskontext. </p>
     *
     * @param   context     application context
     */
    public static void registerReceiver(Context context) {

        if (!REGISTERED.getAndSet(true)) {
            context.registerReceiver(
                new TimezoneChangedReceiver(),
                new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED));
        }

    }

    /**
     * <p>Prepares and optimizes the access to all internal resource files. </p>
     *
     * @param   application     Android app
     * @since   3.5
     */
    /*[deutsch]
     * <p>Bereitet den allgemeinen Ressourcenzugriff vor und optimiert ihn. </p>
     *
     * @param   application     Android app
     * @since   3.5
     */
    public static void prepareResources(Application application) {

        prepareAssets(application);

    }

    private static void prepareAssets(Context context) {

        if (!PREPARED.getAndSet(true)) {
            System.setProperty(
                "net.time4j.base.ResourceLoader",
                "net.time4j.android.spi.AndroidResourceLoader");
            ((AndroidResourceLoader) ResourceLoader.getInstance()).init(context);
        }

    }

    //~ Innere Klassen ----------------------------------------------------

    private static class TimezoneChangedReceiver extends BroadcastReceiver {

        //~ Methoden ------------------------------------------------------

        @Override
        public void onReceive(Context context, Intent intent) {
            String ref = intent.getStringExtra("time-zone");

            try {
                TZID tzid = Timezone.of(ref).getID();
                Timezone.Cache.refresh();

                Log.i(
                    TIME4A,
                    "Event ACTION_TIMEZONE_CHANGED received, system timezone changed to: ["
                        + tzid.canonical()
                        + "]. Changed android timezone was: ["
                        + ref
                        + "]");
            } catch (IllegalArgumentException iae) {
                Log.e(
                    TIME4A,
                    "Could not recognize timezone id: [" + ref + "]", iae);
            }
        }

    }

}
