/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

public class DriveOperations extends ODataOperations {

	 public DriveOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

	public DriveOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
}