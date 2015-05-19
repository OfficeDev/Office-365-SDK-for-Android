/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.orc.*;
import com.microsoft.services.orc.Readable;
import com.microsoft.services.orc.interfaces.*;
import com.microsoft.sampleservice.*; 
import com.microsoft.sampleservice.*;       

/**
 * The type  SampleEntityFetcher.
 */
public class SampleEntityFetcher extends OrcEntityFetcher<SampleEntity,SampleEntityOperations>
                                     implements Readable<SampleEntity> {

     /**
     * Instantiates a new SampleEntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public SampleEntityFetcher(String urlComponent, OrcExecutable parent) {
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
    public OrcCollectionFetcher<AnotherEntity, AnotherEntityFetcher, AnotherEntityCollectionOperations> getNavigations() {
        return new OrcCollectionFetcher<AnotherEntity, AnotherEntityFetcher,AnotherEntityCollectionOperations>("Navigations", this, AnotherEntity.class,AnotherEntityCollectionOperations.class);
    }

    /**
     * Gets navigation.
     *
     * @return the navigation
     */
    public AnotherEntityFetcher getNavigation(String id){
         return new OrcCollectionFetcher<AnotherEntity, AnotherEntityFetcher,AnotherEntityCollectionOperations>("Navigations", this, AnotherEntity.class,AnotherEntityCollectionOperations.class).getById(id);
    }
}