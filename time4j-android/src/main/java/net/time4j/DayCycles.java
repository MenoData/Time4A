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

package net.time4j;

import java.io.Serializable;


/**
 * <p>Represents the rolling result of a plain time if a possible day overflow
 * is to be taken into account. </p>
 *
 * @author              Meno Hochschild
 * @see                 PlainTime#roll(long,ClockUnit)
 */
/*[deutsch]
 * <p>Verk&ouml;rpert das Rollergebnis auf einer Uhrzeit, wenn auch ein
 * tageweiser &Uuml;berlauf gez&auml;hlt werden soll. </p>
 *
 * @author              Meno Hochschild
 * @see                 PlainTime#roll(long,ClockUnit)
 */
public final class DayCycles implements Serializable {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -4124961309622141228L;

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  day overflow
     */
    /*[deutsch]
     * @serial  &Uuml;berlauf in Tagen
     */
    private final long days;

    /**
     * @serial  wall time
     */
    /*[deutsch]
     * @serial  Uhrzeit
     */
    private final PlainTime time;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Konstruiert eine neue Instanz mit &Uuml;berlauf und Uhrzeit. </p>
     *
     * @param   days    day overflow
     * @param   time    wall time result of last calculation
     */
    DayCycles(long days, PlainTime time) {
        super();
        this.days = days;
        this.time = time;
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the day overflow after rolling a plain time. </p>
     *
     * @return  count of day cycles ({@code 0} if without overflow)
     */
    /*[deutsch]
     * <p>Ermittelt den tageweise &Uuml;berlauf im Additionsergebnis. </p>
     *
     * @return  count of day cycles ({@code 0} if without overflow)
     */
    public long getDayOverflow() {
        return this.days;
    }

    /**
     * <p>Gets the rolled wall time. </p>
     *
     * @return  wall time (never 24:00)
     */
    /*[deutsch]
     * <p>Ermittelt die Uhrzeit. </p>
     *
     * @return  wall time (never 24:00)
     */
    public PlainTime getWallTime() {
        return this.time;
    }

}
