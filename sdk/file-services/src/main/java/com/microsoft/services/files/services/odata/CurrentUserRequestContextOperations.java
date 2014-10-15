/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services.odata;

public class CurrentUserRequestContextOperations extends ODataOperations {

	 public CurrentUserRequestContextOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

	public CurrentUserRequestContextOperations addParameter(String name, Object value) {
		addCustomParameter(name, value);
		return this;
	}
}