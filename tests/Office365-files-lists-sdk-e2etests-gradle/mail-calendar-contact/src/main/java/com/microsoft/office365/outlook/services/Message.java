/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.outlook.services;

/**
 * The type Message.
*/
public class Message extends Item {
	private String subject;


     /**
     * Gets the subject.		
     *
     * @return the subject
     */
	public String getSubject() {
		 return subject; 
	}
	
     /**
     * Sets the subject.		
     *
     * @param value the value
     */
	public void setSubject(String value) { 
		subject = value; 
	}
	private ItemBody body;


     /**
     * Gets the body.		
     *
     * @return the body
     */
	public ItemBody getBody() {
		 return body; 
	}
	
     /**
     * Sets the body.		
     *
     * @param value the value
     */
	public void setBody(ItemBody value) { 
		body = value; 
	}
	private String bodyPreview;


     /**
     * Gets the body preview.		
     *
     * @return the body preview
     */
	public String getBodyPreview() {
		 return bodyPreview; 
	}
	
     /**
     * Sets the body preview.		
     *
     * @param value the value
     */
	public void setBodyPreview(String value) { 
		bodyPreview = value; 
	}
	private Importance importance;


     /**
     * Gets the importance.		
     *
     * @return the importance
     */
	public Importance getImportance() {
		 return importance; 
	}
	
     /**
     * Sets the importance.		
     *
     * @param value the value
     */
	public void setImportance(Importance value) { 
		importance = value; 
	}
	private Boolean hasAttachments;


     /**
     * Gets the has attachments.		
     *
     * @return the has attachments
     */
	public Boolean getHasAttachments() {
		 return hasAttachments; 
	}
	
     /**
     * Sets the has attachments.		
     *
     * @param value the value
     */
	public void setHasAttachments(Boolean value) { 
		hasAttachments = value; 
	}
	private String parentFolderId;


     /**
     * Gets the parent folder id.		
     *
     * @return the parent folder id
     */
	public String getParentFolderId() {
		 return parentFolderId; 
	}
	
     /**
     * Sets the parent folder id.		
     *
     * @param value the value
     */
	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private Recipient from;


     /**
     * Gets the from.		
     *
     * @return the from
     */
	public Recipient getFrom() {
		 return from; 
	}
	
     /**
     * Sets the from.		
     *
     * @param value the value
     */
	public void setFrom(Recipient value) { 
		from = value; 
	}
	private Recipient sender;


     /**
     * Gets the sender.		
     *
     * @return the sender
     */
	public Recipient getSender() {
		 return sender; 
	}
	
     /**
     * Sets the sender.		
     *
     * @param value the value
     */
	public void setSender(Recipient value) { 
		sender = value; 
	}
	private java.util.List<Recipient> toRecipients;


     /**
     * Gets the to recipients.		
     *
     * @return the to recipients
     */
	public java.util.List<Recipient> getToRecipients() {
		 return toRecipients; 
	}
	
     /**
     * Sets the to recipients.		
     *
     * @param value the value
     */
	public void setToRecipients(java.util.List<Recipient> value) { 
		toRecipients = value; 
	}
	private java.util.List<Recipient> ccRecipients;


     /**
     * Gets the cc recipients.		
     *
     * @return the cc recipients
     */
	public java.util.List<Recipient> getCcRecipients() {
		 return ccRecipients; 
	}
	
     /**
     * Sets the cc recipients.		
     *
     * @param value the value
     */
	public void setCcRecipients(java.util.List<Recipient> value) { 
		ccRecipients = value; 
	}
	private java.util.List<Recipient> bccRecipients;


     /**
     * Gets the bcc recipients.		
     *
     * @return the bcc recipients
     */
	public java.util.List<Recipient> getBccRecipients() {
		 return bccRecipients; 
	}
	
     /**
     * Sets the bcc recipients.		
     *
     * @param value the value
     */
	public void setBccRecipients(java.util.List<Recipient> value) { 
		bccRecipients = value; 
	}
	private java.util.List<Recipient> replyTo;


     /**
     * Gets the reply to.		
     *
     * @return the reply to
     */
	public java.util.List<Recipient> getReplyTo() {
		 return replyTo; 
	}
	
     /**
     * Sets the reply to.		
     *
     * @param value the value
     */
	public void setReplyTo(java.util.List<Recipient> value) { 
		replyTo = value; 
	}
	private String conversationId;


     /**
     * Gets the conversation id.		
     *
     * @return the conversation id
     */
	public String getConversationId() {
		 return conversationId; 
	}
	
     /**
     * Sets the conversation id.		
     *
     * @param value the value
     */
	public void setConversationId(String value) { 
		conversationId = value; 
	}
	private ItemBody uniqueBody;


     /**
     * Gets the unique body.		
     *
     * @return the unique body
     */
	public ItemBody getUniqueBody() {
		 return uniqueBody; 
	}
	
     /**
     * Sets the unique body.		
     *
     * @param value the value
     */
	public void setUniqueBody(ItemBody value) { 
		uniqueBody = value; 
	}
	private java.util.Calendar dateTimeReceived;


     /**
     * Gets the date time received.		
     *
     * @return the date time received
     */
	public java.util.Calendar getDateTimeReceived() {
		 return dateTimeReceived; 
	}
	
     /**
     * Sets the date time received.		
     *
     * @param value the value
     */
	public void setDateTimeReceived(java.util.Calendar value) { 
		dateTimeReceived = value; 
	}
	private java.util.Calendar dateTimeSent;


     /**
     * Gets the date time sent.		
     *
     * @return the date time sent
     */
	public java.util.Calendar getDateTimeSent() {
		 return dateTimeSent; 
	}
	
     /**
     * Sets the date time sent.		
     *
     * @param value the value
     */
	public void setDateTimeSent(java.util.Calendar value) { 
		dateTimeSent = value; 
	}
	private Boolean isDeliveryReceiptRequested;


     /**
     * Gets the is delivery receipt requested.		
     *
     * @return the is delivery receipt requested
     */
	public Boolean getIsDeliveryReceiptRequested() {
		 return isDeliveryReceiptRequested; 
	}
	
     /**
     * Sets the is delivery receipt requested.		
     *
     * @param value the value
     */
	public void setIsDeliveryReceiptRequested(Boolean value) { 
		isDeliveryReceiptRequested = value; 
	}
	private Boolean isReadReceiptRequested;


     /**
     * Gets the is read receipt requested.		
     *
     * @return the is read receipt requested
     */
	public Boolean getIsReadReceiptRequested() {
		 return isReadReceiptRequested; 
	}
	
     /**
     * Sets the is read receipt requested.		
     *
     * @param value the value
     */
	public void setIsReadReceiptRequested(Boolean value) { 
		isReadReceiptRequested = value; 
	}
	private Boolean isDraft;


     /**
     * Gets the is draft.		
     *
     * @return the is draft
     */
	public Boolean getIsDraft() {
		 return isDraft; 
	}
	
     /**
     * Sets the is draft.		
     *
     * @param value the value
     */
	public void setIsDraft(Boolean value) { 
		isDraft = value; 
	}
	private Boolean isRead;


     /**
     * Gets the is read.		
     *
     * @return the is read
     */
	public Boolean getIsRead() {
		 return isRead; 
	}
	
     /**
     * Sets the is read.		
     *
     * @param value the value
     */
	public void setIsRead(Boolean value) { 
		isRead = value; 
	}
}