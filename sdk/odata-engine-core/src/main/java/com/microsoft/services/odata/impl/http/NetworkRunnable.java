/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.impl.http;


import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Runnable that executes a network operation
 */
public abstract class NetworkRunnable implements Runnable {

    /**
     * The connection.
     */
    protected HttpURLConnection mConnection = null;
    /**
     * The response stream.
     */
    protected InputStream mResponseStream = null;
    /**
     * The request.
     */
    protected Request mRequest;
    /**
     * The future.
     */
    protected SettableFuture<Response> mFuture;

    /**
     * The  close lock.
     */
    protected final Object mCloseLock = new Object();

    /**
     * Initializes the network runnable
     *
     * @param request The request to execute
     * @param future Future for the operation
     */
    public NetworkRunnable(Request request, SettableFuture<Response> future) {
        mRequest = request;
        mFuture = future;
    }

    /**
     * Closes the stream and connection, if possible
     */
    void closeStreamAndConnection() {
        synchronized (mCloseLock) {
            if (mResponseStream != null) {
                try {
                    mResponseStream.close();
                } catch (IOException e) {
                }
            }

            if (mConnection != null) {
                mConnection.disconnect();
            }
        }
    }

}
