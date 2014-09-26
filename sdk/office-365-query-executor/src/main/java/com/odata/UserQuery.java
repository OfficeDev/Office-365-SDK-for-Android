package com.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.infrastructure.DependencyResolver;
import com.infrastructure.http.Response;
import com.model.Message;
import com.model.User;

import java.io.IOException;

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
    ListenableFuture<Response> oDataExecute(String path) {
        return parent.oDataExecute(urlComponent + "/" + path);
    }

    @Override
    public DependencyResolver getResolver() {
        return parent.getResolver();
    }

    @Override
    public ListenableFuture<User> execute() {
        final SettableFuture<User> result = SettableFuture.create();

        ListenableFuture<Response> future = oDataExecute("");
        Futures.addCallback(future, new FutureCallback<Response>() {
            @Override
            public void onSuccess(Response response) {
                try {
                    getResolver().getJsonSerializer().deserialize(response.readToEnd(), User.class);
                } catch (IOException e) {
                    e.printStackTrace();
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
