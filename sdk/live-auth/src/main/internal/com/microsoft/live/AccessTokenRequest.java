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
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.text.TextUtils;

/**
 * AccessTokenRequest represents a request for an Access Token.
 * It subclasses the abstract class TokenRequest, which does most of the work.
 * This class adds the proper parameters for the access token request via the
 * constructBody() hook.
 */
class AccessTokenRequest extends TokenRequest {

    /**
     * REQUIRED.  The authorization code received from the
     * authorization server.
     */
    private final String code;

    /** REQUIRED.  Value MUST be set to "authorization_code". */
    private final OAuth.GrantType grantType;

    /**
     * REQUIRED, if the "redirect_uri" parameter was included in the
     * authorization request as described in Section 4.1.1, and their
     * values MUST be identical.
     */
    private final String redirectUri;

    /**
     * Constructs a new AccessTokenRequest, and initializes its member variables
     *
     * @param client the HttpClient to make HTTP requests on
     * @param clientId the client_id of the calling application
     * @param redirectUri the redirect_uri to be called back
     * @param code the authorization code received from the AuthorizationRequest
     */
    public AccessTokenRequest(HttpClient client,
                              String clientId,
                              String redirectUri,
                              String code) {
        super(client, clientId);

        assert !TextUtils.isEmpty(redirectUri);
        assert !TextUtils.isEmpty(code);

        this.redirectUri = redirectUri;
        this.code = code;
        this.grantType = OAuth.GrantType.AUTHORIZATION_CODE;
    }

    /**
     * Adds the "code", "redirect_uri", and "grant_type" parameters to the body.
     *
     * @param body the list of NameValuePairs to be placed in the body of the HTTP request
     */
    @Override
    protected void constructBody(List<NameValuePair> body) {
        body.add(new BasicNameValuePair(OAuth.CODE, this.code));
        body.add(new BasicNameValuePair(OAuth.REDIRECT_URI, this.redirectUri));
        body.add(new BasicNameValuePair(OAuth.GRANT_TYPE,
                                        this.grantType.toString().toLowerCase(Locale.US)));
    }
}
