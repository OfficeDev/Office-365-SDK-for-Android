/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class ContactFolderOperations extends BaseEntityOperations<ContactFolder> implements Executable<ContactFolder> {

	 public ContactFolderOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ContactFolder.class);
    }
	public ODataCollection<Contact, ContactOperations, ContactCollectionOperations> getContacts() {
        return new ODataCollection<Contact, ContactOperations,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollection<ContactFolder, ContactFolderOperations, ContactFolderCollectionOperations> getChildFolders() {
        return new ODataCollection<ContactFolder, ContactFolderOperations,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}