/*******************************************************************************
 Copyright (c) Microsoft Open Technologies, Inc. All Rights Reserved.
 Licensed under the MIT or Apache License; see LICENSE in the source repository
 root for authoritative license information.﻿

 **NOTE** This code was generated by a tool and will occasionally be
 overwritten. We welcome comments and issues regarding this code; they will be
 addressed in the generation tool. If you wish to submit pull requests, please
 do so for the templates in that tool.

 This code was generated by Vipr (https://github.com/microsoft/vipr) using
 the T4TemplateWriter (https://github.com/msopentech/vipr-t4templatewriter).
 ******************************************************************************/
package com.microsoft.outlookservices.orc;

import com.microsoft.outlookservices.*;
import com.microsoft.services.orc.*;
import com.microsoft.services.orc.interfaces.DependencyResolver;

/**
 * The type EntityContainerClient.
 */
public class OutlookClient extends BaseOrcContainer {

     /**
     * Instantiates a new EntityContainerClient.
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
    public OrcCollectionFetcher<User, UserFetcher, UserCollectionOperations> getUsers() {
        return new OrcCollectionFetcher<User, UserFetcher, UserCollectionOperations>("Users", this, User.class,UserCollectionOperations.class);
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