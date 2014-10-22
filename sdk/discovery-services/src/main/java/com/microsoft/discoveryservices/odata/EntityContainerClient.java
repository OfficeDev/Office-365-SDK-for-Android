/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.discoveryservices.odata;

import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.discoveryservices.*;
import com.microsoft.discoveryservices.odata.*;

/**
 * The type EntityContainerClient.
 */
public class EntityContainerClient extends BaseODataContainer {

	 /**
     * Instantiates a new EntityContainerClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public EntityContainerClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }
	 /**
     * Gets ServiceInfo.
     *
     * @return the ServiceInfo
     */
	public ODataCollectionFetcher<ServiceInfo, ServiceInfoFetcher, ServiceInfoCollectionOperations> getAllServices() {
        return new ODataCollectionFetcher<ServiceInfo, ServiceInfoFetcher,ServiceInfoCollectionOperations>("AllServices", this, ServiceInfo.class,ServiceInfoCollectionOperations.class);
    }
	 /**
     * Gets ServiceInfo.
     *
     * @return the ServiceInfo
     */
	public ODataCollectionFetcher<ServiceInfo, ServiceInfoFetcher, ServiceInfoCollectionOperations> getServices() {
        return new ODataCollectionFetcher<ServiceInfo, ServiceInfoFetcher,ServiceInfoCollectionOperations>("Services", this, ServiceInfo.class,ServiceInfoCollectionOperations.class);
    }
}