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
 * The type ItemCollectionOperations
 */
public class ItemCollectionOperations extends ODataOperations{

    /**
     * Instantiates a new ItemCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public ItemCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the file attachment collection operations
     */
	public ItemCollectionOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
	 /**
     * getByPath listenable future.
        * @param path the path

	 * @return the listenable future
     */		
	public ListenableFuture<Item> getByPath(String path) {
        final SettableFuture<Item> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("path", path);
			
		ODataURL url = getResolver().createODataURL();
        
				String parameters = getFunctionParameters(map);
		url.appendPathComponent("getByPath(" + parameters + ")");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.GET);

		addEntityResultCallback(result, future, getResolver(), Item.class);
		
        return result;
    }
				
}
