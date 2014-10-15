package com.microsoft.services.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.ODataURL;

/**
 * The type Entity fetcher helper.
 */
public class EntityFetcherHelper {

    /**
     * Add entity result callback.
     *
     * @param <E>  the type parameter
     * @param result the result
     * @param future the future
     * @param resolver the resolver
     * @param clazz the clazz
     */
    public static <E> void addEntityResultCallback(final SettableFuture<E> result, ListenableFuture<byte[]> future, final DependencyResolver resolver, final Class<E> clazz) {
        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                try {
                    String string = new String(payload, Constants.UTF8_NAME);
                    E entity = resolver.getJsonSerializer().deserialize(string, clazz);
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
     * @param <E>  the type parameter
     * @param result the result
     * @param future the future
     * @param resolver the resolver
     * @param clazz the clazz
     */
    public static <E> void addByteArrayResultCallback(final SettableFuture<byte[]> result, ListenableFuture<byte[]> future, final DependencyResolver resolver, final Class<E> clazz) {
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
     * @param <E>  the type parameter
     * @param result the result
     * @param future the future
     */
    public static <E> void addNullResultCallback(final SettableFuture<E> result, ListenableFuture<byte[]> future) {
        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] payload) {
                result.set(null);
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });
    }

    /**
     * Sets path for collections.
     *
     * @param url the url
     * @param urlComponent the url component
     * @param top the top
     * @param skip the skip
     * @param select the select
     * @param expand the expand
     * @param filter the filter
     */
    public static void setPathForCollections(ODataURL url, String urlComponent, int top, int skip, String select, String expand, String filter) {
        if (top > -1) {
            url.addQueryStringParameter("$top", Integer.valueOf(top).toString());
        }

        if (skip> -1) {
            url.addQueryStringParameter("$skip", Integer.valueOf(skip).toString());
        }

        if (select != null) {
            url.addQueryStringParameter("$select", select);
        }

        if (expand != null) {
            url.addQueryStringParameter("$expand", expand);
        }

        if (filter!= null) {
            url.addQueryStringParameter("$filter", filter);
        }

        url.prependPathComponent(urlComponent);
    }

    /**
     * Sets selector url.
     *
     * @param url the url
     * @param urlComponent the url component
     * @param selectedId the selected id
     */
    public static void setSelectorUrl(ODataURL url, String urlComponent, String selectedId) {
        String selector = "('" + selectedId + "')";
        url.prependPathComponent(urlComponent + selector);
    }
}
