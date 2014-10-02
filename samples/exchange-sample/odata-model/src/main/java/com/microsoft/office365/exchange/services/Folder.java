/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class Folder extends Entity {
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private String displayName;

	public String getDisplayName() {
		 return displayName; 
	}

	public void setDisplayName(String value) { 
		displayName = value; 
	}
	private String className;

	public String getClassName() {
		 return className; 
	}

	public void setClassName(String value) { 
		className = value; 
	}
	private Integer totalCount;

	public Integer getTotalCount() {
		 return totalCount; 
	}

	public void setTotalCount(Integer value) { 
		totalCount = value; 
	}
	private Integer childFolderCount;

	public Integer getChildFolderCount() {
		 return childFolderCount; 
	}

	public void setChildFolderCount(Integer value) { 
		childFolderCount = value; 
	}
	private Integer unreadItemCount;

	public Integer getUnreadItemCount() {
		 return unreadItemCount; 
	}

	public void setUnreadItemCount(Integer value) { 
		unreadItemCount = value; 
	}
}