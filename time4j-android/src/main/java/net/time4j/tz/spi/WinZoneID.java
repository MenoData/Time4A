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

package net.time4j.tz.spi;

import net.time4j.tz.TZID;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;


/**
 * <p>The resolved id of a windows zone. </p>
 *
 * @author  Meno Hochschild
 * @since   4.4
 */
final class WinZoneID
    implements TZID, Serializable {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -4077231634935102213L;

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  canonical windows zone id
     */
    private final String id;

    //~ Konstruktoren -----------------------------------------------------

    WinZoneID(String id) {
        super();

        check(id);
        this.id = id;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public String canonical() {

        return this.id;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof WinZoneID) {
            WinZoneID that = (WinZoneID) obj;
            return this.id.equals(that.id);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return this.id.hashCode();

    }

    @Override
    public String toString() {

        return this.id;

    }

    private static void check(String id) {

        if (!id.startsWith("WINDOWS~")) {
            throw new IllegalArgumentException("Not a windows zone: " + id);
        }

    }

    /**
     * @serialData  Checks the consistency.
     * @throws      InvalidObjectException in case of inconsistencies
     */
    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        check(this.id);

    }

}
