/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.sampleservice.*;
import static com.microsoft.services.odata.Helpers.*;



/**
 * The type SampleEntityCollectionOperations
 */
public class SampleEntityCollectionOperations extends EntityCollectionOperations{

    /**
     * Instantiates a new SampleEntityCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public SampleEntityCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public SampleEntityCollectionOperations addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public SampleEntityCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }
 
     
     /**
     * SomeFunction listenable future.
     * @param path the path 
     * @return the listenable future
     */         
    public ListenableFuture<SampleComplexType> someFunction(String path) { 

        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("path", path);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));

        String parameters = getFunctionParameters(map);
        request.getUrl().appendPathComponent("SomeFunction(" + parameters + ")");
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
                return transformToEntityListenableFuture(future, SampleComplexType.class, getResolver());
        
   }
    
                
}
