/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services;

/**
 * The type Item.
*/
public class Item {
	private IdentitySet createdBy;


     /**
     * Gets the created by.		
     *
     * @return the created by
     */
	public IdentitySet getcreatedBy() {
		 return createdBy; 
	}
	
     /**
     * Sets the created by.		
     *
     * @param value the value
     */
	public void setcreatedBy(IdentitySet value) { 
		createdBy = value; 
	}
	private String eTag;


     /**
     * Gets the e tag.		
     *
     * @return the e tag
     */
	public String geteTag() {
		 return eTag; 
	}
	
     /**
     * Sets the e tag.		
     *
     * @param value the value
     */
	public void seteTag(String value) { 
		eTag = value; 
	}
	private String id;


     /**
     * Gets the id.		
     *
     * @return the id
     */
	public String getid() {
		 return id; 
	}
	
     /**
     * Sets the id.		
     *
     * @param value the value
     */
	public void setid(String value) { 
		id = value; 
	}
	private IdentitySet lastModifiedBy;


     /**
     * Gets the last modified by.		
     *
     * @return the last modified by
     */
	public IdentitySet getlastModifiedBy() {
		 return lastModifiedBy; 
	}
	
     /**
     * Sets the last modified by.		
     *
     * @param value the value
     */
	public void setlastModifiedBy(IdentitySet value) { 
		lastModifiedBy = value; 
	}
	private String name;


     /**
     * Gets the name.		
     *
     * @return the name
     */
	public String getname() {
		 return name; 
	}
	
     /**
     * Sets the name.		
     *
     * @param value the value
     */
	public void setname(String value) { 
		name = value; 
	}
	private ItemReference parentReference;


     /**
     * Gets the parent reference.		
     *
     * @return the parent reference
     */
	public ItemReference getparentReference() {
		 return parentReference; 
	}
	
     /**
     * Sets the parent reference.		
     *
     * @param value the value
     */
	public void setparentReference(ItemReference value) { 
		parentReference = value; 
	}
	private long size;


     /**
     * Gets the size.		
     *
     * @return the size
     */
	public long getsize() {
		 return size; 
	}
	
     /**
     * Sets the size.		
     *
     * @param value the value
     */
	public void setsize(long value) { 
		size = value; 
	}
	private java.util.Calendar dateTimeCreated;


     /**
     * Gets the date time created.		
     *
     * @return the date time created
     */
	public java.util.Calendar getdateTimeCreated() {
		 return dateTimeCreated; 
	}
	
     /**
     * Sets the date time created.		
     *
     * @param value the value
     */
	public void setdateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar dateTimeLastModified;


     /**
     * Gets the date time last modified.		
     *
     * @return the date time last modified
     */
	public java.util.Calendar getdateTimeLastModified() {
		 return dateTimeLastModified; 
	}
	
     /**
     * Sets the date time last modified.		
     *
     * @param value the value
     */
	public void setdateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
	private String type;


     /**
     * Gets the type.		
     *
     * @return the type
     */
	public String gettype() {
		 return type; 
	}
	
     /**
     * Sets the type.		
     *
     * @param value the value
     */
	public void settype(String value) { 
		type = value; 
	}
	private String webUrl;


     /**
     * Gets the web url.		
     *
     * @return the web url
     */
	public String getwebUrl() {
		 return webUrl; 
	}
	
     /**
     * Sets the web url.		
     *
     * @param value the value
     */
	public void setwebUrl(String value) { 
		webUrl = value; 
	}
}