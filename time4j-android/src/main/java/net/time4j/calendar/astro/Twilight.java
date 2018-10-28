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
 * <p>Enumeration of various twilight definitions. </p>
 *
 * <p>See also <a href="https://en.wikipedia.org/wiki/Twilight">Wikipedia</a>. </p>
 *
 * @author 	Meno Hochschild
 * @since 	3.34/4.29
 */
/*[deutsch]
 * <p>Aufz&auml;hlung verschiedener D&auml;mmerungsdefinitionen. </p>
 *
 * <p>Siehe auch <a href="https://en.wikipedia.org/wiki/Twilight">Wikipedia</a>. </p>
 *
 * @author 	Meno Hochschild
 * @since 	3.34/4.29
 */
public enum Twilight {

	//~ Statische Felder/Initialisierungen --------------------------------

	/**
	 * <p>Marks the time when the sun is 4 degrees below the horizon. </p>
	 *
	 * <p>Mainly used by photographers. There is no official definition but usually photographers
	 * talk about the blue hour when the sun is between 4 and eight degrees below the horizon.
	 * See also <a href="https://en.wikipedia.org/wiki/Blue_hour">Wikipedia</a>. </p>
	 */
	/*[deutsch]
	 * <p>Markiert die Zeit, wenn die Sonne 4 Grad unter dem Horizont ist. </p>
	 *
	 * <p>Haupts&auml;chlich von Fotografen verwendet. Es gibt keine offizielle Definition, aber
	 * normalerweise sprechen Fotografen von der blauen Stunden, wenn die Sonne zwischen 4 und 8 Grad
	 * unter dem Horizont steht. Siehe auch <a href="https://en.wikipedia.org/wiki/Blue_hour">Wikipedia</a>. </p>
	 */
	BLUE_HOUR(4.0),

	/**
	 * <p>Marks the time when the sun is 6 degrees below the horizon. </p>
	 */
	/*[deutsch]
	 * <p>Markiert die Zeit, wenn die Sonne 6 Grad unter dem Horizont ist. </p>
	 */
	CIVIL(6.0),

	/**
	 * <p>Marks the time when the sun is 12 degrees below the horizon. </p>
	 */
	/*[deutsch]
	 * <p>Markiert die Zeit, wenn die Sonne 12 Grad unter dem Horizont ist. </p>
	 */
	NAUTICAL(12.0),

	/**
	 * <p>Marks the time when the sun is 18 degrees below the horizon. </p>
	 *
	 * <p>Is the sun even deeper below the horizon then people talk about night. </p>
	 */
	/*[deutsch]
	 * <p>Markiert die Zeit, wenn die Sonne 18 Grad unter dem Horizont ist. </p>
	 *
	 * <p>Steht die Sonne noch tiefer, spricht man von tiefer Nacht. </p>
	 */
	ASTRONOMICAL(18.0);

	//~ Instanzvariablen --------------------------------------------------

	private transient final double angle;

	//~ Konstruktoren -----------------------------------------------------

	private Twilight(double angle) {
		this.angle = angle;
	}

	//~ Methoden ----------------------------------------------------------

	/**
	 * <p>Obtains the associated angle of the sun relative to the horizon in degrees. </p>
	 *
	 * @return	angle in degrees
	 */
	/*[deutsch]
	 * <p>Liefert den assozierten Winkel in Grad, unter dem die Sonne relativ zum Horizont erscheint. </p>
	 *
	 * @return	angle in degrees
	 */
	double getAngle() {

		return this.angle;

	}

}
