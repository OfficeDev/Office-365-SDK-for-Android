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
 * The type  DirectoryRoleFetcher.
 */
public class DirectoryRoleFetcher extends ODataEntityFetcher<DirectoryRole,DirectoryRoleOperations> implements Readable<DirectoryRole> {

     /**
     * Instantiates a new DirectoryRoleFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public DirectoryRoleFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, DirectoryRole.class,DirectoryRoleOperations.class);
    }
}