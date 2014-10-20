/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addByteArrayResultCallback;


/**
 * The type DirectoryObjectOperations.
 */
public class DirectoryObjectOperations extends ODataOperations {

     /**
      * Instantiates a new DirectoryObjectOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
	public DirectoryObjectOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

      /**
      * Add parameter.
      *
      * @param name the name
      * @param value the value
      * @return the directoryobject operations.
      */
	public DirectoryObjectOperations addParameter(String name, Object value) {
	    addCustomParameter(name, value);
        return this;
	}
	
     /**
     * checkMemberGroups listenable future.
     * @param groupIds the groupIds

	 * @return the listenable future
     */			
	public ListenableFuture<String> checkMemberGroups(java.util.List<String> groupIds) {
	    final SettableFuture<String> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("groupIds", groupIds);
		
		ODataURL url = getResolver().createODataURL();

				String parameters = getFunctionParameters(map);
		url.appendPathComponent("checkMemberGroups(" + parameters + ")");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.GET);
		
		addEntityResultCallback(result, future, getResolver(), String.class);
		
		return result;
    }
	
     /**
     * getMemberGroups listenable future.
     * @param securityEnabledOnly the securityEnabledOnly

	 * @return the listenable future
     */			
	public ListenableFuture<String> getMemberGroups(Boolean securityEnabledOnly) {
	    final SettableFuture<String> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("securityEnabledOnly", securityEnabledOnly);
		
		ODataURL url = getResolver().createODataURL();

				String parameters = getFunctionParameters(map);
		url.appendPathComponent("getMemberGroups(" + parameters + ")");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.GET);
		
		addEntityResultCallback(result, future, getResolver(), String.class);
		
		return result;
    }
	
     /**
     * getMemberObjects listenable future.
     * @param securityEnabledOnly the securityEnabledOnly

	 * @return the listenable future
     */			
	public ListenableFuture<String> getMemberObjects(Boolean securityEnabledOnly) {
	    final SettableFuture<String> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("securityEnabledOnly", securityEnabledOnly);
		
		ODataURL url = getResolver().createODataURL();

				String parameters = getFunctionParameters(map);
		url.appendPathComponent("getMemberObjects(" + parameters + ")");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.GET);
		
		addEntityResultCallback(result, future, getResolver(), String.class);
		
		return result;
    }
}