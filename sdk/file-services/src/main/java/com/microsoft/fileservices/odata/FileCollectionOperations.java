/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*;
import static com.microsoft.services.odata.Helpers.*;



/**
 * The type FileCollectionOperations
 */
public class FileCollectionOperations extends ItemCollectionOperations{

    /**
     * Instantiates a new FileCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public FileCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public FileCollectionOperations addParameter(String name, Object value) {
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
    public FileCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }
}
