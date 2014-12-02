package com.microsoft.services.odata;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

/**
 * The type O data fetcher.
 *
 * @param <TEntity> the type parameter
 */
public abstract class ODataFetcher<TEntity> extends ODataExecutable {

    /**
     * The Clazz.
     */
    protected Class<TEntity> clazz;
    /**
     * The Url component.
     */
    protected String urlComponent;
    /**
     * The Parent.
     */
    protected ODataExecutable parent;


    /**
     * Instantiates a new O data fetcher.
     *
     * @param urlComponent the url component
     * @param parent       the parent
     * @param clazz        the clazz
     */
    public ODataFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz) {
        this.clazz = clazz;
        this.urlComponent = urlComponent;
        this.parent = parent;
    }

    @Override
    protected DependencyResolver getResolver() {
        return parent.getResolver();
    }

    /**
     * Read raw.
     *
     * @return the listenable future
     */
    protected ListenableFuture<String> readRaw() {
        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.GET);
        ListenableFuture<ODataResponse> future = oDataExecute(request);
        return Helpers.transformToStringListenableFuture(future);
    }


    /**
     * Add byte array result callback.
     *
     * @param result the result
     * @param future the future
     */
    protected void addByteArrayResultCallback(final SettableFuture<byte[]> result,
                                              ListenableFuture<byte[]> future) {

        // TODO: Review usage

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                try {
                    result.set(payload);
                } catch (Throwable e) {
                    result.setException(e);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });
    }

    /**
     * Sets selector url.
     *
     * @param url          the url
     * @param urlComponent the url component
     * @param selectedId   the selected id
     */
    protected void setSelectorUrl(ODataURL url, String urlComponent, String selectedId) {
        String selector = "('" + selectedId + "')";
        url.prependPathComponent(urlComponent + selector);
    }
}
