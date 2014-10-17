/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type Drive Quota.
*/
public class DriveQuota {

	private long deleted;

	/**
	* Gets the deleted.
	*
	* @return the long
	*/
	public long getdeleted() {
		 return deleted; 
	}

	/**
	* Sets the deleted.
	*
	* @param value the long
	*/
	public void setdeleted(long value) { 
		deleted = value; 
	}

	private long remaining;

	/**
	* Gets the remaining.
	*
	* @return the long
	*/
	public long getremaining() {
		 return remaining; 
	}

	/**
	* Sets the remaining.
	*
	* @param value the long
	*/
	public void setremaining(long value) { 
		remaining = value; 
	}

	private String state;

	/**
	* Gets the state.
	*
	* @return the String
	*/
	public String getstate() {
		 return state; 
	}

	/**
	* Sets the state.
	*
	* @param value the String
	*/
	public void setstate(String value) { 
		state = value; 
	}

	private long total;

	/**
	* Gets the total.
	*
	* @return the long
	*/
	public long gettotal() {
		 return total; 
	}

	/**
	* Sets the total.
	*
	* @param value the long
	*/
	public void settotal(long value) { 
		total = value; 
	}
}