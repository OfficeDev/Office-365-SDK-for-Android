package com.microsoft.office365.odata.interfaces;

import com.google.common.util.concurrent.ListenableFuture;

public interface HttpTransport {
    Request createRequest();
    ListenableFuture<Response> execute(Request request);
}
