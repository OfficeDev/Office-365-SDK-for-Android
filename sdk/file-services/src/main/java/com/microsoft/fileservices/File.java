/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type File.
*/
public class File extends Item {

    public File(){
        setODataType("#Microsoft.FileServices.File");
    }

        
    private String contentUrl;

    /**
    * Gets the content Url.
    *
    * @return the String
    */
    public String getcontentUrl() {
        return this.contentUrl; 
    }

    /**
    * Sets the content Url.
    *
    * @param value the String
    */
    public void setcontentUrl(String value) { 
        this.contentUrl = value; 
    }
}

