package com.microsoft.office365.test.integration.tests;


import com.microsoft.discoveryservices.ServiceInfo;
import com.microsoft.discoveryservices.fetchers.DiscoveryClient;
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
                    List<ServiceInfo> allServices = client.getAllServices().read().get();

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
                    List<ServiceInfo> services = client.getServices().read().get();

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
                    List<ServiceInfo> services = client.getServices().read().get();

                    //Act
                    ServiceInfo service = null;
                    if (services.size() > 0)
                        service = client.getServices().getById(services.get(0).getEntityKey()).read().get();

                    //Assert
                    if (service != null && service.getServiceId() != null)
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
                    List<ServiceInfo> services = client.getAllServices().read().get();

                    //Act

                    List<ServiceInfo> filteredServices = client.getAllServices()
                            .filter("entityKey eq '" + services.get(0).getEntityKey() + "'")
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
                    List<ServiceInfo> services = client.getAllServices().read().get();

                    //Act
                    List<ServiceInfo> filteredServices = client.getAllServices()
                            .filter("ServiceName eq '" + services.get(0).getServiceName() + "'")
                            .select("providerName")
                            .read().get();

                    //Assert
                    if (filteredServices.size() > 0 && !filteredServices.get(0).getProviderName().equals("") && filteredServices.get(0).getServiceName() == null)
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
                    List<ServiceInfo> filteredServices = client.getAllServices()
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
