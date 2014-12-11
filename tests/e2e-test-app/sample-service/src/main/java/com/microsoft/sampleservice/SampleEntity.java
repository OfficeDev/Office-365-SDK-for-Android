/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.sampleservice;

/**
 * The type Sample Entity.
*/
public class SampleEntity extends Entity {

    public SampleEntity(){
        setODataType("#Microsoft.SampleService.SampleEntity");
    }

    
    private java.util.List<AnotherEntity> Navigations = new java.util.ArrayList<AnotherEntity>();

    /**
    * Gets the Navigations.
    *
    * @return the Navigations
    */
    public java.util.List<AnotherEntity> getNavigations() {
        return this.Navigations; 
    }
            
	private String DisplayName;

    /**
    * Gets the Display Name.
    *
    * @return the String
    */
    public String getDisplayName() {
        return this.DisplayName; 
    }

    /**
    * Sets the Display Name.
    *
    * @param value the String
    */
    public void setDisplayName(String value) { 
        this.DisplayName = value; 
    }
    
	private String entityKey;

    /**
    * Gets the entity Key.
    *
    * @return the String
    */
    public String getentityKey() {
        return this.entityKey; 
    }

    /**
    * Sets the entity Key.
    *
    * @param value the String
    */
    public void setentityKey(String value) { 
        this.entityKey = value; 
    }
}