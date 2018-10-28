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
 * <p>Describes a generic format style. </p>
 *
 * @author  Meno Hochschild
 * @since   3.10/4.7
 */
/*[deutsch]
 * <p>Beschreibt einen allgemeinen Formatstil. </p>
 *
 * @author  Meno Hochschild
 * @since   3.10/4.7
 */
public interface DisplayStyle {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Determines the appropriate {@code DateFormat}-constant. </p>
     *
     * @return  int
     * @see     java.text.DateFormat#FULL
     * @see     java.text.DateFormat#LONG
     * @see     java.text.DateFormat#MEDIUM
     * @see     java.text.DateFormat#SHORT
     * @since   3.10/4.7
     */
    /*[deutsch]
     * <p>Ermittelt die assoziierte {@code DateFormat}-Konstante. </p>
     *
     * @return  int
     * @see     java.text.DateFormat#FULL
     * @see     java.text.DateFormat#LONG
     * @see     java.text.DateFormat#MEDIUM
     * @see     java.text.DateFormat#SHORT
     * @since   3.10/4.7
     */
    int getStyleValue();

}
