/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Extension Property.
*/
public class ExtensionProperty extends DirectoryObject {

	public ExtensionProperty(){
		setODataType("#Microsoft.DirectoryServices.ExtensionProperty");
	}

	private String appDisplayName;

	/**
	* Gets the app Display Name.
	*
	* @return the String
	*/
	public String getappDisplayName() {
		return this.appDisplayName; 
	}

	/**
	* Sets the app Display Name.
	*
	* @param value the String
	*/
	public void setappDisplayName(String value) { 
		this.appDisplayName = value; 
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
	private String dataType;

	/**
	* Gets the data Type.
	*
	* @return the String
	*/
	public String getdataType() {
		return this.dataType; 
	}

	/**
	* Sets the data Type.
	*
	* @param value the String
	*/
	public void setdataType(String value) { 
		this.dataType = value; 
	}
	private Boolean isSyncedFromOnPremises;

	/**
	* Gets the is Synced From On Premises.
	*
	* @return the Boolean
	*/
	public Boolean getisSyncedFromOnPremises() {
		return this.isSyncedFromOnPremises; 
	}

	/**
	* Sets the is Synced From On Premises.
	*
	* @param value the Boolean
	*/
	public void setisSyncedFromOnPremises(Boolean value) { 
		this.isSyncedFromOnPremises = value; 
	}
	private java.util.List<String> targetObjects;

	/**
	* Gets the target Objects.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> gettargetObjects() {
		return this.targetObjects; 
	}

	/**
	* Sets the target Objects.
	*
	* @param value the java.util.List<String>
	*/
	public void settargetObjects(java.util.List<String> value) { 
		this.targetObjects = value; 
	}
}