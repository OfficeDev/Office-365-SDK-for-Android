/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;
import static com.microsoft.office365.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.office365.odata.EntityFetcherHelper.addEntityResultCallback;

public class EventOperations extends ODataOperations {

	 public EventOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

	public EventOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
			
	public ListenableFuture<Integer> accept(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		

		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Accept");
        ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
			
	public ListenableFuture<Integer> decline(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		

		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("Decline");
        ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
			
	public ListenableFuture<Integer> tentativelyAccept(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		

		ODataURL url = getResolver().createODataURL();
		url.appendPathComponent("TentativelyAccept");
        ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
}