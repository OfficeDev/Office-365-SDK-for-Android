/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class EntityOperations extends BaseEntityOperations<Entity> implements Executable<Entity> {

	 public EntityOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Entity.class);
    }
}