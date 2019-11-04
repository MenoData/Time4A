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

import net.time4j.base.MathUtils;

import java.util.List;


/**
 * <p>Defines a timespan using the default algorithm of Time4J. </p>
 *
 * <p><a name="algorithm"></a>Dependent on the sign of the duration
 * there are three cases: </p>
 *
 * <ol>
 *  <li>Empty duration =&gt; The method {@code addTo()} just yields
 *  a given time point unaffected. </li>
 *  <li>Positive duration =&gt; All contained time units will be added
 *  in the order from largest to smallest units. Convertible units will
 *  be consolidated in one step. The new time point is relative to
 *  given time point argument in the future. </li>
 *  <li>Negative duration =&gt; All contained time units will be
 *  subtracted in the reversed order from the smallest to the largest
 *  units. Convertible units will be consolidated in one step. The
 *  new time point is relative to given time point argument in the
 *  past. </li>
 * </ol>
 *
 * <p>Usually possible element overflows will be truncated such that
 * the last valid time point will be determined. The rest of the
 * discussion is about the gregorian calendar system and the addition
 * of months and days, but is also applicable on other calendar
 * systems. Examples in pseudo-code: </p>
 *
 * <ul>
 *  <li>[2011-05-31] + [P4D] = [2011-06-04]</li>
 *  <li>[2011-05-31] + [P9M] = [2012-02-29]</li>
 *  <li>[2011-05-31] + [-P1M] = [2011-04-30]</li>
 *  <li>[2011-05-30] + [P1M1D] = [2011-07-01]</li>
 *  <li>[2011-05-31] + [P1M1D] = [2011-07-01]</li>
 *  <li>[2011-07-01] + [-P1M1D] = [2011-05-30]</li>
 *  <li>[2011-05-31] + [-P1Y1M1D] = [2010-04-30]</li>
 * </ul>
 *
 * <p>If the smallest existing time unit is used then following
 * invariants hold for the addition of a duration and the delta
 * between two time points. Let t1 and t2 be two time points with
 * {@code t1 <= t2}: </p>
 *
 * <ul><li>FIRST INVARIANCE:
 *  {@code t1.plus(t1.until(t2)).equals(t2) == true}
 * </li><li>SECOND INVARIANCE:
 *  {@code t2.until(t1).equals(t1.until(t2).inverse()) == true}
 * </li></ul>
 *
 * <p>Following condition only holds if either the day-of-month of any involved date is
 * smaller than 28 or if a reversible metric is used: </p>
 *
 * <ul><li>THIRD INVARIANCE:
 *  {@code t2.minus(t1.until(t2)).equals(t1) == true}
 * </li></ul>
 *
 * <p><strong>Note:</strong> Usually the third invariance is NOT valid. A counter example
 * is given with following dates: {t1, t2} = {[2011-03-31], [2011-07-01]} =&gt; P3M1D (using
 * the standard metric) because of [2011-07-01] - P3M1D = [2011-03-30]. Example for using
 * a reversible metric: </p>
 *
 * <pre>
 *     PlainDate d1 = PlainDate.of(2011, 3, 31);
 *     PlainDate d2 = PlainDate.of(2011, 7, 1);
 *
 *     TimeMetric&lt;CalendarUnit, Duration&lt;CalendarUnit&gt;&gt; metric =
 *        Duration.inYearsMonthsDays().reversible();
 *     Duration&lt;CalendarUnit&gt; duration =
 *        metric.between(d1, d2); // P2M31D
 *     Duration&lt;CalendarUnit&gt; invDur =
 *        metric.between(d2, d1); // -P2M31D
 *
 *     boolean firstInvariance = d1.plus(duration).equals(d2); // true
 *     boolean secondInvariance = invDur.equals(duration.inverse()); // true
 *     boolean thirdInvariance = d2.minus(duration).equals(d1); // true
 * </pre>
 *
 * <div style="background-color:#E0E0E0;padding:5px;margin:5px;">
 *
 * <p><strong>About the mathematical background of specified
 * algorithm:</strong> Note that the addition is not commutative,
 * hence the order of addition steps will impact the result. For
 * example a two-step-addition looks like: </p>
 *
 * <ul>
 *  <li>[2011-05-30] + [P1M] + [P2D] = [2011-07-02]</li>
 *  <li>[2011-05-30] + [P2D] + [P1M] = [2011-07-01]</li>
 * </ul>
 *
 * <p>In this context it is understandable that the order of
 * addition steps is dependent on the sign of the duration. If
 * the addition of a negative duration is interpreted as the
 * reversal of the addition of a positive duration then following
 * equivalent relation holds (paying attention to non-commutativity
 * and given the side conditions to compute the duration without
 * remainder completely and to consider a minus-operation as
 * equalizing a plus-operation: </p>
 *
 * <ul>
 *  <li>[t1] - [months] - [days] = [t2]</li>
 *  <li>=&gt; [t1] - [months] - [days] + [days] = [t2] + [days]</li>
 *  <li>=&gt; [t1] - [months] = [t2] + [days]</li>
 *  <li>=&gt; [t1] - [months] + [months] = [t2] + [days] + [months]</li>
 *  <li>=&gt; [t1] = [t2] + [days] + [months] // day-of-month &lt;= 28</li>
 * </ul>
 *
 * <p>The permutation of addition steps is obvious. If Time4J had
 * tried the alternative to first add the months and then the days
 * even in case of a negative duration then we would have with</p>
 *
 * <ul>
 *  <li>t1 = [2013-02-01]</li>
 *  <li>t2 = [2013-03-31]</li>
 *  <li>duration = t1.until(t2) = [P1M30D]</li>
 * </ul>
 *
 * <p>the situation that the mentioned third invariance would be violated
 * even if the day of month is the first day of month: t2.minus(P1M30D)
 * would not yield t1 but [2013-01-29]. Surely, the sign-dependent
 * execution of addition steps cannot completely guarantee the third
 * invariance in case of factory-created durations but it can guarantee
 * it at least for all days in original date until the 28th of month. </p>
 *
 * <p>Furthermore the specified algorithm ensures the second invariance
 * {@code Duration([t1, t2]) = -Duration([t2, t1])} which expresses
 * a physical property of any duration as a directed temporal amount.
 * The second invariance means that the sign of a duration can only
 * qualify if the first time point is before the second time point or
 * other way around. The sign must not qualify the always positive length
 * of a duration itself however. </p>
 * </div>
 *
 * @author  Meno Hochschild
 * @see     AbstractMetric
 * @see     #addTo(TimePoint)
 * @see     #subtractFrom(TimePoint)
 */
