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
 * The type  ServicePrincipalFetcher.
 */
public class ServicePrincipalFetcher extends ODataEntityFetcher<ServicePrincipal,ServicePrincipalOperations> 
                                     implements Readable<ServicePrincipal> {

     /**
     * Instantiates a new ServicePrincipalFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public ServicePrincipalFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, ServicePrincipal.class, ServicePrincipalOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public ServicePrincipalFetcher addParameter(String name, Object value) {
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
    public ServicePrincipalFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	     /**
     * Gets app role assigned to.
     *
     * @return the app role assigned to
     */
    public ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher, AppRoleAssignmentCollectionOperations> getappRoleAssignedTo() {
        return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignedTo", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class);
    }

    /**
     * Gets app role assigned to.
     *
     * @return the app role assigned to
     */
    public AppRoleAssignmentFetcher getappRoleAssignedTo(String id){
         return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignedTo", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class).getById(id);
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
}