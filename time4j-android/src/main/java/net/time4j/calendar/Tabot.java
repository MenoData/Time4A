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

import net.time4j.calendar.service.GenericTextProviderSPI;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoDisplay;
import net.time4j.format.Attributes;
import net.time4j.format.TextElement;
import net.time4j.i18n.PropertyBundle;

import java.io.IOException;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * Used in Ethiopian calendar for associating the day of month with a name.
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
/*[deutsch]
 * Wird im &auml;thiopischen Kalender verwendet, um den Tag des Monats mit einem Namen zu verbinden.
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
public final class Tabot
    implements Comparable<Tabot> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final String[] TRANSSCRIPTION;
    private static final String[] AMHARIC;
    private static final Tabot[] INSTANCES;

    static {
        GenericTextProviderSPI spi = new GenericTextProviderSPI();
        PropertyBundle rbRoot = getBundle(Locale.ROOT);
        PropertyBundle rbAmharic = getBundle(new Locale("am"));

        String[] transscription = new String[30];
        String[] amharic = new String[30];
        Tabot[] tabots = new Tabot[30];

        for (int i = 0; i < 30; i++) {
            String key = "T_" + String.valueOf(i + 1);
            transscription[i] = rbRoot.getString(key);
            amharic[i] = rbAmharic.getString(key);
            tabots[i] = new Tabot(i + 1);
        }

        TRANSSCRIPTION = transscription;
        AMHARIC = amharic;
        INSTANCES = tabots;
    }

    //~ Instanzvariablen --------------------------------------------------

    private final int index;

    //~ Konstruktoren -----------------------------------------------------

    private Tabot(int index) {
        super();

        this.index = index;

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Returns an immutable list of all available tabots. </p>
     *
     * @return  unmodifiable list of available tabots
     * @since   3.11/4.8
     */
    /*[deutsch]
     * <p>Liefert eine unver&auml;nderliche Liste aller verf&uuml;gbaren <i>Tabots</i>. </p>
     *
     * @return  unmodifiable list of available tabots
     * @since   3.11/4.8
     */
    public static List<Tabot> asList() {

        return Collections.unmodifiableList(Arrays.asList(INSTANCES));

    }

    /**
     * <p>Yields an instance for given day of month. </p>
     *
     * @param   dayOfMonth  tabot index in range 1-30
     * @return  cached {@code Tabot}
     * @throws  IllegalArgumentException if the argument is out of range 1-30
     * @since   3.11/4.8
     */
    /*[deutsch]
     * <p>Liefert eine Instanz f&uuml;r den angegebenen Monatstag. </p>
     *
     * @param   dayOfMonth  tabot index in range 1-30
     * @return  cached {@code Tabot}
     * @throws  IllegalArgumentException if the argument is out of range 1-30
     * @since   3.11/4.8
     */
    public static Tabot of(int dayOfMonth) {

        if (dayOfMonth < 1 || dayOfMonth > 30) {
            throw new IllegalArgumentException("Out of range 1-30: " + dayOfMonth);
        }

        return INSTANCES[dayOfMonth - 1];

    }

    /**
     * <p>Yields the associated day-of-month. </p>
     *
     * @return  int
     * @since   3.11/4.8
     */
    /*[deutsch]
     * <p>Liefert den assoziierten Tag des Monats. </p>
     *
     * @return  int
     * @since   3.11/4.8
     */
    public int getDayOfMonth() {

        return this.index;

    }

    /**
     * <p>Determines either an Amharic translation of this instance or a latin transscription. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (never {@code null})
     * @since   3.11/4.8
     */
    /*[deutsch]
     * <p>Liefert entweder eine &Uuml;bersetzung auf Amharic oder eine lateinische Umschreibung. </p>
     *
     * @param   locale      language setting
     * @return  descriptive text (never {@code null})
     * @since   3.11/4.8
     */
    public String getDisplayName(Locale locale) {

        if (locale.getLanguage().equals("am")) {
            return AMHARIC[this.index - 1];
        }

        return TRANSSCRIPTION[this.index - 1];

    }

    @Override
    public int compareTo(Tabot other) {

        return (this.index - other.index);

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof Tabot) {
            return (this.index == ((Tabot) obj).index);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return Integer.valueOf(this.index).hashCode();

    }

    @Override
    public String toString() {

        return "Tabot of day-of-month " + this.index;

    }

    private static PropertyBundle getBundle(Locale locale) {

        return PropertyBundle.load(
            "calendar/names/ethiopic/ethiopic",
            locale);

    }

    //~ Innere Klassen ----------------------------------------------------

    static enum Element
        implements TextElement<Tabot> {

        //~ Statische Felder/Initialisierungen ----------------------------

        TABOT;

        //~ Methoden ------------------------------------------------------

        @Override
        public Class<Tabot> getType() {

            return Tabot.class;

        }

        @Override
        public char getSymbol() {

            return '\u0000';

        }

        @Override
        public int compare(
            ChronoDisplay o1,
            ChronoDisplay o2
        ) {

            return (o1.get(TABOT).getDayOfMonth() - o2.get(TABOT).getDayOfMonth());

        }

        @Override
        public Tabot getDefaultMinimum() {

            return Tabot.of(1);

        }

        @Override
        public Tabot getDefaultMaximum() {

            return Tabot.of(30);

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

            return this.name();

        }

        @Override
        public void print(
            ChronoDisplay context,
            Appendable buffer,
            AttributeQuery attributes
        ) throws IOException {

            buffer.append(context.get(TABOT).getDisplayName(attributes.get(Attributes.LANGUAGE, Locale.ROOT)));

        }

        @Override
        public Tabot parse(
            CharSequence text,
            ParsePosition status,
            AttributeQuery attributes
        ) {

            Locale lang = attributes.get(Attributes.LANGUAGE, Locale.ROOT);
            int offset = status.getIndex();

            for (int i = 1; i <= 30; i++) {
                String test = Tabot.of(i).getDisplayName(lang);
                int len = test.length();

                if (
                    offset + len <= text.length()
                    && test.equals(text.subSequence(offset, offset + len).toString())
                ) {
                    status.setIndex(offset + len);
                    return Tabot.of(i);
                }
            }

            return null;

        }

    }

}
