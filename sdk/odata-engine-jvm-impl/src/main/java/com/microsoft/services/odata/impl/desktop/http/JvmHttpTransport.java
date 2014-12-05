package com.microsoft.services.odata.impl.desktop.http;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.impl.http.BaseHttpTransport;
import com.microsoft.services.odata.impl.http.NetworkRunnable;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;

public class JvmHttpTransport extends BaseHttpTransport {
    @Override
    protected NetworkRunnable createNetworkRunnable(Request request, SettableFuture<Response> future) {
        return new JvmNetworkRunnable(request, future);
    }
}
