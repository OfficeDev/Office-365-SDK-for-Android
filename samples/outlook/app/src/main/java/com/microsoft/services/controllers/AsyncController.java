/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.controllers;

import android.app.Activity;
import android.widget.Toast;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * holds the context of execution across activities
 */
public class AsyncController {

    private static AsyncController INSTANCE;

    ExecutorService executor;

    private AsyncController() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    /**
     * Creates a singleton instance of the AsyncController
     * @return an instance of AsyncController class
     */
    public static synchronized AsyncController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AsyncController();
        }

        return INSTANCE;
    }



    /**
     * post an async task to the executor thread pool
     * @param callable the task to be executed
     *
     */
    public <V> void postAsyncTask(Callable<V> callable) {
        this.executor.submit(callable);
    }

    /**
     * notifies about the exception on executing the Future
     *
     * @param msg error message to be displayed
     */
    public void handleError(final Activity activity, final String msg) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}

