package com.microsoft.services.odata.unittests;

import com.microsoft.outlookservices.odata.OutlookClient;

import org.junit.Test;

public class OutlookServicesTest {

    private static String exchangeEndpointUrl = "https://outlook.office365.com/api/v1.0";

    @Test
    public void canCreateOutlookClient() {

        OutlookClient client = new OutlookClient(exchangeEndpointUrl, null );
    }
}
