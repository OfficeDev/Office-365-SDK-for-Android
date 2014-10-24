/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Service Principal.
*/
public class ServicePrincipal extends DirectoryObject {

	public ServicePrincipal(){
		setODataType("#Microsoft.DirectoryServices.ServicePrincipal");
	}

	private Boolean accountEnabled;

	/**
	* Gets the account Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getaccountEnabled() {
		return this.accountEnabled; 
	}

	/**
	* Sets the account Enabled.
	*
	* @param value the Boolean
	*/
	public void setaccountEnabled(Boolean value) { 
		this.accountEnabled = value; 
	}
	private String appDisplayName;

	/**
	* Gets the app Display Name.
	*
	* @return the String
	*/
	public String getappDisplayName() {
		return this.appDisplayName; 
	}

	/**
	* Sets the app Display Name.
	*
	* @param value the String
	*/
	public void setappDisplayName(String value) { 
		this.appDisplayName = value; 
	}
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
		this.appId = value; 
	}
	private java.util.UUID appOwnerTenantId;

	/**
	* Gets the app Owner Tenant Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getappOwnerTenantId() {
		return this.appOwnerTenantId; 
	}

	/**
	* Sets the app Owner Tenant Id.
	*
	* @param value the java.util.UUID
	*/
	public void setappOwnerTenantId(java.util.UUID value) { 
		this.appOwnerTenantId = value; 
	}
	private Boolean appRoleAssignmentRequired;

	/**
	* Gets the app Role Assignment Required.
	*
	* @return the Boolean
	*/
	public Boolean getappRoleAssignmentRequired() {
		return this.appRoleAssignmentRequired; 
	}

	/**
	* Sets the app Role Assignment Required.
	*
	* @param value the Boolean
	*/
	public void setappRoleAssignmentRequired(Boolean value) { 
		this.appRoleAssignmentRequired = value; 
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
		this.appRoles = value; 
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
		this.displayName = value; 
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
		this.errorUrl = value; 
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
		this.homepage = value; 
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
		this.keyCredentials = value; 
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
		this.logoutUrl = value; 
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
		this.oauth2Permissions = value; 
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
		this.passwordCredentials = value; 
	}
	private String preferredTokenSigningKeyThumbprint;

	/**
	* Gets the preferred Token Signing Key Thumbprint.
	*
	* @return the String
	*/
	public String getpreferredTokenSigningKeyThumbprint() {
		return this.preferredTokenSigningKeyThumbprint; 
	}

	/**
	* Sets the preferred Token Signing Key Thumbprint.
	*
	* @param value the String
	*/
	public void setpreferredTokenSigningKeyThumbprint(String value) { 
		this.preferredTokenSigningKeyThumbprint = value; 
	}
	private String publisherName;

	/**
	* Gets the publisher Name.
	*
	* @return the String
	*/
	public String getpublisherName() {
		return this.publisherName; 
	}

	/**
	* Sets the publisher Name.
	*
	* @param value the String
	*/
	public void setpublisherName(String value) { 
		this.publisherName = value; 
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
		this.replyUrls = value; 
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
		this.samlMetadataUrl = value; 
	}
	private java.util.List<String> servicePrincipalNames;

	/**
	* Gets the service Principal Names.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getservicePrincipalNames() {
		return this.servicePrincipalNames; 
	}

	/**
	* Sets the service Principal Names.
	*
	* @param value the java.util.List<String>
	*/
	public void setservicePrincipalNames(java.util.List<String> value) { 
		this.servicePrincipalNames = value; 
	}
	private java.util.List<String> tags;

	/**
	* Gets the tags.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> gettags() {
		return this.tags; 
	}

	/**
	* Sets the tags.
	*
	* @param value the java.util.List<String>
	*/
	public void settags(java.util.List<String> value) { 
		this.tags = value; 
	}
}