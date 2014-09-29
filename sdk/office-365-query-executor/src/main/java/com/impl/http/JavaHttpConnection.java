/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.impl.http;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.interfaces.HttpTransport;
import com.interfaces.Request;
import com.interfaces.Response;

/**
 * Java HttpConnection implementation, based on HttpURLConnection and threads
 * async operations
 */
public class JavaHttpConnection implements HttpTransport {

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

        request.addHeader(USER_AGENT_HEADER, "Office365-SDK-Test");

        final SettableFuture<Response> future = SettableFuture.create();
        final NetworkRunnable target = new NetworkRunnable(request, future);

        final NetworkThread networkThread = new NetworkThread(target) {
            @Override
            void releaseAndStop() {
                try {
                    target.closeStreamAndConnection();
                } catch (Throwable error) {
                }
            }
        };

        Futures.addCallback(future, new FutureCallback<Response>() {
            @Override
            public void onFailure(Throwable arg0) {
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
