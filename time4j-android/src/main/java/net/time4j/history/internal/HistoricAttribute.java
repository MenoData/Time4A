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

package net.time4j.history.internal;

import net.time4j.engine.AttributeKey;
import net.time4j.format.Attributes;
import net.time4j.history.ChronoHistory;


/**
 * <p>Collection of some format attributes for internal purposes only. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
/*[deutsch]
 * <p>Eine Menge von einigen Formatattributen nur f&uuml;r interne Zwecke. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
public final class HistoricAttribute {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Format attribute which determines the calendar history. </p>
     *
     * <p>Users will not directly use this attribute but adjust a given {@code ChronoFormatter}
     * by its method {@code with(ChronoHistory)}. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#with(net.time4j.history.ChronoHistory)
     * @since   3.14/4.11
     */
    /*[deutsch]
     * <p>Formatattribut, das die Kalenderhistorie bestimmt. </p>
     *
     * <p>Anwender werden nicht direkt dieses Attribut verwenden, sondern stattdessen die
     * Methode {@code ChronoFormatter.with(ChronoHistory)} aufrufen. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#with(net.time4j.history.ChronoHistory)
     * @since   3.14/4.11
     */
    public static final AttributeKey<ChronoHistory> CALENDAR_HISTORY =
        Attributes.createKey("CALENDAR_HISTORY", ChronoHistory.class);

    /**
     * <p>Format attribute which prefers the notation of &quot;Common Era&quot; in formatting
     * an enum of type {@link net.time4j.history.HistoricEra}. </p>
     *
     * <p>Users will not directly use this attribute but call the method
     * {@code ChronoFormatter.withAlternativeEraNames()} instead. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#withAlternativeEraNames()
     */
    /*[deutsch]
     * <p>Formatattribut, das eine alternative nicht-christliche Schreibweise f&uuml;r die Formatierung
     * eines Enums des Typs {@link net.time4j.history.HistoricEra} bevorzugt. </p>
     *
     * <p>Anwender werden nicht direkt dieses Attribut verwenden, sondern stattdessen die
     * Methode {@code ChronoFormatter.withAlternativeEraNames()} aufrufen. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#withAlternativeEraNames()
     */
    public static final AttributeKey<Boolean> COMMON_ERA = Attributes.createKey("COMMON_ERA", Boolean.class);

    /**
     * <p>Format attribute which enforces latin notations of historic eras ignoring the locale. </p>
     *
     * <p>Users will not directly use this attribute but call the method
     * {@code ChronoFormatter.withLatinEraNames()} instead. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#withLatinEraNames()
     */
    /*[deutsch]
     * <p>Formatattribut, das eine lateinische Schreibweise f&uuml;r die Formatierung
     * eines Enums des Typs {@link HistoricEra} erzwingt, ohne Ber&uuml;cksichtigung der Spracheinstellung. </p>
     *
     * <p>Anwender werden nicht direkt dieses Attribut verwenden, sondern stattdessen die
     * Methode {@code ChronoFormatter.withLatinEraNames()} aufrufen. </p>
     *
     * @see     net.time4j.format.expert.ChronoFormatter#withLatinEraNames()
     */
    public static final AttributeKey<Boolean> LATIN_ERA = Attributes.createKey("LATIN_ERA", Boolean.class);

    //~ Konstruktoren -----------------------------------------------------

    private HistoricAttribute() {
        // no instantiation

    }

}
