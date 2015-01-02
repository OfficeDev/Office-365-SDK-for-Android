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
 * The type  AnotherEntityFetcher.
 */
public class AnotherEntityFetcher extends ODataEntityFetcher<AnotherEntity,AnotherEntityOperations> 
                                     implements Readable<AnotherEntity> {

     /**
     * Instantiates a new AnotherEntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public AnotherEntityFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, AnotherEntity.class, AnotherEntityOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public AnotherEntityFetcher addParameter(String name, Object value) {
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
    public AnotherEntityFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	}