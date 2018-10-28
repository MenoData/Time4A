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

import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoExtension;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;


/**
 * <p>Wochenmodell-Erweiterung. </p>
 *
 * @author  Meno Hochschild
 */
class WeekExtension
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

        if (locale.getCountry().isEmpty()) {
            return Collections.emptySet();
        }

        return Weekmodel.of(locale).getElements();

    }

    @Override
    public ChronoEntity<?> resolve(
        ChronoEntity<?> entity,
        Locale locale,
        AttributeQuery attributes
    ) {

        return entity; // no-op

    }

    @Override
    public boolean canResolve(ChronoElement<?> element) {

        return false;

    }

}
