
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.core.*;


import com.microsoft.exchange.services.odata.model.Folder;



import com.microsoft.exchange.services.odata.model.Message;



public class Folder extends Entity {

	@SerializedName("ParentFolderId")
	@Expose
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}

	@SerializedName("DisplayName")
	@Expose
	private String displayName;

	public String getDisplayName() {
		 return displayName; 
	}

	public void setDisplayName(String value) { 
		displayName = value; 
	}

	@SerializedName("ClassName")
	@Expose
	private String className;

	public String getClassName() {
		 return className; 
	}

	public void setClassName(String value) { 
		className = value; 
	}

	@SerializedName("TotalCount")
	@Expose
	private Integer totalCount;

	public Integer getTotalCount() {
		 return totalCount; 
	}

	public void setTotalCount(Integer value) { 
		totalCount = value; 
	}

	@SerializedName("ChildFolderCount")
	@Expose
	private Integer childFolderCount;

	public Integer getChildFolderCount() {
		 return childFolderCount; 
	}

	public void setChildFolderCount(Integer value) { 
		childFolderCount = value; 
	}

	@SerializedName("UnreadItemCount")
	@Expose
	private Integer unreadItemCount;

	public Integer getUnreadItemCount() {
		 return unreadItemCount; 
	}

	public void setUnreadItemCount(Integer value) { 
		unreadItemCount = value; 
	}

	@SerializedName("ChildFolders")
	@Expose
	private java.util.List<Folder> childFolders;

	public java.util.List<Folder> getChildFolders() {
		 return childFolders; 
	}

	public void setChildFolders(java.util.List<Folder> value) { 
		childFolders = value; 
	}

	@SerializedName("Messages")
	@Expose
	private java.util.List<Message> messages;

	public java.util.List<Message> getMessages() {
		 return messages; 
	}

	public void setMessages(java.util.List<Message> value) { 
		messages = value; 
	}

	public FolderActions getOperations(){
		return new FolderActions(this.getId());
	}

}