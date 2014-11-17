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
 * The type  DirectoryObjectFetcher.
 */
public class DirectoryObjectFetcher extends ODataEntityFetcher<DirectoryObject,DirectoryObjectOperations> 
                                     implements Readable<DirectoryObject> {

     /**
     * Instantiates a new DirectoryObjectFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public DirectoryObjectFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, DirectoryObject.class, DirectoryObjectOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public DirectoryObjectFetcher addParameter(String name, Object value) {
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
    public DirectoryObjectFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    public ApplicationFetcher asApplication(){
        return new ApplicationFetcher(this.urlComponent, this.parent);
    }	   

    public UserFetcher asUser(){
        return new UserFetcher(this.urlComponent, this.parent);
    }	   

    public ExtensionPropertyFetcher asExtensionProperty(){
        return new ExtensionPropertyFetcher(this.urlComponent, this.parent);
    }	   

    public ContactFetcher asContact(){
        return new ContactFetcher(this.urlComponent, this.parent);
    }	   

    public DeviceFetcher asDevice(){
        return new DeviceFetcher(this.urlComponent, this.parent);
    }	   

    public DeviceConfigurationFetcher asDeviceConfiguration(){
        return new DeviceConfigurationFetcher(this.urlComponent, this.parent);
    }	   

    public DirectoryLinkChangeFetcher asDirectoryLinkChange(){
        return new DirectoryLinkChangeFetcher(this.urlComponent, this.parent);
    }	   

    public AppRoleAssignmentFetcher asAppRoleAssignment(){
        return new AppRoleAssignmentFetcher(this.urlComponent, this.parent);
    }	   

    public GroupFetcher asGroup(){
        return new GroupFetcher(this.urlComponent, this.parent);
    }	   

    public DirectoryRoleFetcher asDirectoryRole(){
        return new DirectoryRoleFetcher(this.urlComponent, this.parent);
    }	   

    public DirectoryRoleTemplateFetcher asDirectoryRoleTemplate(){
        return new DirectoryRoleTemplateFetcher(this.urlComponent, this.parent);
    }	   

    public ServicePrincipalFetcher asServicePrincipal(){
        return new ServicePrincipalFetcher(this.urlComponent, this.parent);
    }	   

    public TenantDetailFetcher asTenantDetail(){
        return new TenantDetailFetcher(this.urlComponent, this.parent);
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
     * Gets created object.
     *
     * @return the created object
     */
    public DirectoryObjectFetcher getcreatedObject(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("createdObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
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
     * Gets direct report.
     *
     * @return the direct report
     */
    public DirectoryObjectFetcher getdirectReport(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("directReports", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
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
     * Gets member.
     *
     * @return the member
     */
    public DirectoryObjectFetcher getmember(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("members", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
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
     * Gets member of.
     *
     * @return the member of
     */
    public DirectoryObjectFetcher getmemberOf(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("memberOf", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
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
     * Gets owner.
     *
     * @return the owner
     */
    public DirectoryObjectFetcher getowner(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("owners", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
    }
     /**
     * Gets owned objects.
     *
     * @return the owned objects
     */
    public ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher, DirectoryObjectCollectionOperations> getownedObjects() {
        return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("ownedObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class);
    }

    /**
     * Gets owned object.
     *
     * @return the owned object
     */
    public DirectoryObjectFetcher getownedObject(String id){
         return new ODataCollectionFetcher<DirectoryObject, DirectoryObjectFetcher,DirectoryObjectCollectionOperations>("ownedObjects", this, DirectoryObject.class,DirectoryObjectCollectionOperations.class).getById(id);
    }
}