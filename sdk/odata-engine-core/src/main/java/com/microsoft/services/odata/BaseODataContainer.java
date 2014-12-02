/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpTransport;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Logger;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.Request;
import com.microsoft.services.odata.interfaces.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * The type BaseODataContainer.
 */
public abstract class BaseODataContainer extends ODataExecutable {

    private String url;
    private DependencyResolver resolver;

    public BaseODataContainer(String url, DependencyResolver resolver) {
        this.url = url;
        this.resolver = resolver;
    }

    @Override
    protected ListenableFuture<ODataResponse> oDataExecute(Request request) {
        final SettableFuture<ODataResponse> result = SettableFuture.create();
        final Logger logger = resolver.getLogger();

        try {
            request.getUrl().setBaseUrl(this.url);
            String fullUrl = request.getUrl().toString();

            String executionInfo = String.format("URL: %s - HTTP VERB: %s", fullUrl, request.getVerb());
            logger.log("Start preparing OData execution for " + executionInfo, LogLevel.INFO);

            if (request.getContent() != null) {
                logger.log("With " + request.getContent().length + " bytes of payload", LogLevel.INFO);
            }

            HttpTransport httpTransport = resolver.getHttpTransport();

            String userAgent = resolver.getPlatformUserAgent(this.getClass().getCanonicalName());
            request.addHeader(Constants.USER_AGENT_HEADER, userAgent);
            request.addHeader(Constants.TELEMETRY_HEADER, userAgent);
            request.addHeader(Constants.CONTENT_TYPE_HEADER, Constants.JSON_CONTENT_TYPE);
            request.addHeader(Constants.ACCEPT_HEADER, Constants.JSON_CONTENT_TYPE);
            request.addHeader(Constants.ODATA_VERSION_HEADER, Constants.ODATA_VERSION);
            request.addHeader(Constants.ODATA_MAXVERSION_HEADER, Constants.ODATA_MAXVERSION);

            if (request.getHeaders() != null) {
                for (String key : request.getHeaders().keySet()) {
                    request.addHeader(key, request.getHeaders().get(key));
                }
            }

            boolean credentialsSet = false;

            Credentials cred = resolver.getCredentials();
            if (cred != null) {
                cred.prepareRequest(request);
                credentialsSet = true;
            }

            if (!credentialsSet) {
                logger.log("Executing request without setting credentials", LogLevel.WARNING);
            }


            logger.log("Request Headers: ", LogLevel.VERBOSE);
            for (String key : request.getHeaders().keySet()) {
                logger.log(key + " : " + request.getHeaders().get(key), LogLevel.VERBOSE);
            }

            final ListenableFuture<Response> future = httpTransport.execute(request);
            logger.log("OData request executed", LogLevel.INFO);

            Futures.addCallback(future, new FutureCallback<Response>() {

                @Override
                public void onSuccess(Response response) {
                    try {
                        logger.log("OData response received", LogLevel.INFO);

                        logger.log("Reading response data...", LogLevel.VERBOSE);
                        byte[] data = readAllBytes(response.getStream());
                        logger.log(data.length + " bytes read from response", LogLevel.VERBOSE);

                        int status = response.getStatus();
                        logger.log("Response Status Code: " + status, LogLevel.INFO);

                        try {
                            logger.log("Closing response", LogLevel.VERBOSE);
                            response.close();
                        } catch (Throwable t) {
                            logger.log("Error closing response: " + t.toString(), LogLevel.ERROR);
                            result.setException(t);
                            return;
                        }

                        ODataResponse odataResponse = new ODataResponseImpl(data, response);
                        if (status < 200 || status > 299) {
                            logger.log("Invalid status code. Processing response content as String", LogLevel.VERBOSE);
                            String responseData = new String(data, Constants.UTF8_NAME);
                            String message = "Response status: " + response.getStatus() + "\n" + "Response content: " + responseData;
                            logger.log(message, LogLevel.ERROR);
                            result.setException(new ODataException(odataResponse, message));
                            return;
                        }
                        result.set(odataResponse);
                    } catch (Throwable t) {
                        logger.log("Unexpected error: " + t.toString(), LogLevel.ERROR);
                        ODataResponse odataResponse = new ODataResponseImpl(null, response);
                        result.setException(new ODataException(odataResponse, t));
                    }
                }

                @Override
                public void onFailure(Throwable throwable) {
                    result.setException(throwable);
                }
            });
        } catch (Throwable t) {
            result.setException(t);
        }
        return result;

    }

    /**
     * Read all bytes.
     *
     * @param stream the stream
     * @return the byte [ ]
     * @throws java.io.IOException the iO exception
     */
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

    /**
     * Generate parameters payload.
     *
     * @param parameters the parameters
     * @param resolver   the resolver
     * @return the string
     */
    public static String generateParametersPayload(Map<String, Object> parameters, DependencyResolver resolver) {
        return resolver.getJsonSerializer().serialize(parameters);
    }

    @Override
    protected DependencyResolver getResolver() {
        return resolver;
    }
}