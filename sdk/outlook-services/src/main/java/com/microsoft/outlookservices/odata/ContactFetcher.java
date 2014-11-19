/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;       

/**
 * The type  ContactFetcher.
 */
public class ContactFetcher extends ODataEntityFetcher<Contact,ContactOperations> 
                                     implements Readable<Contact> {

     /**
     * Instantiates a new ContactFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public ContactFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Contact.class, ContactOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public ContactFetcher addParameter(String name, Object value) {
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
    public ContactFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	}