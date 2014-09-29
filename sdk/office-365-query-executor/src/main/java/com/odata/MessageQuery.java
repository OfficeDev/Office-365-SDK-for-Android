package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.Executable;
import com.model.Message;


public class MessageQuery extends ODataEntityQuery<Message> implements Executable<Message> {

    public MessageQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

    @Override
    public ListenableFuture<Message> execute() {
        return executeInternal(Message.class);
    }
}
