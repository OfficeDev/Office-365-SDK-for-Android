/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.outlookservices.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.services.odata.*;
import com.microsoft.services.odata.interfaces.*;
import com.microsoft.outlookservices.*;
import static com.microsoft.services.odata.Helpers.*;



/**
 * The type CalendarCollectionOperations
 */
public class CalendarCollectionOperations extends EntityCollectionOperations{

    /**
     * Instantiates a new CalendarCollectionOperations.
     *
     * @param urlComponent the url component
     * @param parent the parent
     */
    public CalendarCollectionOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }

     /**
     * Add parameter.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public CalendarCollectionOperations addParameter(String name, Object value) {
        addCustomParameter(name, value);
        return this;
    }

     /**
     * Add header.
     *
     * @param name the name
     * @param value the value
     * @return the collection operations
     */
    public CalendarCollectionOperations addHeader(String name, String value) {
        addCustomHeader(name, value);
        return this;
    }
}
