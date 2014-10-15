/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.outlook.services.*;

public class EntityFetcher extends ODataEntityFetcher<Entity,EntityOperations> implements Readable<Entity> {

	 public EntityFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Entity.class,EntityOperations.class);
    }

	public EntityFetcher addParameter(String name, Object value) {
	    addCustomParameter(name, value);
		return this;
	}
}