package com.microsoft.office365.odata;

import com.microsoft.office365.odata.model.Message;

public class MessageQuery extends ODataEntityQuery<Message> implements Executable<Message> {

    public MessageQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Message.class);

    }
}
