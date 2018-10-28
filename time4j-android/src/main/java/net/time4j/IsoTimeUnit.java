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

package net.time4j;


/**
 * <p>Represents a wall time unit conforming to ISO-8601. </p>
 *
 * <p>Most applications will simply use a standard unit of type
 * {@code ClockUnit}. Other units must inherit from the class
 * {@link net.time4j.engine.BasicUnit} appropriately and
 * also make sure that the length of time unit is always smaller
 * than the length of a day. </p>
 *
 * @author  Meno Hochschild
 * @see     ClockUnit
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine ISO-konforme Uhrzeiteinheit. </p>
 *
 * <p>Die meisten Anwendungen werden einfach eine Standardeinheit vom
 * Typ {@code ClockUnit} benutzen. Andere Zeiteinheiten m&uuml;ssen
 * von der Klasse {@link net.time4j.engine.BasicUnit} erben und beachten,
 * da&szlig; die L&auml;nge der Zeiteinheit stets unterhalb eines Tages
 * bleiben mu&szlig;. </p>
 *
 * @author  Meno Hochschild
 * @see     ClockUnit
 */
public interface IsoTimeUnit
    extends IsoUnit {

}

