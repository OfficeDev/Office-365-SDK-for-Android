/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.directoryservices;

/**
 * The type Device.
*/
public class Device extends DirectoryObject {

	public Device(){
		setODataType("#Microsoft.DirectoryServices.Device");
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
	private java.util.List<AlternativeSecurityId> alternativeSecurityIds;

	/**
	* Gets the alternative Security Ids.
	*
	* @return the java.util.List<AlternativeSecurityId>
	*/
	public java.util.List<AlternativeSecurityId> getalternativeSecurityIds() {
		return this.alternativeSecurityIds; 
	}

	/**
	* Sets the alternative Security Ids.
	*
	* @param value the java.util.List<AlternativeSecurityId>
	*/
	public void setalternativeSecurityIds(java.util.List<AlternativeSecurityId> value) { 
		this.alternativeSecurityIds = value; 
	}
	private java.util.Calendar approximateLastLogonTimestamp;

	/**
	* Gets the approximate Last Logon Timestamp.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getapproximateLastLogonTimestamp() {
		return this.approximateLastLogonTimestamp; 
	}

	/**
	* Sets the approximate Last Logon Timestamp.
	*
	* @param value the java.util.Calendar
	*/
	public void setapproximateLastLogonTimestamp(java.util.Calendar value) { 
		this.approximateLastLogonTimestamp = value; 
	}
	private java.util.UUID deviceId;

	/**
	* Gets the device Id.
	*
	* @return the java.util.UUID
	*/
	public java.util.UUID getdeviceId() {
		return this.deviceId; 
	}

	/**
	* Sets the device Id.
	*
	* @param value the java.util.UUID
	*/
	public void setdeviceId(java.util.UUID value) { 
		this.deviceId = value; 
	}
	private String deviceMetadata;

	/**
	* Gets the device Metadata.
	*
	* @return the String
	*/
	public String getdeviceMetadata() {
		return this.deviceMetadata; 
	}

	/**
	* Sets the device Metadata.
	*
	* @param value the String
	*/
	public void setdeviceMetadata(String value) { 
		this.deviceMetadata = value; 
	}
	private Integer deviceObjectVersion;

	/**
	* Gets the device Object Version.
	*
	* @return the Integer
	*/
	public Integer getdeviceObjectVersion() {
		return this.deviceObjectVersion; 
	}

	/**
	* Sets the device Object Version.
	*
	* @param value the Integer
	*/
	public void setdeviceObjectVersion(Integer value) { 
		this.deviceObjectVersion = value; 
	}
	private String deviceOSType;

	/**
	* Gets the device OSType.
	*
	* @return the String
	*/
	public String getdeviceOSType() {
		return this.deviceOSType; 
	}

	/**
	* Sets the device OSType.
	*
	* @param value the String
	*/
	public void setdeviceOSType(String value) { 
		this.deviceOSType = value; 
	}
	private String deviceOSVersion;

	/**
	* Gets the device OSVersion.
	*
	* @return the String
	*/
	public String getdeviceOSVersion() {
		return this.deviceOSVersion; 
	}

	/**
	* Sets the device OSVersion.
	*
	* @param value the String
	*/
	public void setdeviceOSVersion(String value) { 
		this.deviceOSVersion = value; 
	}
	private java.util.List<String> devicePhysicalIds;

	/**
	* Gets the device Physical Ids.
	*
	* @return the java.util.List<String>
	*/
	public java.util.List<String> getdevicePhysicalIds() {
		return this.devicePhysicalIds; 
	}

	/**
	* Sets the device Physical Ids.
	*
	* @param value the java.util.List<String>
	*/
	public void setdevicePhysicalIds(java.util.List<String> value) { 
		this.devicePhysicalIds = value; 
	}
	private String deviceTrustType;

	/**
	* Gets the device Trust Type.
	*
	* @return the String
	*/
	public String getdeviceTrustType() {
		return this.deviceTrustType; 
	}

	/**
	* Sets the device Trust Type.
	*
	* @param value the String
	*/
	public void setdeviceTrustType(String value) { 
		this.deviceTrustType = value; 
	}
	private Boolean dirSyncEnabled;

	/**
	* Gets the dir Sync Enabled.
	*
	* @return the Boolean
	*/
	public Boolean getdirSyncEnabled() {
		return this.dirSyncEnabled; 
	}

	/**
	* Sets the dir Sync Enabled.
	*
	* @param value the Boolean
	*/
	public void setdirSyncEnabled(Boolean value) { 
		this.dirSyncEnabled = value; 
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
	private java.util.Calendar lastDirSyncTime;

	/**
	* Gets the last Dir Sync Time.
	*
	* @return the java.util.Calendar
	*/
	public java.util.Calendar getlastDirSyncTime() {
		return this.lastDirSyncTime; 
	}

	/**
	* Sets the last Dir Sync Time.
	*
	* @param value the java.util.Calendar
	*/
	public void setlastDirSyncTime(java.util.Calendar value) { 
		this.lastDirSyncTime = value; 
	}
}