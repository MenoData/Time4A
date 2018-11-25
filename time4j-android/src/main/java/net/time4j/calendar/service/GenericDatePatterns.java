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


import net.time4j.engine.DisplayStyle;
import net.time4j.format.CalendarText;
import net.time4j.format.DisplayMode;
import net.time4j.i18n.PropertyBundle;

import java.util.Locale;


/**
 * <p>Represents a low-level access to generic non-iso date patterns. </p>
 *
 * @author  Meno Hochschild
 * @since   3.10/4.7
 */
public final class GenericDatePatterns {

    //~ Konstruktoren -----------------------------------------------------

    private GenericDatePatterns() {
        // no instantiation
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Determines a suitable date pattern. </p>
     *
     * @param   calendarType    general calendar type
     * @param   style           format style
     * @param   locale          desired language and/or country
     * @return  localized date pattern
     * @throws  UnsupportedOperationException if given style is not supported
     * @since   3.10/4.7
     */
    public static String get(
        String calendarType,
        DisplayStyle style,
        Locale locale
    ) {

        DisplayMode mode = DisplayMode.ofStyle(style.getStyleValue());

        if (calendarType.equals(CalendarText.ISO_CALENDAR_TYPE)) {
            return CalendarText.patternForDate(mode, locale);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("F(");
        sb.append(Character.toLowerCase(mode.name().charAt(0)));
        sb.append(')');
        String key = sb.toString();

        PropertyBundle rb = GenericTextProviderSPI.getBundle(calendarType, locale);

        if (!rb.containsKey(key)) {
            rb = GenericTextProviderSPI.getBundle("generic", locale);
        }

        return rb.getString(key);

    }

}
