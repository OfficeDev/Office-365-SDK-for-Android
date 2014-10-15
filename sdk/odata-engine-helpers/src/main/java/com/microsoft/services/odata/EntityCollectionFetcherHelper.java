package com.microsoft.services.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.DependencyResolver;

import java.util.List;

/**
 * The type Entity collection fetcher helper.
 */
public class EntityCollectionFetcherHelper {
    /**
     * Add list result callback.
     *
     * @param <T>  the type parameter
     * @param result the result
     * @param future the future
     * @param resolver the resolver
     * @param clazz the clazz
     */
    public static <T> void addListResultCallback(final SettableFuture<List<T>> result, ListenableFuture<byte[]> future, final DependencyResolver resolver, final Class<T> clazz) {
        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                List<T> list;
                try {
                    String string = new String(payload, Constants.UTF8_NAME);
                    list = resolver.getJsonSerializer().deserializeList(string, clazz);
                    result.set(list);
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
}
