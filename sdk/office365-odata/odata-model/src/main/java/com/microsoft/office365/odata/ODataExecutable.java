package com.microsoft.office365.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.odata.interfaces.DependencyResolver;
import com.microsoft.office365.odata.interfaces.HttpVerb;


abstract class ODataExecutable {

    abstract ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb);

    abstract DependencyResolver getResolver();
}
