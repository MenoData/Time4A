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


/**
 * <p>Determines a suitable strategy for handling numerical signs. </p>
 *
 * <p>Note: Signs can usually only occur in ISO-8601-context. Therefore
 * Time4J will never process signs in a localized way. That means signs
 * are the ASCII-chars &#39;+&#39; and &#39;-&#39;. A sign precedes the
 * sequence of numerical digits. Localized formats of elements with signs
 * (for example in arab language) require a special {@code ChronoPrinter}
 * and {@code ChronoParser}. </p>
 *
 * <p>A parser will only pay attention to this configuration in strict
 * mode. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Legt die Format- und Interpretationsstrategie f&uuml;r numerische
 * Vorzeichen fest. </p>
 *
 * <p>Notiz: Vorzeichen kommen in Time4J nur im ISO-8601-Kontext vor, daher
 * werden sie niemals lokalisiert behandelt. Das bedeutet, als Vorzeichen
 * werden die ASCII-Zeichen &#39;+&#39; und &#39;-&#39; benutzt und links von
 * der Ziffernfolge angeordnet. Lokalisierte Formate von vorzeichenbehafteten
 * Elementen etwa im arabischen Sprachraum ben&ouml;tigen einen speziellen
 * {@code ChronoPrinter} bzw. {@code ChronoParser}. </p>
 *
 * <p>Ein Parser wird diese Eigenschaft nur im strikten Modus beachten. </p>
 *
 * @author  Meno Hochschild
 */
public enum SignPolicy {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>A sign will never be printed or accepted in parsing. </p>
     *
     * <p>This setting is the default. </p>
     */
    /*[deutsch]
     * <p>Das Vorzeichen wird niemals ausgegeben oder akzeptiert. </p>
     *
     * <p>Diese Einstellung dient als Standardvorgabe. </p>
     */
    SHOW_NEVER,

    /**
     * <p>A positive sign will never be printed, but a negative sign is
     * always printed. </p>
     *
     * <p>This setting is the default for proleptic years in ISO-8601 format
     * if the year numbers have less than four, but not two digits. </p>
     */
    /*[deutsch]
     * <p>Ein positives Vorzeichen wird niemals ausgegeben, aber ein negatives
     * Vorzeichen immer. </p>
     *
     * <p>Diese Einstellung ist Vorgabe f&uuml;r Jahre im ISO-8601-Format, wenn
     * die Jahreszahlen weniger als 5, aber nicht 2 Stellen haben. </p>
     */
    SHOW_WHEN_NEGATIVE,

    /**
     * <p>A positive sign will be printed if the numerical amount has more
     * digits than specified. </p>
     *
     * <p>This setting is the default for proleptic years in ISO-8601-format
     * if the year numbers have more than four digits. Negative signs will
     * always be printed. </p>
     */
    /*[deutsch]
     * <p>Ein positives Vorzeichen wird ausgegeben, wenn der zugeh&ouml;rige
     * numerische Betrag mehr Stellen hat als minimal vorgegeben. </p>
     *
     * <p>Diese Einstellung ist Vorgabe f&uuml;r Jahre im ISO-8601-Format, wenn
     * die Jahreszahlen mehr als 4 Stellen haben. Negative Vorzeichen werden
     * immer ausgegeben. </p>
     */
    SHOW_WHEN_BIG_NUMBER,

    /**
     * <p>The sign will always be printed. </p>
     */
    /*[deutsch]
     * <p>Das Vorzeichen wird immer ausgegeben. </p>
     */
    SHOW_ALWAYS;

}
