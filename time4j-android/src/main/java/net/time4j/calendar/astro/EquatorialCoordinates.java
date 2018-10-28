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
 * Describes a celestial coordinate system which projects the earth equator and poles onto the celestial sphere
 * using right ascension and declination in the reference frame J2000 as epoch.
 *
 * <p>See also: <a href="https://en.wikipedia.org/wiki/Celestial_coordinate_system">Wikipedia</a>.
 * The effect of precession will have an impact on right ascension and declination if times far away
 * from year 2000 are considered. </p>
 *
 * @author  Meno Hochschild
 * @since   4.37
 */
/*[deutsch]
 * Beschreibt ein Himmelskoordinatensystem, das den Erd&auml;quator und die Erdpole auf die Himmelskugel
 * projiziert, indem die Rektaszension und die Deklination mit Hilfe der J2000-Epoche als Polarkoordinaten
 * benutzt werden.
 *
 * <p>Siehe auch: <a href="https://de.wikipedia.org/wiki/Astronomische_Koordinatensysteme">Wikipedia</a>.
 * Der Effekt der Pr&auml;zession wirkt sich auf Rektaszension und Deklination aus, wenn Zeiten weit weg
 * vom gregorianischen Jahr 2000 betrachtet werden. </p>
 *
 * @author  Meno Hochschild
 * @since   4.37
 */
public interface EquatorialCoordinates {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Obtains the right ascension in degrees. </p>
     *
     * @return  double in range 0-360
     */
    /*[deutsch]
     * <p>Liefert die Rektaszension in Grad. </p>
     *
     * @return  double in range 0-360
     */
    double getRightAscension();

    /**
     * <p>Obtains the declination in degrees. </p>
     *
     * @return  double
     */
    /*[deutsch]
     * <p>Liefert die Deklination in Grad. </p>
     *
     * @return  double
     */
    double getDeclination();

}
