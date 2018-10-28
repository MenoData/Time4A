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
 * <p>Represents the wall time. </p>
 *
 * <p>Defines additional operators for moving a timestamp to a new wall time
 * possibly changing the day. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 */
/*[deutsch]
 * <p>Repr&auml;sentiert die Uhrzeit. </p>
 *
 * <p>Definiert weitere Operatoren, die einen Zeitstempel zu einer neuen
 * Uhrzeit bewegen und bei Bedarf den Tag wechseln. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 */
public interface WallTimeElement
    extends ZonalElement<PlainTime> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Moves a timestamp to the next given wall time and change the day
     * if necessary. </p>
     *
     * @param   value   new wall time which is after current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    /*[deutsch]
     * <p>Setzt einen Zeitpunkt auf die n&auml;chste angegebene Uhrzeit und
     * wechselt bei Bedarf den Tag. </p>
     *
     * @param   value   new wall time which is after current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    ElementOperator<?> setToNext(PlainTime value);

    /**
     * <p>Moves a timestamp to the previous given wall time and change the day
     * backwards if necessary. </p>
     *
     * @param   value   new wall time which is before current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    /*[deutsch]
     * <p>Setzt einen Zeitpunkt auf die vorherige angegebene Uhrzeit und
     * wechselt bei Bedarf den Tag r&uuml;ckw&auml;rts. </p>
     *
     * @param   value   new wall time which is before current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    ElementOperator<?> setToPrevious(PlainTime value);

    /**
     * <p>Moves a timestamp to the next or same given wall time and change
     * the day if necessary. </p>
     *
     * @param   value   new wall time which is not before current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    /*[deutsch]
     * <p>Setzt einen Zeitpunkt auf die n&auml;chste oder gleiche angegebene
     * Uhrzeit und wechselt bei Bedarf den Tag. </p>
     *
     * @param   value   new wall time which is not before current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    ElementOperator<?> setToNextOrSame(PlainTime value);

    /**
     * <p>Moves a timestamp to the previous or same given wall time and
     * change the day backwards if necessary. </p>
     *
     * @param   value   new wall time which is not after current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    /*[deutsch]
     * <p>Setzt einen Zeitpunkt auf die vorherige oder gleiche angegebene
     * Uhrzeit und wechselt bei Bedarf den Tag r&uuml;ckw&auml;rts. </p>
     *
     * @param   value   new wall time which is not after current wall time
     * @return  operator directly applicable on {@code PlainTimestamp}
     * @since   1.2
     * @see     PlainTimestamp#with(ElementOperator)
     */
    ElementOperator<?> setToPreviousOrSame(PlainTime value);

    /**
     * <p>Performs rounding to full hour in half rounding mode. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    /*[deutsch]
     * <p>Rundet kaufm&auml;nnisch zur vollen Stunde. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    ElementOperator<PlainTime> roundedToFullHour();

    /**
     * <p>Performs rounding to full minute in half rounding mode. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    /*[deutsch]
     * <p>Rundet kaufm&auml;nnisch zur vollen Minute. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    ElementOperator<PlainTime> roundedToFullMinute();

    /**
     * <p>Adjusts to next full hour. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    /*[deutsch]
     * <p>Verstellt zur n&auml;chsten vollen Stunde. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    ElementOperator<PlainTime> setToNextFullHour();

    /**
     * <p>Adjusts to next full minute. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    /*[deutsch]
     * <p>Verstellt zur n&auml;chsten vollen Minute. </p>
     *
     * @return  operator also applicable on {@code PlainTimestamp}
     * @since   1.2
     */
    ElementOperator<PlainTime> setToNextFullMinute();

}
