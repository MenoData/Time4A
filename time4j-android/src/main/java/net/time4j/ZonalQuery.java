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

import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoFunction;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;


class ZonalQuery<V>
    implements ChronoFunction<Moment, V> {

    //~ Instanzvariablen --------------------------------------------------

    private final ChronoElement<V> element;
    private final Timezone tz;
    private final ZonalOffset offset;

    //~ Konstruktoren -----------------------------------------------------

    ZonalQuery(
        ChronoElement<V> element,
        Timezone tz
    ) {
        super();

        if (tz == null) {
            throw new NullPointerException("Missing timezone.");
        }

        this.element = element;
        this.tz = tz;
        this.offset = null;

    }

    ZonalQuery(
        ChronoElement<V> element,
        ZonalOffset offset
    ) {
        super();

        if (offset == null) {
            throw new NullPointerException("Missing timezone offset.");
        }

        this.element = element;
        this.tz = null;
        this.offset = offset;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public V apply(Moment context) {

        ZonalOffset shift = (
            (this.offset == null)
            ? this.tz.getOffset(context)
            : this.offset);

        if (
            (this.element == PlainTime.SECOND_OF_MINUTE)
            && context.isLeapSecond()
            && (shift.getFractionalAmount() == 0)
            && ((shift.getAbsoluteSeconds() % 60) == 0)
        ) {
            return this.element.getType().cast(Integer.valueOf(60));
        }

        return PlainTimestamp.from(context, shift).get(this.element);

    }

}
