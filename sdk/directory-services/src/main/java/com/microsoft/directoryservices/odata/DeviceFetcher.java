/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.directoryservices.*; 
import com.microsoft.directoryservices.*;       

/**
 * The type  DeviceFetcher.
 */
public class DeviceFetcher extends ODataEntityFetcher<Device,DeviceOperations> 
                                     implements Readable<Device> {

     /**
     * Instantiates a new DeviceFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public DeviceFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Device.class, DeviceOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public DeviceFetcher addParameter(String name, Object value) {
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
    public DeviceFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
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
     * Gets registered owner.
     *
     * @return the registered owner
     */
    public DirectoryObjectFetcher getregisteredOwner(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredOwners", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
    }
     /**
     * Gets registered users.
     *
     * @return the registered users
     */
    public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getregisteredUsers() {
        return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredUsers", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }

    /**
     * Gets registered user.
     *
     * @return the registered user
     */
    public DirectoryObjectFetcher getregisteredUser(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("registeredUsers", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
    }
}