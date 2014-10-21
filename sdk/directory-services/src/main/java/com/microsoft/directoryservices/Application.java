/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Application.
*/
public class Application extends DirectoryObject {
	private String appId;

	/**
	* Gets the app Id.
	*
	* @return the String
	*/
	public String getappId() {
		return this.appId; 
	}

	/**
	* Sets the app Id.
	*
	* @param value the String
	*/
	public void setappId(String value) { 
		appId = value; 
	}
	private java.util.List<AppRole> appRoles;

	/**
	* Gets the app Roles.
	*
	* @return the java.util.List<AppRole>
	*/
	public java.util.List<AppRole> getappRoles() {
		return this.appRoles; 
	}

	/**
	* Sets the app Roles.
	*
	* @param value the java.util.List<AppRole>
	*/
	public void setappRoles(java.util.List<AppRole> value) { 
		appRoles = value; 
	}
	private Boolean availableToOtherTenants;

	/**
	* Gets the available To Other Tenants.
	*
	* @return the Boolean
	*/
	public Boolean getavailableToOtherTenants() {
		return this.availableToOtherTenants; 
	}

	/**
	* Sets the available To Other Tenants.
	*
	* @param value the Boolean
	*/
	public void setavailableToOtherTenants(Boolean value) { 
		availableToOtherTenants = value; 
	}
	private String displayName;

	/**
	* Gets the display Name.
	*
	* @return the String
	*/
	public String getdisplayName() {
		return this.displayName; 
	}

	/**
	* Sets the display Name.
	*
	* @param value the String
	*/
	public void setdisplayName(String value) { 
		displayName = value; 
	}
	private String errorUrl;

	/**
	* Gets the error Url.
	*
	* @return the String
	*/
	public String geterrorUrl() {
		return this.errorUrl; 
	}

	/**
	* Sets the error Url.
	*
	* @param value the String
	*/
	public void seterrorUrl(String value) { 
		errorUrl = value; 
	}
	private String groupMembershipClaims;

	/**
	* Gets the group Membership Claims.
	*
	* @return the String
	*/
	public String getgroupMembershipClaims() {
		return this.groupMembershipClaims; 
	}

	/**
	* Sets the group Membership Claims.
	*
	* @param value the String
	*/
	public void setgroupMembershipClaims(String value) { 
		groupMembershipClaims = value; 
	}
	private String homepage;

	/**
	* Gets the homepage.
	*
	* @return the String
	*/
	public String gethomepage() {
		return this.homepage; 
	}

	/**
	* Sets the homepage.
	*
	* @param value the String
	*/
	public void sethomepage(String value) { 
		homepage = value; 
	}
	private java.util.List<String> identifierUris;

	/**
	* Gets the identifier Uris.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getidentifierUris() {
		return this.identifierUris; 
	}

	/**
	* Sets the identifier Uris.
	*
	* @param value the java.util.List<String>
	*/
	public void setidentifierUris(java.util.List<String> value) { 
		identifierUris = value; 
	}
	private java.util.List<KeyCredential> keyCredentials;

	/**
	* Gets the key Credentials.
	*
	* @return the java.util.List<KeyCredential>
	*/
	public java.util.List<KeyCredential> getkeyCredentials() {
		return this.keyCredentials; 
	}

	/**
	* Sets the key Credentials.
	*
	* @param value the java.util.List<KeyCredential>
	*/
	public void setkeyCredentials(java.util.List<KeyCredential> value) { 
		keyCredentials = value; 
	}
	private java.util.List<java.util.UUID> knownClientApplications;

	/**
	* Gets the known Client Applications.
	*
	* @return the java.util.List<java.util.UUID>
	*/
	public java.util.List<java.util.UUID> getknownClientApplications() {
		return this.knownClientApplications; 
	}

	/**
	* Sets the known Client Applications.
	*
	* @param value the java.util.List<java.util.UUID>
	*/
	public void setknownClientApplications(java.util.List<java.util.UUID> value) { 
		knownClientApplications = value; 
	}
	private byte[] mainLogo;

	/**
	* Gets the main Logo.
	*
	* @return the byte[]
	*/
	public byte[] getmainLogo() {
		return this.mainLogo; 
	}

	/**
	* Sets the main Logo.
	*
	* @param value the byte[]
	*/
	public void setmainLogo(byte[] value) { 
		mainLogo = value; 
	}
	private String logoutUrl;

	/**
	* Gets the logout Url.
	*
	* @return the String
	*/
	public String getlogoutUrl() {
		return this.logoutUrl; 
	}

