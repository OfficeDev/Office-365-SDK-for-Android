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
 * The type UserOperations.
 */
public class UserOperations extends EntityOperations {

     /**
      * Instantiates a new UserOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public UserOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public UserOperations addParameter(String name, Object value) {
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
    public UserOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    
    /**
     * SendMail listenable future.
     * @param message the message @param saveToSentItems the saveToSentItems 
     * @return the listenable future
     */         
    public ListenableFuture<Integer> sendMail(Message message, Boolean saveToSentItems) { 
        JsonSerializer serializer = getResolver().getJsonSerializer();      
        String serializedMessage = serializer.serialize(message);
		String serializedSaveToSentItems = serializer.serialize(saveToSentItems);
		  
        
        ListenableFuture<String> future = sendMailRaw(serializedMessage, serializedSaveToSentItems);
        return transformToEntityListenableFuture(future, Integer.class, getResolver());
        
    }

     /**
     * SendMailRaw listenable future.
     * @param message the message @param saveToSentItems the saveToSentItems 
     * @return the listenable future
     */ 
    public ListenableFuture<String> sendMailRaw(String message, String saveToSentItems){
        
        java.util.Map<String, String> map = new java.util.HashMap<String, String>();
        
        map.put("Message", message);
		map.put("SaveToSentItems", saveToSentItems);
		
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.POST);
        
        request.setContent(getResolver().getJsonSerializer()
               .jsonObjectFromJsonMap(map).getBytes(Constants.UTF8));

        
        request.getUrl().appendPathComponent("SendMail");
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return transformToStringListenableFuture(future);
    }


}
