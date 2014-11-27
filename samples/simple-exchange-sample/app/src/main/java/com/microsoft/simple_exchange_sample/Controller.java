/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.widget.Toast;

import com.microsoft.services.odata.impl.ADALDependencyResolver;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * holds the context of execution across activities
 */
public class Controller {

    private ADALDependencyResolver dependencyResolver;

    private static Controller INSTANCE;

    ExecutorService executor;

    private Controller() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    /**
     * Creates a singleton instance of the Controller
     * @return an instance of Controller class
     */
    public static synchronized Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }

        return INSTANCE;
    }

    /**
     * sets the dependency resolver
     * @param resolver the resolver object
     */
    public void setDependencyResolver(ADALDependencyResolver resolver) {
        this.dependencyResolver = resolver;
    }

    /**
     * gets the dependency resolver object
     * @return the instance of the resolver
     */
    public ADALDependencyResolver getDependencyResolver() {
        return this.dependencyResolver;
    }

    /**
     * post an async task to the executor thread pool
     * @param callable the task to be executed
     * @param <V> the type of the task to be executed
     */
    public <V> void postASyncTask(Callable<V> callable) {
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

