/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.viewmodel;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceViewItem.
 */
public class ServiceViewItem {
	
	private String mName;
	private String mEndpointUri;
	private String mResourceId;
	private Boolean mSelectable;
	private String mCapability;

	public String getName(){
		return mName;
	}
	
	public void setName(String name){
		 mName = name;
	}
	
	public String getEndpointUri(){
		return mEndpointUri;
	}
	
	public void setEndpointUri(String endpointUri){
		mEndpointUri = endpointUri;
	}
	
	public String getResourceId(){
		return mResourceId;
	}
	
	public void setResourceId(String resourceId){
		mResourceId = resourceId;
	}
	
	public boolean getSelectable(){
		return mSelectable;
	}
	
	public void setSelectable(boolean selectable){
		 mSelectable = selectable;
	}
	
	public String getCapability(){
		return mCapability;
	}
	
	public void setCapability(String capability){
		mCapability = capability;
	}	
}
