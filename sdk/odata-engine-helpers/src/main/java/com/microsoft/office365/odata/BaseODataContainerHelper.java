package com.microsoft.office365.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.microsoft.office365.odata.interfaces.DependencyResolver;
import com.microsoft.office365.odata.interfaces.HttpTransport;
import com.microsoft.office365.odata.interfaces.HttpVerb;
import com.microsoft.office365.odata.interfaces.LogLevel;
import com.microsoft.office365.odata.interfaces.Request;
import com.microsoft.office365.odata.interfaces.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import static com.microsoft.office365.odata.Helpers.urlEncode;

public class BaseODataContainerHelper {

    public static String getODataParameterValue(DependencyResolver resolver, Object value) {

        String serialized = resolver.getJsonSerializer().serialize(value);
        return  urlEncode(serialized);
    }

    public static ListenableFuture<byte[]> oDataExecute(String path, byte[] content, HttpVerb verb, String url, DependencyResolver resolver) {

        HttpTransport httpTransport = resolver.getHttpTransport();
        Request request = httpTransport.createRequest();
        request.setVerb(verb);
        request.setUrl(url + "/" + path);
        request.setContent(content);
        resolver.getLogger().log(new String(content == null ? new byte[0] : content, Constants.UTF8), LogLevel.VERBOSE);
        request.addHeader("Content-Type", "application/json");
        resolver.getCredentialsFactory().getCredentials().prepareRequest(request);

        resolver.getLogger().log("URL: " + request.getUrl(), LogLevel.VERBOSE);
        resolver.getLogger().log("HEADERS: ", LogLevel.VERBOSE);
        for (String key : request.getHeaders().keySet()) {
            resolver.getLogger().log(key + " : " + request.getHeaders().get(key).toString(), LogLevel.VERBOSE);
        }

        final ListenableFuture<Response> future = httpTransport.execute(request);
        final SettableFuture<byte[]> result = SettableFuture.create();

        Futures.addCallback(future, new FutureCallback<Response>() {

            @Override
            public void onSuccess(Response response) {
                try {
                    byte[] data = readAllBytes(response.getStream());
                    int status = response.getStatus();
                    try {
                        response.close();
                    } catch (Throwable t) {
                        result.setException(t);
                        return;
                    }

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

    public static byte[] readAllBytes(InputStream stream) throws IOException {
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

    public static String generateParametersPayload(Map<String, Object> parameters, DependencyResolver resolver) {
        return resolver.getJsonSerializer().serialize(parameters);
    }

}
