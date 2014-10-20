/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices;

/**
 * The Enum Meeting Message Type.
*/
public enum MeetingMessageType	
{
    /**
	* None
	*/
	None,
    /**
	* Meeting Request
	*/
	MeetingRequest,
    /**
	* Meeting Cancelled
	*/
	MeetingCancelled,
    /**
	* Meeting Accepted
	*/
	MeetingAccepted,
    /**
	* Meeting Tenatively Accepted
	*/
	MeetingTenativelyAccepted,
    /**
	* Meeting Declined
	*/
	MeetingDeclined,
}