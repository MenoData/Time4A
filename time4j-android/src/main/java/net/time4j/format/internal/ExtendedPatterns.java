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

package net.time4j.format.internal;

import net.time4j.format.DisplayMode;
import net.time4j.format.FormatPatternProvider;

import java.util.Locale;


/**
 * <p>This <strong>SPI-interface</strong> serves for internal purposes only and is not part of public API. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   3.13/4.10
 * @see     java.util.ServiceLoader
 */
/*[deutsch]
 * <p>Dieses <strong>SPI-Interface</strong> dient nur internen Zwecken und ist nicht Teil
 * des &ouml;ffentlichen APIs. </p>
 *
 * @author  Meno Hochschild
 * @since   3.13/4.10
 * @see     java.util.ServiceLoader
 */
public interface ExtendedPatterns
    extends FormatPatternProvider {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Returns the localized time pattern suitable for formatting of objects
     * of type {@code PlainTime}. </p>
     *
     * @param   mode        display mode
     * @param   locale      language and country setting
     * @param   alt         requests alternative format
     * @return  localized time pattern
     * @see     net.time4j.PlainTime
     */
    /*[deutsch]
     * <p>Liefert das lokalisierte Uhrzeitmuster geeignet f&uuml;r die
     * Formatierung von Instanzen des Typs {@code PlainTime}. </p>
     *
     * @param   mode        display mode
     * @param   locale      language and country setting
     * @param   alt         requests alternative format
     * @return  localized time pattern
     * @see     net.time4j.PlainTime
     */
    String getTimePattern(
        DisplayMode mode,
        Locale locale,
        boolean alt
    );

}
