/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;		

/**
 * The type  ContactFolderFetcher.
 */
public class ContactFolderFetcher extends ODataEntityFetcher<ContactFolder,ContactFolderOperations> implements Readable<ContactFolder> {

     /**
     * Instantiates a new ContactFolderFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ContactFolderFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, ContactFolder.class,ContactFolderOperations.class);
    }

	     /**
     * Gets contacts.
     *
     * @return the contacts
     */
	public ODataCollectionFetcher<Contact, ContactFetcher, ContactCollectionOperations> getContacts() {
		return new ODataCollectionFetcher<Contact, ContactFetcher,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
     /**
     * Gets child folders.
     *
     * @return the child folders
     */
	public ODataCollectionFetcher<ContactFolder, ContactFolderFetcher, ContactFolderCollectionOperations> getChildFolders() {
		return new ODataCollectionFetcher<ContactFolder, ContactFolderFetcher,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}