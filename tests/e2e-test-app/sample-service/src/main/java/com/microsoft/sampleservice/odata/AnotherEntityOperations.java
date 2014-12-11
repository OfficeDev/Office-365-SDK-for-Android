/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.sampleservice.*;
import static com.microsoft.services.odata.Helpers.*;

/**
 * The type AnotherEntityOperations.
 */
public class AnotherEntityOperations extends EntityOperations {

     /**
      * Instantiates a new AnotherEntityOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public AnotherEntityOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public AnotherEntityOperations addParameter(String name, Object value) {
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
    public AnotherEntityOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }


}