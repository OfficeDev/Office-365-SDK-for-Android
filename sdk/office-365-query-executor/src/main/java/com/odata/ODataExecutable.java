package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.DependencyResolver;

abstract class ODataExecutable {

    abstract ListenableFuture<byte[]> oDataExecute(String path);

    abstract DependencyResolver getResolver();
}
