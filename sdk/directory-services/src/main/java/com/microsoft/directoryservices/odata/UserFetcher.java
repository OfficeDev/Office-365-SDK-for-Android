/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;       

/**
 * The type  UserFetcher.
 */
public class UserFetcher extends ODataEntityFetcher<User,UserOperations> 
                                     implements Readable<User> {

     /**
     * Instantiates a new UserFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public UserFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, User.class, UserOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public UserFetcher addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public UserFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	     /**
     * Gets app role assignments.
     *
     * @return the app role assignments
     */
    public ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher, AppRoleAssignmentCollectionOperations> getappRoleAssignments() {
        return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignments", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class);
    }

    /**
     * Gets app role assignment.
     *
     * @return the app role assignment
     */
    public AppRoleAssignmentFetcher getappRoleAssignment(String id){
         return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignments", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class).getById(id);
    }
     /**
     * Gets oauth2permission grants.
     *
     * @return the oauth2permission grants
     */
    public ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher, OAuth2PermissionGrantCollectionOperations> getoauth2PermissionGrants() {
        return new ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher,OAuth2PermissionGrantCollectionOperations>("oauth2PermissionGrants", this, OAuth2PermissionGrant.class,OAuth2PermissionGrantCollectionOperations.class);
    }

    /**
     * Gets oauth2permission grants.
     *
     * @return the oauth2permission grants
     */
    public OAuth2PermissionGrantFetcher getoauth2PermissionGrants(String id){
         return new ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher,OAuth2PermissionGrantCollectionOperations>("oauth2PermissionGrants", this, OAuth2PermissionGrant.class,OAuth2PermissionGrantCollectionOperations.class).getById(id);
    }
     /**
     * Gets owned devices.
     *
     * @return the owned devices
     */
    public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getownedDevices() {
        return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("ownedDevices", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }

    /**
     * Gets owned device.
     *
     * @return the owned device
     */
    public DirectoryObjectFetcher getownedDevice(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("ownedDevices", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
    }
     /**
     * Gets registered devices.
     *
     * @return the registered devices
     */
    public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getregisteredDevices() {
        return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredDevices", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }

    /**
     * Gets registered device.
     *
     * @return the registered device
     */
    public DirectoryObjectFetcher getregisteredDevice(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredDevices", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
    }
}