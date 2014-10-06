package com.microsoft.office365.test.integration.tests;

import com.microsoft.office365.lists.SPListField;
import com.microsoft.office365.lists.SharepointListsClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

import java.util.List;

import static com.microsoft.office365.QueryOperations.startsWith;

public class MailTests extends TestGroup {

    public MailTests() {
        super("Sharepoint Lists tests");

        this.addTest(test1("Test1", true));

    }

    private TestCase test1(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    // Test stuff

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
