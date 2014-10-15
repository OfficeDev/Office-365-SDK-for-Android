/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class FolderFetcher extends ODataEntityFetcher<Folder,FolderOperations> implements Readable<Folder> {

	 public FolderFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Folder.class,FolderOperations.class);
    }

	public FolderFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
	public ODataCollectionFetcher<Folder, FolderFetcher, FolderCollectionOperations> getChildFolders() {
        return new ODataCollectionFetcher<Folder, FolderFetcher,FolderCollectionOperations>("ChildFolders", this, Folder.class,FolderCollectionOperations.class);
    }
	public ODataCollectionFetcher<Message, MessageFetcher, MessageCollectionOperations> getMessages() {
        return new ODataCollectionFetcher<Message, MessageFetcher,MessageCollectionOperations>("Messages", this, Message.class,MessageCollectionOperations.class);
    }
}