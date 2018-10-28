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

package net.time4j.i18n;

import net.time4j.PlainDate;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoExtension;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.Leniency;
import net.time4j.history.ChronoHistory;
import net.time4j.history.HistoricDate;
import net.time4j.history.HistoricEra;
import net.time4j.history.YearDefinition;
import net.time4j.history.internal.HistoricAttribute;
import net.time4j.history.internal.StdHistoricalElement;

import java.util.Locale;
import java.util.Set;

import static net.time4j.history.YearDefinition.DUAL_DATING;


/**
 * <p>Defines a historic extension of {@code PlainDate}. </p>
 *
 * @author  Meno Hochschild
 * @since   3.1
 */
public class HistoricExtension
    implements ChronoExtension {

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean accept(Class<?> chronoType) {

        return (chronoType == PlainDate.class);

    }

    @Override
    public Set<ChronoElement<?>> getElements(
        Locale locale,
        AttributeQuery attributes
    ) {

        return getHistory(locale, attributes).getElements();

    }

    @Override
    public ChronoEntity<?> resolve(
        ChronoEntity<?> entity,
        Locale locale,
        AttributeQuery attributes
    ) {

        return this.resolve(entity, getHistory(locale, attributes), attributes);

    }

    /**
     * <p>Also used by {@code HistoricCalendar}. </p>
     *
     * @param   entity  any kind of map from chronological elements to
     *                  their values (note that the main use case of parsed
     *                  data has no chronology and allows the virtual value
     *                  {@code null} to be set as indication for removing
     *                  associated element)
     * @param   history         calendar history
     * @param   attributes      global configuration attributes of parser
     * @return  eventually changed entity
     * @throws  IllegalArgumentException if resolving fails due to inconsistencies
     * @since   3.36/4.31
     */
    /*[deutsch]
     * <p>Auch von {@code HistoricCalendar} gebraucht. </p>
     *
     * @param   entity  any kind of map from chronological elements to
     *                  their values (note that the main use case of parsed
     *                  data has no chronology and allows the virtual value
     *                  {@code null} to be set as indication for removing
     *                  associated element)
     * @param   history         calendar history
     * @param   attributes      global configuration attributes of parser
     * @return  eventually changed entity
     * @throws  IllegalArgumentException if resolving fails due to inconsistencies
     * @since   3.36/4.31
     */
    public ChronoEntity<?> resolve(
        ChronoEntity<?> entity,
        ChronoHistory history,
        AttributeQuery attributes
    ) {

        HistoricEra era = null;

        if (entity.contains(history.era())) {
            era = entity.get(history.era());
        } else if (attributes.get(Attributes.LENIENCY, Leniency.SMART).isLax()) {
            era = HistoricEra.AD;
        }

        if ((era != null) && entity.contains(history.yearOfEra())) {
            int yearOfEra = entity.getInt(history.yearOfEra());

            if (entity.contains(history.month()) && entity.contains(history.dayOfMonth())) {
                YearDefinition yd = attributes.get(ChronoHistory.YEAR_DEFINITION, DUAL_DATING);
                int month = entity.getInt(history.month());
                int dayOfMonth = entity.getInt(history.dayOfMonth());
                HistoricDate hd = HistoricDate.of(era, yearOfEra, month, dayOfMonth, yd, history.getNewYearStrategy());
                PlainDate date = history.convert(hd);
                entity.with(history.era(), null);
                entity.with(history.yearOfEra(), null);
                entity.with(history.month(), null);
                entity.with(history.dayOfMonth(), null);
                return entity.with(PlainDate.COMPONENT, date);
            } else if (entity.contains(history.dayOfYear())) {
                int doy = entity.getInt(history.dayOfYear());
                if (entity.contains(StdHistoricalElement.YEAR_OF_DISPLAY)) {
                    yearOfEra = entity.getInt(StdHistoricalElement.YEAR_OF_DISPLAY);
                }
                HistoricDate newYear = history.getBeginOfYear(era, yearOfEra);
                PlainDate date = history.convert(newYear).with(history.dayOfYear(), doy);
                return entity.with(PlainDate.COMPONENT, date);
            }
        }

        return entity;

    }

    @Override
    public boolean canResolve(ChronoElement<?> element) {

        return (element instanceof StdHistoricalElement);

    }

    private static ChronoHistory getHistory(
        Locale locale,
        AttributeQuery attributes
    ) {

        if (attributes.get(Attributes.CALENDAR_TYPE, CalendarText.ISO_CALENDAR_TYPE).equals("julian")) {
            return ChronoHistory.PROLEPTIC_JULIAN;
        } else if (attributes.contains(HistoricAttribute.CALENDAR_HISTORY)) {
            return attributes.get(HistoricAttribute.CALENDAR_HISTORY);
        } else if (
            attributes.get(Attributes.CALENDAR_TYPE, CalendarText.ISO_CALENDAR_TYPE).equals("historic")
            && attributes.contains(Attributes.CALENDAR_VARIANT)
        ) {
            return ChronoHistory.from(attributes.get(Attributes.CALENDAR_VARIANT));
        } else {
            return ChronoHistory.of(locale);
        }

    }

}
