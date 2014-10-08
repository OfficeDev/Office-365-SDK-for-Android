package com.microsoft.office365.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.odata.interfaces.DependencyResolver;

import static com.microsoft.office365.odata.Helpers.urlEncode;

public class EntityFetcherHelper {

    public static String getODataExecuteUrlForPath(String path, String urlComponent) {
        StringBuilder url = new StringBuilder();
        if (urlComponent.length() > 0) {
            url.append(urlComponent);
        }

        if (path.length() > 0) {
            url.append("/");
            url.append(path);
        }
        return url.toString();
    }

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

    public static String getQueryString(String urlComponent, int top, int skip, String select, String expand, String filter) {
        StringBuilder query = new StringBuilder();

        query.append("?");
        if (top > -1) {
            query.append("&$top=");
            query.append(top);
        }

        if (skip> -1) {
            query.append("&$skip=");
            query.append(skip);
        }

        if (select != null) {
            query.append("&$select=");
            query.append(urlEncode(select));
        }

        if (expand != null) {
            query.append("&expand=");
            query.append(urlEncode(expand));
        }

        if (filter!= null) {
            query.append("&filter=");
            query.append(urlEncode(filter));
        }
        return urlComponent + query.toString();
    }

    public static String getSelectorUrl(String urlComponent, String selectedId, String path) {
        String selector = "('" + selectedId + "')";
        return urlComponent + selector + "/" + path;
    }
}
