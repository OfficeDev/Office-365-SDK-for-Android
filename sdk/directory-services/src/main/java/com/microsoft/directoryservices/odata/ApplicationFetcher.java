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
 * The type  ApplicationFetcher.
 */
public class ApplicationFetcher extends ODataEntityFetcher<Application,ApplicationOperations> implements Readable<Application> {

     /**
     * Instantiates a new ApplicationFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ApplicationFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Application.class,ApplicationOperations.class);
    }
     /**
     * Gets extension properties.
     *
     * @return the extension properties
     */
	public ODataCollectionFetcher<ExtensionProperty, ExtensionPropertyFetcher, ExtensionPropertyCollectionOperations> getextensionProperties() {
		return new ODataCollectionFetcher<ExtensionProperty, ExtensionPropertyFetcher,ExtensionPropertyCollectionOperations>("extensionProperties", this, ExtensionProperty.class,ExtensionPropertyCollectionOperations.class);
    }
}