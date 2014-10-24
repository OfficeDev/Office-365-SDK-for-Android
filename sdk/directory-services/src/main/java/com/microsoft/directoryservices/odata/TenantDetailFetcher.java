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
 * The type  TenantDetailFetcher.
 */
public class TenantDetailFetcher extends ODataEntityFetcher<TenantDetail,TenantDetailOperations> implements Readable<TenantDetail> {

     /**
     * Instantiates a new TenantDetailFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public TenantDetailFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, TenantDetail.class,TenantDetailOperations.class);
    }

	}