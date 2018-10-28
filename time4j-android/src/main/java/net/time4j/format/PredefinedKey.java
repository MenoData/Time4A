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

import net.time4j.engine.AttributeKey;


/**
 * <p>Repr&auml;sentiert einen vordefinierten Attributschl&uuml;ssel. </p>
 *
 * @param   <A> generic type of associated attribute values
 * @author  Meno Hochschild
 */
final class PredefinedKey<A>
    implements AttributeKey<A> {

    //~ Instanzvariablen --------------------------------------------------

    private final String name;
    private final Class<A> type;

    //~ Konstruktoren -----------------------------------------------------

    private PredefinedKey(
        String name,
        Class<A> type
    ) {
        super();

        if (name == null) {
            throw new NullPointerException("Missing name of attribute key.");
        } else if (type == null) {
            throw new NullPointerException("Missing type of attribute.");
        }

        this.name = name;
        this.type = type;

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Erzeugt einen neuen vordefinierten Attributschl&uuml;ssel. </p>
     *
     * @param   <A> generic type of associated attribute values
     * @param   name    name of attribute key
     * @param   type    reified type of attribute values
     * @return  new instance
     */
    static <A> PredefinedKey<A> valueOf(
        String name,
        Class<A> type
    ) {

        return new PredefinedKey<A>(name, type);

    }

    @Override
    public String name() {

        return this.name;

    }

    @Override
    public Class<A> type() {

        return this.type;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof PredefinedKey) {
            PredefinedKey<?> that = (PredefinedKey) obj;
            return (this.name.equals(that.name) && this.type.equals(that.type));
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return this.name.hashCode();

    }

    @Override
    public String toString() {

        return this.type.getName() + "@" + this.name;

    }

}
