/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.fileservices.*; 
import com.microsoft.fileservices.*;       

/**
 * The type  DriveFetcher.
 */
public class DriveFetcher extends ODataEntityFetcher<Drive,DriveOperations> 
                                     implements Readable<Drive> {

     /**
     * Instantiates a new DriveFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public DriveFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Drive.class, DriveOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public DriveFetcher addParameter(String name, Object value) {
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
    public DriveFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	}