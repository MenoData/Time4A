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

import net.time4j.PlainDate;
import net.time4j.calendar.KoreanCalendar;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoExtension;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;


/**
 * <p>Koreanische Datumserweiterung. </p>
 *
 * @author  Meno Hochschild
 * @since   3.40/4.35
 */
public class KoreanExtension
    implements ChronoExtension {

    //~ Methoden ------------------------------------------------------

    @Override
    public boolean accept(Class<?> chronoType) {

        return (chronoType == PlainDate.class);

    }

    @Override
    public Set<ChronoElement<?>> getElements(
        Locale locale,
        AttributeQuery attributes
    ) {

        return Collections.emptySet();

    }

    @Override
    public ChronoEntity<?> resolve(
        ChronoEntity<?> entity,
        Locale locale,
        AttributeQuery attributes
    ) {

        if (entity.contains(KoreanCalendar.YEAR_OF_ERA)) {
            int dangi = entity.getInt(KoreanCalendar.YEAR_OF_ERA);
            entity = entity.with(PlainDate.YEAR, dangi - 2333);
        }

        return entity;

    }

    @Override
    public boolean canResolve(ChronoElement<?> element) {

        return (element == KoreanCalendar.YEAR_OF_ERA);

    }

}
