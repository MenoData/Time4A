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

package net.time4j.engine;


/**
 * <p>Provides the name of any calendar variant. </p>
 *
 * @author  Meno Hochschild
 * @since   3.6/4.4
 */
/*[deutsch]
 * <p>Liefert den Namen irgendeiner Kalendervariante. </p>
 *
 * @author  Meno Hochschild
 * @since   3.6/4.4
 */
public interface VariantSource {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Yields the variant name of a calendar system. </p>
     *
     * @return  String which is empty if there are no variants
     * @since   3.6/4.4
     */
    /*[deutsch]
     * <p>Liefert den Variantennamen eines Kalendersystems. </p>
     *
     * @return  String which is empty if there are no variants
     * @since   3.6/4.4
     */
    String getVariant();

}
