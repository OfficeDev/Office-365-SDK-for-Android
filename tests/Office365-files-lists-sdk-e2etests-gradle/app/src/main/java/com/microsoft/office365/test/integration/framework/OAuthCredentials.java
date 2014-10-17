package com.microsoft.office365.test.integration.framework;


import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.Request;

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
