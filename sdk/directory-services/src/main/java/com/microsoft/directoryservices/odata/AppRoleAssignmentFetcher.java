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
 * The type  AppRoleAssignmentFetcher.
 */
public class AppRoleAssignmentFetcher extends ODataEntityFetcher<AppRoleAssignment,AppRoleAssignmentOperations> implements Readable<AppRoleAssignment> {

     /**
     * Instantiates a new AppRoleAssignmentFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public AppRoleAssignmentFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, AppRoleAssignment.class,AppRoleAssignmentOperations.class);
    }

	}