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
 * <p>Represents the component of a transition strategy how to handle gaps
 * on the local timeline. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     OverlapResolver
 * @see     TransitionStrategy
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Komponente einer &Uuml;bergangsstrategie, wie
 * L&uuml;cken auf dem lokalen Zeitstrahl behandelt werden. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     OverlapResolver
 * @see     TransitionStrategy
 */
public enum GapResolver {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Default strategy which moves an invalid local time by the length
     * of the gap into the future. </p>
     *
     * <p>Example for the switch to summer time in the timezone
     * &quot;Europe/Berlin&quot;:
     * {@code 2015-03-29T02:30+01:00 => 2015-03-29T03:30+02:00} </p>
     */
    /*[deutsch]
     * <p>Standardstrategie, die eine ung&uuml;ltige lokale Zeit um die
     * L&auml;nge der L&uuml;cke in die Zukunft verschiebt. </p>
     *
     * <p>Beispiel f&uuml;r die Umschaltung auf Sommerzeit in der Zeitzone
     * &quot;Europe/Berlin&quot;:
     * {@code 2015-03-29T02:30+01:00 => 2015-03-29T03:30+02:00} </p>
     */
    PUSH_FORWARD,

    /**
     * <p>Alternative strategy which moves an invalid local time forward
     * to the first valid time after transition. </p>
     *
     * <p>Example for the switch to summer time in the timezone
     * &quot;Europe/Berlin&quot;:
     * {@code 2015-03-29T02:30+01:00 => 2015-03-29T03:00+02:00} </p>
     */
    /*[deutsch]
     * <p>Alternative Strategie, die eine ung&uuml;ltige lokale Zeit auf die
     * erste g&uuml;ltige lokale Zeit nach dem &Uuml;bergang setzt. </p>
     *
     * <p>Beispiel f&uuml;r die Umschaltung auf Sommerzeit in der Zeitzone
     * &quot;Europe/Berlin&quot;:
     * {@code 2015-03-29T02:30+01:00 => 2015-03-29T03:00+02:00} </p>
     */
    NEXT_VALID_TIME,

    /**
     * <p>Strict strategy which rejects an invalid local time by throwing
     * an exception. </p>
     */
    /*[deutsch]
     * <p>Strikte Strategie, die eine ung&uuml;ltige lokale Zeit mit einer
     * Ausnahme verwirft. </p>
     */
    ABORT;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields a transition strategy as combination of given overlap resolver
     * and this instance. </p>
     *
     * @param   overlapResolver     strategy how to handle overlaps on the
     *                              local timeline
     * @return  transition strategy for handling gaps and overlaps
     * @since   2.2
     */
    /*[deutsch]
     * <p>Liefert eine &Uuml;bergangsstrategie als Kombination der angegebenen
     * &Uuml;berlappungsstrategie und dieser Instanz. </p>
     *
     * @param   overlapResolver     strategy how to handle overlaps on the
     *                              local timeline
     * @return  transition strategy for handling gaps and overlaps
     * @since   2.2
     */
    public TransitionStrategy and(OverlapResolver overlapResolver) {

        return TransitionResolver.of(this, overlapResolver);

    }

}
