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

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.text.TextUtils;

import com.microsoft.live.OAuth.GrantType;

/**
 * RefreshAccessTokenRequest performs a refresh access token request. Most of the work
 * is done by the parent class, TokenRequest. This class adds in the required body parameters via
 * TokenRequest's hook method, constructBody().
 */
class RefreshAccessTokenRequest extends TokenRequest {

    /** REQUIRED. Value MUST be set to "refresh_token". */
    private final GrantType grantType = GrantType.REFRESH_TOKEN;

    /**  REQUIRED. The refresh token issued to the client. */
    private final String refreshToken;

    private final String scope;

    public RefreshAccessTokenRequest(HttpClient client,
                                     String clientId,
                                     String refreshToken,
                                     String scope) {
        super(client, clientId);

        assert refreshToken != null;
        assert !TextUtils.isEmpty(refreshToken);
        assert scope != null;
        assert !TextUtils.isEmpty(scope);

        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    @Override
    protected void constructBody(List<NameValuePair> body) {
        body.add(new BasicNameValuePair(OAuth.REFRESH_TOKEN, this.refreshToken));
        body.add(new BasicNameValuePair(OAuth.SCOPE, this.scope));
        body.add(new BasicNameValuePair(OAuth.GRANT_TYPE, this.grantType.toString()));
    }
}
