/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Alternative Security Id.
*/
public class AlternativeSecurityId {

	private Integer type;

	/**
	* Gets the type.
	*
	* @return the Integer
	*/
	public Integer gettype() {
		return this.type; 
	}

	/**
	* Sets the type.
	*
	* @param value the Integer
	*/
	public void settype(Integer value) { 
		type = value; 
	}

	private String identityProvider;

	/**
	* Gets the identity Provider.
	*
	* @return the String
	*/
	public String getidentityProvider() {
		return this.identityProvider; 
	}

	/**
	* Sets the identity Provider.
	*
	* @param value the String
	*/
	public void setidentityProvider(String value) { 
		identityProvider = value; 
	}

	private byte[] key;

	/**
	* Gets the key.
	*
	* @return the byte[]
	*/
	public byte[] getkey() {
		return this.key; 
	}

	/**
	* Sets the key.
	*
	* @param value the byte[]
	*/
	public void setkey(byte[] value) { 
		key = value; 
	}
}