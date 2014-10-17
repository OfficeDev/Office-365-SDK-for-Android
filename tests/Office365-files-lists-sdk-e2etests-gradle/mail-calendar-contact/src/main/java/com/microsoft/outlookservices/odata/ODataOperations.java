/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.odata.interfaces.*;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;

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
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb) {
		path.prependPathComponent(urlComponent);
		addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
        return parent.oDataExecute(path, content, verb);
    }

    @Override
    DependencyResolver getResolver() {
        return parent.getResolver();
    }
}
