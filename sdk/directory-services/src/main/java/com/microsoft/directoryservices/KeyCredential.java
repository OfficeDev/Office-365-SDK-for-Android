/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Key Credential.
*/
public class KeyCredential {

	private byte[] customKeyIdentifier;

	/**
	* Gets the custom Key Identifier.
	*
	* @return the byte[]
	*/
	public byte[] getcustomKeyIdentifier() {
		return this.customKeyIdentifier; 
	}

	/**
	* Sets the custom Key Identifier.
	*
	* @param value the byte[]
	*/
	public void setcustomKeyIdentifier(byte[] value) { 
		customKeyIdentifier = value; 
	}

	private java.util.Calendar endDate;

	/**
	* Gets the end Date.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getendDate() {
		return this.endDate; 
	}

	/**
	* Sets the end Date.
	*
	* @param value the java.util.Calendar
	*/
	public void setendDate(java.util.Calendar value) { 
		endDate = value; 
	}

	private java.util.UUID keyId;

	/**
	* Gets the key Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getkeyId() {
		return this.keyId; 
	}

	/**
	* Sets the key Id.
	*
	* @param value the java.util.UUID
	*/
	public void setkeyId(java.util.UUID value) { 
		keyId = value; 
	}

	private java.util.Calendar startDate;

	/**
	* Gets the start Date.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getstartDate() {
		return this.startDate; 
	}

	/**
	* Sets the start Date.
	*
	* @param value the java.util.Calendar
	*/
	public void setstartDate(java.util.Calendar value) { 
		startDate = value; 
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

	private String usage;

	/**
	* Gets the usage.
	*
	* @return the String
	*/
	public String getusage() {
		return this.usage; 
	}

	/**
	* Sets the usage.
	*
	* @param value the String
	*/
	public void setusage(String value) { 
		usage = value; 
	}

	private byte[] value;

	/**
	* Gets the value.
	*
	* @return the byte[]
	*/
	public byte[] getvalue() {
		return this.value; 
	}

	/**
	* Sets the value.
	*
	* @param value the byte[]
	*/
	public void setvalue(byte[] value) { 
		value = value; 
	}
}