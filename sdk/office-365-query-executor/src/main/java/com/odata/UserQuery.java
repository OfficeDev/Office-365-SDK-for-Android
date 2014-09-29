package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.model.Message;
import com.model.User;



public class UserQuery extends ODataEntityQuery<User> implements Executable<User> {

    public UserQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, User.class);
    }

    public ODataCollection<Message, MessageQuery> getMessages() {
        return new ODataCollection<Message, MessageQuery>("Messages", this, Message.class);
    }

    public FolderQuery getInbox() {
        return new FolderQuery("Inbox",this);
    }
}
