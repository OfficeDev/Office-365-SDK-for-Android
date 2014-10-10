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

public class MessageOperations extends ODataOperations {

	 public MessageOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }
			
	public ListenableFuture<Message> copy(String destinationId) {
        final SettableFuture<Message> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("DestinationId", destinationId);
		

        ListenableFuture<byte[]> future = oDataExecute("Copy", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Message.class);

        return result;
    }
			
	public ListenableFuture<Message> move(String destinationId) {
        final SettableFuture<Message> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("DestinationId", destinationId);
		

        ListenableFuture<byte[]> future = oDataExecute("Move", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Message.class);

        return result;
    }
			
	public ListenableFuture<Message> createReply() {
        final SettableFuture<Message> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		

        ListenableFuture<byte[]> future = oDataExecute("CreateReply", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Message.class);

        return result;
    }
			
	public ListenableFuture<Message> createReplyAll() {
        final SettableFuture<Message> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		

        ListenableFuture<byte[]> future = oDataExecute("CreateReplyAll", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Message.class);

        return result;
    }
			
	public ListenableFuture<Message> createForward() {
        final SettableFuture<Message> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		

        ListenableFuture<byte[]> future = oDataExecute("CreateForward", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Message.class);

        return result;
    }
			
	public ListenableFuture<Integer> reply(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		

        ListenableFuture<byte[]> future = oDataExecute("Reply", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
			
	public ListenableFuture<Integer> replyAll(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		

        ListenableFuture<byte[]> future = oDataExecute("ReplyAll", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
			
	public ListenableFuture<Integer> forward(String comment, java.util.List<Recipient> toRecipients) {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		map.put("ToRecipients", toRecipients);
		

        ListenableFuture<byte[]> future = oDataExecute("Forward", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
			
	public ListenableFuture<Integer> send() {
        final SettableFuture<Integer> result = SettableFuture.create();

		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		

        ListenableFuture<byte[]> future = oDataExecute("Send", serializeToJsonByteArray(map, getResolver()), HttpVerb.POST);
		addEntityResultCallback(result,future,getResolver(),Integer.class);

        return result;
    }
}