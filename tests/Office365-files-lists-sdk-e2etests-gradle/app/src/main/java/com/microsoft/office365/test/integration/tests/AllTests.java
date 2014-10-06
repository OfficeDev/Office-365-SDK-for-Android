package com.microsoft.office365.test.integration.tests;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;

public class AllTests extends TestGroup {
	public AllTests() {
		super("All available tests");

		for (TestGroup testGroup : getTestGroups()) {
			for (TestCase testCase : testGroup.getTestCases()){
				this.addTest(testCase);
			}
		}
	}

	private List<TestGroup> getTestGroups(){

		List<TestGroup> testGroups = new ArrayList<TestGroup>();

		testGroups.add(new MailTests());

		return testGroups;
	}

}
