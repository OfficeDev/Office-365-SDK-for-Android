/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;


/**
 * The type DirectoryLinkChangeOperations.
 */
public class DirectoryLinkChangeOperations extends DirectoryObjectOperations {

     /**
      * Instantiates a new DirectoryLinkChangeOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
    public DirectoryLinkChangeOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

    /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the operations
     */
    public DirectoryLinkChangeOperations addParameter(String name, Object value) {
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
    public DirectoryLinkChangeOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

}