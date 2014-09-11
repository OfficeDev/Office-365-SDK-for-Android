package com.microsoft.office365.api;

import com.microsoft.office365.oauth.OAuthCredentials;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.FileAttachment;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Folder;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Folder.ChildFolders;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.ItemAttachment;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Message;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.MessageCollection;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Recipient;

/**
 * The Class MailClient.
 */
public class MailClient extends BaseOfficeClient {

	Builder mBuilder;

	protected MailClient(MailClient.Builder builder) {
		super(builder);
		mBuilder = builder;
	}

	/**
	 * New message.
	 * 
	 * @return the message
	 */
	public Message newMessage() {
		return container.newEntityInstance(Message.class);

	}

	/**
	 * Save.
	 * 
	 * @param message
	 *            the message
	 */
	public void save(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		container.flush();
	}

	/**
	 * Send.
	 * 
	 * @param message
	 *            the message
	 */
	public void send(Message message) {
		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		message.operations().send().execute();
	}

	/**
	 * Creates the reply.
	 * 
	 * @param message
	 *            the message
	 * @return the message
	 */
	public Message createReply(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		Message replyMessage = message.operations().createReply().execute();
		return replyMessage;
	}

	// TODO:Is this one necessary or createReply is enough??
	/**
	 * Creates the reply all.
	 * 
	 * @param message
	 *            the message
	 * @return the message
	 */
	public Message createReplyAll(Message message) {
		return null;
	}

	/**
	 * Reply.
	 * 
	 * @param messageId
	 *            the message id
	 * @param comment
	 *            the comment
	 */
	public void reply(String messageId, String comment) {
		Message message = container.getMe().getMessages().getByKey(messageId);
		if (message != null) {
			message.operations().reply(comment).execute();
		}
	}

