/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class ContactFolderFetcher extends ODataEntityFetcher<ContactFolder,ContactFolderOperations> implements Readable<ContactFolder> {

	 public ContactFolderFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ContactFolder.class,ContactFolderOperations.class);
    }
	public ODataCollectionFetcher<Contact, ContactFetcher, ContactCollectionOperations> getContacts() {
        return new ODataCollectionFetcher<Contact, ContactFetcher,ContactCollectionOperations>("Contacts", this, Contact.class,ContactCollectionOperations.class);
    }
	public ODataCollectionFetcher<ContactFolder, ContactFolderFetcher, ContactFolderCollectionOperations> getChildFolders() {
        return new ODataCollectionFetcher<ContactFolder, ContactFolderFetcher,ContactFolderCollectionOperations>("ChildFolders", this, ContactFolder.class,ContactFolderCollectionOperations.class);
    }
}