/*******************************************************************************
**NOTE** This code was generated by a tool and will occasionally be
overwritten. We welcome comments and issues regarding this code; they will be
addressed in the generation tool. If you wish to submit pull requests, please
do so for the templates in that tool.

This code was generated by Vipr (https://github.com/microsoft/vipr) using
the T4TemplateWriter (https://github.com/msopentech/vipr-t4templatewriter).

Copyright (c) Microsoft Open Technologies, Inc. All Rights Reserved.
Licensed under the Apache License 2.0; see LICENSE in the source repository
root for authoritative license information.﻿
******************************************************************************/
package com.microsoft.services.graph;

import com.microsoft.services.orc.core.ODataBaseEntity;


/**
 * The type Provisioned Plan.
*/
public class ProvisionedPlan extends ODataBaseEntity {

    public ProvisionedPlan(){
        setODataType("#Microsoft.Graph.ProvisionedPlan");
    }

    private String capabilityStatus;

    /**
    * Gets the capability Status.
    *
    * @return the String
    */
    public String getCapabilityStatus() {
        return this.capabilityStatus; 
    }

    /**
    * Sets the capability Status.
    *
    * @param value the String
    */
    public void setCapabilityStatus(String value) { 
        this.capabilityStatus = value;
        valueChanged("capabilityStatus", value);

    }

    private String provisioningStatus;

    /**
    * Gets the provisioning Status.
    *
    * @return the String
    */
    public String getProvisioningStatus() {
        return this.provisioningStatus; 
    }

    /**
    * Sets the provisioning Status.
    *
    * @param value the String
    */
    public void setProvisioningStatus(String value) { 
        this.provisioningStatus = value;
        valueChanged("provisioningStatus", value);

    }

    private String service;

    /**
    * Gets the service.
    *
    * @return the String
    */
    public String getService() {
        return this.service; 
    }

    /**
    * Sets the service.
    *
    * @param value the String
    */
    public void setService(String value) { 
        this.service = value;
        valueChanged("service", value);

    }
}