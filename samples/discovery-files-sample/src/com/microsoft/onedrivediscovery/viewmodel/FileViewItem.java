/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.viewmodel;

// TODO: Auto-generated Javadoc
/**
 * The Class FileViewItem.
 */
public class FileViewItem {
	private String mId;
	private String mName;
	private String mCreatedOn;
	private boolean mSelectable;
	
	public String getId(){
		return mId;
	}
	
	public void setId(String id){
		 mId = id;
	}
	
	public String getName(){
		return mName;
	}
	
	public void setName(String name){
		 mName = name;
	}
	
	public String getCreatedOn(){
		return mCreatedOn;
	}
	
	public void setCreatedOn(String createdOn){
		mCreatedOn = createdOn;
	}
	
	public boolean getSelectable(){
		return mSelectable;
	}
	
	public void setSelectable(boolean selectable){
		 mSelectable = selectable;
	}
}
