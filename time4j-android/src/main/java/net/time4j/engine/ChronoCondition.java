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
 * <p>Represents a temporal condition. </p>
 *
 * <p>Common examples are queries for Friday the thirteenth or if a date
 * matches a holiday or a weekend. This interface is very similar to the
 * type {@code ChronoQuery<T, Boolean>} but allows more clarity and does
 * not throw any exception. </p>
 *
 * @author  Meno Hochschild
 * @see     ChronoEntity#matches(ChronoCondition)
 */
/*[deutsch]
 * <p>Repr&auml;sentiert eine zeitliche Bedingung. </p>
 *
 * <p>Die g&auml;ngigsten Beispiele w&auml;ren etwa Freitag, der 13. oder
 * die Frage, ob ein Feiertag oder ein Wochenende vorliegen. Dieses Interface
 * ist im Prinzip sehr &auml;hnlich zu {@code ChronoQuery<T, Boolean>}, erlaubt
 * aber eine bessere sprachliche Klarheit und wirft keine Ausnahme. </p>
 *
 * @author  Meno Hochschild
 * @see     ChronoEntity#matches(ChronoCondition)
 */
public interface ChronoCondition<T> {

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Decides if given context matches this condition. </p>
     *
     * <p>Due to better readability it is recommended to use following
     * equivalent approach instead of this method:: </p>
     *
     * <pre>
     *  import static net.time4j.Weekday.SATURDAY;
     *  import static net.time4j.Month.JANUARY;
     *
     *  PlainDate date = PlainDate.of(2014, JANUARY, 25);
     *  System.out.println(SATURDAY.test(date)); // direct use
     *  System.out.println(date.matches(SATURDAY)); // recommended callback
     * </pre>
     *
     * @param   context     context as base of testing this condition
     * @return  {@code true} if given time context matches this condition
     *          else {@code false}
     */
    /*[deutsch]
     * <p>Entscheidet, ob der angegebene Zeitwertkontext diese Bedingung
     * erf&uuml;llt. </p>
     *
     * <p>Aus Gr&uuml;nden der Lesbarkeit ist es meistens besser, statt dieser
     * Methode vielmehr folgenden &auml;quivalenten Ausdruck zu verwenden: </p>
     *
     * <pre>
     *  import static net.time4j.Weekday.SATURDAY;
     *  import static net.time4j.Month.JANUARY;
     *
     *  PlainDate date = PlainDate.of(2014, JANUARY, 25);
     *  System.out.println(SATURDAY.test(date)); // direkte Benutzung
     *  System.out.println(date.matches(SATURDAY)); // empfohlenes Callback
     * </pre>
     *
     * @param   context     context as base of testing this condition
     * @return  {@code true} if given time context matches this condition
     *          else {@code false}
     */
    boolean test(T context);

}
