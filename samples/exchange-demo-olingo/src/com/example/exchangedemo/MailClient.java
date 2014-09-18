package com.example.exchangedemo;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.office365.http.OAuthCredentials;


/**
 * The Class MailClient.
 */
public class MailClient {

	OAuthCredentials mCredentials;	

	protected MailClient(OAuthCredentials credentials, String url){
		mCredentials = credentials;
	}
	/**
	 * Create message.
	 *
	 * @return the message
	 */
	public ListenableFuture<Message> createMessage() {
		return null;
	}

	/**
	 * Gets the messages.
	 *
	 * @param folderId the folder id
	 * @return the messages
	 */
	public ListenableFuture<List<Message>> getMessages(String folderId) {		
		return null;
	}

	/**
	 * Sends a Message.
	 *
	 * @param message the message
	 */
	public void sendMessage(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("message cannot be null");
		}
		
		// TODO: Send message
	}

	//	/**
	//	 * Gets a Message with a given folder id and message id.
	//	 *
	//	 * @param folderId  the folder id
	//	 * @param messageId the message id
	//	 * @return the message
	//	 */
	//	public Message getMessage(String folderId, String messageId) {
	//		return null;
	//	}
	//
	//	/**
	//	 * Creates the reply.
	//	 *
	//	 * @param message the message
	//	 * @return the message
	//	 */
	//	public Message createReply(Message message) {
	//		return null;
	//	}
	//
	//	/**
	//	 * Creates the reply all.
	//	 *
	//	 * @param message the message
	//	 * @return the message
	//	 */
	//	public Message createReplyAll(Message message) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Reply.
	//	 *
	//	 * @param messageId the message id
	//	 * @param comment   the comment
	//	 */
	//	public void reply(String messageId, String comment) {
	//
	//	}
	//
	//	/**
	//	 * Reply.
	//	 *
	//	 * @param message the message
	//	 * @param comment the comment
	//	 */
	//	public void reply(Message message, String comment) {
	//
	//	}
	//
	//	/**
	//	 * Reply all.
	//	 *
	//	 * @param messageId the message id
	//	 * @param comment   the comment
	//	 */
	//	public void replyAll(String messageId, String comment) {
	//
	//	}
	//
	//	/**
	//	 * Reply all.
	//	 *
	//	 * @param message the message
	//	 * @param comment the comment
	//	 */
	//	public void replyAll(Message message, String comment) {
	//
	//	}
	//
	//	/**
	//	 * Creates the file attachment.
	//	 *
	//	 * @param message the message
	//	 * @param name    the FileAttachment name
	//	 * @param data    the data
	//	 * @return the file attachment
	//	 */
	//	public Attachment addAttachment(Message message, String name, byte[] data) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Creates the item attachment.
	//	 *
	//	 * @param message the message
	//	 * @param name    the ItemAttachment name
	//	 * @param item    the ItemAttachment
	//	 * @return the ItemAttachment
	//	 */
	//	public ItemAttachment addAttachment(Message message, String name, Item item) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Move.
	//	 *
	//	 * @param message           the message
	//	 * @param destinationFolder the destination folder
	//	 */
	//	public Message moveMessage(Message message, Folder destinationFolder) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Move Message.
	//	 *
	//	 * @param messageId           the message
	//	 * @param destinationFolderId the destination folder
	//	 */
	//	public Message moveMessage(String messageId, String destinationFolderId) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Copy Message.
	//	 *
	//	 * @param messageId           the message
	//	 * @param destinationFolderId the destination folder
	//	 */
	//	public Message copyMessage(String messageId, String destinationFolderId) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Delete Message.
	//	 *
	//	 * @param messageId the message
	//	 */
	//	public void deleteMessage(String messageId) {
	//
	//	}
	//
	//	/**
	//	 * Creates a Folder with given folder name.
	//	 *
	//	 * @param parentFolderId the parent folder id
	//	 * @param folderName     the folder name
	//	 */
	//	public Folder createFolder(String parentFolderId, String folderName) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Move Folder.
	//	 *
	//	 * @param folder         the message
	//	 * @param parentFolderId the destination folder
	//	 */
	//	public Folder moveFolder(Folder folder, String parentFolderId) {
	//
	//		return null;
	//	}
	//
	//	/**
	//	 * Deletes a Folder with given folder Id.
	//	 *
	//	 * @param folderId the message
	//	 */
	//	public void deleteFolder(String folderId) {
	//
	//
	//	}
	//	
	//	/**
	//	 * Gets the messages.
	//	 *
	//	 * @param folderId the folder id
	//	 * @param skip     the from
	//	 * @return the messages
	//	 */
	//	public List<Message> getMessages(String folderId, int skip) {
	//
	//		return null;
	//
	//		//return getMessages(folderId, null, null, skip, 0);
	//	}
	//
	//	/**
	//	 * Gets the messages.
	//	 *
	//	 * @param folderId the folder id
	//	 * @param skip     the from
	//	 * @param top      the top
	//	 * @return the messages
	//	 */
	//	public List<Message> getMessages(String folderId, int skip, int top) {
	//		return null;
	//	}
	//
	//	/**
	//	 * Gets the messages.
	//	 *
	//	 * @param folderId     the folder id
	//	 * @param selectClause the select clause
	//	 * @return the messages
	//	 */
	//	public List<Message> getMessages(String folderId, String selectClause) {
	//		return null;
	//	}
	//
	//	/**
	//	 * Gets the messages.
	//	 *
	//	 * @param folderId     the folder id
	//	 * @param selectClause the select clause
	//	 * @param expandClause the expand clause
	//	 * @return the messages
	//	 */
	//	public List<Message> getMessages(String folderId, String selectClause,
	//			String expandClause) {
	//		return null;
	//	}
	//
	//	/**
	//	 * Gets the messages.
	//	 *
	//	 * @param folderId     the folder id
	//	 * @param selectClause the select clause
	//	 * @param expandClause the expand clause
	//	 * @param skip         number of records to skip
	//	 * @param top          number of records to return
	//	 * @return the messages
	//	 */
	//	public List<Message> getMessages(String folderId, String selectClause,
	//			String expandClause, int skip, int top) {
	//
	//		return null;
	//	}

	//    private Folder.Messages addSkipAndTop(int skip, int topResults, Folder.Messages messages) {
	//
	//    	return null;
	//    }

	/**
	 * The Class Builder.
	 */
	//    public static final class Builder extends BaseOfficeClient.Builder {
	//
	//        private int mMaxResults;
	//
	//        /**
	//         * Instantiates a new builder.
	//         */
	//        public Builder() {
	//            super();
	//        }
	//
	//        /**
	//         * Instantiates a new builder.
	//         *
	//         * @param credentials   the credentials
	//         * @param resourceId    the resource id
	//         * @param odataEndpoint the odata endpoint
	//         */
	//        public Builder(OAuthCredentials credentials, String resourceId, String odataEndpoint) {
	//            super(credentials, resourceId, odataEndpoint);
	//        }
	//
	//        /*
	//         * (non-Javadoc)
	//         *
	//         * @see com.microsoft.office365.api.BaseOfficeClient.Builder#build()
	//         */
	//        @Override
	//        public MailClient build() {
	//            // TODO:Singleton?
	//            return new MailClient(this);
	//        }
	//
	//        /*
	//         * (non-Javadoc)
	//         *
	//         * @see
	//         * com.microsoft.office365.api.BaseOfficeClient.Builder#setCredentials
	//         * (com.microsoft.office365.http.OAuthCredentials)
	//         */
	//        @Override
	//        public Builder setCredentials(OAuthCredentials credentials) {
	//            return (Builder) super.setCredentials(credentials);
	//        }
	//
	//        /*
	//         * (non-Javadoc)
	//         *
	//         * @see
	//         * com.microsoft.office365.api.BaseOfficeClient.Builder#setOdataEndpoint
	//         * (java.lang.String)
	//         */
	//        @Override
	//        public Builder setODataEndpoint(String odataEndpoint) {
	//            return (Builder) super.setODataEndpoint(odataEndpoint);
	//        }
	//
	//        /*
	//         * (non-Javadoc)
	//         *
	//         * @see
	//         * com.microsoft.office365.api.BaseOfficeClient.Builder#setResourceId
	//         * (java.lang.String)
	//         */
	//        @Override
	//        public Builder setResourceId(String resourceId) {
	//            return (Builder) super.setResourceId(resourceId);
	//        }
	//
	//        /**
	//         * Sets the max results.
	//         *
	//         * @param maxResults the max results
	//         * @return the builder
	//         */
	//        public Builder setMaxDefaultResults(int maxResults) {
	//            mMaxResults = maxResults;
	//            return this;
	//        }
	//
	//        /**
	//         * Gets the max results.
	//         *
	//         * @return the max results
	//         */
	//        public int getMaxDefaultResults() {
	//            return mMaxResults;
	//        }
	//    }
}