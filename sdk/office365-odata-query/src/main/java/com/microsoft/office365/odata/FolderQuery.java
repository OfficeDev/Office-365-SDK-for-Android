package com.microsoft.office365.odata;

import com.microsoft.office365.odata.model.Folder;
import com.microsoft.office365.odata.model.Message;

public class FolderQuery extends ODataEntityQuery<Folder> implements Executable<Folder> {

    public FolderQuery(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Folder.class);
    }

    public ODataCollection<Message, MessageQuery> getMessages() {
        return new ODataCollection<Message, MessageQuery>("Messages", this, Message.class);
    }

    public ODataCollection<Folder, FolderQuery> getChildFolders(){
        return new ODataCollection<Folder, FolderQuery>("ChildFolders",this, Folder.class);
    }
}
