/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.directoryservices.*;

/**
 * The type DirectoryClient.
 */
public class DirectoryClient extends BaseODataContainer {

     /**
     * Instantiates a new DirectoryClient.
     *
     * @param url the url
     * @param resolver the resolver
     */
    public DirectoryClient(String url, DependencyResolver resolver) {
        super(url, resolver);
    }

	     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public DirectoryClient addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the client
     */
    public DirectoryClient addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
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
     /**
     * Gets User.
     *
     * @return the User
     */
    public ODataCollectionFetcher<User, UserFetcher, UserCollectionOperations> getusers() {
        return new ODataCollectionFetcher<User, UserFetcher,UserCollectionOperations>("users", this, User.class,UserCollectionOperations.class);
    }
     /**
     * Gets Application.
     *
     * @return the Application
     */
    public ODataCollectionFetcher<Application, ApplicationFetcher, ApplicationCollectionOperations> getapplications() {
        return new ODataCollectionFetcher<Application, ApplicationFetcher,ApplicationCollectionOperations>("applications", this, Application.class,ApplicationCollectionOperations.class);
    }
     /**
     * Gets Contact.
     *
     * @return the Contact
     */
    public ODataCollectionFetcher<Contact, ContactFetcher, ContactCollectionOperations> getcontacts() {
        return new ODataCollectionFetcher<Contact, ContactFetcher,ContactCollectionOperations>("contacts", this, Contact.class,ContactCollectionOperations.class);
    }
     /**
     * Gets Group.
     *
     * @return the Group
     */
    public ODataCollectionFetcher<Group, GroupFetcher, GroupCollectionOperations> getgroups() {
        return new ODataCollectionFetcher<Group, GroupFetcher,GroupCollectionOperations>("groups", this, Group.class,GroupCollectionOperations.class);
    }
     /**
     * Gets DirectoryRole.
     *
     * @return the DirectoryRole
     */
    public ODataCollectionFetcher<DirectoryRole, DirectoryRoleFetcher, DirectoryRoleCollectionOperations> getroles() {
        return new ODataCollectionFetcher<DirectoryRole, DirectoryRoleFetcher,DirectoryRoleCollectionOperations>("roles", this, DirectoryRole.class,DirectoryRoleCollectionOperations.class);
    }
     /**
     * Gets ServicePrincipal.
     *
     * @return the ServicePrincipal
     */
    public ODataCollectionFetcher<ServicePrincipal, ServicePrincipalFetcher, ServicePrincipalCollectionOperations> getservicePrincipals() {
        return new ODataCollectionFetcher<ServicePrincipal, ServicePrincipalFetcher,ServicePrincipalCollectionOperations>("servicePrincipals", this, ServicePrincipal.class,ServicePrincipalCollectionOperations.class);
    }
     /**
     * Gets TenantDetail.
     *
     * @return the TenantDetail
     */
    public ODataCollectionFetcher<TenantDetail, TenantDetailFetcher, TenantDetailCollectionOperations> gettenantDetails() {
        return new ODataCollectionFetcher<TenantDetail, TenantDetailFetcher,TenantDetailCollectionOperations>("tenantDetails", this, TenantDetail.class,TenantDetailCollectionOperations.class);
    }
     /**
     * Gets Device.
     *
     * @return the Device
     */
    public ODataCollectionFetcher<Device, DeviceFetcher, DeviceCollectionOperations> getdevices() {
        return new ODataCollectionFetcher<Device, DeviceFetcher,DeviceCollectionOperations>("devices", this, Device.class,DeviceCollectionOperations.class);
    }
}