package com.interfaces;

public interface DependencyResolver {
    HttpTransport getHttpTransport();

    Logger getLogger();

    JsonSerializer getJsonSerializer();

    CredentialsFactory getCredentialsFactory();
}
