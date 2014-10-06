package com.microsoft.office365.test.integration.framework;

import com.microsoft.office365.odata.interfaces.Credentials;
import com.microsoft.office365.odata.interfaces.Request;

public class OAuthCredentials implements Credentials {
    String mToken;

    public OAuthCredentials(String token){
        mToken = token;
    }

    @Override
    public void prepareRequest(Request request) {
        request.addHeader("Authorization","Bearer " + mToken);
    }
}
