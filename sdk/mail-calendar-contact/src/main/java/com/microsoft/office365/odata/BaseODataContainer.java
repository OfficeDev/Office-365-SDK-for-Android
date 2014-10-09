/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.odata.BaseODataContainerHelper;
import com.microsoft.office365.odata.interfaces.DependencyResolver;
import com.microsoft.office365.odata.interfaces.HttpVerb;

public abstract class BaseODataContainer extends ODataExecutable {

    private String url;
    private DependencyResolver resolver;

    public BaseODataContainer(String url, DependencyResolver resolver) {
        this.url = url;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb) {
		return BaseODataContainerHelper.oDataExecute(path, content, verb, url, getResolver());
    }

    @Override
    DependencyResolver getResolver() {
        return resolver;
    }
}