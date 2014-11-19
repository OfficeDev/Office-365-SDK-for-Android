package com.microsoft.services.odata;

import com.microsoft.services.odata.interfaces.ODataResponse;

public class ODataException extends Exception {

    private ODataResponse response;

    public ODataException(ODataResponse response, String message) {
        super(message);
        this.response = response;
    }

    public ODataException(ODataResponse response, Throwable inner) {
        super(inner);
        this.response = response;
    }

    public ODataResponse getODataResponse() {
        return this.response;
    }
}
