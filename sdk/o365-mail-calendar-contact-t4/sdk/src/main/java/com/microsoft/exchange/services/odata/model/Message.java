
package com.microsoft.exchange.services.odata.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.core.*;


import com.microsoft.exchange.services.odata.model.Recipient;



import com.microsoft.exchange.services.odata.model.ItemBody;



import com.microsoft.exchange.services.odata.model.MeetingMessageType;



public class Message extends Item {

	@SerializedName("ParentFolderId")
	@Expose
	private String parentFolderId;

	public String getParentFolderId() {
		 return parentFolderId; 
	}

	public void setParentFolderId(String value) { 
		parentFolderId = value; 
	}

	@SerializedName("From")
	@Expose
	private Recipient from;

	public Recipient getFrom() {
		 return from; 
	}

	public void setFrom(Recipient value) { 
		from = value; 
	}

	@SerializedName("Sender")
	@Expose
	private Recipient sender;

	public Recipient getSender() {
		 return sender; 
	}

	public void setSender(Recipient value) { 
		sender = value; 
	}

	@SerializedName("ToRecipients")
	@Expose
	private java.util.List<Recipient> toRecipients;

	public java.util.List<Recipient> getToRecipients() {
		 return toRecipients; 
	}

	public void setToRecipients(java.util.List<Recipient> value) { 
		toRecipients = value; 
	}

	@SerializedName("CcRecipients")
	@Expose
	private java.util.List<Recipient> ccRecipients;

	public java.util.List<Recipient> getCcRecipients() {
		 return ccRecipients; 
	}

	public void setCcRecipients(java.util.List<Recipient> value) { 
		ccRecipients = value; 
	}

	@SerializedName("BccRecipients")
	@Expose
	private java.util.List<Recipient> bccRecipients;

	public java.util.List<Recipient> getBccRecipients() {
		 return bccRecipients; 
	}

	public void setBccRecipients(java.util.List<Recipient> value) { 
		bccRecipients = value; 
	}

	@SerializedName("ReplyTo")
	@Expose
	private java.util.List<Recipient> replyTo;

	public java.util.List<Recipient> getReplyTo() {
		 return replyTo; 
	}

	public void setReplyTo(java.util.List<Recipient> value) { 
		replyTo = value; 
	}

	@SerializedName("ConversationId")
	@Expose
	private String conversationId;

	public String getConversationId() {
		 return conversationId; 
	}

	public void setConversationId(String value) { 
		conversationId = value; 
	}

	@SerializedName("UniqueBody")
	@Expose
	private ItemBody uniqueBody;

	public ItemBody getUniqueBody() {
		 return uniqueBody; 
	}

	public void setUniqueBody(ItemBody value) { 
		uniqueBody = value; 
	}

	@SerializedName("DateTimeReceived")
	@Expose
	private java.util.Date dateTimeReceived;

	public java.util.Date getDateTimeReceived() {
		 return dateTimeReceived; 
	}

	public void setDateTimeReceived(java.util.Date value) { 
		dateTimeReceived = value; 
	}

	@SerializedName("DateTimeSent")
	@Expose
	private java.util.Date dateTimeSent;

	public java.util.Date getDateTimeSent() {
		 return dateTimeSent; 
	}

	public void setDateTimeSent(java.util.Date value) { 
		dateTimeSent = value; 
	}

	@SerializedName("IsDeliveryReceiptRequested")
	@Expose
	private Boolean isDeliveryReceiptRequested;

	public Boolean getIsDeliveryReceiptRequested() {
		 return isDeliveryReceiptRequested; 
	}

	public void setIsDeliveryReceiptRequested(Boolean value) { 
		isDeliveryReceiptRequested = value; 
	}

	@SerializedName("IsReadReceiptRequested")
	@Expose
	private Boolean isReadReceiptRequested;

	public Boolean getIsReadReceiptRequested() {
		 return isReadReceiptRequested; 
	}

	public void setIsReadReceiptRequested(Boolean value) { 
		isReadReceiptRequested = value; 
	}

	@SerializedName("IsDraft")
	@Expose
	private Boolean isDraft;

	public Boolean getIsDraft() {
		 return isDraft; 
	}

	public void setIsDraft(Boolean value) { 
		isDraft = value; 
	}

	@SerializedName("IsRead")
	@Expose
	private Boolean isRead;

	public Boolean getIsRead() {
		 return isRead; 
	}

	public void setIsRead(Boolean value) { 
		isRead = value; 
	}

	@SerializedName("EventId")
	@Expose
	private String eventId;

	public String getEventId() {
		 return eventId; 
	}

	public void setEventId(String value) { 
		eventId = value; 
	}

	@SerializedName("MeetingMessageType")
	@Expose
	private MeetingMessageType meetingMessageType;

	public MeetingMessageType getMeetingMessageType() {
		 return meetingMessageType; 
	}

	public void setMeetingMessageType(MeetingMessageType value) { 
		meetingMessageType = value; 
	}

	@SerializedName("DateTimeCreated")
	@Expose
	private java.util.Date dateTimeCreated;

	public java.util.Date getDateTimeCreated() {
		 return dateTimeCreated; 
	}

	public void setDateTimeCreated(java.util.Date value) { 
		dateTimeCreated = value; 
	}

	@SerializedName("LastModifiedTime")
	@Expose
	private java.util.Date lastModifiedTime;

	public java.util.Date getLastModifiedTime() {
		 return lastModifiedTime; 
	}

	public void setLastModifiedTime(java.util.Date value) { 
		lastModifiedTime = value; 
	}

	public MessageActions getOperations(){
		return new MessageActions(this.getId());
	}

}