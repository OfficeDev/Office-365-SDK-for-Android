package com.microsoft.office365.odata.impl.http;

import com.microsoft.office365.odata.interfaces.Credentials;
import com.microsoft.office365.odata.interfaces.CredentialsFactory;

public class CredentialsFactoryImpl implements CredentialsFactory {

    private Credentials mCredentials;

    @Override
    public Credentials getCredentials() {
        return mCredentials;
    }

    public void setCredentials(Credentials credentials){
        mCredentials = credentials;
    }
}
