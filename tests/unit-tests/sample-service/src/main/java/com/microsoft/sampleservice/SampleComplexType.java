/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice;

/**
 * The type Sample Complex Type.
*/
public class SampleComplexType extends ODataBaseEntity {

	public SampleComplexType(){
		setODataType("#Microsoft.SampleService.SampleComplexType");
	}


	private String Name;

	/**
	* Gets the Name.
	*
	* @return the String
	*/
	public String getName() {
		return this.Name; 
	}

	/**
	* Sets the Name.
	*
	* @param value the String
	*/
	public void setName(String value) { 
		this.Name = value; 
	}

	private String AnotherProperty;

	/**
	* Gets the Another Property.
	*
	* @return the String
	*/
	public String getAnotherProperty() {
		return this.AnotherProperty; 
	}

	/**
	* Sets the Another Property.
	*
	* @param value the String
	*/
	public void setAnotherProperty(String value) { 
		this.AnotherProperty = value; 
	}
}