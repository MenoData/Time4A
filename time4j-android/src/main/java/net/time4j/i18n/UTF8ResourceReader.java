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

package net.time4j.i18n;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;


/**
 * Skips eventually existing byte-order-marks of an input stream encoded in UTF-8.
 *
 * @author  Meno Hochschild
 * @since   3.23/4.19
 */
class UTF8ResourceReader
    extends Reader {

    //~ Instanzvariablen --------------------------------------------------

    private final PushbackInputStream pis;
    private BufferedReader internal = null;

    //~ Konstruktoren -----------------------------------------------------

    UTF8ResourceReader(InputStream is) {
        super();

        this.pis = new PushbackInputStream(is, 3);

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * See {@code BufferedReader}.
     *
     * @return  A String containing the contents of the line, not including any line-termination characters,
     *          or null if the end of the stream has been reached
     * @throws  IOException in case of any I/O-error
     */
    public String readLine() throws IOException {

        this.init();
        return this.internal.readLine();

    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {

        this.init();
        return this.internal.read(cbuf, off, len);

    }

    @Override
    public boolean ready() throws IOException {

        this.init();
        return this.internal.ready();

    }

    @Override
    public void close() throws IOException {

        final Reader reader = this.internal;

        if (reader == null) {
            this.pis.close();
        } else {
            reader.close();
        }

    }

    private void init() throws IOException {

        if (this.internal != null) {
            return;
        }

        byte[] bom = new byte[3];
        int n = this.pis.read(bom, 0, 3);

        boolean hasByteOrderMarks = (
            (n == 3)
            && (bom[0] == (byte) 0xEF)
            && (bom[1] == (byte) 0xBB)
            && (bom[2] == (byte) 0xBF)
        );

        if (!hasByteOrderMarks && (n > 0)) {
            this.pis.unread(bom, 0, n);
        }

        this.internal = new BufferedReader(new InputStreamReader(this.pis, "UTF-8"));

    }

}
