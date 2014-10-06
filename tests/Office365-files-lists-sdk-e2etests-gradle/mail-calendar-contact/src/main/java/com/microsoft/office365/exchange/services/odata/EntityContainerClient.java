/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services.odata;

import com.microsoft.office365.odata.interfaces.DependencyResolver;
import com.microsoft.office365.exchange.services.model.*;

public class EntityContainerClient extends BaseODataContainer {

    public EntityContainerClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }
	
    public UserFetcher getMe() {
        return new UserFetcher("Me", this);
    }
	
	public ODataCollectionFetcher<User, UserFetcher, UserCollectionOperations> getUsers() {
        return new ODataCollectionFetcher<User, UserFetcher,UserCollectionOperations>("Users", this, User.class,UserCollectionOperations.class);
    }
}