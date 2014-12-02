/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.Request;

import static com.microsoft.services.odata.Helpers.addCustomParametersToODataRequest;

/**
 * The type ODataOperations.
 */
public abstract class ODataOperations extends ODataExecutable {
    private String urlComponent;
    private ODataExecutable parent;

	 /**
     * Instantiates a new ODataOperation.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public ODataOperations(String urlComponent, ODataExecutable parent) {
        this.urlComponent = urlComponent;
        this.parent = parent;
    }

    @Override
    protected ListenableFuture<ODataResponse> oDataExecute(Request request) {
        request.getUrl().prependPathComponent(urlComponent);
        addCustomParametersToODataRequest(request, getParameters(), getHeaders());
        return parent.oDataExecute(request);
    }

    @Override
    protected  DependencyResolver getResolver() {
        return parent.getResolver();
    }
}
