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
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;

/**
 * The type FileOperations.
 */
public class FileOperations extends ODataOperations {

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
     * Copy listenable future.
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
		url.appendPathComponent("Copy");
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result, future, getResolver(), File.class);
		
		return result;
    }
	
     /**
     * UploadContent listenable future.
     * @param contentStream the contentStream

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> uploadContent(byte[] contentStream) {
	    final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("contentStream", contentStream);
		
		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("UploadContent");
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
	
     /**
     * Content listenable future.
    
	 * @return the listenable future
     */			
	public ListenableFuture<byte[]> content() {
	    final SettableFuture<byte[]> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		
		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Content");
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result, future, getResolver(), byte[].class);
		
		return result;
    }
}