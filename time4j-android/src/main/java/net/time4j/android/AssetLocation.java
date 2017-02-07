/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2017 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (AssetLocation.java) is part of project Time4J.
 *
 * Time4J is free software: You can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Time4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Time4J. If not, see <http://www.gnu.org/licenses/>.
 * -----------------------------------------------------------------------
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
