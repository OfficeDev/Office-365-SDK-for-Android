/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Verified Domain.
*/
public class VerifiedDomain extends ODataBaseEntity {

	public VerifiedDomain(){
		setODataType("#Microsoft.DirectoryServices.VerifiedDomain");
	}


	private String capabilities;

	/**
	* Gets the capabilities.
	*
	* @return the String
	*/
	public String getcapabilities() {
		return this.capabilities; 
	}

	/**
	* Sets the capabilities.
	*
	* @param value the String
	*/
	public void setcapabilities(String value) { 
		this.capabilities = value; 
	}

	private Boolean $$__$$default;

	/**
	* Gets the default.
	*
	* @return the Boolean
	*/
	public Boolean getdefault() {
		return this.$$__$$default; 
	}

	/**
	* Sets the default.
	*
	* @param value the Boolean
	*/
	public void setdefault(Boolean value) { 
		this.$$__$$default = value; 
	}

	private String id;

	/**
	* Gets the id.
	*
	* @return the String
	*/
	public String getid() {
		return this.id; 
	}

	/**
	* Sets the id.
	*
	* @param value the String
	*/
	public void setid(String value) { 
		this.id = value; 
	}

	private Boolean initial;

	/**
	* Gets the initial.
	*
	* @return the Boolean
	*/
	public Boolean getinitial() {
		return this.initial; 
	}

	/**
	* Sets the initial.
	*
	* @param value the Boolean
	*/
	public void setinitial(Boolean value) { 
		this.initial = value; 
	}

	private String name;

	/**
	* Gets the name.
	*
	* @return the String
	*/
	public String getname() {
		return this.name; 
	}

	/**
	* Sets the name.
	*
	* @param value the String
	*/
	public void setname(String value) { 
		this.name = value; 
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
		this.type = value; 
	}
}