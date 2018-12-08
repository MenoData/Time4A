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

package net.time4j.calendar.astro;

import net.time4j.Moment;

import net.time4j.scale.LeapSeconds;
import net.time4j.scale.TimeScale;
import net.time4j.tz.ZonalOffset;


/**
 * <p>The four astronomical seasons (Spring, Summer, Autumn and Winter). </p>
 *
 * <p>If there is no context information then Time4J will assume that any season is related
 * to the northern hemisphere. </p>
 *
 * @author 	Meno Hochschild
 * @since 	3.33/4.28
 */
/*[deutsch]
 * <p>Die vier astronomischen Jahreszeiten (Fr&uuml;hling, Sommer, Herbst und Winter). </p>
 *
 * <p>Falls keine Kontextinformation verf&uuml;gbar ist, wird Time4J annehmen, da&szlig;
 * eine gegebene Jahreszeit sich auf die Nordhalbkugel der Erde bezieht. </p>
 *
 * @author 	Meno Hochschild
 * @since 	3.33/4.28
 */
public enum AstronomicalSeason {

	//~ Statische Felder/Initialisierungen --------------------------------

	/**
	 * <p>Begin of Spring on the northern hemisphere in March (or Autumn on the southern hemisphere). </p>
	 */
	/*[deutsch]
	 * <p>Fr&uuml;hlingsanfang auf der n&oumlr;dlichen Halbkugel im M&auml;rz
	 * (oder Herbstanfang auf der s&uuml;dlichen Halbkugel). </p>
	 */
	VERNAL_EQUINOX,

	/**
	 * <p>Begin of Summer on the northern hemisphere in June (or Winter on the southern hemisphere). </p>
	 */
	/*[deutsch]
	 * <p>Sommeranfang auf der n&oumlr;dlichen Halbkugel im Juni
	 * (oder Winteranfang auf der s&uuml;dlichen Halbkugel). </p>
	 */
	SUMMER_SOLSTICE,

	/**
	 * <p>Begin of Autumn on the northern hemisphere in September (or Spring on the southern hemisphere). </p>
	 */
	/*[deutsch]
	 * <p>Herbstanfang auf der n&oumlr;dlichen Halbkugel im September
	 * (oder Fr&uuml;hlingsanfang auf der s&uuml;dlichen Halbkugel). </p>
	 */
	AUTUMNAL_EQUINOX,

	/**
	 * <p>Begin of Winter on the northern hemisphere in December (or Summer on the southern hemisphere). </p>
	 */
	/*[deutsch]
	 * <p>Winteranfang auf der n&oumlr;dlichen Halbkugel im Dezember
	 * (oder Sommeranfang auf der s&uuml;dlichen Halbkugel). </p>
	 */
	WINTER_SOLSTICE;

	//~ Methoden ----------------------------------------------------------

	/**
	 * <p>Determines the astronomical season related to given moment. </p>
	 *
	 * <p>The result will be valid for the northern hemisphere. For the southern equivalent, please call
	 * the method {@link #onSouthernHemisphere()} on the result. </p>
	 *
	 * @param 	moment	the moment/instant for which the season is to be determined
	 * @return	astronomical season (on the northern hemisphere)
     * @throws  IllegalArgumentException if the year is out of range {@code -2000 <= year <= +3000}
	 * @since 	4.2
	 */
	/*[deutsch]
	 * <p>Bestimmt die astronomische Saison, die zum angegebenen Moment geh&ouml;rt. </p>
	 *
	 * <p>Das Ergebnis ist f&uuml;r die Nordhalbkugel der Erde g&uuml;ltig. Auf der S&uuml;dhalbkugel
	 * sollten Anwender die Methode {@link #onSouthernHemisphere()} auf dem Ergebnis aufrufen. </p>
	 *
	 * @param 	moment	the moment/instant for which the season is to be determined
	 * @return	astronomical season (on the northern hemisphere)
     * @throws  IllegalArgumentException if the year is out of range {@code -2000 <= year <= +3000}
	 * @since 	4.2
	 */
	public static AstronomicalSeason of(Moment moment) {

		// season does not change around new year so using UTC-offset is okay
		int year = moment.toZonalTimestamp(ZonalOffset.UTC).getYear();
        checkYear(year);

		// determine the season
		if (moment.isBefore(AstronomicalSeason.VERNAL_EQUINOX.inYear(year))) {
			return AstronomicalSeason.WINTER_SOLSTICE;
		} else if (moment.isBefore(AstronomicalSeason.SUMMER_SOLSTICE.inYear(year))) {
			return AstronomicalSeason.VERNAL_EQUINOX;
		} else if (moment.isBefore(AstronomicalSeason.AUTUMNAL_EQUINOX.inYear(year))) {
			return AstronomicalSeason.SUMMER_SOLSTICE;
		} else if (moment.isBefore(AstronomicalSeason.WINTER_SOLSTICE.inYear(year))) {
			return AstronomicalSeason.AUTUMNAL_EQUINOX;
		} else {
			return AstronomicalSeason.WINTER_SOLSTICE;
		}

	}

