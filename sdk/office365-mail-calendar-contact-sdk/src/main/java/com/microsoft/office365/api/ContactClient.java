package com.microsoft.office365.api;

import com.microsoft.office365.oauth.OAuthCredentials;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.Contact;
import com.microsoft.office365.microsoft.exchange.services.odata.model.types.ContactCollection;

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

        return getEntityContainer().newEntityInstance(Contact.class);

    }

    public void addContact(Contact contact) {
        getEntityContainer().getMe().getContacts().add(contact);
        getEntityContainer().flush();
    }

    public void deleteContact(String contactId) {

        Contact contact = getEntityContainer().getMe().getContacts().getByKey(contactId);
        if (contact != null) {
            getEntityContainer().getMe().getContacts().delete(contact);
            getEntityContainer().flush();
        }
    }

    public void deleteContact(Contact contact) {
        getEntityContainer().getMe().getContacts().delete(contact); // TODO:REVIEW
        getEntityContainer().flush();
    }

    /**
     * Gets the contacts.
     *
     * @return the contacts
     */
    public ContactCollection getContacts() {

        ContactCollection contacts = getEntityContainer().getMe().getContacts()
                .top(mBuilder.getMaxResults())
                .execute();
        return contacts;
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
         * @param credentials   the credentials
         * @param resourceId    the resource id
         * @param odataEndpoint the odata endpoint
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
        public Builder setODataEndpoint(String odataEndpoint) {
            return (Builder) super.setODataEndpoint(odataEndpoint);
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
         * @param maxResults the max results
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
