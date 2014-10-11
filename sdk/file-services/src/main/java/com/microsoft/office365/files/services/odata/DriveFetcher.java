/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.files.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.files.services.*;

public class DriveFetcher extends ODataEntityFetcher<Drive,DriveOperations> implements Readable<Drive> {

	 public DriveFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Drive.class,DriveOperations.class);
    }

	public DriveFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
}