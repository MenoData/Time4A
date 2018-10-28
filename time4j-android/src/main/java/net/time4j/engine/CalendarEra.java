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

package net.time4j.engine;


/**
 * <p>Represents an era in a calendar system. </p>
 *
 * <p>An era is date-related and defines a reference date which can be
 * interpreted as start or end of an era. Not all calendar systems know
 * an era concept, however. Especially all ISO-calendar systems have no
 * era according to ISO-8601-standard. If an era exists in a given
 * calendar system then the meaning of a year changes such that it
 * means the year of an era counting as positive number from {@code >= 1}. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine &Auml;ra in einem Kalendersystem. </p>
 *
 * <p>Eine &Auml;ra ist grunds&auml;tzlich datumsbezogen und definiert ein
 * Bezugsdatum, das als Beginn oder Ende der &Auml;ra interpretiert werden
 * kann. Nicht alle Kalender kennen &uuml;berhaupt eine &Auml;ra, insbesondere
 * alle ISO-Kalendersysteme haben dieses Konzept nicht. Wenn eine &Auml;ra
 * existiert, ver&auml;ndert sich die Bedeutung einer Jahresangabe im
 * jeweiligen Kalendersystem dahingehend, da&szlig; die Jahresangabe dann
 * als Jahr innerhalb einer &Auml;ra zu interpretieren ist. Jahre innerhalb
 * einer &Auml;ra sind grunds&auml;tzlich positive Zahlen {@code >= 1}. </p>
 *
 * @author  Meno Hochschild
 */
public interface CalendarEra {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the canonical non-localized name. </p>
     *
     * @return  String
     */
    /*[deutsch]
     * <p>Liefert den kanonischen nicht lokalisierten Namen. </p>
     *
     * @return  String
     */
    String name();

}
