/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.file.services.model;

public class DriveQuota {

	private long deleted;

	public long getdeleted() {
		 return deleted; 
	}

	public void setdeleted(long value) { 
		deleted = value; 
	}

	private long remaining;

	public long getremaining() {
		 return remaining; 
	}

	public void setremaining(long value) { 
		remaining = value; 
	}

	private String state;

	public String getstate() {
		 return state; 
	}

	public void setstate(String value) { 
		state = value; 
	}

	private long total;

	public long gettotal() {
		 return total; 
	}

	public void settotal(long value) { 
		total = value; 
	}
}