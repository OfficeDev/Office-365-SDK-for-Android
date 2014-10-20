/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*;
import static com.microsoft.services.odata.Helpers.serializeToJsonByteArray;
import static com.microsoft.services.odata.Helpers.getFunctionParameters;
import static com.microsoft.services.odata.EntityFetcherHelper.addEntityResultCallback;
import static com.microsoft.services.odata.EntityFetcherHelper.addByteArrayResultCallback;


/**
 * The type AppRoleAssignmentCollectionOperations
 */
public class AppRoleAssignmentCollectionOperations extends DirectoryObjectCollectionOperations{

    /**
     * Instantiates a new AppRoleAssignmentCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public AppRoleAssignmentCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the file attachment collection operations
     */
	public AppRoleAssignmentCollectionOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
}
