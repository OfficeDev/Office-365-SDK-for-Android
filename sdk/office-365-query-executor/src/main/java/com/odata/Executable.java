package com.odata;

import com.google.common.util.concurrent.ListenableFuture;

public interface Executable<T> {
    public ListenableFuture<T> execute();
}
