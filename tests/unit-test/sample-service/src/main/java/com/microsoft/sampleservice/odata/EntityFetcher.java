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
 * The type  EntityFetcher.
 */
public class EntityFetcher extends ODataEntityFetcher<Entity,EntityOperations> 
                                     implements Readable<Entity> {

     /**
     * Instantiates a new EntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public EntityFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Entity.class, EntityOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public EntityFetcher addParameter(String name, Object value) {
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
    public EntityFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    public AnotherEntityFetcher asAnotherEntity(){
        return new AnotherEntityFetcher(this.urlComponent, this.parent);
    }	   

    public SampleEntityFetcher asSampleEntity(){
        return new SampleEntityFetcher(this.urlComponent, this.parent);
    }	   
	}