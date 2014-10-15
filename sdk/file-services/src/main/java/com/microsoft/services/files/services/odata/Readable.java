/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

import com.google.common.util.concurrent.ListenableFuture;

public interface Readable<T> {
    public ListenableFuture<T> read();
}
