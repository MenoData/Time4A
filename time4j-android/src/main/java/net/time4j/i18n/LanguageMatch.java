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

import java.util.Locale;


/**
 * <p>Definiert CLDR-Alias-Sprachen. </p>
 *
 * @author  Meno Hochschild
 */
public enum LanguageMatch {

    //~ Statische Felder/Initialisierungen --------------------------------

    tl("fil"), // tagalog
    no("nb"), // norsk bokmål
    in("id"), // indonesian (old code)
    iw("he"); // hebrew (old code)

    static final LanguageMatch[] ALIASES = LanguageMatch.values();

    //~ Instanzvariablen --------------------------------------------------

    private final String alias;

    //~ Konstruktoren -----------------------------------------------------

    private LanguageMatch(String alias) {
        this.alias = alias;
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Liefert die unterst&uuml;tzte Ausweichsprache. </p>
     *
     * @param   desired     gew&uuml;nschte Sprache
     * @return  Ausweichsprache oder {@code desired} wenn nicht gefunden
     */
    public static String getAlias(Locale desired) {

        String key = desired.getLanguage();

        if (key.equals("no") && desired.getVariant().equals("NY") && desired.getCountry().equals("NO")) {
            return "nn";
        }

        for (LanguageMatch lm : ALIASES) {
            if (key.equals(lm.name())) {
                return lm.alias;
            }
        }

        return key;

    }

}
