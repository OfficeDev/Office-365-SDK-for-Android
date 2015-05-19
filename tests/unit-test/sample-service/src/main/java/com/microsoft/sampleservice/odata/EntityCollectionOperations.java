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
import com.microsoft.services.orc.OrcOperations;

import static com.microsoft.services.orc.Helpers.*;



/**
 * The type EntityCollectionOperations
 */
public class EntityCollectionOperations extends OrcOperations{

    /**
     * Instantiates a new EntityCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public EntityCollectionOperations(String urlComponent, OrcExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public EntityCollectionOperations addParameter(String name, Object value) {
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
    public EntityCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }
}
