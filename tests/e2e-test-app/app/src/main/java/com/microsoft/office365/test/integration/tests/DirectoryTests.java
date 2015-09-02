
package com.microsoft.office365.test.integration.tests;

import com.microsoft.services.directory.*;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.services.directory.fetchers.DirectoryClient;

import java.util.List;
import java.util.UUID;

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
                    List<User> users = client.getUsers().read().get();

                    //Assert}
                    if (users != null && users.size() > 0)
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
                    User addedUser = client.getUsers().add(newUser).get();

                    //Act
                    User storedUser = client.getUsers().getById(addedUser.getObjectId()).read().get();

                    //Assert}
                    if (storedUser != null && storedUser.getDisplayName().equals(newUser.getDisplayName()))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    //client.getusers().getById(storedUser.getobjectId()).delete().get();
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
                    User addedUser = client.getUsers().add(newUser).get();

                    //Assert}
                    if (addedUser != null && addedUser.getDisplayName().equals(newUser.getDisplayName()))
                        result.setStatus(TestStatus.Passed);

                    //Cleanup
                    //if(addedUser!= null)
                    //    client.getusers().getById(addedUser.getobjectId()).delete().get();

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
                    User addedUser = client.getUsers().add(newUser).get();

                    //Act
                    String updatedDisplayName = "Updated display name";
                    newUser.setDisplayName(updatedDisplayName);
                    client.getUsers().getById(addedUser.getObjectId()).update(newUser).get();

                    //Assert
                    result.setStatus(TestStatus.Passed);

                    //Cleanup
                    //if(updatedUser!= null)
                    //    client.getusers().getById(updatedUser.getobjectId()).delete().get();

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
                    User addedUser = client.getUsers().add(newUser).get();

                    //Act
                    client.getUsers().getById(addedUser.getObjectId()).delete().get();

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
    //*


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
                    List<Application> applications = client.getApplications().read().get();

                    //Assert}
                    if (applications != null)
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
                    List<Contact> contacts = client.getContacts().read().get();

                    //Assert}
                    if (contacts != null)
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
                    List<DirectoryObject> deletedDirectoryObjects = client.getDeletedDirectoryObjects().read().get();

                    //Assert}
                    if (deletedDirectoryObjects != null)
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
                    List<DirectoryObject> directoryObjects = client.getDirectoryObjects().read().get();

                    //Assert}
                    if (directoryObjects != null)
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
                    List<Group> groups = client.getGroups().read().get();

                    //Assert}
                    if (groups != null)
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
                    List<OAuth2PermissionGrant> permissionGrants = client.getOauth2PermissionGrants().read().get();

                    //Assert}
                    if (permissionGrants != null)
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
                    List<DirectoryRole> roles = client.getRoles().read().get();

                    //Assert}
                    if (roles != null)
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
                    List<ServicePrincipal> servicePrincipals = client.getServicePrincipals().read().get();

                    //Assert}
                    if (servicePrincipals != null)
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
                    List<SubscribedSku> subscribedSkus = client.getSubscribedSkus().read().get();

                    //Assert}
                    if (subscribedSkus != null)
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
                    List<Device> devices = client.getDevices().read().get();

                    //Assert}
                    if (devices != null)
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
                    List<TenantDetail> tenantDetails = client.getTenantDetails().read().get();

                    //Assert}
                    if (tenantDetails != null)
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

    private User getUser() {
        String guid = UUID.randomUUID().toString().replace("-", "");
        String userName = String.format("Alex%s", guid);
        User user = new User();
        user.setAccountEnabled(true);
        user.setGivenName(userName);
        user.setUserPrincipalName(String.format("%s@teeudev1.onmicrosoft.com", userName));
        user.setDisplayName("Display Name");
        user.setMailNickname(userName);
        user.setCountry("MyCountry");
        PasswordProfile profile = new PasswordProfile();
        profile.setPassword("TestPassword1");
        profile.setForceChangePasswordNextLogin(false);
        user.setPasswordProfile(profile);
        return user;
    }
}

