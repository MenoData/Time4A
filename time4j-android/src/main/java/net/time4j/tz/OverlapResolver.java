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
 * <p>Represents the component of a transition strategy how to handle overlaps
 * on the local timeline. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     GapResolver
 * @see     TransitionStrategy
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Komponente einer &Uuml;bergangsstrategie, wie
 * &Uuml;berlappungen auf dem lokalen Zeitstrahl behandelt werden. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     GapResolver
 * @see     TransitionStrategy
 */
public enum OverlapResolver {

    //~ Statische Felder/Initialisierungen --------------------------------

    /**
     * <p>Strategy which selects the earlier offset before a transition
     * where the local time is ambivalent due to an overlap on the local
     * timeline. </p>
     */
    /*[deutsch]
     * <p>Strategie, die den fr&uuml;heren Offset vor einem &Uuml;bergang
     * w&auml;hlt, wenn eine lokale Zeit wegen einer &Uuml;berlappung auf dem
     * lokalen Zeitstrahl nicht eindeutig ist. </p>
     */
    EARLIER_OFFSET,

    /**
     * <p>Default strategy which selects the later offset after a transition
     * where the local time is ambivalent due to an overlap on the local
     * timeline. </p>
     */
    /*[deutsch]
     * <p>Standardstrategie, die den sp&auml;teren Offset nach einem
     * &Uuml;bergang w&auml;hlt, wenn eine lokale Zeit wegen einer
     * &Uuml;berlappung auf dem lokalen Zeitstrahl nicht eindeutig ist. </p>
     */
    LATER_OFFSET;

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields a transition strategy as combination of given gap resolver
     * and this instance. </p>
     *
     * @param   gapResolver    strategy how to handle gaps on the local timeline
     * @return  transition strategy for handling gaps and overlaps
     * @since   2.2
     * @see     GapResolver#and(OverlapResolver)
     */
    /*[deutsch]
     * <p>Liefert eine &Uuml;bergangsstrategie als Kombination der angegebenen
     * L&uuml;ckenstrategie und dieser Instanz. </p>
     *
     * @param   gapResolver    strategy how to handle gaps on the local timeline
     * @return  transition strategy for handling gaps and overlaps
     * @since   2.2
     * @see     GapResolver#and(OverlapResolver)
     */
    public TransitionStrategy and(GapResolver gapResolver) {

        return gapResolver.and(this);

    }

}
