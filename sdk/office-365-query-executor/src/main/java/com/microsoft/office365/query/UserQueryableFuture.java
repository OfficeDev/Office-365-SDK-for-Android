package com.microsoft.office365.query;

import java.util.List;

/**
 * Created by marcote on 9/25/14.
 */

public class UserQueryableFuture extends QueryableFuture<User>{

    public QueryableFuture<List<Message>> getMessages(){
        return null;
    }

    @Override
    String getEntityName() {
        return "User";
    }
}
