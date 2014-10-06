/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.odata.interfaces.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

abstract class ODataExecutable {

    abstract ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb);

    abstract DependencyResolver getResolver();

    String getODataParameterValue(Object value){
        String serialized = getResolver().getJsonSerializer().serialize(value);
        return urlEncode(serialized);
    }

    String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, Constants.UTF8_NAME);
        } catch (UnsupportedEncodingException ignored) {
            return s;
        }
    }
}
