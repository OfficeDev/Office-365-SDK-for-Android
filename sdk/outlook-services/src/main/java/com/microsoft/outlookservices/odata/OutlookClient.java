/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.microsoft.outlookservices.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.DependencyResolver;

/**
 * The type OutlookClient.
 */
public class OutlookClient extends BaseODataContainer {

     /**
     * Instantiates a new OutlookClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public OutlookClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public OutlookClient addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public OutlookClient addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

     /**
     * Gets User.
     *
     * @return the User
     */
    public ODataCollectionFetcher<User, UserFetcher, UserCollectionOperations> getUsers() {
        return new ODataCollectionFetcher<User, UserFetcher, UserCollectionOperations>("Users", this, User.class,UserCollectionOperations.class);
    }
     /**
     * Gets Me.
     *
     * @return the Me
     */
    public UserFetcher getMe() {
        return new UserFetcher("Me", this);
    }
}