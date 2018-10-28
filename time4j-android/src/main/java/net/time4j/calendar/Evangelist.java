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

package net.time4j.calendar;

import net.time4j.format.CalendarText;

import java.util.Locale;


/**
 * <p>Enumeration of the four evangelists of the bible, used in some calendars with historic or religious context. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
/*[deutsch]
 * <p>Aufz&auml;hlung der vier Evangelien der Bibel, verwendet im historischen oder religi&ouml;sen Kontext. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
public enum Evangelist {

    //~ Statische Felder/Initialisierungen --------------------------------

    MATTHEW,

    MARK,

    LUKE,

    JOHN;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Determines a translation of this instance with fallback to Roman numerals. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (never {@code null})
     * @since   3.11/4.8
     */
    /*[deutsch]
     * <p>Liefert eine &Uuml;bersetzung oder r&ouml;mische Numerale, wenn nicht vorhanden. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (never {@code null})
     * @since   3.11/4.8
     */
    public String getDisplayName(Locale locale) {

        CalendarText names = CalendarText.getInstance("generic", locale);
        return names.getTextForms("EV", Evangelist.class).print(this);

    }

}
