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

package net.time4j.tz.model;

import net.time4j.base.GregorianDate;
import net.time4j.base.UnixTime;
import net.time4j.base.WallTime;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZonalOffset;
import net.time4j.tz.ZonalTransition;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static net.time4j.tz.model.TransitionModel.NEW_LINE;


/**
 * <p>Transition history without any defined transition. </p>
 *
 * @author      Meno Hochschild
 * @since       2.3
 * @serial      include
 */
final class EmptyTransitionModel
    implements TransitionHistory, Serializable {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = 1374714021808040253L;

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  fixed offset in seconds
     */
    private final ZonalOffset offset;

    //~ Konstruktoren -----------------------------------------------------

    EmptyTransitionModel(ZonalOffset offset) {
        super();

        this.offset = offset;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public ZonalOffset getInitialOffset() {

        return this.offset;

    }

    @Override
    public ZonalTransition getStartTransition(UnixTime ut) {

        return null;

    }

    @Override
    public ZonalTransition getNextTransition(UnixTime ut) {

        return null;

    }

    @Override
    public ZonalTransition getConflictTransition(
        GregorianDate localDate,
        WallTime localTime
    ) {

        return null;

    }

    @Override
    public List<ZonalOffset> getValidOffsets(
        GregorianDate localDate,
        WallTime localTime
    ) {

        return Collections.singletonList(this.offset);

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
    public boolean isEmpty() {

        return true;

    }

    @Override
    public void dump(Appendable buffer) throws IOException {

        buffer.append("*** Fixed offset:").append(NEW_LINE).append(">>> ");
        buffer.append(this.getInitialOffset().canonical()).append(NEW_LINE);

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof EmptyTransitionModel) {
            EmptyTransitionModel that = (EmptyTransitionModel) obj;
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

        return "EmptyTransitionModel=" + this.offset.canonical();

    }

}
