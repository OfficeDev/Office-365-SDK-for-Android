/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;

/**
 * The type  EntityFetcher.
 */
public class EntityFetcher extends ODataEntityFetcher<Entity,EntityOperations> implements Readable<Entity> {

     /**
     * Instantiates a new EntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public EntityFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Entity.class,EntityOperations.class);
    }
}