/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.discoveryservices.odata;

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
    abstract ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb);

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
}
