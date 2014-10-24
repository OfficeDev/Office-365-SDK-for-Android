/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addByteArrayResultCallback;


/**
 * The type MessageCollectionOperations
 */
public class MessageCollectionOperations extends ItemCollectionOperations{

    /**
     * Instantiates a new MessageCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public MessageCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the file attachment collection operations
     */
	public MessageCollectionOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
}
