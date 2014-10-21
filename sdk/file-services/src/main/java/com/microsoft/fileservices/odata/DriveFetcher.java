/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*; 
import com.microsoft.fileservices.*;		

/**
 * The type  DriveFetcher.
 */
public class DriveFetcher extends ODataEntityFetcher<Drive,DriveOperations> implements Readable<Drive> {

     /**
     * Instantiates a new DriveFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public DriveFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Drive.class,DriveOperations.class);
    }

	}