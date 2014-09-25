package com.microsoft.office365.query;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import java.io.Console;

/**
 * Created by marcote on 9/25/14.
 */
public class HttpClient {
    public ListenableFuture<String> executeRequest(String s) {
        Logger.Log(s);
        SettableFuture<String> future = SettableFuture.create();
        future.set("");
        return future;
    }
}
