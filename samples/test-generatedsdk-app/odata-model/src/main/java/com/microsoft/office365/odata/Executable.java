package com.microsoft.office365.odata;

import com.google.common.util.concurrent.ListenableFuture;

public interface Executable<T> {
    public ListenableFuture<T> execute();
}
