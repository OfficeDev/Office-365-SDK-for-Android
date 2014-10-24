/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.fileservices;

/**
 * The type ODataBaseEntity.
*/
public abstract class ODataBaseEntity {

   protected String $$__ODataType;

   protected void setODataType(String value){
		this.$$__ODataType = value;
   }

}