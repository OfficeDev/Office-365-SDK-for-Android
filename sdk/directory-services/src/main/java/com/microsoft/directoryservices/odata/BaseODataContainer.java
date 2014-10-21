/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.odata.BaseODataContainerHelper;
import com.microsoft.services.odata.interfaces.*;

/**
 * The type BaseODataContainer.
*/
public abstract class BaseODataContainer extends ODataExecutable {

    private String url;
    private DependencyResolver resolver;

    public BaseODataContainer(String url, DependencyResolver resolver) {
        this.url = url;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb) {
		return BaseODataContainerHelper.oDataExecute(path, content, verb, url, getResolver(), this.getClass().getCanonicalName());
    }

    @Override
    DependencyResolver getResolver() {
        return resolver;
    }
}