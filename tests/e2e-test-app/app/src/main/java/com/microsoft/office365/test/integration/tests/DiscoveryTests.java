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

        //Select, top, filter
        this.addTest(canFilterServices("Can use filter in directory", true));
        this.addTest(canSelectServices("Can use select in directory", true));
        this.addTest(canTopServices("Can use top in directory", true));
    }

    private TestCase canGetAllServices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DiscoveryClient client = ApplicationContext.getDiscoveryClient();

                    //Act
                    List<ServiceInfo> allServices = client.getallServices().read().get();

                    //Assert
                    if (allServices != null && allServices.size() > 0)
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

                    DiscoveryClient client = ApplicationContext.getDiscoveryClient();

                    //Act
                    List<ServiceInfo> services = client.getservices().read().get();

                    //Assert
                    if (services != null && services.size() > 0)
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

                    DiscoveryClient client = ApplicationContext.getDiscoveryClient();

                    //Prepare
                    List<ServiceInfo> services = client.getservices().read().get();

                    //Act
                    ServiceInfo service = null;
                    if (services.size() > 0)
                        service = client.getservices().getById(services.get(0).getentityKey()).read().get();

                    //Assert
                    if (service != null && service.getserviceId() != null)
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

    // Filter, Select, Top, Skip
    private TestCase canFilterServices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);


                    DiscoveryClient client = ApplicationContext.getDiscoveryClient();

                    //Prepare
                    List<ServiceInfo> services = client.getallServices().read().get();

                    //Act

                    List<ServiceInfo> filteredServices = client.getallServices()
                            .filter("entityKey eq '" + services.get(0).getentityKey() + "'")
                            .read().get();

                    //Assert
                    if (filteredServices.size() == 1)
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

    private TestCase canSelectServices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DiscoveryClient client = ApplicationContext.getDiscoveryClient();

                    //Prepare
                    List<ServiceInfo> services = client.getallServices().read().get();

                    //Act
                    List<ServiceInfo> filteredServices = client.getallServices()
                            .filter("ServiceName eq '" + services.get(0).getserviceName() + "'")
                            .select("providerName")
                            .read().get();

                    //Assert
                    if (filteredServices.size() > 0 && !filteredServices.get(0).getproviderName().equals("") && filteredServices.get(0).getserviceName() == null)
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


    private TestCase canTopServices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DiscoveryClient client = ApplicationContext.getDiscoveryClient();

                    //Act
                    List<ServiceInfo> filteredServices = client.getallServices()
                            .top(1)
                            .read().get();

                    //Assert
                    if (filteredServices.size() == 1)
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

}
