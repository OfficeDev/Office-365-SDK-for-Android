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
 * Handles callback methods for LiveAuthClient init, login, and logout methods.
 * Returns the * status of the operation when onAuthComplete is called. If there was an error
 * during the operation, onAuthError is called with the exception that was thrown.
 */
public interface LiveAuthListener {

    /**
     * Invoked when the operation completes successfully.
     *
     * @param status The {@link LiveStatus} for an operation. If successful, the status is
     *               CONNECTED. If unsuccessful, NOT_CONNECTED or UNKNOWN are returned.
     * @param session The {@link LiveConnectSession} from the {@link LiveAuthClient}.
     * @param userState An arbitrary object that is used to determine the caller of the method.
     */
    public void onAuthComplete(LiveStatus status, LiveConnectSession session, Object userState);

    /**
     * Invoked when the method call fails.
     *
     * @param exception The {@link LiveAuthException} error.
     * @param userState An arbitrary object that is used to determine the caller of the method.
     */
    public void onAuthError(LiveAuthException exception, Object userState);
}
