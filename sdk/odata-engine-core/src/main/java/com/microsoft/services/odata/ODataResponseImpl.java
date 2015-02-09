package com.microsoft.services.odata;

import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

class ODataResponseImpl implements ODataResponse {

    private Response response;
    private byte[] payload = null;

    public ODataResponseImpl(Response response) {
        this.response = response;
    }

    @Override
    public byte[] getPayload() throws IOException {
        if (payload == null) {
            payload = readAllBytes(this.response.getStream());
        }
        return this.payload;
    }

    @Override
    public Response getResponse() {
        return this.response;
    }

    @Override
    public InputStream openStreamedResponse() {
        return this.response.getStream();
    }

    @Override
    public void closeStreamedResponse() throws IOException{
        this.response.close();
    }

    /**
     * Read all bytes.
     *
     * @param stream the stream
     * @return the byte [ ]
     * @throws java.io.IOException the iO exception
     */
    private static byte[] readAllBytes(InputStream stream) throws IOException {
        if (stream == null) {
            return new byte[0];
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            os.write(data, 0, nRead);
        }
        return os.toByteArray();
    }
}
