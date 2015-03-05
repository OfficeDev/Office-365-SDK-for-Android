/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Drive Quota.
*/
public class DriveQuota extends ODataBaseEntity {

    public DriveQuota(){
        setODataType("#Microsoft.FileServices.DriveQuota");
    }

    private Long deleted;

    /**
    * Gets the deleted.
    *
    * @return the Long
    */
    public Long getdeleted() {
        return this.deleted; 
    }

    /**
    * Sets the deleted.
    *
    * @param value the Long
    */
    public void setdeleted(Long value) { 
        this.deleted = value; 
    }

    private Long remaining;

    /**
    * Gets the remaining.
    *
    * @return the Long
    */
    public Long getremaining() {
        return this.remaining; 
    }

    /**
    * Sets the remaining.
    *
    * @param value the Long
    */
    public void setremaining(Long value) { 
        this.remaining = value; 
    }

    private String state;

    /**
    * Gets the state.
    *
    * @return the String
    */
    public String getstate() {
        return this.state; 
    }

    /**
    * Sets the state.
    *
    * @param value the String
    */
    public void setstate(String value) { 
        this.state = value; 
    }

    private Long total;

    /**
    * Gets the total.
    *
    * @return the Long
    */
    public Long gettotal() {
        return this.total; 
    }

    /**
    * Sets the total.
    *
    * @param value the Long
    */
    public void settotal(Long value) { 
        this.total = value; 
    }
}
