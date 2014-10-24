/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Directory Link Change.
*/
public class DirectoryLinkChange extends DirectoryObject {

	public DirectoryLinkChange(){
		setODataType("#Microsoft.DirectoryServices.DirectoryLinkChange");
	}

	private String associationType;

	/**
	* Gets the association Type.
	*
	* @return the String
	*/
	public String getassociationType() {
		return this.associationType; 
	}

	/**
	* Sets the association Type.
	*
	* @param value the String
	*/
	public void setassociationType(String value) { 
		this.associationType = value; 
	}
	private String sourceObjectId;

	/**
	* Gets the source Object Id.
	*
	* @return the String
	*/
	public String getsourceObjectId() {
		return this.sourceObjectId; 
	}

	/**
	* Sets the source Object Id.
	*
	* @param value the String
	*/
	public void setsourceObjectId(String value) { 
		this.sourceObjectId = value; 
	}
	private String sourceObjectType;

	/**
	* Gets the source Object Type.
	*
	* @return the String
	*/
	public String getsourceObjectType() {
		return this.sourceObjectType; 
	}

	/**
	* Sets the source Object Type.
	*
	* @param value the String
	*/
	public void setsourceObjectType(String value) { 
		this.sourceObjectType = value; 
	}
	private String sourceObjectUri;

	/**
	* Gets the source Object Uri.
	*
	* @return the String
	*/
	public String getsourceObjectUri() {
		return this.sourceObjectUri; 
	}

	/**
	* Sets the source Object Uri.
	*
	* @param value the String
	*/
	public void setsourceObjectUri(String value) { 
		this.sourceObjectUri = value; 
	}
	private String targetObjectId;

	/**
	* Gets the target Object Id.
	*
	* @return the String
	*/
	public String gettargetObjectId() {
		return this.targetObjectId; 
	}

	/**
	* Sets the target Object Id.
	*
	* @param value the String
	*/
	public void settargetObjectId(String value) { 
		this.targetObjectId = value; 
	}
	private String targetObjectType;

	/**
	* Gets the target Object Type.
	*
	* @return the String
	*/
	public String gettargetObjectType() {
		return this.targetObjectType; 
	}

	/**
	* Sets the target Object Type.
	*
	* @param value the String
	*/
	public void settargetObjectType(String value) { 
		this.targetObjectType = value; 
	}
	private String targetObjectUri;

	/**
	* Gets the target Object Uri.
	*
	* @return the String
	*/
	public String gettargetObjectUri() {
		return this.targetObjectUri; 
	}

	/**
	* Sets the target Object Uri.
	*
	* @param value the String
	*/
	public void settargetObjectUri(String value) { 
		this.targetObjectUri = value; 
	}
}