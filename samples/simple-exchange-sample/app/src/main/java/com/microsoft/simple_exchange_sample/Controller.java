/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.widget.Toast;

import com.microsoft.services.odata.interfaces.DependencyResolver;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * holds the context of execution across activities
 */
public class Controller {

    private DependencyResolver dependencyResolver;

    private static Controller INSTANCE;

    ExecutorService executor;

    private Controller() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    public static synchronized Controller getInstance() {
        Controller controller = null;
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }

        return INSTANCE;
    }

    public void setDependencyResolver(DependencyResolver resolver) {
        this.dependencyResolver = resolver;
    }

    public DependencyResolver getDependencyResolver() {
        return this.dependencyResolver;
    }

    public <V> void postASyncTask(Callable<V> callable) {
        this.executor.submit(callable);
    }

    /**
     * notifies about the exception on executing the Future
     * @param msg error message to be displayed
     */
    public static void handleError(final Activity activity, final String msg) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
