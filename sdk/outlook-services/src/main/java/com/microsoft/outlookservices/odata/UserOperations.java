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
import static com.microsoft.services.odata.Helpers.getFunctionParameters;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addByteArrayResultCallback;


/**
 * The type UserOperations.
 */
public class UserOperations extends EntityOperations {

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
     * SendMail listenable future.
     * @param message the message
 * @param saveToSentItems the saveToSentItems

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> sendMail(Message message, Boolean saveToSentItems) {
	    final SettableFuture<Integer> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Message", message);
		map.put("SaveToSentItems", saveToSentItems);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("SendMail");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
}