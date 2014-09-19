package com.example.exchangedemo;

import android.app.Application;
import android.preference.PreferenceManager;
import android.util.Log;

import com.microsoft.office365.Service;
import com.microsoft.office365.microsoft.exchange.services.odata.model.EntityContainer;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.olingo.client.api.http.HttpMethod;
import org.apache.olingo.client.api.v4.EdmEnabledODataClient;
import org.apache.olingo.client.core.http.DefaultHttpUriRequestFactory;
import org.apache.olingo.commons.api.format.ODataFormat;

import java.net.URI;

public class AndroidApplication extends Application {
    private AppPreferences mPreferences;
    private String mToken;

    public void setToken(String token) {
        mToken = token;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mPreferences = new AppPreferences(
                PreferenceManager.getDefaultSharedPreferences(this));
    }

    public AppPreferences getAppPreferences() {
        return mPreferences;
    }

    public EntityContainer getEntityContainer() {

        Service<EdmEnabledODataClient> service = Service.getV4(Constants.RESOURCE_ID +
                Constants.ODATA_ENDPOINT, false);
        service.getClient().getConfiguration()
                .setDefaultPubFormat(ODataFormat.JSON_FULL_METADATA);

        service.getClient().getConfiguration().setUseChuncked(false);
        service.getClient().getConfiguration().setAddressingDerivedTypes(false);
        service.getClient().getConfiguration().setUseUrlOperationFQN(false);

        service.getClient().getConfiguration()
                .setHttpUriRequestFactory(new DefaultHttpUriRequestFactory() {
                    @Override
                    public HttpUriRequest create(HttpMethod method, URI uri) {

                        HttpUriRequest request = super.create(method, uri);
                        request.addHeader("Authorization", "Bearer " + mToken);
                        Log.e("Request", uri.toString());
                        return request;
                    }
                });

        return service.getEntityContainer(EntityContainer.class);
    }

    public boolean hasConfiguration() {

        if (isNullOrEmpty(mPreferences.getClientId()))
            return false;

        if (isNullOrEmpty(mPreferences.getRedirectUrl()))
            return false;

        return true;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }

}
