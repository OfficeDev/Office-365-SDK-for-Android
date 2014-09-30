package com.microsoft.office365.odata.impl.http;

import com.interfaces.Credentials;
import com.interfaces.CredentialsFactory;

public class CredentialsImpl implements CredentialsFactory {

    private Credentials mCredentials;

    @Override
    public Credentials getCredentials() {
        return mCredentials;
    }

    public void setCredentials(Credentials credentials){
        mCredentials = credentials;
    }
}
