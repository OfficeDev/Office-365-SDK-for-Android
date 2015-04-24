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

import android.os.AsyncTask;

/**
 * TokenRequestAsync performs an async token request. It takes in a TokenRequest,
 * executes it, checks the OAuthResponse, and then calls the given listener.
 */
class TokenRequestAsync extends AsyncTask<Void, Void, Void> implements ObservableOAuthRequest {

    private final DefaultObservableOAuthRequest observerable;

    /** Not null if there was an exception */
    private LiveAuthException exception;

    /** Not null if there was a response */
    private OAuthResponse response;

    private final TokenRequest request;

    /**
     * Constructs a new TokenRequestAsync and initializes its member variables
     *
     * @param request to perform
     */
    public TokenRequestAsync(TokenRequest request) {
        assert request != null;

        this.observerable = new DefaultObservableOAuthRequest();
        this.request = request;
    }

    @Override
    public void addObserver(OAuthRequestObserver observer) {
        this.observerable.addObserver(observer);
    }

    @Override
    public boolean removeObserver(OAuthRequestObserver observer) {
        return this.observerable.removeObserver(observer);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            this.response = this.request.execute();
        } catch (LiveAuthException e) {
            this.exception = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (this.response != null) {
            this.observerable.notifyObservers(this.response);
        } else if (this.exception != null) {
            this.observerable.notifyObservers(this.exception);
        } else {
            final LiveAuthException exception = new LiveAuthException(ErrorMessages.CLIENT_ERROR);
            this.observerable.notifyObservers(exception);
        }
    }
}
