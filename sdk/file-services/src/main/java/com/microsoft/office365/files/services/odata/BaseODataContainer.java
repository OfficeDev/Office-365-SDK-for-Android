/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.odata.BaseODataContainerHelper;
import com.microsoft.office365.odata.interfaces.*;
import static com.microsoft.office365.odata.Helpers.addCustomParametersToODataURL;

public abstract class BaseODataContainer extends ODataExecutable {

    private String url;
    private DependencyResolver resolver;

    public BaseODataContainer(String url, DependencyResolver resolver) {
        this.url = url;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb) {
        addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
		return BaseODataContainerHelper.oDataExecute(path, content, verb, url, getResolver(), this.getClass().getCanonicalName());
    }

    @Override
    DependencyResolver getResolver() {
        return resolver;
    }
}