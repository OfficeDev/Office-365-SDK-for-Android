/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type App Role Assignment.
*/
public class AppRoleAssignment extends DirectoryObject {
	private java.util.Calendar creationTimestamp;

	/**
	* Gets the creation Timestamp.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getcreationTimestamp() {
		return this.creationTimestamp; 
	}

	/**
	* Sets the creation Timestamp.
	*
	* @param value the java.util.Calendar
	*/
	public void setcreationTimestamp(java.util.Calendar value) { 
		creationTimestamp = value; 
	}
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
	private String principalDisplayName;

	/**
	* Gets the principal Display Name.
	*
	* @return the String
	*/
	public String getprincipalDisplayName() {
		return this.principalDisplayName; 
	}

	/**
	* Sets the principal Display Name.
	*
	* @param value the String
	*/
	public void setprincipalDisplayName(String value) { 
		principalDisplayName = value; 
	}
	private java.util.UUID principalId;

	/**
	* Gets the principal Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getprincipalId() {
		return this.principalId; 
	}

	/**
	* Sets the principal Id.
	*
	* @param value the java.util.UUID
	*/
	public void setprincipalId(java.util.UUID value) { 
		principalId = value; 
	}
	private String principalType;

	/**
	* Gets the principal Type.
	*
	* @return the String
	*/
	public String getprincipalType() {
		return this.principalType; 
	}

	/**
	* Sets the principal Type.
	*
	* @param value the String
	*/
	public void setprincipalType(String value) { 
		principalType = value; 
	}
	private String resourceDisplayName;

	/**
	* Gets the resource Display Name.
	*
	* @return the String
	*/
	public String getresourceDisplayName() {
		return this.resourceDisplayName; 
	}

	/**
	* Sets the resource Display Name.
	*
	* @param value the String
	*/
	public void setresourceDisplayName(String value) { 
		resourceDisplayName = value; 
	}
	private java.util.UUID resourceId;

	/**
	* Gets the resource Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getresourceId() {
		return this.resourceId; 
	}

	/**
	* Sets the resource Id.
	*
	* @param value the java.util.UUID
	*/
	public void setresourceId(java.util.UUID value) { 
		resourceId = value; 
	}
}