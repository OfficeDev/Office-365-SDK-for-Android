/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Item Attachment.
*/
public class ItemAttachment extends Attachment {

    public ItemAttachment(){
        setODataType("#Microsoft.OutlookServices.ItemAttachment");
    }
            
    private Item Item;
     
    /**
    * Gets the Item.
    *
    * @return the Item
    */
    public Item getItem() {
        return this.Item; 
    }

    /**
    * Sets the Item.
    *
    * @param value the Item
    */
    public void setItem(Item value) { 
        this.Item = value; 
    }
}

