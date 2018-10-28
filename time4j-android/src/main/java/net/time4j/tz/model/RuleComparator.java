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

import java.util.Comparator;

/**
 * <p>Compares daylight saving rules in ascending order. </p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 */
enum RuleComparator
    implements Comparator<DaylightSavingRule> {

    //~ Statische Felder/Initialisierungen --------------------------------

    INSTANCE;

    //~ Methoden ----------------------------------------------------------

    @Override
    public int compare(DaylightSavingRule o1, DaylightSavingRule o2) {

        int result = o1.getDate(2000).compareTo(o2.getDate(2000)); // leap year

        if (result == 0) {
            result = o1.getTimeOfDay().compareTo(o2.getTimeOfDay());
        }

        return result;

    }

}