	/**
	 * Reply.
	 * 
	 * @param message
	 *            the message
	 * @param comment
	 *            the comment
	 */
	public void reply(Message message, String comment) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		message.operations().reply(comment).execute();
		container.flush(); // Required ? execute always flush?
	}

	/**
	 * Reply all.
	 * 
	 * @param messageId
	 *            the message id
	 * @param comment
	 *            the comment
	 */
	public void replyAll(String messageId, String comment) {

		Message message = container.getMe().getMessages().getByKey(messageId);

		if (message != null) {
			message.operations().replyAll(comment).execute();
			container.flush(); // Required?
		}
	}

	/**
	 * Reply all.
	 * 
	 * @param message
	 *            the message
	 * @param comment
	 *            the comment
	 */
	public void replyAll(Message message, String comment) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}

		message.operations().replyAll(comment);
		container.flush();
	}

	/**
	 * Forward.
	 * 
	 * @param message
	 *            the message
	 * @param comment
	 *            the comment
	 * @param recipients
	 *            the recipients
	 */
	public void forward(Message message, String comment, Recipient... recipients) {

	}

	/**
	 * Creates the file attachment.
	 * 
	 * @param message
	 *            the message
	 * @return the file attachment
	 */
	public FileAttachment createFileAttachment(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}

		FileAttachment fileAttachment = container.newEntityInstance(FileAttachment.class);
		message.getAttachments().add(fileAttachment);
		return fileAttachment;
	}

	/**
	 * Creates the item attachment.
	 * 
	 * @param message
	 *            the message
	 * @return the item attachment
	 */
	public ItemAttachment createItemAttachment(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}

		ItemAttachment itemAttachment = container.newEntityInstance(ItemAttachment.class);
		message.getAttachments().add(itemAttachment);
		return itemAttachment;
	}

	/**
	 * Move.
	 * 
	 * @param message
	 *            the message
	 * @param destinationFolder
	 *            the destination folder
	 */
	public Message move(Message message, String destinationFolder) {
		Folder folder = container.getMe().getFolders().getByKey(destinationFolder);

		if (folder != null) {
			return message.operations().move(folder.getId()).execute();
		}
		return null;
	}

	/**
	 * Move.
	 * 
	 * @param message
	 *            the message
	 * @param destinationFolder
	 *            the destination folder
	 */
	public Message move(Message message, Folder destinationFolder) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}

		if (destinationFolder == null) {
			throw new IllegalArgumentException("destinationFolder cannot be null");
		}

		return move(message, destinationFolder.getId());
	}

	/**
	 * Delete.
	 * 
	 * @param message
	 *            the message
	 */
	public void delete(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}

		container.getMe().getMessages().delete(message.getId());
		container.flush();
	}

	// this could be the same as delete but only applies to drafts
	/**
	 * Discard.
	 * 
	 * @param message
	 *            the message
	 */
	public void discard(Message message) {

	}

	/**
	 * Mark as read.
	 * 
	 * @param message
	 *            the message
	 */
	public void markAsRead(Message message) {
		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		message.setIsRead(true);
		container.flush();
	}

	/**
	 * Mark as unread.
	 * 
	 * @param message
	 *            the message
	 */
	public void markAsUnread(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		message.setIsRead(false);
		container.flush();
	}

	/**
	 * Gets the child folders.
	 * 
	 * @return the child folders
	 */
	public ChildFolders getChildFolders() {

		ChildFolders childFolders = container.getMe().getRootFolder().getChildFolders();
		return childFolders;
	}

	/**
	 * Gets the messages.
	 * 
	 * @param folderId
	 *            the folder id
	 * @param from
	 *            the from
	 * @return the messages
	 */
	public MessageCollection getMessages(String folderId, int from) {

		MessageCollection messages = null;

		try {

			messages = container.getMe().getFolders().getByKey(folderId).getMessages()
					.select("Id,From,Sender,Subject,BodyPreview,DateTimeSent,LastModifiedTime")
					.expand("From,Sender")
					.skip(from).top(mBuilder.getMaxRsults()).execute();

		} catch (Throwable t) {
			//Log.e("Client", t.getMessage());
		}

		return messages;

	}

	/**
	 * Gets the messages.
	 * 
	 * @param folderId
	 *            the folder id
	 * @return the messages
	 */
	public MessageCollection getMessages(String folderId) {

		MessageCollection messages = null;
		try {
			messages = container.getMe().getFolders().getByKey(folderId).getMessages()
					.select("Id,From,Sender,Subject,BodyPreview,DateTimeSent,LastModifiedTime")
					.expand("From,Sender")
					.top(mBuilder.getMaxRsults()).execute();
		} catch (Throwable t) {
			//Log.e("Client", t.getMessage());
		}
		return messages;
	}

	/**
	 * The Class Builder.
	 */
	public static final class Builder extends BaseOfficeClient.Builder {

		private int mMaxResults;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			super();
		}

		/**
		 * Instantiates a new builder.
		 * 
		 * @param credentials
		 *            the credentials
		 * @param resourceId
		 *            the resource id
		 * @param odataEndpoint
		 *            the odata endpoint
		 */
		public Builder(OAuthCredentials credentials, String resourceId, String odataEndpoint) {
			super(credentials, resourceId, odataEndpoint);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.microsoft.office365.api.BaseOfficeClient.Builder#build()
		 */
		@Override
		public MailClient build() {
			// TODO:Singleton?
			return new MailClient(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.microsoft.office365.api.BaseOfficeClient.Builder#setCredentials
		 * (com.microsoft.office365.http.OAuthCredentials)
		 */
		@Override
		public Builder setCredentials(OAuthCredentials credentials) {
			return (Builder) super.setCredentials(credentials);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.microsoft.office365.api.BaseOfficeClient.Builder#setOdataEndpoint
		 * (java.lang.String)
		 */
		@Override
		public Builder setOdataEndpoint(String odataEndpoint) {
			return (Builder) super.setOdataEndpoint(odataEndpoint);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.microsoft.office365.api.BaseOfficeClient.Builder#setResourceId
		 * (java.lang.String)
		 */
		@Override
		public Builder setResourceId(String resourceId) {
			return (Builder) super.setResourceId(resourceId);
		}

		/**
		 * Sets the max results.
		 * 
		 * @param maxResults
		 *            the max results
		 * @return the builder
		 */
		public Builder setMaxResults(int maxResults) {
			mMaxResults = maxResults;
			return this;
		}

		/**
		 * Gets the max rsults.
		 * 
		 * @return the max rsults
		 */
		public int getMaxRsults() {
			return mMaxResults;
		}
	}
}
