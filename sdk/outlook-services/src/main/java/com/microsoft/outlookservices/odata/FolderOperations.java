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
import static com.microsoft.services.odata.Helpers.*;

/**
 * The type FolderOperations.
 */
public class FolderOperations extends EntityOperations {

     /**
      * Instantiates a new FolderOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public FolderOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public FolderOperations addParameter(String name, Object value) {
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
    public FolderOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    
    /**
     * Copy listenable future.
     * @param destinationId the destinationId 
     * @return the listenable future
     */         
    public ListenableFuture<Folder> copy(String destinationId) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedDestinationId = serializer.serialize(destinationId);
		  
        ListenableFuture<String> future = copyRaw(serializedDestinationId);
        return transformToEntityListenableFuture(future, Folder.class, getResolver());
        
    }

     /**
     * CopyRaw listenable future.
     * @param destinationId the destinationId 
     * @return the listenable future
     */ 
    public ListenableFuture<String> copyRaw(String destinationId){
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        map.put("DestinationId", destinationId);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(getResolver().getJsonSerializer()
                                        .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        request.getUrl().appendPathComponent("Copy");
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


    
    
    /**
     * Move listenable future.
     * @param destinationId the destinationId 
     * @return the listenable future
     */         
    public ListenableFuture<Folder> move(String destinationId) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedDestinationId = serializer.serialize(destinationId);
		  
        ListenableFuture<String> future = moveRaw(serializedDestinationId);
        return transformToEntityListenableFuture(future, Folder.class, getResolver());
        
    }

     /**
     * MoveRaw listenable future.
     * @param destinationId the destinationId 
     * @return the listenable future
     */ 
    public ListenableFuture<String> moveRaw(String destinationId){
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        map.put("DestinationId", destinationId);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(getResolver().getJsonSerializer()
                                        .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        request.getUrl().appendPathComponent("Move");
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }



}