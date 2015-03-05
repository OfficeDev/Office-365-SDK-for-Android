/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Identity.
*/
public class Identity extends ODataBaseEntity {

    public Identity(){
        setODataType("#Microsoft.FileServices.Identity");
    }

    private String id;

    /**
    * Gets the id.
    *
    * @return the String
    */
    public String getid() {
        return this.id; 
    }

    /**
    * Sets the id.
    *
    * @param value the String
    */
    public void setid(String value) { 
        this.id = value; 
    }

    private String displayName;

    /**
    * Gets the display Name.
    *
    * @return the String
    */
    public String getdisplayName() {
        return this.displayName; 
    }

    /**
    * Sets the display Name.
    *
    * @param value the String
    */
    public void setdisplayName(String value) { 
        this.displayName = value; 
    }
}
