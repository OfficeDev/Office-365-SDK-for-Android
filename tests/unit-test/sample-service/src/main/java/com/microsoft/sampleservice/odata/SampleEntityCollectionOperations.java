/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.sampleservice.SampleComplexType;
import com.microsoft.services.orc.OrcExecutable;
import com.microsoft.services.orc.interfaces.HttpVerb;
import com.microsoft.services.orc.interfaces.OrcResponse;
import com.microsoft.services.orc.interfaces.Request;

import static com.microsoft.services.orc.Helpers.getFunctionParameters;
import static com.microsoft.services.orc.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.orc.Helpers.transformToEntityListenableFuture;
import static com.microsoft.services.orc.Helpers.transformToStringListenableFuture;


/**
 * The type SampleEntityCollectionOperations
 */
public class SampleEntityCollectionOperations extends EntityCollectionOperations {

    /**
     * Instantiates a new SampleEntityCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent       the parent
     */
    public SampleEntityCollectionOperations(String urlComponent, OrcExecutable parent) {
        super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name  the name
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
     * @param name  the name
     * @param value the value
     * @return the collection operations
     */
    public SampleEntityCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }


    /**
     * SomeFunction listenable future.
     *
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
        ListenableFuture<OrcResponse> future = oDataExecute(request);
        return transformToEntityListenableFuture(transformToStringListenableFuture(future), SampleComplexType.class, getResolver());

    }

}
