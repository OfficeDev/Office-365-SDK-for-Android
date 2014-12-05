package com.microsoft.services.odata.unittests;

import com.microsoft.outlookservices.Folder;
import com.microsoft.outlookservices.odata.OutlookClient;
import com.microsoft.services.odata.interfaces.DependencyResolver;
import com.microsoft.services.odata.unittests.testsupport.WireMockResponse;
import com.microsoft.services.odata.unittests.testsupport.WireMockTestClient;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class OutlookServicesTest extends WireMockTestBase {

    private String mockServer = "http://localhost:8080";

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Before
    public void init() {
        testClient = new WireMockTestClient();
    }

    @Test
    public void mappingsLoadedFromJsonFiles() {

        WireMockResponse response = testClient.get("/foo/bar/1");
        assertThat(response.statusCode(), is(200));

        response = testClient.get("/canned/resource/1");
        assertThat(response.statusCode(), is(200));

        response = testClient.get("/Me/TwoParamsActionsFirstIsEntityType/");
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void canCreateOutlookClient() {

        final DependencyResolver resolver = context.mock(DependencyResolver.class);
        OutlookClient client = new OutlookClient(mockServer, resolver);
        assertNotNull(client);
    }
}
