package com.microsoft.office365.exchangeSample;

import com.core.EntityContainer;
import com.microsoft.office365.Credentials;

public class AppData {
    private static Credentials mCredentials;

    public static Credentials getCredentials() {
        return mCredentials;
    }

    public static void setCredentials(Credentials credentials) {
        AppData.mCredentials = credentials;
    }

    public static EntityContainer getEntityContainer() {
        return new EntityContainer("https://outlook.office365.com/EWS/OData", getCredentials());
    }

}
