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

package net.time4j.tz;


/**
 * <p>Represents the sign of a zonal shift. </p>
 *
 * @since   2.0
 */
/*[deutsch]
 * <p>Repr&auml;sentiert das Vorzeichen der zonalen Verschiebung. </p>
 *
 * @since   2.0
 */
public enum OffsetSign {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Negative sign (west for Greenwich meridian). </p>
     */
    /*[deutsch]
     * <p>Negatives Vorzeichen. </p>
     */
    BEHIND_UTC,

    /**
     * <p>Positive sign (also in case of zero offset). </p>
     */
    /*[deutsch]
     * <p>Positives Vorzeichen (auch bei Null-Offset). </p>
     */
    AHEAD_OF_UTC;

}
