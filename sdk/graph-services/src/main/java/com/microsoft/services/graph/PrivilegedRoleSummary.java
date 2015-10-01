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
 * The type Privileged Role Summary.
*/
public class PrivilegedRoleSummary extends ODataBaseEntity {

    public PrivilegedRoleSummary(){
        setODataType("#Microsoft.Graph.PrivilegedRoleSummary");
    }
            
    private java.util.UUID RoleId;
     
    /**
    * Gets the Role Id.
    *
    * @return the java.util.UUID
    */
    public java.util.UUID getRoleId() {
        return this.RoleId; 
    }

    /**
    * Sets the Role Id.
    *
    * @param value the java.util.UUID
    */
    public void setRoleId(java.util.UUID value) { 
        this.RoleId = value; 
        valueChanged("RoleId", value);

    }
            
    private RoleSummaryStatus Status;
     
    /**
    * Gets the Status.
    *
    * @return the RoleSummaryStatus
    */
    public RoleSummaryStatus getStatus() {
        return this.Status; 
    }

    /**
    * Sets the Status.
    *
    * @param value the RoleSummaryStatus
    */
    public void setStatus(RoleSummaryStatus value) { 
        this.Status = value; 
        valueChanged("Status", value);

    }
            
    private Integer UsersCount;
     
    /**
    * Gets the Users Count.
    *
    * @return the Integer
    */
    public Integer getUsersCount() {
        return this.UsersCount; 
    }

    /**
    * Sets the Users Count.
    *
    * @param value the Integer
    */
    public void setUsersCount(Integer value) { 
        this.UsersCount = value; 
        valueChanged("UsersCount", value);

    }
            
    private Integer ManagedCount;
     
    /**
    * Gets the Managed Count.
    *
    * @return the Integer
    */
    public Integer getManagedCount() {
        return this.ManagedCount; 
    }

    /**
    * Sets the Managed Count.
    *
    * @param value the Integer
    */
    public void setManagedCount(Integer value) { 
        this.ManagedCount = value; 
        valueChanged("ManagedCount", value);

    }
            
    private Integer ElevatedCount;
     
    /**
    * Gets the Elevated Count.
    *
    * @return the Integer
    */
    public Integer getElevatedCount() {
        return this.ElevatedCount; 
    }

    /**
    * Sets the Elevated Count.
    *
    * @param value the Integer
    */
    public void setElevatedCount(Integer value) { 
        this.ElevatedCount = value; 
        valueChanged("ElevatedCount", value);

    }
            
    private Boolean MfaEnabled;
     
    /**
    * Gets the Mfa Enabled.
    *
    * @return the Boolean
    */
    public Boolean getMfaEnabled() {
        return this.MfaEnabled; 
    }

    /**
    * Sets the Mfa Enabled.
    *
    * @param value the Boolean
    */
    public void setMfaEnabled(Boolean value) { 
        this.MfaEnabled = value; 
        valueChanged("MfaEnabled", value);

    }
}
