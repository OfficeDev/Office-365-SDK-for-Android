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
 * The type ApplicationOperations.
 */
public class ApplicationOperations extends DirectoryObjectOperations {

     /**
      * Instantiates a new ApplicationOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public ApplicationOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public ApplicationOperations addParameter(String name, Object value) {
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
    public ApplicationOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    
     /**
     * restore listenable future.
     * @param identifierUris the identifierUris 
     * @return the listenable future
     */         
    public ListenableFuture<Application> restore(java.util.List<String> identifierUris) { 

    final SettableFuture<Application> result = SettableFuture.create();
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("identifierUris", identifierUris);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        request.setContent(serializeToJsonByteArray(map, getResolver()));
        String parameters = getFunctionParameters(map);
        request.getUrl().appendPathComponent("restore(" + parameters + ")");   
        ListenableFuture<ODataResponse> future = oDataExecute(request);   
                return transformToEntityListenableFuture(transformToStringListenableFuture(future), Application.class, getResolver());
        
   }
    

}