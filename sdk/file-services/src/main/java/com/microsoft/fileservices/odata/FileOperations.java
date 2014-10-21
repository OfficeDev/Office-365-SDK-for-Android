/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addByteArrayResultCallback;


/**
 * The type FileOperations.
 */
public class FileOperations extends ItemOperations {

     /**
      * Instantiates a new FileOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
	public FileOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

      /**
      * Add parameter.
      *
      * @param name the name
      * @param value the value
      * @return the file operations.
      */
	public FileOperations addParameter(String name, Object value) {
	    addCustomParameter(name, value);
        return this;
	}
	
     /**
     * copy listenable future.
     * @param destFolderId the destFolderId
 * @param destFolderPath the destFolderPath
 * @param newName the newName

	 * @return the listenable future
     */			
	public ListenableFuture<File> copy(String destFolderId, String destFolderPath, String newName) {
	    final SettableFuture<File> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("destFolderId", destFolderId);
		map.put("destFolderPath", destFolderPath);
		map.put("newName", newName);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("copy");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		
		addEntityResultCallback(result, future, getResolver(), File.class);
		
		return result;
    }
}