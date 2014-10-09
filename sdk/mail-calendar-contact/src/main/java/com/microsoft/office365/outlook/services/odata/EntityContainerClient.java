/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.microsoft.office365.odata.interfaces.*;

public class EntityContainerClient extends BaseODataContainer {

    public EntityContainerClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

    public UserFetcher getMe() {
        return new UserFetcher("Me", this);
    }
    public UserFetcher getUsers() {
        return new UserFetcher("Users", this);
    }
}