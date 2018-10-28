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

package net.time4j.format;

import java.util.Locale;


/**
 * <p>This <strong>SPI-interface</strong> enables the access to localized
 * plural rules and is instantiated via a {@code ServiceLoader}-mechanism. </p>
 *
 * <p>If there is no external {@code PluralProvider} then Time4J will use
 * an internal implementation which only supports English and else yield
 * very simplified standard plural rules which might be incorrect. If
 * applications need true i18n-support then the i18n-module should be used
 * which has a general implementation of this interface. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     java.util.ServiceLoader
 */
/*[deutsch]
 * <p>Dieses <strong>SPI-Interface</strong> erm&ouml;glicht den Zugriff
 * auf {@code Locale}-abh&auml;ngige Pluralregeln und wird &uuml;ber einen
 * {@code ServiceLoader}-Mechanismus instanziert. </p>
 *
 * <p>Wird kein externer {@code PluralProvider} gefunden, wird intern
 * eine Instanz erzeugt, die entweder Englisch unterst&uuml;tzt oder sonst
 * stark vereinfachte Pluralregeln liefert, die nicht notwendig korrekt
 * sein m&uuml;ssen. Wenn Anwendungen echte I18n-Unterst&uuml;tzung
 * brauchen, sollten sie das i18n-Modul von Time4J einbinden, das einen
 * allgemeinen {@code PluralProvider} hat. </p>
 *
 * <p><strong>Specification:</strong>
 * Implementations must have a public no-arg constructor.</p>
 *
 * @author  Meno Hochschild
 * @since   2.2
 * @see     java.util.ServiceLoader
 */
public interface PluralProvider {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Defines the plural rules for given country or language. </p>
     *
     * @param   country     country or region
     * @param   numType     numerical category
     * @return  {@code PluralRules}-instance (maybe a default setting)
     * @since   2.2
     */
    /*[deutsch]
     * <p>Definiert die Pluralregeln f&uuml;r das angegebene Land. </p>
     *
     * @param   country     country or region
     * @param   numType     numerical category
     * @return  {@code PluralRules}-instance (maybe a default setting)
     * @since   2.2
     */
    PluralRules load(
        Locale country,
        NumberType numType
    );

}
