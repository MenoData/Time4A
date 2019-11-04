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

    private static final String VERSION = "v4.4.3-2019c";
    private static final int RELEASE_YEAR = 2019;
    private static final int RELEASE_MONTH = 11;
    private static final int RELEASE_DAY = 4;
    private static final String TIME4A = "TIME4A";

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

        initialize(
            context,
            prefetch
                ? new Runnable() {
                    @Override
                    public void run() {
                        long start2 = System.nanoTime();
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
                }
                : null
        );

    }

    /**
     * <p>Customizable initialization. </p>
     *
     * <p>This kind of initialization enables to determine which parts of Time4A should be
     * prefetched. However, the class {@code PlainDate} will always be prefetched. </p>
     *
     * @param   context     Android context
     * @param   prefetch    some code to be performed on background thread (optional)
     * @since   4.4.2
     */
    /*[deutsch]
     * <p>Benutzerdefinierte Initialisierung. </p>
     *
     * <p>Diese Art der Initialisierung erm&ouml;glicht es festzulegen, welche Teile von Time4A
     * im Hintergrund vorgeladen werden. Jedoch wird die Klasse {@code PlainDate} immer vorher
     * geladen. </p>
     *
     * @param   context     Android context
     * @param   prefetch    some code to be performed on background thread (optional)
     * @since   4.4.2
     */
    public static void initialize(
        Context context,
        Runnable prefetch
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

        if (prefetch != null) {
            Executors.defaultThreadFactory().newThread(prefetch).start();
        }

        long delta = (System.nanoTime() - start) / 1000000;
        Log.i(TIME4A, "Main-Thread consumed in ms: " + delta);

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
