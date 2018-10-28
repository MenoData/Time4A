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


/**
 * <p>Determines in which output context the formatting is to be performed. </p>
 *
 * @author  Meno Hochschild
 * @see     Attributes#OUTPUT_CONTEXT
 */
/*[deutsch]
 * <p>Definiert, in welchem Ausgabekontext eine Formatierung erfolgen soll. </p>
 *
 * @author  Meno Hochschild
 * @see     Attributes#OUTPUT_CONTEXT
 */
public enum OutputContext {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Standard format context (for example weekday names within a
     * formatted calendar date). </p>
     */
    /*[deutsch]
     * <p>Standardformatierung (zum Beispiel Wochentagsnamen in einer
     * formatierten Datumsausgabe). </p>
     */
    FORMAT,

    /**
     * <p>Stand-alone-output (for example weekday names as column headers in
     * a month calendar). </p>
     */
    /*[deutsch]
     * <p>Alleinstehende Ausgabe (zum Beispiel Wochentagsnamen als
     * Spalten&uuml;berschriften in einer Monatstabelle). </p>
     */
    STANDALONE;

}
