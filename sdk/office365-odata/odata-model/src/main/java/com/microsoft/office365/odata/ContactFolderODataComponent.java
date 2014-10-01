/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.microsoft.office365.exchange.services.*;

public class ContactFolderODataComponent extends BaseEntityODataComponent<ContactFolder> implements Executable<ContactFolder> {

	 public ContactFolderODataComponent(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ContactFolder.class);
    }
	public ODataCollection<Contact, ContactODataComponent, ContactCollectionOperations> getContacts() {
        return new ODataCollection<Contact, ContactODataComponent,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollection<ContactFolder, ContactFolderODataComponent, ContactFolderCollectionOperations> getChildFolders() {
        return new ODataCollection<ContactFolder, ContactFolderODataComponent,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}