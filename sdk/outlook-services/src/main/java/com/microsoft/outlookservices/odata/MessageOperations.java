/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;


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
     * @return the operations
     */
    public MessageOperations addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public MessageOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Copy");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Message.class);
        
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Move");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Message.class);
        
        return result;
    }
    
     /**
     * CreateReply listenable future.
    
     * @return the listenable future
     */         
    public ListenableFuture<Message> createReply() {
        final SettableFuture<Message> result = SettableFuture.create();
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("CreateReply");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Message.class);
        
        return result;
    }
    
     /**
     * CreateReplyAll listenable future.
    
     * @return the listenable future
     */         
    public ListenableFuture<Message> createReplyAll() {
        final SettableFuture<Message> result = SettableFuture.create();
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("CreateReplyAll");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Message.class);
        
        return result;
    }
    
     /**
     * CreateForward listenable future.
    
     * @return the listenable future
     */         
    public ListenableFuture<Message> createForward() {
        final SettableFuture<Message> result = SettableFuture.create();
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("CreateForward");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Message.class);
        
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Reply");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("ReplyAll");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Forward");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
        return result;
    }
    
     /**
     * Send listenable future.
    
     * @return the listenable future
     */         
    public ListenableFuture<Integer> send() {
        final SettableFuture<Integer> result = SettableFuture.create();
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Send");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
        return result;
    }
}