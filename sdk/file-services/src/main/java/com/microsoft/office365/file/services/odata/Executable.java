/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.odata;

import com.google.common.util.concurrent.ListenableFuture;

public interface Executable<T> {
    public ListenableFuture<T> execute();
}
