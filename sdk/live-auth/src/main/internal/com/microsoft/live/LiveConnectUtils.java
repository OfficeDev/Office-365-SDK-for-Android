// ------------------------------------------------------------------------------
// Copyright (c) 2014 Microsoft Corporation
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
// ------------------------------------------------------------------------------

package com.microsoft.live;


import android.text.TextUtils;

/**
 * LiveConnectUtils is a non-instantiable utility class that contains various helper
 * methods and constants.
 */
final class LiveConnectUtils {

    /**
     * Checks to see if the passed in Object is null, and throws a
     * NullPointerException if it is.
     *
     * @param object to check
     * @param parameterName name of the parameter that is used in the exception message
     * @throws NullPointerException if the Object is null
     */
    public static void assertNotNull(Object object, String parameterName) {
        assert !TextUtils.isEmpty(parameterName);

        if (object == null) {
            final String message = String.format(ErrorMessages.NULL_PARAMETER, parameterName);
            throw new NullPointerException(message);
        }
    }

    /**
     * Checks to see if the passed in is an empty string, and throws an
     * IllegalArgumentException if it is.
     *
     * @param parameter to check
     * @param parameterName name of the parameter that is used in the exception message
     * @throws IllegalArgumentException if the parameter is empty
     * @throws NullPointerException if the String is null
     */
    public static void assertNotNullOrEmpty(String parameter, String parameterName) {
        assert !TextUtils.isEmpty(parameterName);

        assertNotNull(parameter, parameterName);

        if (TextUtils.isEmpty(parameter)) {
            final String message = String.format(ErrorMessages.EMPTY_PARAMETER, parameterName);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Private to prevent instantiation
     */
    private LiveConnectUtils() { throw new AssertionError(ErrorMessages.NON_INSTANTIABLE_CLASS); }
}
