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
 * <p>Represents a calendar unit conforming to ISO-8601. </p>
 *
 * <p>Most applications will simply use a standard unit of type
 * {@code CalendarUnit}. Other units must inherit from the class
 * {@link net.time4j.engine.BasicUnit} appropriately. </p>
 *
 * @author  Meno Hochschild
 * @see     CalendarUnit
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine ISO-konforme kalendarische Zeiteinheit. </p>
 *
 * <p>Die meisten Anwendungen werden einfach eine Standardeinheit vom
 * Typ {@code CalendarUnit} benutzen. Andere Zeiteinheiten m&uuml;ssen
 * von der Klasse {@link net.time4j.engine.BasicUnit} erben. </p>
 *
 * @author  Meno Hochschild
 * @see     CalendarUnit
 */
public interface IsoDateUnit
    extends IsoUnit {

}
