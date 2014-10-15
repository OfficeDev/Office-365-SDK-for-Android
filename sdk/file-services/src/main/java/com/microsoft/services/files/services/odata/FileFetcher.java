/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

import com.microsoft.services.files.services.*;

public class FileFetcher extends ODataEntityFetcher<File,FileOperations> implements Readable<File> {

	 public FileFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, File.class,FileOperations.class);
    }

	public FileFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
}