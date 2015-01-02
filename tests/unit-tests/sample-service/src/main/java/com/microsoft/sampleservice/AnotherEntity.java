/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice;

/**
 * The type Another Entity.
*/
public class AnotherEntity extends Entity {

    public AnotherEntity(){
        setODataType("#Microsoft.SampleService.AnotherEntity");
    }

        
	private String SomeString;

    /**
    * Gets the Some String.
    *
    * @return the String
    */
    public String getSomeString() {
        return this.SomeString; 
    }

    /**
    * Sets the Some String.
    *
    * @param value the String
    */
    public void setSomeString(String value) { 
        this.SomeString = value; 
    }
}