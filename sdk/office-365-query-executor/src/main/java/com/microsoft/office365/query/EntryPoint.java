package com.microsoft.office365.query;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/**
 * Created by marcote on 9/25/14.
 */
public class EntryPoint implements QueryExecutor {

    DependencyResolver mResolver;

    public EntryPoint(String url, DependencyResolver resolver) {
        mResolver = resolver;
    }

    @Override
    public <E> ListenableFuture<?> executeQuery(String path, final Class<E> clazz) {
        final SettableFuture<E> result = SettableFuture.create();

        ListenableFuture<String> future = mResolver.getHttpClient().executeRequest(path);

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                result.set(mResolver.getJsonSerializer().deserialize(s, clazz));
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }

    public UserQueryableFuture getMe(){

        final UserQueryableFuture result = new UserQueryableFuture();

        ListenableFuture<User> future = (ListenableFuture<User>) executeQuery("/Me", User.class);

        Futures.addCallback(future, new FutureCallback<User>() {
            @Override
            public void onSuccess(User u) {
                result.set(u);

            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }

}
