/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.odata.interfaces.*;
import static com.microsoft.services.odata.Helpers.addCustomParametersToODataURL;
import java.util.*;

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
    ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb, Map<String, String> headers) {
		path.prependPathComponent(urlComponent);
		addCustomParametersToODataURL(path, getCustomParameters(), getResolver());
        Map<String, String> newHeaders = new HashMap<String, String>(getCustomHeaders());
        newHeaders.putAll(headers);
        return parent.oDataExecute(path, content, verb, newHeaders);
    }

    @Override
    DependencyResolver getResolver() {
        return parent.getResolver();
    }
}
