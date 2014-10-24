/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.discoveryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.discoveryservices.*; 
import com.microsoft.discoveryservices.*;		

/**
 * The type  ServiceInfoFetcher.
 */
public class ServiceInfoFetcher extends ODataEntityFetcher<ServiceInfo,ServiceInfoOperations> implements Readable<ServiceInfo> {

     /**
     * Instantiates a new ServiceInfoFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ServiceInfoFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, ServiceInfo.class,ServiceInfoOperations.class);
    }

	}