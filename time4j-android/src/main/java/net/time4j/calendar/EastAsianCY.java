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

import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoException;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.Leniency;
import net.time4j.format.TextElement;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.ParsePosition;
import java.util.Locale;


/**
 * Represents a generic element for the cyclic year.
 *
 * @author  Meno Hochschild
 * @since   3.40/4.35
 */
class EastAsianCY
    implements TextElement<CyclicYear>, Serializable {

    //~ Statische Felder/Initialisierungen ----------------------------

    static final EastAsianCY SINGLETON = new EastAsianCY();

    private static final long serialVersionUID = -4211396220263977858L;

    //~ Methoden ------------------------------------------------------

    @Override
    public String name() {

        return "CYCLIC_YEAR";

    }

    @Override
    public Class<CyclicYear> getType() {

        return CyclicYear.class;

    }

    @Override
    public char getSymbol() {

        return 'U';

    }

    @Override
    public int compare(
        ChronoDisplay o1,
        ChronoDisplay o2
    ) {

        return o1.get(this).compareTo(o2.get(this));

    }

    @Override
    public CyclicYear getDefaultMinimum() {

        return CyclicYear.of(1);

    }

    @Override
    public CyclicYear getDefaultMaximum() {

        return CyclicYear.of(60);

    }

    @Override
    public boolean isDateElement() {

        return true;

    }

    @Override
    public boolean isTimeElement() {

        return false;

    }

    @Override
    public boolean isLenient() {

        return false;

    }

    @Override
    public String getDisplayName(Locale language) {

        String key = "L_year";
        String lname = CalendarText.getIsoInstance(language).getTextForms().get(key);
        return ((lname == null) ? this.name() : lname);

    }

    @Override
    public void print(
        ChronoDisplay context,
        Appendable buffer,
        AttributeQuery attributes
    ) throws IOException, ChronoException {

        Locale locale = attributes.get(Attributes.LANGUAGE, Locale.ROOT);
        buffer.append(context.get(this).getDisplayName(locale));

    }

    @Override
    public CyclicYear parse(
        CharSequence text,
        ParsePosition status,
        AttributeQuery attributes
    ) {

        Locale locale = attributes.get(Attributes.LANGUAGE, Locale.ROOT);
        boolean lenient = !attributes.get(Attributes.LENIENCY, Leniency.SMART).isStrict();
        return CyclicYear.parse(text, status, locale, lenient);

    }

    /**
     * @serialData  Preserves the singleton semantic
     * @return      singleton instance
     */
    protected Object readResolve() throws ObjectStreamException {

        return SINGLETON;

    }

}