	/**
	 * <p>Determines the moment of this astronomical event in given year. </p>
	 *
	 * <p>The precision is for modern times (around 2000) better than a minute. The underlying astronomical
	 * calculations are based on formula given by Jean Meeus in his book &quot;Astronomical algorithms&quot;. </p>
	 *
	 * @param 	year	gregorian/julian year
	 * @return	time of this astronomical event (equinox or solstice) in given year
     * @throws  IllegalArgumentException if the year is out of range {@code -2000 <= year <= +3000}
	 */
	/*[deutsch]
	 * <p>Berechnet die Zeit, wann dieses astronomische Ereignis im angegebenen Jahr auftritt. </p>
	 *
	 * <p>Die Genauigkeit ist f&uuml;r moderne Zeiten (um das Jahr 2000 herum) besser als eine Minute.
	 * Die zugrundeliegenden astronomischen Berechnungen fu&szlig;en auf Formeln aus dem Buch
	 * &quot;Astronomical algorithms&quot; von Jean Meeus. </p>
	 *
	 * @param 	year	gregorian/julian year
	 * @return	time of this astronomical event (equinox or solstice) in given year
     * @throws  IllegalArgumentException if the year is out of range {@code -2000 <= year <= +3000}
	 */
	public Moment inYear(int year) {

        checkYear(year);
		double tt = (this.jdEphemerisDays(year) - 2441317.5) * 86400.0;
		boolean ls = LeapSeconds.getInstance().isEnabled();

		double elapsed;
		TimeScale scale;

		if (!ls || (year < 1972)) {
			elapsed = tt - TimeScale.deltaT(year, (this.ordinal() + 1) * 3);
			scale = TimeScale.UT;
		} else {
			elapsed = tt - 42.184;
			scale = TimeScale.UTC;
		}

		long seconds = (long) Math.floor(elapsed);
		int nanos = (int) ((elapsed - seconds) * 1000000000);

		if (!ls) {
			seconds += (86400 * 730);
			scale = TimeScale.POSIX;
		}

		return Moment.of(seconds, nanos, scale);

	}

	/**
	 * <p>Determines the Julian day of this astronomical event in given year. </p>
	 *
	 * <p>The precision is for modern times (around 2000) better than a minute. The underlying astronomical
	 * calculations are based on formula given by Jean Meeus in his book &quot;Astronomical algorithms&quot;. </p>
	 *
	 * @param 	year	gregorian/julian year
	 * @return	JD(TT) of this astronomical event (equinox or solstice) in given year
     * @throws  IllegalArgumentException if the year is out of range {@code -2000 <= year <= +3000}
	 * @see 	JulianDay#ofEphemerisTime(double)
	 */
	/*[deutsch]
	 * <p>Berechnet den julianischen Tag, wann dieses astronomische Ereignis im angegebenen Jahr auftritt. </p>
	 *
	 * <p>Die Genauigkeit ist f&uuml;r moderne Zeiten (um das Jahr 2000 herum) besser als eine Minute.
	 * Die zugrundeliegenden astronomischen Berechnungen fu&szlig;en auf Formeln aus dem Buch
	 * &quot;Astronomical algorithms&quot; von Jean Meeus. </p>
	 *
	 * @param 	year	gregorian/julian year
	 * @return	JD(TT) of this astronomical event (equinox or solstice) in given year
     * @throws  IllegalArgumentException if the year is out of range {@code -2000 <= year <= +3000}
	 * @see 	JulianDay#ofEphemerisTime(double)
	 */
	public JulianDay julianDay(int year) {

	    checkYear(year);
		return JulianDay.ofEphemerisTime(this.jdEphemerisDays(year));

	}

	/**
	 * <p>Interpretes this season as related to the northern hemisphere and returns
	 * this instance unchanged. </p>
	 *
	 * <p>This method does not change anything but serves for better documentation
	 * and more clarity about the meaning of this season. </p>
	 *
	 * @return	this instance (identity operation)
	 * @see 	#onSouthernHemisphere()
	 * @since 	3.35/4.30
	 */
	/*[deutsch]
	 * <p>Interpretiert diese Jahreszeit als auf die Nordhalbkugel der Erde bezogen
	 * und gibt diese Instanz unver&auml;ndert zur&uuml;ck. </p>
	 *
	 * <p>Diese Methode &auml;ndert nichts und ist nur zur besseren Dokumentation
	 * und gr&ouml;&szlig;eren Klarheit &uuml;ber die Bedeutung dieser Jahreszeit
	 * gedacht. </p>
	 *
	 * @return	this instance (identity operation)
	 * @see 	#onSouthernHemisphere()
	 * @since 	3.35/4.30
	 */
	public AstronomicalSeason onNorthernHemisphere() {

		return this;

	}