	/**
	* Sets the logout Url.
	*
	* @param value the String
	*/
	public void setlogoutUrl(String value) { 
		logoutUrl = value; 
	}
	private Boolean oauth2AllowImplicitFlow;

	/**
	* Gets the oauth2Allow Implicit Flow.
	*
	* @return the Boolean
	*/
	public Boolean getoauth2AllowImplicitFlow() {
		return this.oauth2AllowImplicitFlow; 
	}

	/**
	* Sets the oauth2Allow Implicit Flow.
	*
	* @param value the Boolean
	*/
	public void setoauth2AllowImplicitFlow(Boolean value) { 
		oauth2AllowImplicitFlow = value; 
	}
	private Boolean oauth2AllowUrlPathMatching;

	/**
	* Gets the oauth2Allow Url Path Matching.
	*
	* @return the Boolean
	*/
	public Boolean getoauth2AllowUrlPathMatching() {
		return this.oauth2AllowUrlPathMatching; 
	}

	/**
	* Sets the oauth2Allow Url Path Matching.
	*
	* @param value the Boolean
	*/
	public void setoauth2AllowUrlPathMatching(Boolean value) { 
		oauth2AllowUrlPathMatching = value; 
	}
	private java.util.List<OAuth2Permission> oauth2Permissions;

	/**
	* Gets the oauth2Permissions.
	*
	* @return the java.util.List<OAuth2Permission>
	*/
	public java.util.List<OAuth2Permission> getoauth2Permissions() {
		return this.oauth2Permissions; 
	}

	/**
	* Sets the oauth2Permissions.
	*
	* @param value the java.util.List<OAuth2Permission>
	*/
	public void setoauth2Permissions(java.util.List<OAuth2Permission> value) { 
		oauth2Permissions = value; 
	}
	private Boolean oauth2RequirePostResponse;

	/**
	* Gets the oauth2Require Post Response.
	*
	* @return the Boolean
	*/
	public Boolean getoauth2RequirePostResponse() {
		return this.oauth2RequirePostResponse; 
	}

	/**
	* Sets the oauth2Require Post Response.
	*
	* @param value the Boolean
	*/
	public void setoauth2RequirePostResponse(Boolean value) { 
		oauth2RequirePostResponse = value; 
	}
	private java.util.List<PasswordCredential> passwordCredentials;

	/**
	* Gets the password Credentials.
	*
	* @return the java.util.List<PasswordCredential>
	*/
	public java.util.List<PasswordCredential> getpasswordCredentials() {
		return this.passwordCredentials; 
	}

	/**
	* Sets the password Credentials.
	*
	* @param value the java.util.List<PasswordCredential>
	*/
	public void setpasswordCredentials(java.util.List<PasswordCredential> value) { 
		passwordCredentials = value; 
	}
	private Boolean publicClient;

	/**
	* Gets the public Client.
	*
	* @return the Boolean
	*/
	public Boolean getpublicClient() {
		return this.publicClient; 
	}

	/**
	* Sets the public Client.
	*
	* @param value the Boolean
	*/
	public void setpublicClient(Boolean value) { 
		publicClient = value; 
	}
	private java.util.List<String> replyUrls;

	/**
	* Gets the reply Urls.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getreplyUrls() {
		return this.replyUrls; 
	}

	/**
	* Sets the reply Urls.
	*
	* @param value the java.util.List<String>
	*/
	public void setreplyUrls(java.util.List<String> value) { 
		replyUrls = value; 
	}
	private java.util.List<RequiredResourceAccess> requiredResourceAccess;

	/**
	* Gets the required Resource Access.
	*
	* @return the java.util.List<RequiredResourceAccess>
	*/
	public java.util.List<RequiredResourceAccess> getrequiredResourceAccess() {
		return this.requiredResourceAccess; 
	}

	/**
	* Sets the required Resource Access.
	*
	* @param value the java.util.List<RequiredResourceAccess>
	*/
	public void setrequiredResourceAccess(java.util.List<RequiredResourceAccess> value) { 
		requiredResourceAccess = value; 
	}
	private String samlMetadataUrl;

	/**
	* Gets the saml Metadata Url.
	*
	* @return the String
	*/
	public String getsamlMetadataUrl() {
		return this.samlMetadataUrl; 
	}

	/**
	* Sets the saml Metadata Url.
	*
	* @param value the String
	*/
	public void setsamlMetadataUrl(String value) { 
		samlMetadataUrl = value; 
	}
}