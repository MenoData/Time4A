/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2015 Meno Hochschild, <http://www.menodata.de/>
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

import net.time4j.android.spi.AndroidResourceLoader;
import net.time4j.base.ResourceLoader;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

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

    private static final AtomicBoolean REGISTERED = new AtomicBoolean(false);

    //~ Konstruktoren -----------------------------------------------------

    private ApplicationStarter() {
        // no instantiation
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>General purpose initialization. </p>
     *
     * @param   application     Android app
     * @since   3.5
     */
    /*[deutsch]
     * <p>Allgemein verwendbare Initialisierung. </p>
     *
     * @param   application     Android app
     * @since   3.5
     */
    public static void initialize(Application application) {

        prepareResources(application);
        registerReceiver(application.getApplicationContext());

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
            context.getApplicationContext().registerReceiver(
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

        System.setProperty(
            "net.time4j.base.ResourceLoader",
            "net.time4j.android.spi.AndroidResourceLoader");
        ((AndroidResourceLoader) ResourceLoader.getInstance()).init(application);

    }

    //~ Innere Klassen ----------------------------------------------------

    private static class TimezoneChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String ref = intent.getStringExtra("time-zone");

            try {
                TZID tzid = Timezone.of(ref).getID();
                Timezone.Cache.refresh();

                Log.i(
                    "time4j-android",
                    "Event ACTION_TIMEZONE_CHANGED received, system timezone changed to: ["
                        + tzid.canonical()
                        + "]. Changed android timezone was: ["
                        + ref
                        + "]");
            } catch (IllegalArgumentException iae) {
                Log.e(
                    "time4j-android",
                    "Could not recognize timezone id: [" + ref + "]", iae);
            }
        }

    }

}
