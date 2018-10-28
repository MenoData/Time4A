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

package net.time4j.format;

import net.time4j.engine.DisplayStyle;

import java.text.DateFormat;


/**
 * <p>Defines how many details will be displayed in chronological texts. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Definiert Anzeigestile, wie detailliert chronologische Informationen
 * dargestellt werden. </p>
 *
 * @author  Meno Hochschild
 */
public enum DisplayMode
    implements DisplayStyle {

    //~ Statische Felder/Initialisierungen --------------------------------

    /** Full display with maximum detailed content. */
    /*[deutsch] Volle Anzeige mit maximalem Detailgehalt. */
    FULL(DateFormat.FULL),

    /** Verbose display with many details. */
    /*[deutsch] Gespr&auml;chige Anzeige mit vielen Details. */
    LONG(DateFormat.LONG),

    /** Normal display with few details. */
    /*[deutsch] Normale Anzeige mit wenigen Details. */
    MEDIUM(DateFormat.MEDIUM),

    /** Shortened display, typically in numerical form. */
    /*[deutsch] Verk&uuml;rzte Anzeige, typischerweise numerisch. */
    SHORT(DateFormat.SHORT);

    private static DisplayMode[] ENUMS = DisplayMode.values();

    //~ Instanzvariablen --------------------------------------------------

    private transient final int style;

    //~ Konstruktoren -----------------------------------------------------

    private DisplayMode(int style) {
        this.style = style;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public int getStyleValue() {

        return this.style;

    }

    /**
     * <p>Converts a {@code DateFormat}-constant. </p>
     *
     * @param   style   traditional format style
     * @return  associated DisplayMode
     * @throws  UnsupportedOperationException if given style is not supported
     * @see     java.text.DateFormat#FULL
     * @see     java.text.DateFormat#LONG
     * @see     java.text.DateFormat#MEDIUM
     * @see     java.text.DateFormat#SHORT
     * @since   3.10/4.7
     */
    /*[deutsch]
     * <p>Konvertiert eine {@code DateFormat}-Konstante. </p>
     *
     * @param   style   traditional format style
     * @return  associated DisplayMode
     * @throws  UnsupportedOperationException if given style is not supported
     * @see     java.text.DateFormat#FULL
     * @see     java.text.DateFormat#LONG
     * @see     java.text.DateFormat#MEDIUM
     * @see     java.text.DateFormat#SHORT
     * @since   3.10/4.7
     */
    public static DisplayMode ofStyle(int style) {

        for (DisplayMode mode : ENUMS) {
            if (mode.getStyleValue() == style) {
                return mode;
            }
        }

        throw new UnsupportedOperationException("Unknown format style: " + style);

    }

}
