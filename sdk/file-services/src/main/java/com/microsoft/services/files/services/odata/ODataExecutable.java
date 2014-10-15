/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.services.odata.interfaces.*;

import java.util.HashMap;
import java.util.Map;

abstract class ODataExecutable {

    abstract ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb);

    abstract DependencyResolver getResolver();

	Map<String, Object> customParamenters = new HashMap<String, Object>();

    void addCustomParameter(String name, Object value) {
	   this.customParamenters.put(name, value);
	}


	Map<String, Object> getCustomParameters() {
		return customParamenters;
	}

}
