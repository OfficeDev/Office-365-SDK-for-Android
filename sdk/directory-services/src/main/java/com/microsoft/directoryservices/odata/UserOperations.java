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
 * The type UserOperations.
 */
public class UserOperations extends DirectoryObjectOperations {

     /**
      * Instantiates a new UserOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
	public UserOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

      /**
      * Add parameter.
      *
      * @param name the name
      * @param value the value
      * @return the user operations.
      */
	public UserOperations addParameter(String name, Object value) {
	    addCustomParameter(name, value);
        return this;
	}
	
     /**
     * assignLicense listenable future.
     * @param addLicenses the addLicenses
 * @param removeLicenses the removeLicenses

	 * @return the listenable future
     */			
	public ListenableFuture<User> assignLicense(java.util.List<AssignedLicense> addLicenses, java.util.List<java.util.UUID> removeLicenses) {
	    final SettableFuture<User> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("addLicenses", addLicenses);
		map.put("removeLicenses", removeLicenses);
		
		ODataURL url = getResolver().createODataURL();

				String parameters = getFunctionParameters(map);
		url.appendPathComponent("assignLicense(" + parameters + ")");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.GET);
		
		addEntityResultCallback(result, future, getResolver(), User.class);
		
		return result;
    }
}