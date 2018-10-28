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

package net.time4j.calendar.astro;


/**
 * Describes a geographical position with latitude, longitude and optionally altitude.
 *
 * @author  Meno Hochschild
 * @since   3.38/4.33
 */
/*[deutsch]
 * Beschreibt eine geographische Position mit Breiten- und L&auml;ngengrad sowie optional H&ouml;he.
 *
 * @author  Meno Hochschild
 * @since   3.38/4.33
 */
public interface GeoLocation {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Obtains the geographical latitude of this instance. </p>
     *
     * @return  latitude in decimal degrees ({@code -90.0 <= x <= +90.0})
     */
    /*[deutsch]
     * <p>Liefert den geographischen Breitengrad dieser Instanz. </p>
     *
     * @return  latitude in decimal degrees ({@code -90.0 <= x <= +90.0})
     */
    double getLatitude();

    /**
     * <p>Obtains the geographical longitude of this instance. </p>
     *
     * @return  longitude in decimal degrees ({@code -180.0 <= x < 180.0})
     */
    /*[deutsch]
     * <p>Liefert den geographischen L&auml;ngengrad dieser Instanz. </p>
     *
     * @return  longitude in decimal degrees ({@code -180.0 <= x < 180.0})
     */
    double getLongitude();

    /**
     * <p>Obtains the geographical altitude of this instance relative to sea level. </p>
     *
     * @return  altitude in meters ({@code 0 <= x < 11,0000})
     */
    /*[deutsch]
     * <p>Liefert die geographische H&ouml;he dieser Instanz relativ zum Meeresspiegel. </p>
     *
     * @return  altitude in meters ({@code 0 <= x < 11,0000})
     */
    int getAltitude();

}
