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

package net.time4j.android;

import java.io.IOException;
import java.io.InputStream;


/**
 * Encapsulates the knowledge how to access any asset file managed by Time4A.
 *
 * @author  Meno Hochschild
 * @since   3.28
 */
/*[deutsch]
 * Verk&ouml;rpert das Wissen, wie auf eine <i>asset</i>-Ressource von Time4A
 * zugegriffen werden kann.
 *
 * @author  Meno Hochschild
 * @since   3.28
 */
public interface AssetLocation {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Opens given asset as {@code InputStream}. </p>
     *
     * <p>The default implementation uses the {@code AssetManager} of the application context
     * to access the assets of Time4A. The path of such assets consists of three parts: </p>
     *
     * <ol>
     *     <li>net/time4j/</li>
     *     <li>The module name
     *     {&quot;calendar&quot;|&quot;i18n&quot;|&quot;olson&quot;|&quot;tzdata&quot;}</li>
     *     <li>/{asset-file}</li>
     * </ol>
     *
     * <p>Example: The timezone repository can be found by the path
     * &quot;net/time4j/tzdata/tzrepo/tzdata.repository&quot;. </p>
     *
     * @param   assetPath       path of asset
     * @return  InputStream
     * @throws  IOException if the asset cannot be open for any reason
     */
    /*[deutsch]
     * <p>&Ouml;ffnet die angegebene <i>asset</i>-Ressource als {@code InputStream}. </p>
     *
     * <p>Die Standard-Implementierung verwendet den {@code AssetManager} des Anwendungskontexts,
     * um auf die <i>assets</i> von Time4A zuzugreifen. Die notwendige Pfadangabe setzt sich
     * aus drei Teilen zusammen: </p>
     *
     * <ol>
     *     <li>net/time4j/</li>
     *     <li>Der Modulname
     *     {&quot;calendar&quot;|&quot;i18n&quot;|&quot;olson&quot;|&quot;tzdata&quot;}</li>
     *     <li>/{asset-file}</li>
     * </ol>
     *
     * <p>Beispiel: Das Zeitzonenrepositorium kann &uuml;ber den Pfad
     * &quot;net/time4j/tzdata/tzrepo/tzdata.repository&quot; gefunden werden. </p>
     *
     * @param   assetPath       path of asset
     * @return  InputStream
     * @throws  IOException if the asset cannot be open for any reason
     */
    InputStream open(String assetPath) throws IOException;

}
