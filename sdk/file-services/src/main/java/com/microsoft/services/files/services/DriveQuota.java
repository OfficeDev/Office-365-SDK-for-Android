/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.files.services;

/**
 * The type DriveQuota.
*/
public class DriveQuota {

	private long deleted;

	 /**
     * Gets the deleted.		
     *
     * @return the deleted
     */
	public long getdeleted() {
		 return deleted; 
	}

	 /**
     * Sets the deleted.		
     *
     * @param value the value
     */
	public void setdeleted(long value) { 
		deleted = value; 
	}

	private long remaining;

	 /**
     * Gets the remaining.		
     *
     * @return the remaining
     */
	public long getremaining() {
		 return remaining; 
	}

	 /**
     * Sets the remaining.		
     *
     * @param value the value
     */
	public void setremaining(long value) { 
		remaining = value; 
	}

	private String state;

	 /**
     * Gets the state.		
     *
     * @return the state
     */
	public String getstate() {
		 return state; 
	}

	 /**
     * Sets the state.		
     *
     * @param value the value
     */
	public void setstate(String value) { 
		state = value; 
	}

	private long total;

	 /**
     * Gets the total.		
     *
     * @return the total
     */
	public long gettotal() {
		 return total; 
	}

	 /**
     * Sets the total.		
     *
     * @param value the value
     */
	public void settotal(long value) { 
		total = value; 
	}
}