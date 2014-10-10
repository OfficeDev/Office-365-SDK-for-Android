package com.microsoft.office365.odata.interfaces;

public interface DependencyResolver {
    HttpTransport getHttpTransport();

    Logger getLogger();

    JsonSerializer getJsonSerializer();

    CredentialsFactory getCredentialsFactory();

    ODataURL createODataURL();
}
