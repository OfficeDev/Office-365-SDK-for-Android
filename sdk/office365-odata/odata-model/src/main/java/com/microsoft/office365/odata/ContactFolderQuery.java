/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class ContactFolderQuery extends ODataEntityQuery<ContactFolder> implements Executable<ContactFolder> {

	 public ContactFolderQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ContactFolder.class);
    }
	public ODataCollection<Contact, ContactQuery, ContactCollectionOperations> getContacts() {
        return new ODataCollection<Contact, ContactQuery,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollection<ContactFolder, ContactFolderQuery, ContactFolderCollectionOperations> getChildFolders() {
        return new ODataCollection<ContactFolder, ContactFolderQuery,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}