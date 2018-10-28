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

package net.time4j.history;


/**
 * <p>Defines different strategies how to handle the difference between the standard calendar year
 * from first of January to end of December and historic years which follow different new year rules. </p>
 *
 * @author  Meno Hochschild
 * @since   3.18/4.14
 */
/*[deutsch]
 * <p>Definiert verschiedene Verfahren, wie der Unterschied zwischen Standard-Kalendarjahren (vom ersten
 * Januar bis zu Ende Dezember) und historischen Jahren mit anderen Neujahrsregeln behandelt wird. </p>
 *
 * @author  Meno Hochschild
 * @since   3.18/4.14
 */
public enum YearDefinition {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Prefers the standard calendar year whose range is from first of January until end of December. </p>
     *
     * <p>When used in formatting context dual dating like 1602/03 will be used if and only if the historic
     * year deviates from standard calendar year. This year definition is the default. </p>
     */
    /*[deutsch]
     * <p>Bevorzugt das Standard-Kalenderjahr, das vom ersten Januar bis Ende Dezember reicht. </p>
     *
     * <p>Im Formatkontext wird eine duale Jahresanzeige wie 1602/03 genau dann benutzt, wenn das historische
     * Jahr vom Standard-Kalenderjahr abweicht. Diese Jahresdefinition ist Standardeinstellung. </p>
     */
    DUAL_DATING,

    /**
     * <p>Uses the displayed historic year only, even if it deviates from standard calendar year. </p>
     *
     * <p>If historic dates consisting of displayed year, month and day-of-month become ambivalent due
     * to prolonged year length then the date shortly after new year will be taken. Dual dating is not
     * used. </p>
     */
    /*[deutsch]
     * <p>Verwendet nur das historische Jahr, sogar dann, wenn es vom Standard-Kalenderjahr abweicht. </p>
     *
     * <p>Wenn historische Datumsangaben bestehend aus dem angezeigten historischen Jahr, Monat und Tag
     * des Monats wegen vergr&ouml;&szlig;erter Jahresl&auml;ngen zweideutig werden, wird das Datum kurz
     * nach Neujahr genommen. Eine duale Jahresangabe wird nicht verwendet. </p>
     */
    AFTER_NEW_YEAR,

    /**
     * <p>Uses the displayed historic year only, even if it deviates from standard calendar year. </p>
     *
     * <p>If historic dates consisting of displayed year, month and day-of-month become ambivalent due
     * to prolonged year length then the date shortly before next new year will be taken. Dual dating
     * is not used. </p>
     */
    /*[deutsch]
     * <p>Verwendet nur das historische Jahr, sogar dann, wenn es vom Standard-Kalenderjahr abweicht. </p>
     *
     * <p>Wenn historische Datumsangaben bestehend aus dem angezeigten historischen Jahr, Monat und Tag
     * des Monats wegen vergr&ouml;&szlig;erter Jahresl&auml;ngen zweideutig werden, wird das Datum kurz
     * vor Neujahr des n&auml;chsten Jahres genommen. Eine duale Jahresangabe wird nicht verwendet. </p>
     */
    BEFORE_NEW_YEAR

}
