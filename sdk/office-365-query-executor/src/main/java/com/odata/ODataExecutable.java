package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.interfaces.DependencyResolver;
import com.interfaces.HttpVerb;

abstract class ODataExecutable {

    abstract ListenableFuture<byte[]> oDataExecute(String path, HttpVerb verb);

    abstract DependencyResolver getResolver();
}
