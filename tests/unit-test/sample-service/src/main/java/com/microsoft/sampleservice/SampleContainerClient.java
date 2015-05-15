/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice;

import com.microsoft.sampleservice.odata.SampleEntityCollectionOperations;
import com.microsoft.sampleservice.odata.SampleEntityFetcher;
import com.microsoft.services.orc.*;
import com.microsoft.services.orc.interfaces.DependencyResolver;
import com.microsoft.sampleservice.*;

/**
 * The type SampleContainerClient.
 */
public class SampleContainerClient extends BaseOrcContainer {

     /**
     * Instantiates a new SampleContainerClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public SampleContainerClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

	     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public SampleContainerClient addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public SampleContainerClient addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

     /**
     * Gets Me.
     *
     * @return the Me
     */
    public SampleEntityFetcher getMe() {
        return new SampleEntityFetcher("Me", this);
    }
     /**
     * Gets SampleEntity.
     *
     * @return the SampleEntity
     */
    public OrcCollectionFetcher<SampleEntity, SampleEntityFetcher, SampleEntityCollectionOperations> getservices() {
        return new OrcCollectionFetcher<SampleEntity, SampleEntityFetcher,SampleEntityCollectionOperations>("services", this, SampleEntity.class,SampleEntityCollectionOperations.class);
    }
}