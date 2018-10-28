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


/**
 * <p>Marker for a historical calendar variant. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
/*[deutsch]
 * <p>Markiert eine historische Kalendervariante. </p>
 *
 * @author  Meno Hochschild
 * @since   3.11/4.8
 */
public enum HistoricVariant {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Julian calendar assumed to be valid for all times. </p>
     */
    /*[deutsch]
     * <p>Julianischer Kalender, der als f&uuml;r alle Zeiten g&uuml;ltig angesehen wird. </p>
     */
    PROLEPTIC_JULIAN,

    /**
     * <p>Gregorian calendar assumed to be valid for all times. </p>
     */
    /*[deutsch]
     * <p>Gregorianischer Kalender, der als f&uuml;r alle Zeiten g&uuml;ltig angesehen wird. </p>
     */
    PROLEPTIC_GREGORIAN,

    /**
     * <p>Marks the Swedish calendar anomaly. </p>
     */
    /*[deutsch]
     * <p>Kennzeichnet die schwedische Kalenderanomalie. </p>
     */
    SWEDEN,

    /**
     * <p>Marks a standard historical calendar with the original gregorian calendar reform. </p>
     */
    /*[deutsch]
     * <p>Kennzeichnet einen historischen Standardkalender mit der ersten gregorianischen Kalenderreform. </p>
     */
    INTRODUCTION_ON_1582_10_15,

    /**
     * <p>Marks a standard historical calendar with one single gregorian calendar reform. </p>
     */
    /*[deutsch]
     * <p>Kennzeichnet einen historischen Standardkalender mit genau einer gregorianischen Kalenderreform. </p>
     */
    SINGLE_CUTOVER_DATE,

    /**
     * <p>Byzantine calendar assumed to be valid for all times. </p>
     */
    /*[deutsch]
     * <p>Byzantinischer Kalender, der als f&uuml;r alle Zeiten g&uuml;ltig angesehen wird. </p>
     */
    PROLEPTIC_BYZANTINE;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Used in serialization. </p>
     *
     * @return  int
     */
    public int getSerialValue() {

        switch (this) {
            case PROLEPTIC_JULIAN:
                return 2;
            case PROLEPTIC_GREGORIAN:
                return 1;
            case SWEDEN:
                return 4;
            case INTRODUCTION_ON_1582_10_15:
                return 7;
            case PROLEPTIC_BYZANTINE:
                return 3;
            default:
                return 0;
        }

    }

}
