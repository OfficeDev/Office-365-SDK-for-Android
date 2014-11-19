package com.microsoft.services.odata;

import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.Response;

class ODataResponseImpl implements ODataResponse {

    private byte[] payload;
    private Response response;

    public ODataResponseImpl(byte[] payload, Response response) {
        this.payload = payload;
        this.response = response;
    }

    @Override
    public byte[] getPayload() {
        return this.payload;
    }

    @Override
    public Response getResponse() {
        return this.response;
    }
}
