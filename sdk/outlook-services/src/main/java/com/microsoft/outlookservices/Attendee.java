/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Attendee.
*/
public class Attendee extends Recipient {

    public Attendee(){
        setODataType("#Microsoft.OutlookServices.Attendee");
    }

    private ResponseStatus Status;

    /**
    * Gets the Status.
    *
    * @return the ResponseStatus
    */
    public ResponseStatus getStatus() {
        return this.Status; 
    }

    /**
    * Sets the Status.
    *
    * @param value the ResponseStatus
    */
    public void setStatus(ResponseStatus value) { 
        this.Status = value; 
    }

    private AttendeeType Type;

    /**
    * Gets the Type.
    *
    * @return the AttendeeType
    */
    public AttendeeType getType() {
        return this.Type; 
    }

    /**
    * Sets the Type.
    *
    * @param value the AttendeeType
    */
    public void setType(AttendeeType value) { 
        this.Type = value; 
    }
}
