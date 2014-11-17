/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.Readable;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*; 
import com.microsoft.outlookservices.*;       

/**
 * The type  EntityFetcher.
 */
public class EntityFetcher extends ODataEntityFetcher<Entity,EntityOperations> 
                                     implements Readable<Entity> {

     /**
     * Instantiates a new EntityFetcher.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
     public EntityFetcher(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent, Entity.class, EntityOperations.class);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the fetcher
     */
    public EntityFetcher addParameter(String name, Object value) {
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
    public EntityFetcher addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }

    
    public UserFetcher asUser(){
        return new UserFetcher(this.urlComponent, this.parent);
    }	   

    public FolderFetcher asFolder(){
        return new FolderFetcher(this.urlComponent, this.parent);
    }	   

    public ItemFetcher asItem(){
        return new ItemFetcher(this.urlComponent, this.parent);
    }	   

    public AttachmentFetcher asAttachment(){
        return new AttachmentFetcher(this.urlComponent, this.parent);
    }	   

    public CalendarFetcher asCalendar(){
        return new CalendarFetcher(this.urlComponent, this.parent);
    }	   

    public CalendarGroupFetcher asCalendarGroup(){
        return new CalendarGroupFetcher(this.urlComponent, this.parent);
    }	   

    public ContactFolderFetcher asContactFolder(){
        return new ContactFolderFetcher(this.urlComponent, this.parent);
    }	   
	}