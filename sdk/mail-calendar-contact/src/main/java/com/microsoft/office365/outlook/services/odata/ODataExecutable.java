/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.odata.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class ODataExecutable {

    abstract ListenableFuture<byte[]> oDataExecute(ODataURL path, byte[] content, HttpVerb verb);

    abstract DependencyResolver getResolver();

    Map<String, Object> customParamenters = new HashMap<String, Object>();

    public void addCustomParameter(String name, Object value) {
        this.customParamenters.put(name, value);
    }

    protected Map<String, Object> getCustomParameters() {
        return customParamenters;
    }
}
