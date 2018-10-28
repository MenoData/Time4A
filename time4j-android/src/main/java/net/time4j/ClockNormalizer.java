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

import net.time4j.engine.Normalizer;
import net.time4j.engine.TimeSpan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Hilfsobjekt zur Normalisierung einer Uhrzeit-bezogenen Dauer. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
final class ClockNormalizer
    implements Normalizer<ClockUnit> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final int ONLY_MODE = 0;
    private static final int TRUNCATE_MODE = 1;
    private static final int ROUNDING_MODE = 2;

    private static final Map<ClockUnit, ClockNormalizer> MAP_ONLY;
    private static final Map<ClockUnit, ClockNormalizer> MAP_TRUNC;
    private static final Map<ClockUnit, ClockNormalizer> MAP_ROUND;

    static {
        MAP_ONLY = fill(ONLY_MODE);
        MAP_TRUNC = fill(TRUNCATE_MODE);
        MAP_ROUND = fill(ROUNDING_MODE);
    }

    //~ Instanzvariablen --------------------------------------------------

    private final ClockUnit unit;
    private final int mode;

    //~ Konstruktoren -----------------------------------------------------

    private ClockNormalizer(
        ClockUnit unit,
        int mode
    ) {
        super();

        this.unit = unit;
        this.mode = mode;

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Liefert einen passenden Normalisierer. </p>
     *
     * @param   unit    clock unit
     * @return  unique normalizer instance
     */
    static ClockNormalizer ofOnlyMode(ClockUnit unit) {

        ClockNormalizer ret = MAP_ONLY.get(unit);

        if (ret == null) {
            throw new IllegalArgumentException(unit.name());
        }

        return ret;

    }

    /**
     * <p>Liefert einen passenden Normalisierer. </p>
     *
     * @param   unit    clock unit
     * @return  unique normalizer instance
     */
    static ClockNormalizer ofTruncateMode(ClockUnit unit) {

        ClockNormalizer ret = MAP_TRUNC.get(unit);

        if (ret == null) {
            throw new IllegalArgumentException(unit.name());
        }

        return ret;

    }

    /**
     * <p>Liefert einen passenden Normalisierer. </p>
     *
     * @param   unit    clock unit
     * @return  unique normalizer instance
     */
    static ClockNormalizer ofRoundingMode(ClockUnit unit) {

        ClockNormalizer ret = MAP_ROUND.get(unit);

        if (ret == null) {
            throw new IllegalArgumentException(unit.name());
        }

        return ret;

    }

    @Override
    public Duration<ClockUnit> normalize(TimeSpan<? extends ClockUnit> dur) {

        switch (mode) {
            case ONLY_MODE:
                return Duration.of(this.unit.convert(dur), this.unit);
            case TRUNCATE_MODE:
                List<TimeSpan.Item<ClockUnit>> itemList = new ArrayList<TimeSpan.Item<ClockUnit>>();
                for (TimeSpan.Item<? extends ClockUnit> item : dur.getTotalLength()) {
                    ClockUnit unit = item.getUnit();
                    if (unit.compareTo(this.unit) <= 0) {
                        itemList.add(TimeSpan.Item.of(item.getAmount(), unit));
                    }
                }
                if (itemList.isEmpty()) {
                    return Duration.ofZero();
                } else {
                    return new Duration<ClockUnit>(itemList, dur.isNegative());
                }
            case ROUNDING_MODE:
                boolean negative = dur.isNegative();
                Duration<ClockUnit> d = Duration.ofZero();
                d = d.plus(dur);
                if (negative) {
                    d = d.abs();
                }
                d = d.with(Duration.STD_CLOCK_PERIOD);
                long half;
                switch (this.unit) {
                    case HOURS:
                    case MINUTES:
                        half = 30;
                        break;
                    case SECONDS:
                    case MILLIS:
                    case MICROS:
                        half = 500;
                        break;
                    default:
                        return d; // nanos
                }
                ClockUnit smaller = ClockUnit.values()[this.unit.ordinal() + 1];
                if (d.getPartialAmount(smaller) >= half) {
                    d = d.plus(1, this.unit).with(Duration.STD_CLOCK_PERIOD);
                }
                d = d.with(this.unit.truncated());
                if (negative) {
                    d = d.inverse();
                }
                return d;
            default:
                throw new UnsupportedOperationException("Unknown mode: " + mode);
        }

    }

    private static Map<ClockUnit, ClockNormalizer> fill(int mode) {

        Map<ClockUnit, ClockNormalizer> m =
            new EnumMap<ClockUnit, ClockNormalizer>(ClockUnit.class);

        for (ClockUnit unit : ClockUnit.values()) {
            m.put(unit, new ClockNormalizer(unit, mode));
        }

        return Collections.unmodifiableMap(m);

    }

}
