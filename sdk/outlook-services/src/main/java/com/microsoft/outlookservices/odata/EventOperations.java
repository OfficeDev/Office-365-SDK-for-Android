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
 * The type EventOperations.
 */
public class EventOperations extends ItemOperations {

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
     * @return the operations
     */
    public EventOperations addParameter(String name, Object value) {
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
    public EventOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Accept");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("Decline");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
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
		
		Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        request.getUrl().appendPathComponent("TentativelyAccept");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
        addEntityResultCallback(result, future, Integer.class);
        
        return result;
    }
}