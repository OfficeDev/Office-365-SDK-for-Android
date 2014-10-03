/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseODataContainer extends ODataExecutable {

    private String url;
    private DependencyResolver resolver;

    public BaseODataContainer(String url, DependencyResolver resolver) {
        this.url = url;
        this.resolver = resolver;
    }

    @Override
    ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb) {

        HttpTransport httpTransport = resolver.getHttpTransport();
        Request request = httpTransport.createRequest();
        request.setVerb(verb);
        request.setUrl(url + "/" + path);
        request.setContent(content);
        getResolver().getLogger().log(new String(content == null ? new byte[0] : content, Constants.UTF8), LogLevel.VERBOSE);
        request.addHeader("Content-Type", "application/json");
        getResolver().getCredentialsFactory().getCredentials().prepareRequest(request);

        getResolver().getLogger().log("URL: " + request.getUrl(), LogLevel.VERBOSE);
        getResolver().getLogger().log("HEADERS: ", LogLevel.VERBOSE);
        for (String key :request.getHeaders().keySet()) {
            getResolver().getLogger().log(key + " : " + request.getHeaders().get(key).toString(), LogLevel.VERBOSE);
        }

        final ListenableFuture<Response> future = httpTransport.execute(request);
        final SettableFuture<byte[]> result = SettableFuture.create();

        Futures.addCallback(future, new FutureCallback<Response>() {

            @Override
            public void onSuccess(Response response) {
                try {
                    byte[] data = readAllBytes(response.getStream());

                    int status = response.getStatus();
                    if (status < 200 || status > 299) {
                        String responseData = new String(data, Constants.UTF8_NAME);
                        result.setException(new IllegalStateException("Response status: " + response.getStatus() + "\n" + "Response content: " + responseData));
                        return;
                    }
                    result.set(data);
                } catch (Throwable t) {
                    result.setException(t);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                result.setException(throwable);
            }
        });
        return result;
    }

    @Override
    DependencyResolver getResolver() {
        return resolver;
    }

    private byte[] readAllBytes(InputStream stream) throws IOException {
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