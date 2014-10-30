package com.microsoft.office365.test.integration.tests;


import com.microsoft.discoveryservices.ServiceInfo;
import com.microsoft.discoveryservices.odata.DiscoveryClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import java.util.List;

public class DiscoveryTests extends TestGroup {

    public DiscoveryTests() {
        super("Discovery tests");

        this.addTest(canGetAllServices("Can get all services", true));
        this.addTest(canGetServices("Can get services", true));
        this.addTest(canGetServicesById("Can get services by id", true));
    }

    private TestCase canGetAllServices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DiscoveryClient client = getDiscoveryClient();

                    //Act
                    List<ServiceInfo> allServices = client.getallServices().read().get();

                    //Assert
                    if(allServices != null && allServices.size() > 0)
                        result.setStatus(TestStatus.Passed);

                    return result;
                } catch (Exception e) {
                    return createResultFromException(e);
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private TestCase canGetServices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DiscoveryClient client = getDiscoveryClient();

                    //Act
                    List<ServiceInfo> services = client.getservices().read().get();

                    //Assert
                    if(services != null && services.size() > 0)
                        result.setStatus(TestStatus.Passed);

                    return result;
                } catch (Exception e) {
                    return createResultFromException(e);
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private TestCase canGetServicesById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DiscoveryClient client = getDiscoveryClient();

                    //Prepare
                    List<ServiceInfo> services = client.getservices().read().get();

                    //Act
                    ServiceInfo service = null;
                    if(services.size() > 0)
                        service = client.getservices().getById(services.get(0).getentityKey()).read().get();

                    //Assert
                    if(service != null && service.getserviceId() != null)
                        result.setStatus(TestStatus.Passed);

                    return result;
                } catch (Exception e) {
                    return createResultFromException(e);
                }
            }
        };

        test.setName(name);
        test.setEnabled(enabled);
        return test;
    }

    private DiscoveryClient getDiscoveryClient(){
        return ApplicationContext.getDiscoveryClient();
    }
}
