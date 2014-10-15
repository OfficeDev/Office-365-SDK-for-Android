/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;

/**
 * The type EventOperations.
 */
public class EventOperations extends ODataOperations {

     /**
      * Instantiates a new EventOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
	public EventOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

      /**
      * Add parameter.
      *
      * @param name the name
      * @param value the value
      * @return the event operations.
      */
	public EventOperations addParameter(String name, Object value) {
	    addCustomParameter(name, value);
        return this;
	}
	
     /**
     * Accept listenable future.
     * @param comment the comment

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> accept(String comment) {
	    final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		
		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Accept");
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
	
     /**
     * Decline listenable future.
     * @param comment the comment

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> decline(String comment) {
	    final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		
		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Decline");
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
	
     /**
     * TentativelyAccept listenable future.
     * @param comment the comment

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> tentativelyAccept(String comment) {
	    final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		
		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("TentativelyAccept");
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
}