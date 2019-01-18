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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>This {@code Annotation} can be used to document all chronological
 * elements which allow formatted representations. </p>
 *
 * <p>Usage note: Usually only element constants with the modifiers
 * <i>static</i> und <i>final</i> are target of this {@code Annotation}.
 * The target type {@code ElementType.METHOD} is only permitted if
 * elements are generated in a {@code ChronoExtension}. </p>
 *
 * @author  Meno Hochschild
 * @see     ChronoElement
 * @see     ChronoExtension
 */
/*[deutsch]
 * <p>Mit dieser {@code Annotation} k&ouml;nnen alle chronologischen Elemente
 * dokumentiert werden, die formatierte Darstellungen erlauben. </p>
 *
 * <p>Verwendungshinweis: Ziel der {@code Annotation} sind normalerweise
 * nur Elementkonstanten mit den Modifiern <i>static</i> und <i>final</i>.
 * Der Zieltyp {@code ElementType.METHOD} ist lediglich dann erlaubt, wenn
 * Elemente in einer {@code ChronoExtension} generiert werden. </p>
 *
 * @author  Meno Hochschild
 * @see     ChronoElement
 * @see     ChronoExtension
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormattableElement {

    //~ Methoden --------------------------------------------------------------

    /**
     * <p>Returns the associated format pattern symbol in the standard
     * format context. </p>
     *
     * <p>Format pattern symbols should be unique among all registered
     * elements of a given chronology. In standard elements they correspond
     * to the format symbols defined by unicode organization in CLDR. The
     * symbol is case-sensitive. </p>
     *
     * @return  char
     * @see     net.time4j.format.OutputContext#FORMAT
     */
    /*[deutsch]
     * <p>Liefert das zugeh&ouml;rige Formatmusterzeichen im normalen
     * Formatkontext. </p>
     *
     * <p>Formatmusterzeichen sollten unter allen registrierten Elementen
     * einer gegebenen Chronologie eindeutig sein. In Standardelementen
     * entsprechen sie den in der vom Unicode-Konsortium definierten
     * CLDR-Syntax festgelegten Formatsymbolen. Es wird zwischen Gro&szlig;-
     * und Kleinschreibung unterschieden. </p>
     *
     * @return  char
     * @see     net.time4j.format.OutputContext#FORMAT
     */
    String format();

    /**
     * <p>Returns the alternative format pattern symbol. </p>
     *
     * <p>Almost equivalent to {@code format()} with the difference that the
     * element shall be formatted in a stand-alone context in CLDR (for example
     * nominative form &quot;x January&quot; instead of the ordinal form
     * &quot;x-th January&quot;). However, the stand-alone context is not
     * relevant for English - as &quot;January&quot; is still the same word,
     * only relevant for some languages which make an explicit grammar
     * difference. </p>
     *
     * <p>When used in dynamic pattern style, this alternative usually denotes
     * the small letter for numeric formatting - in contrast to the standard
     * big letter which also allows for textual formatting. </p>
     *
     * <p>The default value of an empty string just means that the
     * stand-alone context should use the same value as in the format
     * context. </p>
     *
     * @return  char
     * @see     net.time4j.format.OutputContext#STANDALONE
     */
    /*[deutsch]
     * <p>Liefert das alternative Formatmusterzeichen. </p>
     *
     * <p>Entspricht in CLDR weitgehend {@code format()} mit dem Unterschied,
     * da&szlig; hier das Element in einem alleinstehenden Kontext
     * formatiert werden soll (zum Beispiel nominativ &quot;x Januar&quot;
     * statt der Ordinalform &quot;x-ter Januar&quot;). </p>
     *
     * <p>In dynamischen Formatmustern markiert diese Alternative gew&ouml;hnlich
     * den Kleinbuchstaben f&uuml;r die numerische Formatierung - im Gegensatz zum
     * Gro&szlig;buchstaben, der eine textuelle Formatierung erlauben kann. </p>
     *
     * <p>Der Standardwert einer leeren Zeichenkette bedeutet nur, da&szlig;
     * der gleiche Wert wie im normalen Formatkontext gelten soll. </p>
     *
     * @return  char
     * @see     net.time4j.format.OutputContext#STANDALONE
     */
    String alt() default "";

    /**
     * <p>Defines a dynamic pattern symbol. </p>
     *
     * <p>Dynamic pattern symbols are not handled by CLDR but are resolved by a dynamic element search. </p>
     *
     * @return  boolean (default value is false)
     * @since   4.3
     * @see     net.time4j.format.expert.PatternType#DYNAMIC
     */
    /*[deutsch]
     * <p>Definiert ein dynamisches Formatmustersymbol. </p>
     *
     * <p>Dynamische Formatmustersymbole werden nicht wie in CLDR gehandhabt, sondern werden mittels einer
     * dynamischen Elementsuche aufgel&ouml;st. </p>
     *
     * @return  boolean (default value is false)
     * @since   4.3
     * @see     net.time4j.format.expert.PatternType#DYNAMIC
     */
    boolean dynamic() default false;

}
