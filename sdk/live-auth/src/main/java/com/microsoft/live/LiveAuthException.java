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
 * Indicates that an exception occurred during the Auth process.
 */
public class LiveAuthException extends RuntimeException {

    private static final long serialVersionUID = 3368677530670470856L;

    private final String error;
    private final String errorUri;


    public LiveAuthException(String errorMessage) {
        super(errorMessage);
        this.error = "";
        this.errorUri = "";
    }

    public LiveAuthException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.error = "";
        this.errorUri = "";
    }

    public LiveAuthException(String error, String errorDescription, String errorUri) {
        super(errorDescription);

        assert error != null;

        this.error = error;
        this.errorUri = errorUri;
    }

    public LiveAuthException(String error, String errorDescription, String errorUri, Throwable cause) {
        super(errorDescription, cause);

        assert error != null;

        this.error = error;
        this.errorUri = errorUri;
    }

    /**
     * @return Returns the authentication error.
     */
    public String getError() {
        return this.error;
    }

    /**
     * @return Returns the error URI.
     */
    public String getErrorUri() {
        return this.errorUri;
    }
}
