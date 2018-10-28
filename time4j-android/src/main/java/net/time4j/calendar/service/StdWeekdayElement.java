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

package net.time4j.calendar.service;

import net.time4j.Weekday;
import net.time4j.Weekmodel;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoEntity;


/**
 * <p>General weekday element for weeks which start on Sunday. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
/*[deutsch]
 * <p>Allgemeines Wochentagsselement f&uuml;r Wochen, die am Sonntag beginnen. </p>
 *
 * @author  Meno Hochschild
 * @since   3.5/4.3
 */
public final class StdWeekdayElement<T extends ChronoEntity<T>>
    extends StdEnumDateElement<Weekday, T> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -84764920511581480L;

    //~ Instanzvariablen --------------------------------------------------

    private transient final Weekmodel model;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Standard constructor. </p>
     *
     * @param   chrono      chronological type which registers this element
     * @param   model       default week model
     */
    /*[deutsch]
     * <p>Standard-Konstruktor. </p>
     *
     * @param   chrono      chronological type which registers this element
     * @param   model       default week model
     */
    public StdWeekdayElement(
        Class<T> chrono,
        Weekmodel model
    ) {
        super("DAY_OF_WEEK", chrono, Weekday.class, 'E');

        this.model = model;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Weekday getDefaultMinimum() {

        return this.model.getFirstDayOfWeek();

    }

    @Override
    public Weekday getDefaultMaximum() {

        return this.model.getFirstDayOfWeek().roll(6);

    }

    @Override
    public int numerical(Weekday value) {

        return value.getValue(this.model);

    }

    @Override
    public int compare(
        ChronoDisplay o1,
        ChronoDisplay o2
    ) {

        int i1 = o1.get(this).getValue(this.model);
        int i2 = o2.get(this).getValue(this.model);
        return ((i1 < i2) ? -1 : ((i1 == i2) ? 0 : 1));

    }

}
