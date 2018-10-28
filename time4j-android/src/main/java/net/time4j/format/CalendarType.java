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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>This {@code Annotation} can be used to mark all types of {@code ChronoEntity}
 * which need formatted representations and access to text resources dependent on
 * a calendar system. </p>
 *
 * @author  Meno Hochschild
 * @see     net.time4j.engine.ChronoEntity
 */
/*[deutsch]
 * <p>Mit dieser {@code Annotation} k&ouml;nnen alle {@code ChronoEntity}-Typen
 * ausgezeichnet werden, die formatierte Darstellungen erlauben und einen
 * Zugang zu chronologischen Textressourcen brauchen, die von einem
 * Kalendersystem abh&auml;ngig sind. </p>
 *
 * @author  Meno Hochschild
 * @see     net.time4j.engine.ChronoEntity
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CalendarType {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Determines the reference name for all text resources of a
     * calendar system. </p>
     *
     * <p>The name must not be unique, but has to be in agreement to the
     * conventions defined in CLDR if it exists there. The motivation of
     * the name is a localized access to calendar specific texts of eras,
     * months, quarters, weekdays and AM/PM. The CLDR file
     * <a href="http://unicode.org/repos/cldr/trunk/common/bcp47/calendar.xml"
     * target="_blank">calendar.xml</a> defines both names and aliases.
     * Alias names should be preferred in most cases. ISO systems usually
     * use the name &quot;iso8601&quot;. </p>
     *
     * @return  reference name for calendar specific text resources (usually
     *          a name according to CLDR standard)
     */
    /*[deutsch]
     * <p>Gibt einen Bezugsnamen f&uuml;r die Textressourcen eines
     * chronologischen Systems an. </p>
     *
     * <p>Der Name mu&szlig; nicht eindeutig sein, aber den CLDR-Konventionen
     * gen&uuml;gen, falls in CLDR definiert. Sinn und Zweck des Namens ist
     * der Zugang zu kalendertypischen lokalisierten Bezeichnungen von
     * &Auml;ras, Monaten, Quartalen, Wochentagen und AM/PM. Die CLDR-Datei
     * <a href="http://unicode.org/repos/cldr/trunk/common/bcp47/calendar.xml"
     * target="_blank">calendar.xml</a> definiert Namen wie auch Alias-Namen.
     * Letzteren ist der Vorzug zu geben. ISO-Systeme verwenden in der Regel
     * den Namen &quot;iso8601&quot;. </p>
     *
     * @return  reference name for calendar specific text resources (usually
     *          a name according to CLDR standard)
     */
    String value();

}
