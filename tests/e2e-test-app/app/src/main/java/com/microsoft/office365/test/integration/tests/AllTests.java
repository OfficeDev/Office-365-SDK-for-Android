package com.microsoft.office365.test.integration.tests;

import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;

import java.util.ArrayList;
import java.util.List;

public class AllTests extends TestGroup {
    public AllTests() {
        super("All available tests");

        for (TestGroup testGroup : getTestGroups()) {
            for (TestCase testCase : testGroup.getTestCases()) {
                this.addTest(testCase);
            }
        }
    }

    private List<TestGroup> getTestGroups() {

        List<TestGroup> testGroups = new ArrayList<TestGroup>();

        testGroups.add(new ExchangeTests());
        testGroups.add(new FilesTests());
        testGroups.add(new ListsTests());
        testGroups.add(new DiscoveryTests());
        testGroups.add(new DirectoryTests());
        testGroups.add(new OneNoteTests());
        testGroups.add(new GraphTests());
        return testGroups;
    }

}
