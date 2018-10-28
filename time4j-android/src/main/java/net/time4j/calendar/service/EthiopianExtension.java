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

package net.time4j.calendar.service;

import net.time4j.PlainTime;
import net.time4j.calendar.EthiopianTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoExtension;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;


/**
 * <p>&Auml;thiopische Uhrzeit-Erweiterung. </p>
 *
 * @author  Meno Hochschild
 */
public class EthiopianExtension
    implements ChronoExtension {

    //~ Methoden ------------------------------------------------------

    @Override
    public boolean accept(Class<?> chronoType) {

        return false; // never called

    }

    @Override
    public Set<ChronoElement<?>> getElements(
        Locale locale,
        AttributeQuery attributes
    ) {

        return Collections.<ChronoElement<?>>singleton(EthiopianTime.ETHIOPIAN_HOUR);

    }

    @Override
    public ChronoEntity<?> resolve(
        ChronoEntity<?> entity,
        Locale locale,
        AttributeQuery attributes
    ) {

        if (entity.contains(EthiopianTime.ETHIOPIAN_HOUR)) {
            int h = entity.get(EthiopianTime.ETHIOPIAN_HOUR);
            if (h == 12) {
                h = 0;
            }
            h += 6;
            if (h >= 12) {
                h -= 12;
            }
            entity = entity.with(PlainTime.DIGITAL_HOUR_OF_AMPM, h);
        }

        return entity;

    }

    @Override
    public boolean canResolve(ChronoElement<?> element) {

        return EthiopianTime.ETHIOPIAN_HOUR.equals(element);

    }

}
