
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import com.microsoft.exchange.services.odata.model.Item;



public class ItemAttachment extends Attachment {

	@SerializedName("Item")
	@Expose
	private Item item;

	public Item getItem() {
		 return item; 
	}

	public void setItem(Item value) { 
		item = value; 
	}

}