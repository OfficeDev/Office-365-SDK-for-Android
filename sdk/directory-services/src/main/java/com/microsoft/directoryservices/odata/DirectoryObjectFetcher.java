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
 * The type  DirectoryObjectFetcher.
 */
public class DirectoryObjectFetcher extends ODataEntityFetcher<DirectoryObject,DirectoryObjectOperations> implements Readable<DirectoryObject> {

     /**
     * Instantiates a new DirectoryObjectFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
	 public DirectoryObjectFetcher(String urlComponent, ODataExecutable parent) {
		super(urlComponent, parent, DirectoryObject.class,DirectoryObjectOperations.class);
    }
	 /**
     * Gets createdonbehalfof.
     *
     * @return the created on behalf of
     */
	public DirectoryObjectFetcher getcreatedOnBehalfOf() {
		return new DirectoryObjectFetcher("createdOnBehalfOf", this);
    }
     /**
     * Gets created objects.
     *
     * @return the created objects
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getcreatedObjects() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("createdObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
	 /**
     * Gets manager.
     *
     * @return the manager
     */
	public DirectoryObjectFetcher getmanager() {
		return new DirectoryObjectFetcher("manager", this);
    }
     /**
     * Gets direct reports.
     *
     * @return the direct reports
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getdirectReports() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("directReports", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
     /**
     * Gets members.
     *
     * @return the members
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getmembers() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("members", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
     /**
     * Gets member of.
     *
     * @return the member of
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getmemberOf() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("memberOf", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
     /**
     * Gets owners.
     *
     * @return the owners
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getowners() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("owners", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
     /**
     * Gets owned objects.
     *
     * @return the owned objects
     */
	public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getownedObjects() {
		return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("ownedObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }
}