/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Recurrence Range.
*/
public class RecurrenceRange extends ODataBaseEntity {

    public RecurrenceRange(){
        setODataType("#Microsoft.OutlookServices.RecurrenceRange");
    }

    private RecurrenceRangeType Type;

    /**
    * Gets the Type.
    *
    * @return the RecurrenceRangeType
    */
    public RecurrenceRangeType getType() {
        return this.Type; 
    }

    /**
    * Sets the Type.
    *
    * @param value the RecurrenceRangeType
    */
    public void setType(RecurrenceRangeType value) { 
        this.Type = value; 
    }

    private java.util.Calendar StartDate;

    /**
    * Gets the Start Date.
    *
    * @return the java.util.Calendar
    */
    public java.util.Calendar getStartDate() {
        return this.StartDate; 
    }

    /**
    * Sets the Start Date.
    *
    * @param value the java.util.Calendar
    */
    public void setStartDate(java.util.Calendar value) { 
        this.StartDate = value; 
    }

    private java.util.Calendar EndDate;

    /**
    * Gets the End Date.
    *
    * @return the java.util.Calendar
    */
    public java.util.Calendar getEndDate() {
        return this.EndDate; 
    }

    /**
    * Sets the End Date.
    *
    * @param value the java.util.Calendar
    */
    public void setEndDate(java.util.Calendar value) { 
        this.EndDate = value; 
    }

    private Integer NumberOfOccurrences;

    /**
    * Gets the Number Of Occurrences.
    *
    * @return the Integer
    */
    public Integer getNumberOfOccurrences() {
        return this.NumberOfOccurrences; 
    }

    /**
    * Sets the Number Of Occurrences.
    *
    * @param value the Integer
    */
    public void setNumberOfOccurrences(Integer value) { 
        this.NumberOfOccurrences = value; 
    }
}
