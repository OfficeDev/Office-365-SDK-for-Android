/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.files.services.*;
import static com.microsoft.office365.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.office365.odata.EntityFetcherHelper.addEntityResultCallback;

public class FolderOperations extends ODataOperations {

	 public FolderOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

	public FolderOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
			
	public ListenableFuture<Folder> copy(String destFolderId, String destFolderPath, String newName) {
        final SettableFuture<Folder> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("destFolderId", destFolderId);
		map.put("destFolderPath", destFolderPath);
		map.put("newName", newName);
		

		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Copy");
        ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Folder.class);

        return result;
    }
}