	/**
	 * <p>Interpretes this season as related to the southern hemisphere and returns
	 * the corresponding northern equivalent. </p>
	 *
	 * @return	northern equivalent of this season
	 * @see 	#onNorthernHemisphere()
	 * @since 	3.35/4.30
	 */
	/*[deutsch]
	 * <p>Interpretiert diese Jahreszeit als auf die S&uuml;dhalbkugel der Erde bezogen
	 * und gibt die entsprechende Jahreszeit der Nordhalbkugel zur&uuml;ck. </p>
	 *
	 * @return	northern equivalent of this season
	 * @see 	#onNorthernHemisphere()
	 * @since 	3.35/4.30
	 */
	public AstronomicalSeason onSouthernHemisphere() {

		return AstronomicalSeason.values()[(this.ordinal() + 2) % 4];

	}

    private static void checkYear(int year) {

        if ((year < -2000) || (year > 3000)) {
            throw new IllegalArgumentException("Year out of supported range: -2000 <= " + year + " <= +3000");
        }

    }

    private double jdEphemerisDays(int year) {

		double jd0 = this.jdMean(year);
		double t = (jd0 - 2451545.0) / 36525;
		double w = 35999.373 * t - 2.47;
		double dL = 1 + 0.0334 * cos(w) + 0.0007 * cos(2 * w);
		double s = periodic24(t);
		return jd0 + ((0.00001 * s) / dL);

	}

	private double jdMean(int year) {

		if (year < 1000) { // Meeus - Astronomical Algorithms - p178, Table 27.A
			double y = year / 1000.0;

			switch (this) {

				case VERNAL_EQUINOX :
					return 1721139.29189 + (365242.13740 + (0.06134 + (0.00111 - 0.00071 * y) * y) * y) * y;

				case SUMMER_SOLSTICE :
					return 1721233.25401 + (365241.72562 + (-0.05323 + (0.00907 + 0.00025 * y) * y) * y) * y;

				case AUTUMNAL_EQUINOX :
					return 1721325.70455 + (365242.49558 + (-0.11677 + (-0.00297 + 0.00074 * y) * y) * y) * y;

				case WINTER_SOLSTICE :
					return 1721414.39987 + (365242.88257 + (-0.00769 + (-0.00933 - 0.00006 * y) * y) * y) * y;

				default :
					throw new AssertionError(this);
			}
		} else { // Meeus - Astronomical Algorithms - p178, Table 27.B
			double y = (year - 2000) / 1000.0;

			switch (this) {

				case VERNAL_EQUINOX :
					return 2451623.80984 + (365242.37404 + (0.05169 + (-0.00411 - 0.00057 * y) * y) * y) * y;

				case SUMMER_SOLSTICE :
					return 2451716.56767 + (365241.62603 + (0.00325 + (0.00888 - 0.00030 * y) * y) * y) * y;

				case AUTUMNAL_EQUINOX :
					return 2451810.21715 + (365242.01767 + (-0.11575 + (0.00337 + 0.00078 * y) * y) * y) * y;

				case WINTER_SOLSTICE :
					return 2451900.05952 + (365242.74049 + (-0.06223 + (-0.00823 + 0.00032 * y) * y) * y) * y;

				default :
					throw new AssertionError(this);
			}
		}

	}

	// Meeus - Astronomical Algorithms - p179, Table 27.C
	private static final int[] A = {
		485, 203, 199, 182, 156, 136, 77, 74, 70, 58, 52, 50, 45, 44, 29, 18, 17, 16, 14, 12,
		12, 12, 9, 8
	};

	private static final double[] B = { // in degrees
		324.96, 337.23, 342.08, 27.85, 73.14, 171.52, 222.54, 296.72, 243.58, 119.81, 297.17,
		21.02, 247.54, 325.15, 60.93, 155.12, 288.79, 198.04, 199.76, 95.39, 287.11, 320.81,
		227.73, 15.45
	};

	private static final double[] C = { // in degrees
		1934.136, 32964.467, 20.186, 445267.112, 45036.886, 22518.443, 65928.934, 3034.906,
		9037.513, 33718.147, 150.678, 2281.226, 29929.562, 31555.956, 4443.417, 67555.328,
		4562.452, 62894.029, 31436.921, 14577.848, 31931.756, 34777.259, 1222.114, 16859.074
	};

	private static double periodic24(double t) {

		double s = 0;
		for (int i = 0; i < 24; i++) {
			s += A[i] * cos(B[i] + (C[i] * t));
		}
		return s;

	}

	private static double cos(double deg) {

		return Math.cos(deg * Math.PI / 180);

	}

}
