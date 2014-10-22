/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;		

/**
 * The type  ServicePrincipalFetcher.
 */
public class ServicePrincipalFetcher extends ODataEntityFetcher<ServicePrincipal,ServicePrincipalOperations> implements Readable<ServicePrincipal> {

     /**
     * Instantiates a new ServicePrincipalFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public ServicePrincipalFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, ServicePrincipal.class,ServicePrincipalOperations.class);
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
     * Gets app role assignments.
     *
     * @return the app role assignments
     */
	public ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher, AppRoleAssignmentCollectionOperations> getappRoleAssignments() {
		return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignments", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class);
    }
     /**
     * Gets oauth2permission grants.
     *
     * @return the oauth2permission grants
     */
	public ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher, OAuth2PermissionGrantCollectionOperations> getoauth2PermissionGrants() {
		return new ODataCollectionFetcher<OAuth2PermissionGrant, OAuth2PermissionGrantFetcher,OAuth2PermissionGrantCollectionOperations>("oauth2PermissionGrants", this, OAuth2PermissionGrant.class,OAuth2PermissionGrantCollectionOperations.class);
    }
}