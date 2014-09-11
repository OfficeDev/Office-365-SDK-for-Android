package com.microsoft.office365.api;

import com.microsoft.office365.oauth.OAuthCredentials;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.Contact;
import com.microsoft.office.microsoft.exchange.services.odata.model.types.ContactCollection;

/**
 * The Class ContactClient.
 */
public class ContactClient extends BaseOfficeClient {

	private Builder mBuilder;
	
	protected ContactClient(ContactClient.Builder builder) {
		super(builder);
		
		mBuilder = builder;
	}

	/**
	 * New contact.
	 * 
	 * @return the contact
	 */
	public Contact newContact() {

		// return container.getMe().getContacts().newContact();

		return null;
	}

	public void addContact(Contact contact) {
		container.getMe().getContacts().add(contact);
		container.flush();
	}

	public void deleteContact(String contactId) {

		Contact contact = container.getMe().getContacts().getByKey(contactId);
		if (contact != null){
			container.getMe().getContacts().delete(contact);
			container.flush();
		}
	}

	public void deleteContact(Contact contact) {
		container.getMe().getContacts().delete(contact); // TODO:REVIEW
		container.flush();
	}

	/**
	 * Gets the contacts.
	 * 
	 * @return the contacts
	 */
	public ContactCollection getContacts() {

		ContactCollection contacts = container.getMe().getContacts()
											  .top(mBuilder.getMaxResults())
				.execute();
		return contacts;

		/*
		 * List<IContact> contacts = new ArrayList<IContact>(); try { IContacts
		 * proxy = Me.getContacts(); Query<IContact, IContactCollection> query =
		 * proxy.createQuery(); query.setMaxResults(10); contacts = new
		 * ArrayList<IContact>(query.getResult());
		 * 
		 * } catch (Exception e) { // Log } return contacts;
		 */
		// return null;
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
		public Builder(OAuthCredentials credentials, String resourceId,
				String odataEndpoint) {
			super(credentials, resourceId, odataEndpoint);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.microsoft.office365.api.BaseOfficeClient.Builder#build()
		 */
		@Override
		public ContactClient build() {
			return new ContactClient(this);
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
		public int getMaxResults() {
			return mMaxResults;
		}
	}

}
