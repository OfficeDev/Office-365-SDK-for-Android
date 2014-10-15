/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Item.
*/
public class Item {
	private IdentitySet createdBy;

	/**
	* Gets the created By.
	*
	* @return the IdentitySet
	*/
	public IdentitySet getcreatedBy() {
		 return createdBy; 
	}

	/**
	* Sets the created By.
	*
	* @param value the IdentitySet
	*/
	public void setcreatedBy(IdentitySet value) { 
		createdBy = value; 
	}
	private String eTag;

	/**
	* Gets the e Tag.
	*
	* @return the String
	*/
	public String geteTag() {
		 return eTag; 
	}

	/**
	* Sets the e Tag.
	*
	* @param value the String
	*/
	public void seteTag(String value) { 
		eTag = value; 
	}
	private String id;

	/**
	* Gets the id.
	*
	* @return the String
	*/
	public String getid() {
		 return id; 
	}

	/**
	* Sets the id.
	*
	* @param value the String
	*/
	public void setid(String value) { 
		id = value; 
	}
	private IdentitySet lastModifiedBy;

	/**
	* Gets the last Modified By.
	*
	* @return the IdentitySet
	*/
	public IdentitySet getlastModifiedBy() {
		 return lastModifiedBy; 
	}

	/**
	* Sets the last Modified By.
	*
	* @param value the IdentitySet
	*/
	public void setlastModifiedBy(IdentitySet value) { 
		lastModifiedBy = value; 
	}
	private String name;

	/**
	* Gets the name.
	*
	* @return the String
	*/
	public String getname() {
		 return name; 
	}

	/**
	* Sets the name.
	*
	* @param value the String
	*/
	public void setname(String value) { 
		name = value; 
	}
	private ItemReference parentReference;

	/**
	* Gets the parent Reference.
	*
	* @return the ItemReference
	*/
	public ItemReference getparentReference() {
		 return parentReference; 
	}

	/**
	* Sets the parent Reference.
	*
	* @param value the ItemReference
	*/
	public void setparentReference(ItemReference value) { 
		parentReference = value; 
	}
	private long size;

	/**
	* Gets the size.
	*
	* @return the long
	*/
	public long getsize() {
		 return size; 
	}

	/**
	* Sets the size.
	*
	* @param value the long
	*/
	public void setsize(long value) { 
		size = value; 
	}
	private java.util.Calendar dateTimeCreated;

	/**
	* Gets the date Time Created.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getdateTimeCreated() {
		 return dateTimeCreated; 
	}

	/**
	* Sets the date Time Created.
	*
	* @param value the java.util.Calendar
	*/
	public void setdateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar dateTimeLastModified;

	/**
	* Gets the date Time Last Modified.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getdateTimeLastModified() {
		 return dateTimeLastModified; 
	}

	/**
	* Sets the date Time Last Modified.
	*
	* @param value the java.util.Calendar
	*/
	public void setdateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
	private String type;

	/**
	* Gets the type.
	*
	* @return the String
	*/
	public String gettype() {
		 return type; 
	}

	/**
	* Sets the type.
	*
	* @param value the String
	*/
	public void settype(String value) { 
		type = value; 
	}
	private String webUrl;

	/**
	* Gets the web Url.
	*
	* @return the String
	*/
	public String getwebUrl() {
		 return webUrl; 
	}

	/**
	* Sets the web Url.
	*
	* @param value the String
	*/
	public void setwebUrl(String value) { 
		webUrl = value; 
	}
}