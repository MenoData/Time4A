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

import java.io.IOException;
import java.io.Reader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.Set;


/**
 * <p>Erweiterung um den Zugang zu den eigenen <i>property-keys</i>. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 */
class UTF8ResourceBundle
    extends PropertyResourceBundle {

    //~ Instanzvariablen --------------------------------------------------

    private final Locale bundleLocale;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Standard-Konstruktor zum Auslesen von UTF-8-Dateien. </p>
     *
     * @param   reader          character stream from the property file
     * @param   bundleLocale    associated locale
     */
    UTF8ResourceBundle(
        Reader reader,
        Locale bundleLocale
    ) throws IOException {
        super(reader);

        this.bundleLocale = bundleLocale;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Locale getLocale() {

        return this.bundleLocale; // super.getLocale() can yield null on Android - see issue #316

    }

    /**
     * <p>Liefert die internen Schl&uuml;ssel. </p>
     *
     * @return  property keys contained only in this bundle
     */
    Set<String> getInternalKeys() {

        return super.handleKeySet();

    }

}
