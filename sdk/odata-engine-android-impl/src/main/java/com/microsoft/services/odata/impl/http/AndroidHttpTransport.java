/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.impl.http;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;


/**
 * The type Android http transport.
 */
public class AndroidHttpTransport extends BaseHttpTransport {

    @Override
    protected NetworkRunnable createNetworkRunnable(Request request, SettableFuture<Response> future) {
        return new AndroidNetworkRunnable(request, future);
    }
}
