/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*;
import static com.microsoft.services.odata.Helpers.*;

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
     * @return the operations
     */
    public DirectoryObjectOperations addParameter(String name, Object value) {
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
    public DirectoryObjectOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
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
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        String parameters = getFunctionParameters(map);
        request.getUrl().appendPathComponent("checkMemberGroups(" + parameters + ")");   
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
                return transformToEntityListenableFuture(transformToStringListenableFuture(future), String.class, getResolver());
        
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
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        String parameters = getFunctionParameters(map);
        request.getUrl().appendPathComponent("getMemberGroups(" + parameters + ")");   
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
                return transformToEntityListenableFuture(transformToStringListenableFuture(future), String.class, getResolver());
        
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
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        String parameters = getFunctionParameters(map);
        request.getUrl().appendPathComponent("getMemberObjects(" + parameters + ")");   
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
                return transformToEntityListenableFuture(transformToStringListenableFuture(future), String.class, getResolver());
        
   }
    

}