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

import android.os.SystemClock;
import net.time4j.base.MathUtils;
import net.time4j.scale.TickProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * <p>{@code ServiceProvider}-implementation for accessing the non-freezing clock of Android. </p>
 *
 * @author  Meno Hochschild
 */
class AndroidTickerSPI
    implements TickProvider {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final Class[] EMPTY_PARAMS = new Class[0];
    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final Method ANDROID; // available if API level is 17 or higher

    static {
        Method method;
        try {
            method = SystemClock.class.getMethod("elapsedRealtimeNanos", EMPTY_PARAMS);
            method.invoke(null, EMPTY_ARGS); // test
        } catch (NoSuchMethodException e) {
            method = null;
        } catch (InvocationTargetException e) {
            method = null;
        } catch (IllegalAccessException e) {
            method = null;
        } catch (RuntimeException e) {
            method = null;
        }
        ANDROID = method;
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public String getPlatform() {

        return "Dalvik";

    }

    @Override
    public long getNanos() {

        if (ANDROID != null) {
            try {
                return (Long) ANDROID.invoke(null, EMPTY_ARGS);
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.err);
            } catch (InvocationTargetException e) {
                e.printStackTrace(System.err);
            }
        }

        return MathUtils.safeMultiply(SystemClock.elapsedRealtime(), 1000000L);

    }

}
