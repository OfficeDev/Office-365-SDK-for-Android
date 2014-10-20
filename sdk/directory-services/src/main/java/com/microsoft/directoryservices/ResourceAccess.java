/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Resource Access.
*/
public class ResourceAccess {

	private java.util.UUID id;

	/**
	* Gets the id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getid() {
		return this.id; 
	}

	/**
	* Sets the id.
	*
	* @param value the java.util.UUID
	*/
	public void setid(java.util.UUID value) { 
		id = value; 
	}

	private String type;

	/**
	* Gets the type.
	*
	* @return the String
	*/
	public String gettype() {
		return this.type; 
	}

	/**
	* Sets the type.
	*
	* @param value the String
	*/
	public void settype(String value) { 
		type = value; 
	}
}