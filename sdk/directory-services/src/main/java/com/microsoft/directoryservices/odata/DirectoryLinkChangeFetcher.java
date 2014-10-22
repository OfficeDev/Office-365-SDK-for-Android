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
 * The type  DirectoryLinkChangeFetcher.
 */
public class DirectoryLinkChangeFetcher extends ODataEntityFetcher<DirectoryLinkChange,DirectoryLinkChangeOperations> implements Readable<DirectoryLinkChange> {

     /**
     * Instantiates a new DirectoryLinkChangeFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public DirectoryLinkChangeFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, DirectoryLinkChange.class,DirectoryLinkChangeOperations.class);
    }

	}