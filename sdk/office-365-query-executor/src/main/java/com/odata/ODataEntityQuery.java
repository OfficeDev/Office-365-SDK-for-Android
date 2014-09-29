package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.impl.Constants;
import com.interfaces.DependencyResolver;
import com.interfaces.HttpVerb;

public abstract class ODataEntityQuery<E> extends ODataExecutable implements Executable<E> {

    private String urlComponent;
    private ODataExecutable parent;

    public ODataEntityQuery(String urlComponent, ODataExecutable parent) {
        this.urlComponent = urlComponent;
        this.parent = parent;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(String path, HttpVerb verb) {
        String url = urlComponent + "/" + path;
        return parent.oDataExecute(url, verb);
    }

    @Override
    DependencyResolver getResolver() {
        return parent.getResolver();
    }

    protected ListenableFuture<E> executeInternal(final Class<E> clazz) {
        final SettableFuture<E> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("", HttpVerb.GET);
        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                try {
                    String string = new String(payload, Constants.UTF8_NAME);
                    E entity = getResolver().getJsonSerializer().deserialize(string, clazz);

                    result.set(entity);
                } catch (Throwable t) {
                    result.setException(t);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }
}
