/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.odata.interfaces.*;
import java.util.*;

/**
 * The type ODataExecutable.
 */
abstract class ODataExecutable {

	/**
     * oDataExecute.
     *
     * @param path the path
     * @param content the content
     * @param verb the verb
     * @return the listenable future
     */
    abstract ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb, Map<String, String> headers);

	/**
     * Gets resolver.
     *
     * @return the resolver
     */
    abstract DependencyResolver getResolver();

	/**
     * The Custom parameters.
     */
	Map<String, Object> customParameters = new HashMap<String, Object>();

	/**
     * The Custom headers.
     */
    Map<String, String> customHeaders = new HashMap<String, String>();

	/**
     * Add custom parameter.
     *
     * @param name the name
     * @param value the value
     */
    void addCustomParameter(String name, Object value) {
	   this.customParameters.put(name, value);
	}

	 /**
     * Gets custom parameters.
     *
     * @return the custom parameters
     */
	Map<String, Object> getCustomParameters() {
		return this.customParameters;
	}


    /**
     * Add custom headers.
     *
     * @param name the name
     * @param value the value
     */
    void addCustomHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }

    /**
     * Gets custom headers.
     *
     * @return the custom headers
     */
    Map<String, String> getCustomHeaders() {
        return this.customHeaders;
    }
}
