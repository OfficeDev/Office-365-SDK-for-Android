package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.model.Message;


public class MessageQuery extends ODataEntityQuery<Message> implements Executable<Message> {

    public MessageQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Message.class);
    }
}