/*[deutsch]
 * <p>Definiert eine Zeitspanne unter Verwendung des Standard-Algorithmus
 * von Time4J. </p>
 *
 * <p><a name="algorithm"></a>In Abh&auml;ngigkeit vom Vorzeichen der Dauer
 * gibt es drei F&auml;lle zu betrachten: </p>
 *
 * <ol>
 *  <li>Leere Dauer =&gt; Die Methode {@code addTo()} liefert einfach nur
 *  den angegebenen Zeitpunkt unver&auml;ndert zur&uuml;ck. </li>
 *  <li>Positive Dauer =&gt; Alle in der Dauer enthaltenen
 *  Zeiteinheiten werden in der Reihenfolge von den gr&ouml;&szlig;ten zu
 *  den kleinsten bzw. genauesten Einheiten hin addiert. Konvertierbare
 *  Zeiteinheiten werden in einem Additionsschritt zusammengefasst. Der
 *  neue Zeitpunkt liegt relativ zum Argument in der Zukunft. </li>
 *  <li>Negative Dauer =&gt; Alle in der Dauer enthaltenen
 *  Zeiteinheiten werden in der umgekehrten Reihenfolge von den kleinsten
 *  bzw. genauesten zu den gr&ouml;&szlig;ten Einheiten hin subtrahiert.
 *  Konvertierbare Zeiteinheiten werden in einem Subtraktionsschritt
 *  zusammengefasst. Der neue Zeitpunkt liegt relativ zum Argument
 *  in der Vergangenheit. </li>
 * </ol>
 *
 * <p>&Uuml;blicherweise werden eventuell auftretende &Uuml;berl&auml;ufe
 * auf den zuletzt g&uuml;ltigen Zeitpunkt gekappt. Der Rest der Diskussion
 * besch&auml;ftigt sich mit dem gregorianischen Kalendersystem und der
 * Addition von Monaten und Tagen, gilt aber sinngem&auml;&szlig; auch
 * f&uuml;r andere Kalendersysteme. Beispiele in Pseudocode: </p>
 *
 * <ul>
 *  <li>[2011-05-31] + [P4D] = [2011-06-04]</li>
 *  <li>[2011-05-31] + [P9M] = [2012-02-29]</li>
 *  <li>[2011-05-31] + [-P1M] = [2011-04-30]</li>
 *  <li>[2011-05-30] + [P1M1D] = [2011-07-01]</li>
 *  <li>[2011-05-31] + [P1M1D] = [2011-07-01]</li>
 *  <li>[2011-07-01] + [-P1M1D] = [2011-05-30]</li>
 *  <li>[2011-05-31] + [-P1Y1M1D] = [2010-04-30]</li>
 * </ul>
 *
 * <p>Insgesamt gelten f&uuml;r die Addition einer Dauer und
 * die Differenz zweier Zeitpunkte zu einer Dauer folgende
 * Invarianzbedingungen, wenn auch die jeweils genaueste Zeiteinheit
 * in der Differenzberechnung angegeben wird. Seien t1 und t2 zwei
 * beliebige Zeitpunkte mit {@code t1 <= t2}: </p>
 *
 * <ul><li>ERSTE INVARIANZ:
 *  {@code t1.plus(t1.until(t2)).equals(t2) == true}
 * </li><li>ZWEITE INVARIANZ:
 *  {@code t2.until(t1).equals(t1.until(t2).inverse()) == true}
 * </li></ul>
 *
 * <p>Die folgende Invarianzbedingung gilt nur, wenn entweder der betroffene
 * Tag des Monats in den beteiligten Datumsangaben kleiner als 28 ist oder
 * wenn eine umkehrbare Metrik verwendet wird: </p>
 *
 * <ul><li>DRITTE INVARIANZ:
 *  {@code t2.minus(t1.until(t2)).equals(t1) == true}
 * </li></ul>
 *
 * <p><strong>Note:</strong> Gew&ouml;hnlich ist die dritte Invarianzbedingung nicht g&uuml;ltig.
 * Ein Gegenbeispiel ist mit folgenden Werten gegeben: {t1, t2} = {[2011-03-31], [2011-07-01]}
 * =&gt; P3M1D (im Fall der Standardmetrik) wegen [2011-07-01] - P3M1D = [2011-03-30]. Beispiel
 * zur Verwendung einer umkehrbaren Metrik: </p>
 *
 * <pre>
 *     PlainDate d1 = PlainDate.of(2011, 3, 31);
 *     PlainDate d2 = PlainDate.of(2011, 7, 1);
 *
 *     TimeMetric&lt;CalendarUnit, Duration&lt;CalendarUnit&gt;&gt; metric =
 *        Duration.inYearsMonthsDays().reversible();
 *     Duration&lt;CalendarUnit&gt; duration =
 *        metric.between(d1, d2); // P2M31D
 *     Duration&lt;CalendarUnit&gt; invDur =
 *        metric.between(d2, d1); // -P2M31D
 *
 *     boolean firstInvariance = d1.plus(duration).equals(d2); // true
 *     boolean secondInvariance = invDur.equals(duration.inverse()); // true
 *     boolean thirdInvariance = d2.minus(duration).equals(d1); // true
 * </pre>
 *
 * <div style="background-color:#E0E0E0;padding:5px;margin:5px;">
 *
 * <p><strong>Zum mathematischen Hintergrund des spezifizierten
 * Algorithmus:</strong> Zu beachten ist, da&szlig; die Addition nicht
 * kommutativ ist, also die Reihenfolge der Additionsschritte das Ergebnis
 * beeinflussen kann. So gilt bei getrennten Schritten (2-malige Addition
 * einer Dauer): </p>
 *
 * <ul>
 *  <li>[2011-05-30] + [P1M] + [P2D] = [2011-07-02]</li>
 *  <li>[2011-05-30] + [P2D] + [P1M] = [2011-07-01]</li>
 * </ul>
 *
 * <p>Vor diesem Hintergrund ist zu verstehen, da&szlig; die Reihenfolge
 * der Additionsschritte vom Vorzeichen der Dauer abh&auml;ngig ist.
 * Wenn die Addition einer negativen Dauer als Umkehrung der Addition
 * einer positiven Dauer verstanden wird, so gilt unter Beachtung der
 * Tatsache der Nicht-Kommutativit&auml;t folgende &Auml;quivalenzrelation,
 * falls erstens die Dauer ohne Rest vollst&auml;ndig berechnet wird
 * und zweitens im konkreten Datenkontext eine minus-Operation eine plus-
 * Operation aufheben kann (zum Beispiel in t1 day-of-month &lt;= 28): </p>
 *
 * <ul>
 *  <li>[t1] - [months] - [days] = [t2]</li>
 *  <li>=&gt; [t1] - [months] - [days] + [days] = [t2] + [days]</li>
 *  <li>=&gt; [t1] - [months] = [t2] + [days]</li>
 *  <li>=&gt; [t1] - [months] + [months] = [t2] + [days] + [months]</li>
 *  <li>=&gt; [t1] = [t2] + [days] + [months] // day-of-month &lt;= 28</li>
 * </ul>
 *
 * <p>Die Vertauschung der Reihenfolge der Additionsschritte ist
 * offensichtlich. W&auml;re als Alternative versucht worden, auch
 * im Fall einer negativen Dauer zuerst Monate und dann Tage zu
 * subtrahieren, dann erg&auml;be sich zum Beispiel bereits f&uuml;r
 * die Datumsangaben t1 = [2013-02-01] und t2 = [2013-03-31] sowie der
 * Dauer t1.until(t2) = [P1M30D] die Situation, da&szlig; die oben
 * erw&auml;hnte dritte Invarianz nicht einmal dann gilt, wenn der
 * Tag des Monats im Ausgangsdatum der erste ist. Denn: t2.minus(P1M30D)
 * erg&auml;be dann nicht t1, sondern [2013-01-29]. Zwar kann die
 * vorzeichenabh&auml;ngige Ausf&uuml;hrung der Rechenschritte nicht
 * vollst&auml;ndig die dritte Invarianz garantieren (im Fall von nicht
 * berechneten Dauer-Objekten), aber wenigstens doch f&uuml;r alle Tage
 * im Ausgangsdatum bis zum 28. eines Monats. </p>
 *
 * <p>Gleichzeitig hilft der Time4J-Algorithmus die Invarianz
 * {@code Duration([t1, t2]) = -Duration([t2, t1])} einzuhalten,
 * die eine physikalische Eigenschaft einer jeden zeitlichen Dauer
 * ausdr&uuml;ckt. Die zweite Invarianz bedeutet, da&szlig; das Vorzeichen
 * einer Dauer nur die Lage zweier Zeitpunkte zueinander und nicht die
 * stets positive L&auml;nge der Dauer selbst qualifizieren darf. </p>
 * </div>
 *
 * @author  Meno Hochschild
 * @see     AbstractMetric
 * @see     #addTo(TimePoint)
 * @see     #subtractFrom(TimePoint)
 */
