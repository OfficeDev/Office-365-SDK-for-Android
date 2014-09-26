package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.infrastructure.DependencyResolver;
import com.model.Message;
import com.model.User;
import com.odata.Executable;
import com.odata.ODataExecutable;
import com.odata.Queryable;

/**
 * Created by PabloZaiden on 26/09/14.
 */
public class UserQuery extends ODataExecutable implements Executable<User> {

    private String urlComponent;
    private ODataExecutable parent;

    public UserQuery(String urlComponent, ODataExecutable parent) {
        this.urlComponent = urlComponent;
        this.parent = parent;
    }

    public Queryable<Message> getMessages() {
        return new Queryable<Message>("Messages", this, Message.class);
    }

    @Override
    ListenableFuture<String> oDataExecute(String path) {
        return parent.oDataExecute(urlComponent + "/" + path);
    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<User> execute() {
        final SettableFuture<User> result = SettableFuture.create();

        ListenableFuture<String> future = oDataExecute("");
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                getResolver().getJsonSerializer().deserialize(s, User.class);
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });

        return result;
    }
}
