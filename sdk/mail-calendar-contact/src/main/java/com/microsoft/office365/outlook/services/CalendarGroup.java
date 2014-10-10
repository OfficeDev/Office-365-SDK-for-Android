/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type CalendarGroup.
*/
public class CalendarGroup extends Entity {
	private String name;


     /**
     * Gets the name.		
     *
     * @return the name
     */
	public String getName() {
		 return name; 
	}
	
     /**
     * Sets the name.		
     *
     * @param value the value
     */
	public void setName(String value) { 
		name = value; 
	}
	private String changeKey;


     /**
     * Gets the change key.		
     *
     * @return the change key
     */
	public String getChangeKey() {
		 return changeKey; 
	}
	
     /**
     * Sets the change key.		
     *
     * @param value the value
     */
	public void setChangeKey(String value) { 
		changeKey = value; 
	}
	private java.util.UUID classId;


     /**
     * Gets the class id.		
     *
     * @return the class id
     */
	public java.util.UUID getClassId() {
		 return classId; 
	}
	
     /**
     * Sets the class id.		
     *
     * @param value the value
     */
	public void setClassId(java.util.UUID value) { 
		classId = value; 
	}
}