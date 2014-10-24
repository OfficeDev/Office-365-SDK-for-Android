/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Device Configuration.
*/
public class DeviceConfiguration extends DirectoryObject {

	public DeviceConfiguration(){
		setODataType("#Microsoft.DirectoryServices.DeviceConfiguration");
	}

	private java.util.List<byte[]> publicIssuerCertificates;

	/**
	* Gets the public Issuer Certificates.
	*
	* @return the java.util.List<byte[]>
	*/
	public java.util.List<byte[]> getpublicIssuerCertificates() {
		return this.publicIssuerCertificates; 
	}

	/**
	* Sets the public Issuer Certificates.
	*
	* @param value the java.util.List<byte[]>
	*/
	public void setpublicIssuerCertificates(java.util.List<byte[]> value) { 
		this.publicIssuerCertificates = value; 
	}
	private java.util.List<byte[]> cloudPublicIssuerCertificates;

	/**
	* Gets the cloud Public Issuer Certificates.
	*
	* @return the java.util.List<byte[]>
	*/
	public java.util.List<byte[]> getcloudPublicIssuerCertificates() {
		return this.cloudPublicIssuerCertificates; 
	}

	/**
	* Sets the cloud Public Issuer Certificates.
	*
	* @param value the java.util.List<byte[]>
	*/
	public void setcloudPublicIssuerCertificates(java.util.List<byte[]> value) { 
		this.cloudPublicIssuerCertificates = value; 
	}
	private Integer registrationQuota;

	/**
	* Gets the registration Quota.
	*
	* @return the Integer
	*/
	public Integer getregistrationQuota() {
		return this.registrationQuota; 
	}

	/**
	* Sets the registration Quota.
	*
	* @param value the Integer
	*/
	public void setregistrationQuota(Integer value) { 
		this.registrationQuota = value; 
	}
	private Integer maximumRegistrationInactivityPeriod;

	/**
	* Gets the maximum Registration Inactivity Period.
	*
	* @return the Integer
	*/
	public Integer getmaximumRegistrationInactivityPeriod() {
		return this.maximumRegistrationInactivityPeriod; 
	}

	/**
	* Sets the maximum Registration Inactivity Period.
	*
	* @param value the Integer
	*/
	public void setmaximumRegistrationInactivityPeriod(Integer value) { 
		this.maximumRegistrationInactivityPeriod = value; 
	}
}