/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices.odata;

import com.microsoft.fileservices.*;
import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;

/**
 * The type  FileFetcher.
 */
public class FileFetcher extends ODataMediaEntityFetcher<File,FileOperations> 
                                     implements Readable<File> {

     /**
     * Instantiates a new FileFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public FileFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, File.class, FileOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public FileFetcher addParameter(String name, Object value) {
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
    public FileFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

        
}
