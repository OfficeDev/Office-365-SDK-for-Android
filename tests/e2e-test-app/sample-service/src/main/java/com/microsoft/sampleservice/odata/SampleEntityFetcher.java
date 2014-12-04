/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.sampleservice.*; 
import com.microsoft.sampleservice.*;       

/**
 * The type  SampleEntityFetcher.
 */
public class SampleEntityFetcher extends ODataEntityFetcher<SampleEntity,SampleEntityOperations> 
                                     implements Readable<SampleEntity> {

     /**
     * Instantiates a new SampleEntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public SampleEntityFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, SampleEntity.class, SampleEntityOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public SampleEntityFetcher addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public SampleEntityFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	     /**
     * Gets navigations.
     *
     * @return the navigations
     */
    public ODataCollectionFetcher<AnotherEntity, AnotherEntityFetcher, AnotherEntityCollectionOperations> getNavigations() {
        return new ODataCollectionFetcher<AnotherEntity, AnotherEntityFetcher,AnotherEntityCollectionOperations>("Navigations", this, AnotherEntity.class,AnotherEntityCollectionOperations.class);
    }

    /**
     * Gets navigation.
     *
     * @return the navigation
     */
    public AnotherEntityFetcher getNavigation(String id){
         return new ODataCollectionFetcher<AnotherEntity, AnotherEntityFetcher,AnotherEntityCollectionOperations>("Navigations", this, AnotherEntity.class,AnotherEntityCollectionOperations.class).getById(id);
    }
}