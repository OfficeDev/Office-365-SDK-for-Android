package com.microsoft.services.odata.unittests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsLoader;
import com.microsoft.services.odata.unittests.testsupport.WireMockTestClient;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockTestBase {

    protected static WireMockServer wireMockServer;
    protected static WireMockTestClient testClient;

    protected static void buildWireMock(Options options) {
        wireMockServer = new WireMockServer(options);
        wireMockServer.start();
    }

    @BeforeClass
    public static void setupServer() {
        buildWireMock(wireMockConfig());
        wireMockServer.loadMappingsUsing(new JsonFileMappingsLoader(
                new SingleRootFileSource("src/test/resources/mappings")));
    }


    @AfterClass
    public static void serverShutdown() {
        wireMockServer.stop();
    }


}
