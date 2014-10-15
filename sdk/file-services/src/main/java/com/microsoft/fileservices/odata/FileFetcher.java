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
 * The type  FileFetcher.
 */
public class FileFetcher extends ODataEntityFetcher<File,FileOperations> implements Readable<File> {

     /**
     * Instantiates a new FileFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public FileFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, File.class,FileOperations.class);
    }
}