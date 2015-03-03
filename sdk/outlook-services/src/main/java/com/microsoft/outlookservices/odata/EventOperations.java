/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.microsoft.outlookservices.*;
import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import static com.microsoft.services.odata.Helpers.*;

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
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedComment = serializer.serialize(comment);
		  
        
        ListenableFuture<String> future = acceptRaw(serializedComment);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * AcceptRaw listenable future.
     * @param comment the comment 
     * @return the listenable future
     */ 
    public ListenableFuture<String> acceptRaw(String comment){
        
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        
        map.put("Comment", comment);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        
        request.setContent(getResolver().getJsonSerializer()
               .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        
        request.getUrl().appendPathComponent("Accept");
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


    
    
    /**
     * Decline listenable future.
     * @param comment the comment 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> decline(String comment) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedComment = serializer.serialize(comment);
		  
        
        ListenableFuture<String> future = declineRaw(serializedComment);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * DeclineRaw listenable future.
     * @param comment the comment 
     * @return the listenable future
     */ 
    public ListenableFuture<String> declineRaw(String comment){
        
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        
        map.put("Comment", comment);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        
        request.setContent(getResolver().getJsonSerializer()
               .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        
        request.getUrl().appendPathComponent("Decline");
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


    
    
    /**
     * TentativelyAccept listenable future.
     * @param comment the comment 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> tentativelyAccept(String comment) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedComment = serializer.serialize(comment);
		  
        
        ListenableFuture<String> future = tentativelyAcceptRaw(serializedComment);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * TentativelyAcceptRaw listenable future.
     * @param comment the comment 
     * @return the listenable future
     */ 
    public ListenableFuture<String> tentativelyAcceptRaw(String comment){
        
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        
        map.put("Comment", comment);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        
        request.setContent(getResolver().getJsonSerializer()
               .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        
        request.getUrl().appendPathComponent("TentativelyAccept");
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


}
