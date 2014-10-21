/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;		

/**
 * The type  ContactFetcher.
 */
public class ContactFetcher extends ODataEntityFetcher<Contact,ContactOperations> implements Readable<Contact> {

     /**
     * Instantiates a new ContactFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ContactFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Contact.class,ContactOperations.class);
    }
}