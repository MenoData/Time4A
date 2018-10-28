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

package net.time4j.engine;


/**
 * <p>Represents an object which can be sorted on a time axis in a
 * temporal-only way. </p>
 *
 * @param   <C> generic temporal type for pure temporal comparison purposes
 * @author  Meno Hochschild
 */
/*[deutsch]
 * <p>Repr&auml;sentiert ein Objekt, das auf einem Zeitstrahl rein zeitlich
 * angeordnet werden kann. </p>
 *
 * @param   <C> generic temporal type for pure temporal comparison purposes
 * @author  Meno Hochschild
 */
public interface Temporal<C> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Queries if this object is after given object on a timeline. </p>
     *
     * @param   temporal    object this instance is compared to
     * @return  {@code true} if this instance is temporally after
     *          {@code temporal} else {@code false}
     */
    /*[deutsch]
     * <p>Liegt dieses Objekt zeitlich nach dem angegebenen Argument? </p>
     *
     * @param   temporal    object this instance is compared to
     * @return  {@code true} if this instance is temporally after
     *          {@code temporal} else {@code false}
     */
    boolean isAfter(C temporal);

    /**
     * <p>Queries if this object is before given object on a timeline. </p>
     *
     * @param   temporal    object this instance is compared to
     * @return  {@code true} if this instance is temporally before
     *          {@code temporal} else {@code false}
     */
    /*[deutsch]
     * <p>Liegt dieses Objekt zeitlich vor dem angegebenen Argument? </p>
     *
     * @param   temporal    object this instance is compared to
     * @return  {@code true} if this instance is temporally before
     *          {@code temporal} else {@code false}
     */
    boolean isBefore(C temporal);

    /**
     * <p>Queries if this object and given object have the same position
     * on the time axis. </p>
     *
     * <p>Is equivalent to {@code !isAfter(temporal) && !isBefore(temporal)}.
     * This method differs from the {@code Object}-method {@code equals()}
     * such that first the comparison type must be a temporal one and second
     * that only temporal-only state will be considered. </p>
     *
     * @param   temporal    object this instance is compared to
     * @return  {@code true} if this instance is temporally equal
     *          to {@code temporal} else {@code false}
     */
    /*[deutsch]
     * <p>Sind dieses Objekt und das angegebene Argument zeitlich gleich? </p>
     *
     * <p>Entspricht {@code !isAfter(temporal) && !isBefore(temporal)}. Diese
     * Methode unterscheidet sich von der Objektmethode {@code equals()} darin,
     * da&szlig; erstens der Vergleichstyp ein temporaler sein mu&szlig; und
     * zweitens nur rein zeitliche Zustandsattribute verglichen werden. </p>
     *
     * @param   temporal    object this instance is compared to
     * @return  {@code true} if this instance is temporally equal
     *          to {@code temporal} else {@code false}
     */
    boolean isSimultaneous(C temporal);

}
