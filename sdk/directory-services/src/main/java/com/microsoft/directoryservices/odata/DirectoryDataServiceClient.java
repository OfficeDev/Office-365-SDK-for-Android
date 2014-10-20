/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.directoryservices.*;
import com.microsoft.directoryservices.odata.*;

/**
 * The type DirectoryDataServiceClient.
 */
public class DirectoryDataServiceClient extends BaseODataContainer {

	 /**
     * Instantiates a new DirectoryDataServiceClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public DirectoryDataServiceClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }
	 /**
     * Gets DirectoryObject.
     *
     * @return the DirectoryObject
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getdirectoryObjects() {
        return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("directoryObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
	 /**
     * Gets OAuth2PermissionGrant.
     *
     * @return the OAuth2PermissionGrant
     */
	public ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher, OAuth2PermissionGrantCollectionOperations> getoauth2PermissionGrants() {
        return new ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher,OAuth2PermissionGrantCollectionOperations>("oauth2PermissionGrants", this, OAuth2PermissionGrant.class,OAuth2PermissionGrantCollectionOperations.class);
    }
	 /**
     * Gets SubscribedSku.
     *
     * @return the SubscribedSku
     */
	public ODataCollectionFetcher<SubscribedSku, SubscribedSkuFetcher, SubscribedSkuCollectionOperations> getsubscribedSkus() {
        return new ODataCollectionFetcher<SubscribedSku, SubscribedSkuFetcher,SubscribedSkuCollectionOperations>("subscribedSkus", this, SubscribedSku.class,SubscribedSkuCollectionOperations.class);
    }
	 /**
     * Gets DirectoryObject.
     *
     * @return the DirectoryObject
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getdeletedDirectoryObjects() {
        return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("deletedDirectoryObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
}