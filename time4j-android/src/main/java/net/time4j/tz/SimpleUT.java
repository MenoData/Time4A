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

package net.time4j.tz;

import net.time4j.base.UnixTime;

/**
 * Simple implementation of UnixTime.
 *
 * @author  Meno Hochschild
 * @since   4.0
 */
class SimpleUT
    implements UnixTime {

    //~ Instanzvariablen --------------------------------------------------

    private final long posix;
    private final int nano;

    //~ Konstruktoren -----------------------------------------------------

    private SimpleUT(long posix, int nano) {
        super();

        this.posix = posix;
        this.nano = nano;
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public long getPosixTime() {
        return this.posix;
    }

    @Override
    public int getNanosecond() {
        return this.nano;
    }

    static UnixTime previousTime(UnixTime ut) {
        return previousTime(ut.getPosixTime(), ut.getNanosecond());
    }

    static UnixTime previousTime(
        long posix,
        int nano
    ) {
        return new SimpleUT(
            (nano == 0) ? (posix - 1) : posix,
            (nano == 0) ? 999999999 : (nano - 1)
        );
    }

}
