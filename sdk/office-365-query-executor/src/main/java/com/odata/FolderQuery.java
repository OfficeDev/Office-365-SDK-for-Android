package com.odata;

import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.Executable;
import com.model.Folder;
import com.model.Message;

public class FolderQuery extends ODataEntityQuery<Folder> implements Executable<Folder> {

    public FolderQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

    @Override
    public ListenableFuture<Folder> execute() {
        return executeInternal(Folder.class);
    }

    public Queryable<Message, MessageQuery> getMessages() {
        return new Queryable<Message, MessageQuery>("Messages", this, Message.class);
    }

    public Queryable<Folder, FolderQuery> getChildFolders(){
        return new Queryable<Folder, FolderQuery>("ChildFolders",this, Folder.class);
    }
}
