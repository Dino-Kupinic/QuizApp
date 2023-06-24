/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Michael Ploier
 * @date : 19.6.2023
 * @details Class for Debugging
 */
/*
 * MIT License
 *
 * Copyright (c) 2023 Dino Kupinic, Michael Ploier, Daniel Samhaber, Jannick Angerer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package at.htlsteyr.quizapp.Model;

public interface Debug {
    /**
     * Prints possible NullPointerException in programm
     */
    boolean PRINT_NUllPOINTEXCEP = false;

    /**
     * Prints possible CloneNotSupportedException in programm
     */
    boolean PRINT_CLONENOTSUP = false;

    /**
     * Prints possible IOException in programm
     */
    boolean PRINT_IOEXCEPTION = false;

    /**
     * Prints possible FileNotFoundException in programm
     */
    boolean PRINT_FILENOTFOUNDEXCEP = false;

    /**
     * Prints possible IndexOutOfBoundsException in programm
     */
    boolean PRINT_INDEXOUTOFBOUNDSEXCEP = false;


}
