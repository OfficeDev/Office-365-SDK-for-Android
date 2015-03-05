/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Recurrence Pattern.
*/
public class RecurrencePattern extends ODataBaseEntity {

    public RecurrencePattern(){
        setODataType("#Microsoft.OutlookServices.RecurrencePattern");
    }

    private RecurrencePatternType Type;

    /**
    * Gets the Type.
    *
    * @return the RecurrencePatternType
    */
    public RecurrencePatternType getType() {
        return this.Type; 
    }

    /**
    * Sets the Type.
    *
    * @param value the RecurrencePatternType
    */
    public void setType(RecurrencePatternType value) { 
        this.Type = value; 
    }

    private Integer Interval;

    /**
    * Gets the Interval.
    *
    * @return the Integer
    */
    public Integer getInterval() {
        return this.Interval; 
    }

    /**
    * Sets the Interval.
    *
    * @param value the Integer
    */
    public void setInterval(Integer value) { 
        this.Interval = value; 
    }

    private Integer DayOfMonth;

    /**
    * Gets the Day Of Month.
    *
    * @return the Integer
    */
    public Integer getDayOfMonth() {
        return this.DayOfMonth; 
    }

    /**
    * Sets the Day Of Month.
    *
    * @param value the Integer
    */
    public void setDayOfMonth(Integer value) { 
        this.DayOfMonth = value; 
    }

    private Integer Month;

    /**
    * Gets the Month.
    *
    * @return the Integer
    */
    public Integer getMonth() {
        return this.Month; 
    }

    /**
    * Sets the Month.
    *
    * @param value the Integer
    */
    public void setMonth(Integer value) { 
        this.Month = value; 
    }

    private java.util.List<DayOfWeek> DaysOfWeek;

    /**
    * Gets the Days Of Week.
    *
    * @return the java.util.List<DayOfWeek>
    */
    public java.util.List<DayOfWeek> getDaysOfWeek() {
        return this.DaysOfWeek; 
    }

    /**
    * Sets the Days Of Week.
    *
    * @param value the java.util.List<DayOfWeek>
    */
    public void setDaysOfWeek(java.util.List<DayOfWeek> value) { 
        this.DaysOfWeek = value; 
    }

    private DayOfWeek FirstDayOfWeek;

    /**
    * Gets the First Day Of Week.
    *
    * @return the DayOfWeek
    */
    public DayOfWeek getFirstDayOfWeek() {
        return this.FirstDayOfWeek; 
    }

    /**
    * Sets the First Day Of Week.
    *
    * @param value the DayOfWeek
    */
    public void setFirstDayOfWeek(DayOfWeek value) { 
        this.FirstDayOfWeek = value; 
    }

    private WeekIndex Index;

    /**
    * Gets the Index.
    *
    * @return the WeekIndex
    */
    public WeekIndex getIndex() {
        return this.Index; 
    }

    /**
    * Sets the Index.
    *
    * @param value the WeekIndex
    */
    public void setIndex(WeekIndex value) { 
        this.Index = value; 
    }
}
