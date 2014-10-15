/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;

/**
 * The type ItemOperations.
 */
public class ItemOperations extends ODataOperations {

     /**
      * Instantiates a new ItemOperations.
      *
      * @param urlComponent the url component
      * @param parent the parent
      */
	public ItemOperations(String urlComponent, ODataExecutable parent) {
            super(urlComponent, parent);
    }

      /**
      * Add parameter.
      *
      * @param name the name
      * @param value the value
      * @return the item operations.
      */
	public ItemOperations addParameter(String name, Object value) {
	    addCustomParameter(name, value);
        return this;
	}
}