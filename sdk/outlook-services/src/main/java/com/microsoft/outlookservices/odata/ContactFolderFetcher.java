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
 * The type  ContactFolderFetcher.
 */
public class ContactFolderFetcher extends ODataEntityFetcher<ContactFolder,ContactFolderOperations> 
                                     implements Readable<ContactFolder> {

     /**
     * Instantiates a new ContactFolderFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public ContactFolderFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ContactFolder.class, ContactFolderOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public ContactFolderFetcher addParameter(String name, Object value) {
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
    public ContactFolderFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
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
     * Gets contact.
     *
     * @return the contact
     */
    public ContactFetcher getContact(String id){
         return new ODataCollectionFetcher<Contact, ContactFetcher,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class).getById(id);
    }
     /**
     * Gets child folders.
     *
     * @return the child folders
     */
    public ODataCollectionFetcher<ContactFolder, ContactFolderFetcher, ContactFolderCollectionOperations> getChildFolders() {
        return new ODataCollectionFetcher<ContactFolder, ContactFolderFetcher,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }

    /**
     * Gets child folder.
     *
     * @return the child folder
     */
    public ContactFolderFetcher getChildFolder(String id){
         return new ODataCollectionFetcher<ContactFolder, ContactFolderFetcher,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class).getById(id);
    }
}