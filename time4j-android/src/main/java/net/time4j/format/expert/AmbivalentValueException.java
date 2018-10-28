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

package net.time4j.format.expert;

import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoElement;


/**
 * <p>Zeigt an, da&szlig; das dasselbe Element mit verschiedenen Werten
 * assoziiert wurde. </p>
 *
 * @author  Meno Hochschild
 * @since   3.0
 */
class AmbivalentValueException
    extends ChronoException {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final long serialVersionUID = -4315329288187364457L;

    //~ Konstruktoren -----------------------------------------------------

    AmbivalentValueException(ChronoElement<?> element) {
        super(
            "Duplicate element parsed with different values: "
            + element.name());

    }

}
