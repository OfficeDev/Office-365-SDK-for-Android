/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.files.services.*;

public class FileFetcher extends ODataEntityFetcher<File,FileOperations> implements Readable<File> {

	 public FileFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, File.class,FileOperations.class);
    }

	public FileFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
}