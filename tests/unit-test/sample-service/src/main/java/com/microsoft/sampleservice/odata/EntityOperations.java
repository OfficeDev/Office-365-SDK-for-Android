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
 * The type EntityOperations.
 */
public class EntityOperations extends OrcOperations {

     /**
      * Instantiates a new EntityOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public EntityOperations(String urlComponent, OrcExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public EntityOperations addParameter(String name, Object value) {
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
    public EntityOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }


}