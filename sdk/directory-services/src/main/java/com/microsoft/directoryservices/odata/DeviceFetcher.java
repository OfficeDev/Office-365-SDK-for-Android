/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;		

/**
 * The type  DeviceFetcher.
 */
public class DeviceFetcher extends ODataEntityFetcher<Device,DeviceOperations> implements Readable<Device> {

     /**
     * Instantiates a new DeviceFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public DeviceFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, Device.class,DeviceOperations.class);
    }

	     /**
     * Gets registered owners.
     *
     * @return the registered owners
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getregisteredOwners() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredOwners", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
     /**
     * Gets registered users.
     *
     * @return the registered users
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getregisteredUsers() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredUsers", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
}