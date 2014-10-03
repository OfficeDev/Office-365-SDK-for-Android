/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.exchange.services;

public class Message extends Item {
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}
	private Recipient from;

	public Recipient getFrom() {
		 return from; 
	}

	public void setFrom(Recipient value) { 
		from = value; 
	}
	private Recipient sender;

	public Recipient getSender() {
		 return sender; 
	}

	public void setSender(Recipient value) { 
		sender = value; 
	}
	private java.util.List<Recipient> toRecipients;

	public java.util.List<Recipient> getToRecipients() {
		 return toRecipients; 
	}

	public void setToRecipients(java.util.List<Recipient> value) { 
		toRecipients = value; 
	}
	private java.util.List<Recipient> ccRecipients;

	public java.util.List<Recipient> getCcRecipients() {
		 return ccRecipients; 
	}

	public void setCcRecipients(java.util.List<Recipient> value) { 
		ccRecipients = value; 
	}
	private java.util.List<Recipient> bccRecipients;

	public java.util.List<Recipient> getBccRecipients() {
		 return bccRecipients; 
	}

	public void setBccRecipients(java.util.List<Recipient> value) { 
		bccRecipients = value; 
	}
	private java.util.List<Recipient> replyTo;

	public java.util.List<Recipient> getReplyTo() {
		 return replyTo; 
	}

	public void setReplyTo(java.util.List<Recipient> value) { 
		replyTo = value; 
	}
	private String conversationId;

	public String getConversationId() {
		 return conversationId; 
	}

	public void setConversationId(String value) { 
		conversationId = value; 
	}
	private ItemBody uniqueBody;

	public ItemBody getUniqueBody() {
		 return uniqueBody; 
	}

	public void setUniqueBody(ItemBody value) { 
		uniqueBody = value; 
	}
	private java.util.Calendar dateTimeReceived;

	public java.util.Calendar getDateTimeReceived() {
		 return dateTimeReceived; 
	}

	public void setDateTimeReceived(java.util.Calendar value) { 
		dateTimeReceived = value; 
	}
	private java.util.Calendar dateTimeSent;

	public java.util.Calendar getDateTimeSent() {
		 return dateTimeSent; 
	}

	public void setDateTimeSent(java.util.Calendar value) { 
		dateTimeSent = value; 
	}
	private Boolean isDeliveryReceiptRequested;

	public Boolean getIsDeliveryReceiptRequested() {
		 return isDeliveryReceiptRequested; 
	}

	public void setIsDeliveryReceiptRequested(Boolean value) { 
		isDeliveryReceiptRequested = value; 
	}
	private Boolean isReadReceiptRequested;

	public Boolean getIsReadReceiptRequested() {
		 return isReadReceiptRequested; 
	}

	public void setIsReadReceiptRequested(Boolean value) { 
		isReadReceiptRequested = value; 
	}
	private Boolean isDraft;

	public Boolean getIsDraft() {
		 return isDraft; 
	}

	public void setIsDraft(Boolean value) { 
		isDraft = value; 
	}
	private Boolean isRead;

	public Boolean getIsRead() {
		 return isRead; 
	}

	public void setIsRead(Boolean value) { 
		isRead = value; 
	}
	private String eventId;

	public String getEventId() {
		 return eventId; 
	}

	public void setEventId(String value) { 
		eventId = value; 
	}
	private MeetingMessageType meetingMessageType;

	public MeetingMessageType getMeetingMessageType() {
		 return meetingMessageType; 
	}

	public void setMeetingMessageType(MeetingMessageType value) { 
		meetingMessageType = value; 
	}
	private java.util.Calendar dateTimeCreated;

	public java.util.Calendar getDateTimeCreated() {
		 return dateTimeCreated; 
	}

	public void setDateTimeCreated(java.util.Calendar value) { 
		dateTimeCreated = value; 
	}
	private java.util.Calendar lastModifiedTime;

	public java.util.Calendar getLastModifiedTime() {
		 return lastModifiedTime; 
	}

	public void setLastModifiedTime(java.util.Calendar value) { 
		lastModifiedTime = value; 
	}
}