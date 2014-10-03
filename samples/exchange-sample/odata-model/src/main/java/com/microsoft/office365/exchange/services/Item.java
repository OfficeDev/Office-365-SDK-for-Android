/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class Item extends Entity {
	private String changeKey;

	public String getChangeKey() {
		 return changeKey; 
	}

	public void setChangeKey(String value) { 
		changeKey = value; 
	}
	private String className;

	public String getClassName() {
		 return className; 
	}

	public void setClassName(String value) { 
		className = value; 
	}
	private String subject;

	public String getSubject() {
		 return subject; 
	}

	public void setSubject(String value) { 
		subject = value; 
	}
	private ItemBody body;

	public ItemBody getBody() {
		 return body; 
	}

	public void setBody(ItemBody value) { 
		body = value; 
	}
	private String bodyPreview;

	public String getBodyPreview() {
		 return bodyPreview; 
	}

	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}
	private Importance importance;

	public Importance getImportance() {
		 return importance; 
	}

	public void setImportance(Importance value) { 
		importance = value; 
	}
	private java.util.List<String> categories;

	public java.util.List<String> getCategories() {
		 return categories; 
	}

	public void setCategories(java.util.List<String> value) { 
		categories = value; 
	}
	private Boolean hasAttachments;

	public Boolean getHasAttachments() {
		 return hasAttachments; 
	}

	public void setHasAttachments(Boolean value) { 
		hasAttachments = value; 
	}
}