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

import net.time4j.base.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * <p>Spezielles Bundle-Control wegen UTF-8-Dateizugriff und &Auml;nderung des
 * Standard-Fallback-Mechanismus. </p>
 *
 * @author  Meno Hochschild
 * @since   1.2
 */
public class UTF8ResourceControl
    extends ResourceBundle.Control {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final String FORMAT_ID = "time4j.properties";
    public static final ResourceBundle.Control SINGLETON = new UTF8ResourceControl();

    //~ Konstruktoren -----------------------------------------------------

    /**
     * For subclasses only.
     */
    /*[deutsch]
     * Nur f&uuml;r Subklassen.
     */
    protected UTF8ResourceControl() {
        super();

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public Locale getFallbackLocale(
        String baseName,
        Locale locale
    ) {

        if (baseName == null || locale == null) {
            throw new NullPointerException();
        }

        return null;

	}

    @Override
    public List<String> getFormats(String baseName) {

        return Collections.singletonList(FORMAT_ID);

    }

    @Override
    public ResourceBundle newBundle(
        String baseName,
        Locale locale,
        String format,
        ClassLoader loader,
        boolean reload
    ) throws IllegalAccessException, InstantiationException, IOException {

        if (format.equals(FORMAT_ID)) {

            ResourceBundle bundle = null;

            String bundleName =
                this.toBundleName(baseName, locale);
            String resourceName =
                this.toResourceName(bundleName, "properties");

            URI uri = ResourceLoader.getInstance().locate(getModuleName(), getModuleRef(), resourceName);
            InputStream stream = ResourceLoader.getInstance().load(uri, reload);

            if (stream == null) {
                try {
                    stream = ResourceLoader.getInstance().load(getModuleRef(), resourceName, reload);
                } catch (IOException ioe) {
                    // okay, maybe the resource is simply not there
                    return null;
                }
            }

            if (stream != null) {
                Reader reader = null;

                try {
                    reader = new UTF8ResourceReader(stream);
                    bundle = new UTF8ResourceBundle(reader, locale);
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }

            return bundle;

        } else {
            throw new UnsupportedOperationException(
                "Unknown resource bundle format: " + format);
        }

    }

    @Override
    public String toBundleName(
        String baseName,
        Locale locale
    ) {

        if (locale == Locale.ROOT) {
            return baseName;
        }

        String language = LanguageMatch.getAlias(locale);
        String country = locale.getCountry();
        String variant = locale.getVariant();

        if (language.isEmpty() && country.isEmpty() && variant.isEmpty()) {
            return baseName;
        }

        StringBuilder sb = new StringBuilder(baseName);
        sb.append('_').append(language);

        if (!variant.isEmpty()) {
            sb.append('_').append(country).append('_').append(variant);
        } else if (!country.isEmpty()) {
            sb.append('_').append(country);
        }

        return sb.toString();

    }

    protected String getModuleName() {

        return "i18n";

    }

    protected Class<?> getModuleRef() {

        return UTF8ResourceControl.class;

    }

}
