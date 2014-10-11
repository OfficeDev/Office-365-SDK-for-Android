/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata.impl.http;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.odata.interfaces.HttpTransport;
import com.microsoft.office365.odata.interfaces.Request;
import com.microsoft.office365.odata.interfaces.Response;


/**
 * The type Android http transport.
 */
public class AndroidHttpTransport implements HttpTransport {

    /**
     * User agent header name
     */
    private static final String USER_AGENT_HEADER = "User-Agent";

    @Override
    public Request createRequest() {
        return new RequestImpl();
    }

    @Override
    public ListenableFuture<Response> execute(final Request request) {

        request.addHeader(USER_AGENT_HEADER, "Office365-SDK");

        final SettableFuture<Response> future = SettableFuture.create();
        final NetworkRunnable target = new NetworkRunnable(request, future);

        final NetworkThread networkThread = new NetworkThread(target) {
            @Override
            public void releaseAndStop() {
                try {
                    target.closeStreamAndConnection();
                } catch (Throwable ignored) {
                }
            }
        };

        Futures.addCallback(future, new FutureCallback<Response>() {
            @Override
            public void onFailure(Throwable t) {
                networkThread.releaseAndStop();
            }

            @Override
            public void onSuccess(Response response) {
            }
        });

        networkThread.start();
        return future;
    }
}
