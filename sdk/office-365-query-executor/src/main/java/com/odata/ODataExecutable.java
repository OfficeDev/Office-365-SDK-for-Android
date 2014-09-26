package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.DependencyResolver;

/**
 * Created by PabloZaiden on 26/09/14.
 */
abstract class ODataExecutable {
    abstract ListenableFuture<String> oDataExecute(String path);
    abstract DependencyResolver getResolver();
}
