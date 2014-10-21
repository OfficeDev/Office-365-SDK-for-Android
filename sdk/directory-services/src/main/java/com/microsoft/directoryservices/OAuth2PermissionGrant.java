/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type OAuth2Permission Grant.
*/
public class OAuth2PermissionGrant {
	private String clientId;

	/**
	* Gets the client Id.
	*
	* @return the String
	*/
	public String getclientId() {
		return this.clientId; 
	}

	/**
	* Sets the client Id.
	*
	* @param value the String
	*/
	public void setclientId(String value) { 
		clientId = value; 
	}
	private String consentType;

	/**
	* Gets the consent Type.
	*
	* @return the String
	*/
	public String getconsentType() {
		return this.consentType; 
	}

	/**
	* Sets the consent Type.
	*
	* @param value the String
	*/
	public void setconsentType(String value) { 
		consentType = value; 
	}
	private java.util.Calendar expiryTime;

	/**
	* Gets the expiry Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getexpiryTime() {
		return this.expiryTime; 
	}

	/**
	* Sets the expiry Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setexpiryTime(java.util.Calendar value) { 
		expiryTime = value; 
	}
	private String objectId;

	/**
	* Gets the object Id.
	*
	* @return the String
	*/
	public String getobjectId() {
		return this.objectId; 
	}

	/**
	* Sets the object Id.
	*
	* @param value the String
	*/
	public void setobjectId(String value) { 
		objectId = value; 
	}
	private String principalId;

	/**
	* Gets the principal Id.
	*
	* @return the String
	*/
	public String getprincipalId() {
		return this.principalId; 
	}

	/**
	* Sets the principal Id.
	*
	* @param value the String
	*/
	public void setprincipalId(String value) { 
		principalId = value; 
	}
	private String resourceId;

	/**
	* Gets the resource Id.
	*
	* @return the String
	*/
	public String getresourceId() {
		return this.resourceId; 
	}

	/**
	* Sets the resource Id.
	*
	* @param value the String
	*/
	public void setresourceId(String value) { 
		resourceId = value; 
	}
	private String scope;

	/**
	* Gets the scope.
	*
	* @return the String
	*/
	public String getscope() {
		return this.scope; 
	}

	/**
	* Sets the scope.
	*
	* @param value the String
	*/
	public void setscope(String value) { 
		scope = value; 
	}
	private java.util.Calendar startTime;

	/**
	* Gets the start Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getstartTime() {
		return this.startTime; 
	}

	/**
	* Sets the start Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setstartTime(java.util.Calendar value) { 
		startTime = value; 
	}
}