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

/**
 * QueryParameters is a non-instantiable utility class that holds query parameter constants
 * used by the API service.
 */
final class QueryParameters {

    public static final String PRETTY = "pretty";
    public static final String CALLBACK = "callback";
    public static final String SUPPRESS_REDIRECTS = "suppress_redirects";
    public static final String SUPPRESS_RESPONSE_CODES = "suppress_response_codes";
    public static final String METHOD = "method";
    public static final String OVERWRITE = "overwrite";
    public static final String RETURN_SSL_RESOURCES = "return_ssl_resources";

    /** Private to present instantiation. */
    private QueryParameters() {
        throw new AssertionError(ErrorMessages.NON_INSTANTIABLE_CLASS);
    }
}
