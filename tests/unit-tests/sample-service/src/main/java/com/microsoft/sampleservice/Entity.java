/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice;

/**
 * The type Entity.
*/
public class Entity extends ODataBaseEntity {

    public Entity(){
        setODataType("#Microsoft.SampleService.Entity");
    }

        
	private String Id;

    /**
    * Gets the Id.
    *
    * @return the String
    */
    public String getId() {
        return this.Id; 
    }

    /**
    * Sets the Id.
    *
    * @param value the String
    */
    public void setId(String value) { 
        this.Id = value; 
    }
}