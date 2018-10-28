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

package net.time4j.base;


/**
 * <p>Defines a common calendar date which is based on gregorian calendar
 * rules. </p>
 *
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Definiert ein allgemeines Datum, das auf den gregorianischen
 * Kalenderregeln beruht. </p>
 *
 * @author  Meno Hochschild
 */
public interface GregorianDate {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the proleptic year according to ISO-8601. </p>
     *
     * <p>The term <i>proleptic</i> means that the gregorian calendar rules
     * are applied backwards even before the introduction of this calendar.
     * Second: The year numbering is just the mathematical one as defined
     * in ISO-8601 such that there is a year zero and even negative years:
     * -2 = BC 3, -1 = BC 2, 0 = BC 1, 1 = AD 1, 2 = AD 2, ... </p>
     *
     * @return  proleptic iso year in range
     *          {@link GregorianMath#MIN_YEAR} - {@link GregorianMath#MAX_YEAR}
     */
    /*[deutsch]
     * <p>Liefert das proleptische Jahr entsprechend dem ISO-8601-Standard. </p>
     *
     * <p>Der Begriff <i>proleptic</i> bedeutet, da&szlig; die gregorianischen
     * Kalenderregeln r&uuml;ckw&auml;rts sogar vor der Einf&uuml;hrung
     * des gregorianischen Kalenders angewandt werden. Zweitens: Die
     * Jahresz&auml;hlung ist eine rein mathematische wie in ISO-8601
     * definiert (und von Astronomen benutzt) so, da&szlig; es ein Jahr 0
     * und sogar negative Jahre gibt:
     * -2 = BC 3, -1 = BC 2, 0 = BC 1, 1 = AD 1, 2 = AD 2, ... </p>
     *
     * @return  proleptic iso year in range
     *          {@link GregorianMath#MIN_YEAR} - {@link GregorianMath#MAX_YEAR}
     */
    int getYear();

    /**
     * <p>Yields the gregorian month as integer. </p>
     *
     * @return  gregorian month in range (1 = January, ..., 12 = December)
     */
    /*[deutsch]
     * <p>Liefert den gregorianischen Monat als Integer. </p>
     *
     * @return  gregorian month in range (1 = January, ..., 12 = December)
     */
    int getMonth();

    /**
     * <p>Yields the day of month. </p>
     *
     * @return  day of month in range {@code 1 <= dayOfMonth <= 31}
     */
    /*[deutsch]
     * <p>Liefert den Tag des Monats. </p>
     *
     * @return  day of month in range {@code 1 <= dayOfMonth <= 31}
     */
    int getDayOfMonth();

    /**
     * <p>Yields a canonical representation in ISO-format
     * &quot;YYYY-MM-DD&quot;. </p>
     *
     * @return  date in ISO-8601-format YYYY-MM-DD
     */
    /*[deutsch]
     * <p>Liefert eine kanonische Darstellung im ISO-Format
     * &quot;YYYY-MM-DD&quot;. </p>
     *
     * @return  date in ISO-8601-format YYYY-MM-DD
     */
    @Override
    String toString();

}
