package com.microsoft.office365.test.integration.tests;

import com.microsoft.directoryservices.Application;
import com.microsoft.directoryservices.Contact;
import com.microsoft.directoryservices.Device;
import com.microsoft.directoryservices.DirectoryObject;
import com.microsoft.directoryservices.DirectoryRole;
import com.microsoft.directoryservices.Group;
import com.microsoft.directoryservices.OAuth2PermissionGrant;
import com.microsoft.directoryservices.PasswordProfile;
import com.microsoft.directoryservices.ServicePrincipal;
import com.microsoft.directoryservices.SubscribedSku;
import com.microsoft.directoryservices.TenantDetail;
import com.microsoft.directoryservices.User;
import com.microsoft.directoryservices.odata.DirectoryClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import java.util.List;

public class DirectoryTests extends TestGroup {

    public DirectoryTests() {
        super("Directory tests");

        this.addTest(canGetUsers("Can get users", true));
        this.addTest(canGetUsersById("Can get users by id", false));
        this.addTest(canCreateUser("Can create users", false));
        this.addTest(canUpdateUser("Can update users", false));
        this.addTest(canDeleteUser("Can delete users", false));

        this.addTest(canGetApplications("Can get applications", true));

        this.addTest(canGetContacts("Can get contacts", true));

        this.addTest(canGetDeletedDirectoryObjects("Can get deleted directory obj", false));

        this.addTest(canGetDirectoryObjects("Can get directory obj", false));

        this.addTest(canGetGroups("Can get groups", true));

        this.addTest(canGetOauth2PermissionGrants("Can get oauth2 permissions", false));

        this.addTest(canGetRoles("Can get roles", false));

        this.addTest(canGetServicePrincipals("Can get service principals", true));

        this.addTest(canGetSubscribedSkus("Can get subscribed skus", true));

        this.addTest(canGetDevices("Can get devices", true));

        this.addTest(canGetTenantDetails("Can get tenant details", true));
    }

    // ********* Users Tests *************

    private TestCase canGetUsers(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<User> users = client.getusers().read().get();

                    //Assert}
                    if(users != null && users.size() >0)
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

    private TestCase canGetUsersById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();
                    //Prepare
                    User newUser = getUser();
                    User addedUser = client.getusers().add(newUser).get();

                    //Act
                    User storedUser = client.getusers().getById(addedUser.getobjectId()).read().get();

                    //Assert}
                    if(storedUser != null && storedUser.getdisplayName().equals(newUser.getdisplayName()))
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

    private TestCase canCreateUser(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    User newUser = getUser();
                    User addedUser = client.getusers().add(newUser).get();

                    //Assert}
                    if(addedUser != null && addedUser.getdisplayName().equals(newUser.getdisplayName()))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    if(addedUser!= null)
                        client.getusers().getById(addedUser.getobjectId()).delete().get();

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

    private TestCase canUpdateUser(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Prepare
                    User newUser = getUser();
                    User addedUser = client.getusers().add(newUser).get();

                    //Act
                    String updatedDisplayName ="Updated display name";
                    newUser.setdisplayName(updatedDisplayName);
                    User updatedUser = client.getusers().getById(addedUser.getobjectId()).update(newUser).get();

                    //Assert}
                    if(updatedUser != null && updatedUser.getdisplayName().equals(updatedDisplayName))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    if(updatedUser!= null)
                        client.getusers().getById(updatedUser.getobjectId()).delete().get();

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

    private TestCase canDeleteUser(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Prepare
                    User newUser = getUser();
                    User addedUser = client.getusers().add(newUser).get();

                    //Act
                    client.getusers().getById(addedUser.getobjectId()).delete().get();

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

    // ********* Application Tests *************

    private TestCase canGetApplications(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<Application> applications = client.getapplications().read().get();

                    //Assert}
                    if(applications != null)
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


    // ********* Contacts Tests *************

    private TestCase canGetContacts(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<Contact> contacts = client.getcontacts().read().get();

                    //Assert}
                    if(contacts != null)
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

    // ********* Deleted Directory Objects Tests *************

    private TestCase canGetDeletedDirectoryObjects(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<DirectoryObject> deletedDirectoryObjects = client.getdeletedDirectoryObjects().read().get();

                    //Assert}
                    if(deletedDirectoryObjects != null)
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

    // ********* Directory Objects Tests *************

    private TestCase canGetDirectoryObjects(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<DirectoryObject> directoryObjects = client.getdirectoryObjects().read().get();

                    //Assert}
                    if(directoryObjects != null)
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

    // ********* Groups Tests *************

    private TestCase canGetGroups(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<Group> groups = client.getgroups().read().get();

                    //Assert}
                    if(groups != null)
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

    // ********* Oauth2PermissionGrants Tests*************

    private TestCase canGetOauth2PermissionGrants(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<OAuth2PermissionGrant> permissionGrants = client.getoauth2PermissionGrants().read().get();

                    //Assert}
                    if(permissionGrants != null)
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


    // ********* Roles Tests *************

    private TestCase canGetRoles(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<DirectoryRole> roles = client.getroles().read().get();

                    //Assert}
                    if(roles != null)
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

    // ********* Service Principal Tests *************

    private TestCase canGetServicePrincipals(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<ServicePrincipal> servicePrincipals = client.getservicePrincipals().read().get();

                    //Assert}
                    if(servicePrincipals != null)
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

    // ********* Subscribed Skus Tests *************

    private TestCase canGetSubscribedSkus(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<SubscribedSku> subscribedSkus = client.getsubscribedSkus().read().get();

                    //Assert}
                    if(subscribedSkus != null)
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

    // ********* Devices Tests *************

    private TestCase canGetDevices(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<Device> devices = client.getdevices().read().get();

                    //Assert}
                    if(devices != null)
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

    // ********* Devices Tests *************

    private TestCase canGetTenantDetails(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    DirectoryClient client = ApplicationContext.getDirectoryClient();

                    //Act
                    List<TenantDetail> tenantDetails = client.gettenantDetails().read().get();

                    //Assert}
                    if(tenantDetails != null)
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

    private User getUser(){
        User user = new User();
        user.setgivenName("Given Name");
        user.setdisplayName("Display Name");
        user.setcountry("MyCountry");
        PasswordProfile profile = new PasswordProfile();
        profile.setpassword("TestPassword");
        user.setpasswordProfile(profile);
        return user;
    }
}
