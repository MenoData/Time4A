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

import net.time4j.base.GregorianDate;
import net.time4j.base.UnixTime;
import net.time4j.base.WallTime;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 * <p>Stellt eine Zeitzone ohne &Uuml;berg&auml;nge und mit fester Verschiebung
 * dar. </p>
 *
 * @author      Meno Hochschild
 * @serial      include
 */
final class SingleOffsetTimezone
    extends Timezone
    implements TransitionHistory {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final SingleOffsetTimezone UTC =
        new SingleOffsetTimezone(ZonalOffset.UTC);
    private static final long serialVersionUID = 7807230388259573234L;

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  fixed zone shift in full seconds
     */
    private final ZonalOffset offset;

    //~ Konstruktoren -----------------------------------------------------

    private SingleOffsetTimezone(ZonalOffset offset) {
        super();

        if (offset.getFractionalAmount() == 0) {
            this.offset = offset;
        } else {
            int total = offset.getIntegralAmount();
            if (offset.getFractionalAmount() < 0) {
                total--; // corresponding to floor-divide-algorithm
            }
            this.offset = ZonalOffset.ofTotalSeconds(total);
        }

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public TZID getID() {

        return this.offset;

    }

    @Override
    public ZonalOffset getOffset(UnixTime ut) {

        return this.offset;

    }

    @Override
    public ZonalOffset getStandardOffset(UnixTime ut) {

        return this.offset;

    }

    @Override
    public ZonalOffset getDaylightSavingOffset(UnixTime ut){

        return ZonalOffset.UTC;

    }

    @Override
    public ZonalOffset getOffset(
        GregorianDate localDate,
        WallTime localTime
    ) {

        return this.offset;

    }

    @Override
    public boolean isInvalid(
        GregorianDate localDate,
        WallTime localTime
    ) {

        return false;

    }

    @Override
    public boolean isDaylightSaving(UnixTime ut) {

        return false;

    }

    @Override
    public boolean isFixed() {

        return true;

    }

    @Override
    public boolean isEmpty() {

        return true;

    }

    @Override
    public TransitionHistory getHistory() {

        return this;

    }

    @Override
    public List<ZonalTransition> getStdTransitions() {

        return Collections.emptyList();

    }

    @Override
    public List<ZonalTransition> getTransitions(
        UnixTime startInclusive,
        UnixTime endExclusive
    ) {

        return Collections.emptyList();

    }

    @Override
    public boolean hasNegativeDST() {

        return false;

    }

    @Override
    public List<ZonalOffset> getValidOffsets(
        GregorianDate localDate,
        WallTime localTime
    ) {

        return Collections.singletonList(this.offset);

    }

    // optional
    @Override
    public ZonalTransition getConflictTransition(
        GregorianDate localDate,
        WallTime localTime
    ) {

        return null;

    }

    // optional
    @Override
    public ZonalTransition getStartTransition(UnixTime time) {

        return null;

    }

    // optional
    @Override
    public ZonalTransition getNextTransition(UnixTime ut) {

        return null;

    }

    @Override
    public ZonalOffset getInitialOffset() {

        return this.offset;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof SingleOffsetTimezone) {
            SingleOffsetTimezone that = (SingleOffsetTimezone) obj;
            return this.offset.equals(that.offset);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return this.offset.hashCode();

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        sb.append(this.getClass().getName());
        sb.append(':');
        sb.append(this.offset);
        sb.append(']');
        return sb.toString();

    }

    @Override
    public String getDisplayName(
        NameStyle style,
        Locale locale
    ) {

        return (
            style.isAbbreviation()
            ? this.offset.toString()
            : this.offset.canonical()
        );

    }

    @Override
    public TransitionStrategy getStrategy() {

        return DEFAULT_CONFLICT_STRATEGY;

    }

    @Override
    public Timezone with(TransitionStrategy strategy) {

        return this;

    }

    /**
     * <p>Fabrikmethode. </p>
     *
     * @param   offset  fixed shift of local time relative to UTC in seconds
     */
    static SingleOffsetTimezone of(ZonalOffset offset) {

        if (
            (offset.getIntegralAmount() == 0)
            && (offset.getFractionalAmount() == 0)
        ) {
            return UTC;
        } else {
            return new SingleOffsetTimezone(offset);
        }

    }

    /**
     * @serialData  Checks the consistency.
     * @param       in      object input stream
     * @throws      InvalidObjectException in case of inconsistencies
     * @throws      ClassNotFoundException if class loading fails
     */
    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException {

        in.defaultReadObject();

        if (this.offset.getFractionalAmount() != 0) {
            throw new InvalidObjectException("Fractional offset is invalid.");
        }

    }

}
