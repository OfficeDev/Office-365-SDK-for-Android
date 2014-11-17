/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;



/**
 * The type ItemAttachmentCollectionOperations
 */
public class ItemAttachmentCollectionOperations extends AttachmentCollectionOperations{

    /**
     * Instantiates a new ItemAttachmentCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public ItemAttachmentCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public ItemAttachmentCollectionOperations addParameter(String name, Object value) {
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
    public ItemAttachmentCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }
}
