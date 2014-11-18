/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type File Attachment.
*/
public class FileAttachment extends Attachment {

    public FileAttachment(){
        setODataType("#Microsoft.OutlookServices.FileAttachment");
    }

        
	private String ContentId;

    /**
    * Gets the Content Id.
    *
    * @return the String
    */
    public String getContentId() {
        return this.ContentId; 
    }

    /**
    * Sets the Content Id.
    *
    * @param value the String
    */
    public void setContentId(String value) { 
        this.ContentId = value; 
    }
    
	private String ContentLocation;

    /**
    * Gets the Content Location.
    *
    * @return the String
    */
    public String getContentLocation() {
        return this.ContentLocation; 
    }

    /**
    * Sets the Content Location.
    *
    * @param value the String
    */
    public void setContentLocation(String value) { 
        this.ContentLocation = value; 
    }
    
	private Boolean IsContactPhoto;

    /**
    * Gets the Is Contact Photo.
    *
    * @return the Boolean
    */
    public Boolean getIsContactPhoto() {
        return this.IsContactPhoto; 
    }

    /**
    * Sets the Is Contact Photo.
    *
    * @param value the Boolean
    */
    public void setIsContactPhoto(Boolean value) { 
        this.IsContactPhoto = value; 
    }
    
	private byte[] ContentBytes;

    /**
    * Gets the Content Bytes.
    *
    * @return the byte[]
    */
    public byte[] getContentBytes() {
        return this.ContentBytes; 
    }

    /**
    * Sets the Content Bytes.
    *
    * @param value the byte[]
    */
    public void setContentBytes(byte[] value) { 
        this.ContentBytes = value; 
    }
}