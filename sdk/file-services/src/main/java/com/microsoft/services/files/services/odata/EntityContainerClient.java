/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

import com.microsoft.services.odata.interfaces.*;

public class EntityContainerClient extends BaseODataContainer {

    public EntityContainerClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

    public DriveFetcher getdrive() {
        return new DriveFetcher("drive", this);
    }
    public CurrentUserRequestContextFetcher getme() {
        return new CurrentUserRequestContextFetcher("me", this);
    }
    public ItemFetcher getfiles() {
        return new ItemFetcher("files", this);
    }
}