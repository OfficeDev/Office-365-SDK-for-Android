package com.microsoft.services.odata.impl;

import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.Request;

public class OAuthCredentials implements Credentials {

    private String mToken;

    public OAuthCredentials(String oAuthToken) {
        mToken = oAuthToken;
    }

    /**
     * Returns the OAuth Token
     */
    public String getToken() {
        return mToken;
    }

    @Override
    public void prepareRequest(Request request) {
        request.addHeader("Authorization", "Bearer " + getToken());
    }

}
