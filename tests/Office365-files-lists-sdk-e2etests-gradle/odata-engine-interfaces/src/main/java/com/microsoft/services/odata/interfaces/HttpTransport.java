package com.microsoft.services.odata.interfaces;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * The interface Http transport.
 */
public interface HttpTransport {
    /**
     * Create request.
     *
     * @return the request
     */
    Request createRequest();

    /**
     * Execute listenable future.
     *
     * @param request the request
     * @return the listenable future
     */
    ListenableFuture<Response> execute(Request request);
}