public abstract class AbstractDuration<U extends ChronoUnit>
    implements TimeSpan<U> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final int MIO = 1000000;

    //~ Methoden ----------------------------------------------------------

    @Override
    public boolean contains(U unit) {

        for (Item<?> item : this.getTotalLength()) {
            if (item.getUnit().equals(unit)) {
                return (item.getAmount() > 0);
            }
        }

        return false;

    }

    @Override
    public long getPartialAmount(U unit) {

        for (Item<?> item : this.getTotalLength()) {
            if (item.getUnit().equals(unit)) {
                return item.getAmount();
            }
        }

        return 0;

    }

    /**
     * <p>Creates a copy of this duration with the same amounts and
     * units but the inversed sign. </p>
     *
     * @return  inverted duration
     */
    /*[deutsch]
     * <p>Erzeugt die Negation dieser Dauer mit gleichen Betr&auml;gen,
     * aber umgekehrtem Vorzeichen. </p>
     *
     * @return  inverted duration
     */
    public abstract AbstractDuration<U> inverse();

    @Override
    public boolean isPositive() {

        return !(this.isNegative() || this.isEmpty());

    }

    @Override
    public boolean isEmpty() {

        List<Item<U>> items = this.getTotalLength();

        for (int i = 0, n = items.size(); i < n; i++) {
            if (items.get(i).getAmount() > 0) {
                return false;
            }
        }

        return true;

    }

    /**
     * <p>Yields a canonical representation which optionally starts with
     * the sign then continues with the letter &quot;P&quot; followed
     * by a comma-separated sequence of duration items. </p>
     *
     * @return  String
     */
    /*[deutsch]
     * <p>Liefert eine kanonische Darstellung, die optional mit einem negativen
     * Vorzeichen beginnt, dann mit dem Buchstaben &quot;P&quot; fortsetzt,
     * gefolgt von einer komma-separierten Liste der Dauerelemente. </p>
     *
     * @return  String
     */
    @Override
    public String toString() {

        if (this.isEmpty()) {
            return "PT0S"; // Sekunde als API-Standardmaß
        }

        StringBuilder sb = new StringBuilder();

        if (this.isNegative()) {
            sb.append('-');
        }

        sb.append('P');

        for (
            int index = 0, limit = this.getTotalLength().size();
            index < limit;
            index++
        ) {
            Item<U> item = this.getTotalLength().get(index);

            if (index > 0) {
                sb.append(',');
            }

            sb.append(item.getAmount());
            sb.append('{');
            sb.append(item.getUnit());
            sb.append('}');

        }

        return sb.toString();

    }

    /**
     * <p>Adds this duration to given time point using the
     * <a href="#algorithm">default algorithm</a>. </p>
     */
    /*[deutsch]
     * <p>Addiert diese Dauer zum angegebenen Zeitpunkt unter Verwendung
     * des <a href="#algorithm">Standard-Algorithmus</a>. </p>
     */
    @Override
    public final <T extends TimePoint<? super U, T>> T addTo(T time) {

        return this.add(time, this, false);

    }

    /**
     * <p>Subtracts this duration from given time point using the
     * <a href="#algorithm">default algorithm</a>. </p>
     */
    /*[deutsch]
     * <p>Subtrahiert diese Dauer vom angegebenen Zeitpunkt unter Verwendung
     * des <a href="#algorithm">Standard-Algorithmus</a>. </p>
     */
    @Override
    public final <T extends TimePoint<? super U, T>> T subtractFrom(T time) {

        return this.add(time, this, true);

    }

    private <T extends TimePoint<? super U, T>> T add(
        T time,
        TimeSpan<U> timeSpan,
        boolean inverse
    ) {

        T result = time;
        TimeAxis<? super U, T> engine = time.getChronology();
        List<TimeSpan.Item<U>> items = timeSpan.getTotalLength();
        boolean negative = timeSpan.isNegative();

        if (inverse) {
            negative = !timeSpan.isNegative();
        }

        if (negative) {
            int index = items.size() - 1;
            while (index >= 0) {
                TimeSpan.Item<U> item = items.get(index);
                U unit = item.getUnit();
                long amount = item.getAmount();
                int k = index - 1;
                while (k >= 0) {
                    TimeSpan.Item<U> nextItem = items.get(k);
                    U nextUnit = nextItem.getUnit();
                    long nextAmount = nextItem.getAmount();
                    long factor = getFactor(engine, nextUnit, unit);

                    if (
                        !Double.isNaN(factor)
                        && (nextAmount < Integer.MAX_VALUE)
                        && (factor > 1)
                        && (factor < MIO)
                        && engine.isConvertible(nextUnit, unit)
                    ) {
                        amount =
                            MathUtils.safeAdd(
                                amount,
                                MathUtils.safeMultiply(nextAmount, factor)
                            );
                        k--;
                    } else {
                        break;
                    }
                }
                index = k;
                result = result.plus(MathUtils.safeNegate(amount), unit);
            }
        } else {
            int index = 0;
            int end = items.size();
            while (index < end) {
                TimeSpan.Item<U> item = items.get(index);
                U unit = item.getUnit();
                long amount = item.getAmount();
                int k = index + 1;
                while (k < end) {
                    TimeSpan.Item<U> nextItem = items.get(k);
                    U nextUnit = nextItem.getUnit();
                    long factor = getFactor(engine, unit, nextUnit);

                    if (
                        !Double.isNaN(factor)
                        && (amount < Integer.MAX_VALUE)
                        && (factor > 1)
                        && (factor < MIO)
                        && engine.isConvertible(unit, nextUnit)
                    ) {
                        amount =
                            MathUtils.safeAdd(
                                nextItem.getAmount(),
                                MathUtils.safeMultiply(amount, factor)
                            );
                        unit = nextUnit;
                        k++;
                    } else {
                        break;
                    }
                }
                index = k;
                result = result.plus(amount, unit);
            }
        }

        return result;

    }

    private static <U> long getFactor(
        TimeAxis<U, ?> engine,
        U unit1,
        U unit2
    ) {

        return Math.round(engine.getLength(unit1) / engine.getLength(unit2));

    }

}
