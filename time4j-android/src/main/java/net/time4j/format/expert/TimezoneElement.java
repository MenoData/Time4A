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

package net.time4j.format.expert;

import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.format.CalendarText;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.TZID;
import net.time4j.tz.ZonalOffset;

import java.util.Locale;


/**
 * <p>Spezialelement als Zeiger auf eine Zeitzonen-ID. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
enum TimezoneElement
    implements ChronoElement<TZID> {

    //~ Statische Felder/Initialisierungen --------------------------------

    TIMEZONE_ID,

    TIMEZONE_OFFSET;

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<TZID> getType() {

        return TZID.class;

    }

    @Override
    public char getSymbol() {

        return '\u0000';

    }

    /**
     * <p>Vergleicht die kanonischen Darstellungen auf lexikalischer Basis. </p>
     */
    @Override
    public int compare(
        ChronoDisplay o1,
        ChronoDisplay o2
    ) {

        TZID t1 = o1.getTimezone();
        TZID t2 = o2.getTimezone();

        return t1.canonical().compareTo(t2.canonical());

    }

    /**
     * <p>Als Standardminimum gilt der Offset -14:00. </p>
     *
     * @return  smallest timezone offset according to XML Schema
     */
    @Override
    public TZID getDefaultMinimum() {

        return ZonalOffset.ofHours(OffsetSign.BEHIND_UTC, 14);

    }

    /**
     * <p>Als Standardmaximum gilt der Offset +14:00. </p>
     *
     * @return  largest timezone offset according to XML Schema
     */
    @Override
    public TZID getDefaultMaximum() {

        return ZonalOffset.ofHours(OffsetSign.AHEAD_OF_UTC, 14);

    }

    /**
     * <p>Ein zonales Element ist kein Datumselement. </p>
     *
     * @return  {@code false}
     */
    @Override
    public boolean isDateElement() {

        return false;

    }

    /**
     * <p>Ein zonales Element ist kein Uhrzeitelement. </p>
     *
     * @return  {@code false}
     */
    @Override
    public boolean isTimeElement() {

        return false;

    }

    /**
     * <p>Ein zonales Element ist nicht nachsichtig. </p>
     *
     * @return  {@code false}
     */
    @Override
    public boolean isLenient() {

        return false;

    }

    @Override
    public String getDisplayName(Locale language) {

        String lname = CalendarText.getIsoInstance(language).getTextForms().get("L_zone");
        return ((lname == null) ? this.name() : lname);

    }

}
