/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;       

/**
 * The type  OAuth2PermissionGrantFetcher.
 */
public class OAuth2PermissionGrantFetcher extends ODataEntityFetcher<OAuth2PermissionGrant,OAuth2PermissionGrantOperations> 
                                     implements Readable<OAuth2PermissionGrant> {

     /**
     * Instantiates a new OAuth2PermissionGrantFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public OAuth2PermissionGrantFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, OAuth2PermissionGrant.class, OAuth2PermissionGrantOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public OAuth2PermissionGrantFetcher addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public OAuth2PermissionGrantFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	}