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
 * The type AnotherEntityCollectionOperations
 */
public class AnotherEntityCollectionOperations extends EntityCollectionOperations{

    /**
     * Instantiates a new AnotherEntityCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public AnotherEntityCollectionOperations(String urlComponent, OrcExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public AnotherEntityCollectionOperations addParameter(String name, Object value) {
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
    public AnotherEntityCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }
}
