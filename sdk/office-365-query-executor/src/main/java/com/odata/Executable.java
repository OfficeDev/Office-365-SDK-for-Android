package com.odata;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Created by PabloZaiden on 26/09/14.
 */
public interface Executable<T> {
    public ListenableFuture<T> execute();
}
