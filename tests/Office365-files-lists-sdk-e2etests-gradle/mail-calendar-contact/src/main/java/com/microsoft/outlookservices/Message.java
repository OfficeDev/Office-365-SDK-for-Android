/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The type Message.
*/
public class Message extends Item {
	private String subject;

	/**
	* Gets the Subject.
	*
	* @return the String
	*/
	public String getSubject() {
		 return subject; 
	}

	/**
	* Sets the Subject.
	*
	* @param value the String
	*/
	public void setSubject(String value) { 
		subject = value; 
	}
	private ItemBody body;

	/**
	* Gets the Body.
	*
	* @return the ItemBody
	*/
	public ItemBody getBody() {
		 return body; 
	}

	/**
	* Sets the Body.
	*
	* @param value the ItemBody
	*/
	public void setBody(ItemBody value) { 
		body = value; 
	}
	private String bodyPreview;

	/**
	* Gets the Body Preview.
	*
	* @return the String
	*/
	public String getBodyPreview() {
		 return bodyPreview; 
	}

	/**
	* Sets the Body Preview.
	*
	* @param value the String
	*/
	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}
	private Importance importance;

	/**
	* Gets the Importance.
	*
	* @return the Importance
	*/
	public Importance getImportance() {
		 return importance; 
	}

	/**
	* Sets the Importance.
	*
	* @param value the Importance
	*/
	public void setImportance(Importance value) { 
		importance = value; 
	}
	private Boolean hasAttachments;

	/**
	* Gets the Has Attachments.
	*
	* @return the Boolean
	*/
	public Boolean getHasAttachments() {
		 return hasAttachments; 
	}

	/**
	* Sets the Has Attachments.
	*
	* @param value the Boolean
	*/
	public void setHasAttachments(Boolean value) { 
		hasAttachments = value; 
	}
	private String parentFolderId;

	/**
	* Gets the Parent Folder Id.
	*
	* @return the String
	*/
	public String getParentFolderId() {
		 return parentFolderId; 
	}

	/**
	* Sets the Parent Folder Id.
	*
	* @param value the String
	*/
	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private Recipient from;

	/**
	* Gets the From.
	*
	* @return the Recipient
	*/
	public Recipient getFrom() {
		 return from; 
	}

	/**
	* Sets the From.
	*
	* @param value the Recipient
	*/
	public void setFrom(Recipient value) { 
		from = value; 
	}
	private Recipient sender;

	/**
	* Gets the Sender.
	*
	* @return the Recipient
	*/
	public Recipient getSender() {
		 return sender; 
	}

	/**
	* Sets the Sender.
	*
	* @param value the Recipient
	*/
	public void setSender(Recipient value) { 
		sender = value; 
	}
	private java.util.List<Recipient> toRecipients;

	/**
	* Gets the To Recipients.
	*
	* @return the java.util.List<Recipient>
	*/
	public java.util.List<Recipient> getToRecipients() {
		 return toRecipients; 
	}

	/**
	* Sets the To Recipients.
	*
	* @param value the java.util.List<Recipient>
	*/
	public void setToRecipients(java.util.List<Recipient> value) { 
		toRecipients = value; 
	}
	private java.util.List<Recipient> ccRecipients;

	/**
	* Gets the Cc Recipients.
	*
	* @return the java.util.List<Recipient>
	*/
	public java.util.List<Recipient> getCcRecipients() {
		 return ccRecipients; 
	}

	/**
	* Sets the Cc Recipients.
	*
	* @param value the java.util.List<Recipient>
	*/
	public void setCcRecipients(java.util.List<Recipient> value) { 
		ccRecipients = value; 
	}
	private java.util.List<Recipient> bccRecipients;

	/**
	* Gets the Bcc Recipients.
	*
	* @return the java.util.List<Recipient>
	*/
	public java.util.List<Recipient> getBccRecipients() {
		 return bccRecipients; 
	}

	/**
	* Sets the Bcc Recipients.
	*
	* @param value the java.util.List<Recipient>
	*/
	public void setBccRecipients(java.util.List<Recipient> value) { 
		bccRecipients = value; 
	}
	private java.util.List<Recipient> replyTo;

	/**
	* Gets the Reply To.
	*
	* @return the java.util.List<Recipient>
	*/
	public java.util.List<Recipient> getReplyTo() {
		 return replyTo; 
	}

	/**
	* Sets the Reply To.
	*
	* @param value the java.util.List<Recipient>
	*/
	public void setReplyTo(java.util.List<Recipient> value) { 
		replyTo = value; 
	}
	private String conversationId;

	/**
	* Gets the Conversation Id.
	*
	* @return the String
	*/
	public String getConversationId() {
		 return conversationId; 
	}

	/**
	* Sets the Conversation Id.
	*
	* @param value the String
	*/
	public void setConversationId(String value) { 
		conversationId = value; 
	}
	private ItemBody uniqueBody;

	/**
	* Gets the Unique Body.
	*
	* @return the ItemBody
	*/
	public ItemBody getUniqueBody() {
		 return uniqueBody; 
	}

	/**
	* Sets the Unique Body.
	*
	* @param value the ItemBody
	*/
	public void setUniqueBody(ItemBody value) { 
		uniqueBody = value; 
	}
	private java.util.Calendar dateTimeReceived;

	/**
	* Gets the Date Time Received.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getDateTimeReceived() {
		 return dateTimeReceived; 
	}

	/**
	* Sets the Date Time Received.
	*
	* @param value the java.util.Calendar
	*/
	public void setDateTimeReceived(java.util.Calendar value) { 
		dateTimeReceived = value; 
	}
	private java.util.Calendar dateTimeSent;

	/**
	* Gets the Date Time Sent.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getDateTimeSent() {
		 return dateTimeSent; 
	}

	/**
	* Sets the Date Time Sent.
	*
	* @param value the java.util.Calendar
	*/
	public void setDateTimeSent(java.util.Calendar value) { 
		dateTimeSent = value; 
	}
	private Boolean isDeliveryReceiptRequested;

	/**
	* Gets the Is Delivery Receipt Requested.
	*
	* @return the Boolean
	*/
	public Boolean getIsDeliveryReceiptRequested() {
		 return isDeliveryReceiptRequested; 
	}

	/**
	* Sets the Is Delivery Receipt Requested.
	*
	* @param value the Boolean
	*/
	public void setIsDeliveryReceiptRequested(Boolean value) { 
		isDeliveryReceiptRequested = value; 
	}
	private Boolean isReadReceiptRequested;

	/**
	* Gets the Is Read Receipt Requested.
	*
	* @return the Boolean
	*/
	public Boolean getIsReadReceiptRequested() {
		 return isReadReceiptRequested; 
	}

	/**
	* Sets the Is Read Receipt Requested.
	*
	* @param value the Boolean
	*/
	public void setIsReadReceiptRequested(Boolean value) { 
		isReadReceiptRequested = value; 
	}
	private Boolean isDraft;

	/**
	* Gets the Is Draft.
	*
	* @return the Boolean
	*/
	public Boolean getIsDraft() {
		 return isDraft; 
	}

	/**
	* Sets the Is Draft.
	*
	* @param value the Boolean
	*/
	public void setIsDraft(Boolean value) { 
		isDraft = value; 
	}
	private Boolean isRead;

	/**
	* Gets the Is Read.
	*
	* @return the Boolean
	*/
	public Boolean getIsRead() {
		 return isRead; 
	}

	/**
	* Sets the Is Read.
	*
	* @param value the Boolean
	*/
	public void setIsRead(Boolean value) { 
		isRead = value; 
	}
}