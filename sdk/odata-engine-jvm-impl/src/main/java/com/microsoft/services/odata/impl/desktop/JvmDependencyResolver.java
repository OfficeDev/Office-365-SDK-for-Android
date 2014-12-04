package com.microsoft.services.odata.impl.desktop;

import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.impl.ODataURLImpl;
import com.microsoft.services.odata.impl.desktop.http.JvmHttpTransport;
import com.microsoft.services.odata.impl.http.RequestImpl;
import com.microsoft.services.odata.interfaces.Credentials;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.interfaces.HttpTransport;
import com.microsoft.services.odata.interfaces.JsonSerializer;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

public class JvmDependencyResolver implements DependencyResolver {

    private LoggerImpl logger;
    private String token;

    public JvmDependencyResolver(String token) {
        this.logger = new LoggerImpl();
        this.token = token;
    }

    @Override
    public HttpTransport getHttpTransport() {
        return new JvmHttpTransport();
    }

    @Override
    public LoggerImpl getLogger() {
        return this.logger;
    }

    @Override
    public JsonSerializer getJsonSerializer() {
        return new GsonSerializer();
    }

    @Override
    public ODataURL createODataURL() {
        return new ODataURLImpl();
    }

    @Override
    public Request createRequest() {
        return new RequestImpl();
    }

    @Override
    public String getPlatformUserAgent(String productName) {

        String osName = System.getProperty("os.name");
        return String.format(
                "%s/1.0 (lang=%s; os=%s; version=%s)",
                productName, "Java", osName, Constants.SDK_VERSION);
    }

    @Override
    public Credentials getCredentials() {
        return new Credentials() {
            @Override
            public void prepareRequest(Request request) {
                request.addHeader("Authorization", "Bearer " + token);
            }
        };
    }
}
