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

package net.time4j.format.expert;

import net.time4j.engine.AttributeKey;
import net.time4j.engine.AttributeQuery;


/**
 * <p>Represents a merged set of format attributes where the outer attributes
 * have precedence over the inner attributes. </p>
 *
 * @since   3.22/4.18
 */
final class MergedAttributes
    implements AttributeQuery {

    //~ Instanzvariablen --------------------------------------------------

    private final AttributeQuery outer;
    private final AttributeQuery inner;

    //~ Konstruktoren -----------------------------------------------------

    MergedAttributes(
        AttributeQuery outer,
        AttributeQuery inner
    ) {
        super();

        this.outer = outer;
        this.inner = inner;

    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean contains(AttributeKey<?> key) {

        return (this.outer.contains(key) || this.inner.contains(key));

    }

    @Override
    public <A> A get(AttributeKey<A> key) {

        if (this.outer.contains(key)) {
            return this.outer.get(key);
        }

        return this.inner.get(key);

    }

    @Override
    public <A> A get(
        AttributeKey<A> key,
        A defaultValue
    ) {

        if (this.outer.contains(key)) {
            return this.outer.get(key);
        }

        return this.inner.get(key, defaultValue);

    }

}
