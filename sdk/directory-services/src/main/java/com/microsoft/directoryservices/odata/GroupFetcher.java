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
 * The type  GroupFetcher.
 */
public class GroupFetcher extends ODataEntityFetcher<Group,GroupOperations> implements Readable<Group> {

     /**
     * Instantiates a new GroupFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public GroupFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Group.class,GroupOperations.class);
    }

	     /**
     * Gets app role assignments.
     *
     * @return the app role assignments
     */
	public ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher, AppRoleAssignmentCollectionOperations> getappRoleAssignments() {
		return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignments", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class);
    }
}