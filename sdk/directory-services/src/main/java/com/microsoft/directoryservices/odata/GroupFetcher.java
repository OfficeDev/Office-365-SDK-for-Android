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
 * The type  GroupFetcher.
 */
public class GroupFetcher extends ODataEntityFetcher<Group,GroupOperations> 
                                     implements Readable<Group> {

     /**
     * Instantiates a new GroupFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public GroupFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Group.class, GroupOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public GroupFetcher addParameter(String name, Object value) {
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
    public GroupFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    	     /**
     * Gets app role assignments.
     *
     * @return the app role assignments
     */
    public ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher, AppRoleAssignmentCollectionOperations> getappRoleAssignments() {
        return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignments", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class);
    }

    /**
     * Gets app role assignment.
     *
     * @return the app role assignment
     */
    public AppRoleAssignmentFetcher getappRoleAssignment(String id){
         return new ODataCollectionFetcher<AppRoleAssignment, AppRoleAssignmentFetcher,AppRoleAssignmentCollectionOperations>("appRoleAssignments", this, AppRoleAssignment.class,AppRoleAssignmentCollectionOperations.class).getById(id);
    }
}