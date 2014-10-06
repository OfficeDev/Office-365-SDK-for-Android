/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.model;

public class Item {
	private IdentitySet createdBy;

	public IdentitySet getcreatedBy() {
		 return createdBy; 
	}

	public void setcreatedBy(IdentitySet value) { 
		createdBy = value; 
	}
	private String eTag;

	public String geteTag() {
		 return eTag; 
	}

	public void seteTag(String value) { 
		eTag = value; 
	}
	private String id;

	public String getid() {
		 return id; 
	}

	public void setid(String value) { 
		id = value; 
	}
	private IdentitySet lastModifiedBy;

	public IdentitySet getlastModifiedBy() {
		 return lastModifiedBy; 
	}

	public void setlastModifiedBy(IdentitySet value) { 
		lastModifiedBy = value; 
	}
	private String name;

	public String getname() {
		 return name; 
	}

	public void setname(String value) { 
		name = value; 
	}
	private ItemReference parentReference;

	public ItemReference getparentReference() {
		 return parentReference; 
	}

	public void setparentReference(ItemReference value) { 
		parentReference = value; 
	}
	private long size;

	public long getsize() {
		 return size; 
	}

	public void setsize(long value) { 
		size = value; 
	}
	private java.util.Calendar dateTimeCreated;

	public java.util.Calendar getdateTimeCreated() {
		 return dateTimeCreated; 
	}

	public void setdateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar dateTimeLastModified;

	public java.util.Calendar getdateTimeLastModified() {
		 return dateTimeLastModified; 
	}

	public void setdateTimeLastModified(java.util.Calendar value) { 
		dateTimeLastModified = value; 
	}
	private String type;

	public String gettype() {
		 return type; 
	}

	public void settype(String value) { 
		type = value; 
	}
	private String webUrl;

	public String getwebUrl() {
		 return webUrl; 
	}

	public void setwebUrl(String value) { 
		webUrl = value; 
	}
}