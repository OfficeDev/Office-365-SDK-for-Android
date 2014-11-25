package com.microsoft.services.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.ODataURL;

public abstract class ODataFetcher<TEntity> extends ODataExecutable {

    protected Class<TEntity> clazz;
    protected String urlComponent;
    protected ODataExecutable parent;


    public ODataFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz) {
        this.clazz = clazz;
        this.urlComponent = urlComponent;
        this.parent = parent;
    }

    @Override
    protected DependencyResolver getResolver() {
        return parent.getResolver();
    }

    protected void addEntityResultCallback(final SettableFuture<TEntity> result,
                                            ListenableFuture<ODataResponse> future) {
        Futures.addCallback(future, new FutureCallback<ODataResponse>() {
            @Override
            public void onSuccess(ODataResponse response) {
                try {
                    log("Entity Deserialization Started", LogLevel.VERBOSE);
                    String string = new String(response.getPayload(), Constants.UTF8_NAME);
                    TEntity entity = getResolver().getJsonSerializer().deserialize(string, clazz);
                    log("Entity Deserialization Finished", LogLevel.VERBOSE);

                    result.set(entity);
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
     * Add byte array result callback.
     *
     * @param result   the result
     * @param future   the future
     */
    protected void addByteArrayResultCallback(final SettableFuture<byte[]> result,
                                              ListenableFuture<byte[]> future) {
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
     * Add null result callback.
     *
     * @param result the result
     * @param future the future
     */
    protected void addNullResultCallback(final SettableFuture<?> result, ListenableFuture<ODataResponse> future) {
        Futures.addCallback(future, new FutureCallback<ODataResponse>() {
            @Override
            public void onSuccess(ODataResponse response) {
                result.set(null);
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
