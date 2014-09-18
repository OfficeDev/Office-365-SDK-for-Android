/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.viewmodel;

// TODO: Auto-generated Javadoc
/**
 * The Class FileSaveItem.
 */
public class FileItem {
	private String mName;
	private String mEndpoint;
	private String mResourceId;
	private String mId;
	private byte[] mContent;

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getEndpoint() {
		return mEndpoint;
	}

	public void setEndpoint(String endpoint) {
		mEndpoint = endpoint;
	}

	public String getResourceId() {
		return mResourceId;
	}

	public void setResourceId(String resourceId) {
		mResourceId = resourceId;
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		mId = id;
	}

	public byte[] getContent() {
		return mContent;
	}

	public void setContent(byte[] content) {
		mContent = content;
	}
}
