package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.DependencyResolver;
import com.infrastructure.http.Request;
import com.infrastructure.http.Response;

/**
 * Created by PabloZaiden on 26/09/14.
 */
abstract class ODataExecutable {

    abstract ListenableFuture<Response> oDataExecute(String path);

    abstract DependencyResolver getResolver();
}
