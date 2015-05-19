/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.orc.*;
import com.microsoft.services.orc.interfaces.*;
import com.microsoft.sampleservice.*;
import com.microsoft.services.orc.OrcExecutable;

import static com.microsoft.services.orc.Helpers.*;

/**
 * The type SampleEntityOperations.
 */
public class SampleEntityOperations extends EntityOperations {

     /**
      * Instantiates a new SampleEntityOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public SampleEntityOperations(String urlComponent, OrcExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public SampleEntityOperations addParameter(String name, Object value) {
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
    public SampleEntityOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    
    /**
     * TwoParamsActionsFirstIsEntityType listenable future.
     * @param anEntity the anEntity @param booleanParams the booleanParams 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> twoParamsActionsFirstIsEntityType(SampleEntity anEntity, Boolean booleanParams) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedAnEntity = serializer.serialize(anEntity);
		String serializedBooleanParams = serializer.serialize(booleanParams);
		  
        ListenableFuture<String> future = twoParamsActionsFirstIsEntityTypeRaw(serializedAnEntity, serializedBooleanParams);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * TwoParamsActionsFirstIsEntityTypeRaw listenable future.
     * @param anEntity the anEntity @param booleanParams the booleanParams 
     * @return the listenable future
     */ 
    public ListenableFuture<String> twoParamsActionsFirstIsEntityTypeRaw(String anEntity, String booleanParams){
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        map.put("AnEntity", anEntity);
		map.put("BooleanParams", booleanParams);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(getResolver().getJsonSerializer()
                                        .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        request.getUrl().appendPathComponent("TwoParamsActionsFirstIsEntityType");
        ListenableFuture<OrcResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


    
    
    /**
     * TwoParamsActionsFirstIsComplexType listenable future.
     * @param complexType the complexType @param booleanParams the booleanParams 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> twoParamsActionsFirstIsComplexType(SampleComplexType complexType, Boolean booleanParams) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedComplexType = serializer.serialize(complexType);
		String serializedBooleanParams = serializer.serialize(booleanParams);
		  
        ListenableFuture<String> future = twoParamsActionsFirstIsComplexTypeRaw(serializedComplexType, serializedBooleanParams);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * TwoParamsActionsFirstIsComplexTypeRaw listenable future.
     * @param complexType the complexType @param booleanParams the booleanParams 
     * @return the listenable future
     */ 
    public ListenableFuture<String> twoParamsActionsFirstIsComplexTypeRaw(String complexType, String booleanParams){
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        map.put("ComplexType", complexType);
		map.put("BooleanParams", booleanParams);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(getResolver().getJsonSerializer()
                                        .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        request.getUrl().appendPathComponent("TwoParamsActionsFirstIsComplexType");
        ListenableFuture<OrcResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


    
    
    /**
     * TwoParamsActionsFirstIsCollectionEntityType listenable future.
     * @param collectionType the collectionType @param booleanParams the booleanParams 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> twoParamsActionsFirstIsCollectionEntityType(java.util.List<SampleEntity> collectionType, Boolean booleanParams) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedCollectionType = serializer.serialize(collectionType);
		String serializedBooleanParams = serializer.serialize(booleanParams);
		  
        ListenableFuture<String> future = twoParamsActionsFirstIsCollectionEntityTypeRaw(serializedCollectionType, serializedBooleanParams);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * TwoParamsActionsFirstIsCollectionEntityTypeRaw listenable future.
     * @param collectionType the collectionType @param booleanParams the booleanParams 
     * @return the listenable future
     */ 
    public ListenableFuture<String> twoParamsActionsFirstIsCollectionEntityTypeRaw(String collectionType, String booleanParams){
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        map.put("CollectionType", collectionType);
		map.put("BooleanParams", booleanParams);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(getResolver().getJsonSerializer()
                                        .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        request.getUrl().appendPathComponent("TwoParamsActionsFirstIsCollectionEntityType");
        ListenableFuture<OrcResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


    
    
    /**
     * TwoParamsActionsFirstIsCollectionComplexType listenable future.
     * @param collectionType the collectionType @param booleanParams the booleanParams 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> twoParamsActionsFirstIsCollectionComplexType(java.util.List<SampleComplexType> collectionType, Boolean booleanParams) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedCollectionType = serializer.serialize(collectionType);
		String serializedBooleanParams = serializer.serialize(booleanParams);
		  
        ListenableFuture<String> future = twoParamsActionsFirstIsCollectionComplexTypeRaw(serializedCollectionType, serializedBooleanParams);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * TwoParamsActionsFirstIsCollectionComplexTypeRaw listenable future.
     * @param collectionType the collectionType @param booleanParams the booleanParams 
     * @return the listenable future
     */ 
    public ListenableFuture<String> twoParamsActionsFirstIsCollectionComplexTypeRaw(String collectionType, String booleanParams){
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        map.put("CollectionType", collectionType);
		map.put("BooleanParams", booleanParams);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(getResolver().getJsonSerializer()
                                        .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        request.getUrl().appendPathComponent("TwoParamsActionsFirstIsCollectionComplexType");
        ListenableFuture<OrcResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }



}