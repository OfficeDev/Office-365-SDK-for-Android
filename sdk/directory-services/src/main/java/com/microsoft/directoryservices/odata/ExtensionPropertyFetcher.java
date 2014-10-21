/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;		

/**
 * The type  ExtensionPropertyFetcher.
 */
public class ExtensionPropertyFetcher extends ODataEntityFetcher<ExtensionProperty,ExtensionPropertyOperations> implements Readable<ExtensionProperty> {

     /**
     * Instantiates a new ExtensionPropertyFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ExtensionPropertyFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, ExtensionProperty.class,ExtensionPropertyOperations.class);
    }
}