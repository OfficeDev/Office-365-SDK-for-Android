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
 * The type MessageOperations.
 */
public class MessageOperations extends ItemOperations {

     /**
      * Instantiates a new MessageOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
	public MessageOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

      /**
      * Add parameter.
      *
      * @param name the name
      * @param value the value
      * @return the message operations.
      */
	public MessageOperations addParameter(String name, Object value) {
	    addCustomParameter(name, value);
        return this;
	}
	
     /**
     * Copy listenable future.
     * @param destinationId the destinationId

	 * @return the listenable future
     */			
	public ListenableFuture<Message> copy(String destinationId) {
	    final SettableFuture<Message> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("DestinationId", destinationId);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("Copy");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Message.class);
		
		return result;
    }
	
     /**
     * Move listenable future.
     * @param destinationId the destinationId

	 * @return the listenable future
     */			
	public ListenableFuture<Message> move(String destinationId) {
	    final SettableFuture<Message> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("DestinationId", destinationId);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("Move");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Message.class);
		
		return result;
    }
	
     /**
     * CreateReply listenable future.
    
	 * @return the listenable future
     */			
	public ListenableFuture<Message> createReply() {
	    final SettableFuture<Message> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("CreateReply");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Message.class);
		
		return result;
    }
	
     /**
     * CreateReplyAll listenable future.
    
	 * @return the listenable future
     */			
	public ListenableFuture<Message> createReplyAll() {
	    final SettableFuture<Message> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("CreateReplyAll");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Message.class);
		
		return result;
    }
	
     /**
     * CreateForward listenable future.
    
	 * @return the listenable future
     */			
	public ListenableFuture<Message> createForward() {
	    final SettableFuture<Message> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("CreateForward");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Message.class);
		
		return result;
    }
	
     /**
     * Reply listenable future.
     * @param comment the comment

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> reply(String comment) {
	    final SettableFuture<Integer> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("Reply");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
	
     /**
     * ReplyAll listenable future.
     * @param comment the comment

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> replyAll(String comment) {
	    final SettableFuture<Integer> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("ReplyAll");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
	
     /**
     * Forward listenable future.
     * @param comment the comment
 * @param toRecipients the toRecipients

	 * @return the listenable future
     */			
	public ListenableFuture<Integer> forward(String comment, java.util.List<Recipient> toRecipients) {
	    final SettableFuture<Integer> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("Comment", comment);
		map.put("ToRecipients", toRecipients);
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("Forward");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
	
     /**
     * Send listenable future.
    
	 * @return the listenable future
     */			
	public ListenableFuture<Integer> send() {
	    final SettableFuture<Integer> result = SettableFuture.create();
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		
		ODataURL url = getResolver().createODataURL();

				url.appendPathComponent("Send");
		
		ListenableFuture<byte[]> future = oDataExecute(url, serializeToJsonByteArray(map, getResolver()), HttpVerb.POST, getCustomHeaders());
		
		addEntityResultCallback(result, future, getResolver(), Integer.class);
		
		return result;
    }
}