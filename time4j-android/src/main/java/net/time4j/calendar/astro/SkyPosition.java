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


/**
 * <p>Simple implementation using a pair of RA/Dec. </p>
 *
 * @author  Meno Hochschild
 * @since   4.37
 */
final class SkyPosition
    implements EquatorialCoordinates {

    //~ Instanzvariablen --------------------------------------------------

    private final double ra;
    private final double dec;

    //~ Konstruktoren -----------------------------------------------------

    SkyPosition(
        double ra,
        double dec
    ) {
        super();

        if (Double.isNaN(ra) || Double.isInfinite(ra) || Double.isNaN(dec) || Double.isInfinite(dec)) {
            throw new IllegalArgumentException("Not finite: " + ra + "/" + dec);
        }

        this.ra = ra;
        this.dec = dec;
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public double getRightAscension() {

        return this.ra;

    }

    @Override
    public double getDeclination() {

        return this.dec;

    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof SkyPosition) {
            SkyPosition that = (SkyPosition) obj;
            return (this.ra == that.ra) && (this.dec == that.dec);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return AstroUtils.hashCode(this.ra) + 37 * AstroUtils.hashCode(this.dec);

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("RA/Dec=[");
        sb.append(this.ra);
        sb.append(',');
        sb.append(this.dec);
        sb.append(']');
        return sb.toString();

    }

}
