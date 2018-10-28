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

package net.time4j.calendar;

import net.time4j.base.GregorianMath;
import net.time4j.engine.BasicElement;

import java.io.ObjectStreamException;


/**
 * <p>Specific element for the related gregorian year. </p>
 *
 * @author  Meno Hochschild
 * @since   3.20/4.16
 */
/*[deutsch]
 * <p>Spezialelement f&uuml;r das gregorianische Bezugsjahr. </p>
 *
 * @author  Meno Hochschild
 * @since   3.20/4.16
 */
final class RelatedGregorianYearElement
    extends BasicElement<Integer> {

    //~ Statische Felder/Initialisierungen --------------------------------

    static final RelatedGregorianYearElement SINGLETON = new RelatedGregorianYearElement();

    private static final long serialVersionUID = -1117064522468823402L;

    //~ Konstruktoren -----------------------------------------------------

    private RelatedGregorianYearElement() {
        super("RELATED_GREGORIAN_YEAR");

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Integer> getType() {

        return Integer.class;

    }

    @Override
    public Integer getDefaultMinimum() {

        return Integer.valueOf(GregorianMath.MIN_YEAR);

    }

    @Override
    public Integer getDefaultMaximum() {

        return Integer.valueOf(GregorianMath.MAX_YEAR);

    }

    @Override
    public char getSymbol() {

        return 'r';

    }

    @Override
    public boolean isDateElement() {

        return true;

    }

    @Override
    public boolean isTimeElement() {

        return false;

    }

    @Override
    protected boolean isSingleton() {

        return true;

    }

    /**
     * @serialData  preserves singleton semantic
     * @return      resolved singleton
     */
    protected Object readResolve() throws ObjectStreamException {

        return SINGLETON;

    }

}
