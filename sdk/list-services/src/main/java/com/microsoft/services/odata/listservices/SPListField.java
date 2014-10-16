/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata.listservices;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * The Class SPListField.
 */
public class SPListField extends OfficeEntity {

    /**
     * List from json.
     *
     * @param json the json
     * @return the list
     */
    public static List<SPListField> listFromJson(JsonObject json) {
        return OfficeEntity.listFromJson(json, SPListField.class);
    }

    /**
     * Instantiates a new SP list field.
     */
    public SPListField() {
        super();
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return getData("Title").toString();
    }

    /**
     * Gets the entity property name.
     *
     * @return the entity property name
     */
    public String getEntityPropertyName() {
        return getData("EntityPropertyName").toString();
    }

}
