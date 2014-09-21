
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import com.microsoft.exchange.services.odata.model.ItemBody;



import com.microsoft.exchange.services.odata.model.Importance;



import com.microsoft.exchange.services.odata.model.Attachment;



public class Item extends Entity {

	@SerializedName("ChangeKey")
	@Expose
	private String changeKey;

	public String getChangeKey() {
		 return changeKey; 
	}

	public void setChangeKey(String value) { 
		changeKey = value; 
	}

	@SerializedName("ClassName")
	@Expose
	private String className;

	public String getClassName() {
		 return className; 
	}

	public void setClassName(String value) { 
		className = value; 
	}

	@SerializedName("Subject")
	@Expose
	private String subject;

	public String getSubject() {
		 return subject; 
	}

	public void setSubject(String value) { 
		subject = value; 
	}

	@SerializedName("Body")
	@Expose
	private ItemBody body;

	public ItemBody getBody() {
		 return body; 
	}

	public void setBody(ItemBody value) { 
		body = value; 
	}

	@SerializedName("BodyPreview")
	@Expose
	private String bodyPreview;

	public String getBodyPreview() {
		 return bodyPreview; 
	}

	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}

	@SerializedName("Importance")
	@Expose
	private Importance importance;

	public Importance getImportance() {
		 return importance; 
	}

	public void setImportance(Importance value) { 
		importance = value; 
	}

	@SerializedName("Categories")
	@Expose
	private java.util.List<String> categories;

	public java.util.List<String> getCategories() {
		 return categories; 
	}

	public void setCategories(java.util.List<String> value) { 
		categories = value; 
	}

	@SerializedName("HasAttachments")
	@Expose
	private Boolean hasAttachments;

	public Boolean getHasAttachments() {
		 return hasAttachments; 
	}

	public void setHasAttachments(Boolean value) { 
		hasAttachments = value; 
	}

	@SerializedName("Attachments")
	@Expose
	private java.util.List<Attachment> attachments;

	public java.util.List<Attachment> getAttachments() {
		 return attachments; 
	}

	public void setAttachments(java.util.List<Attachment> value) { 
		attachments = value; 
	}

}