package com.microsoft.office365.test.integration.tests;

import java.util.List;

import com.microsoft.office.microsoft.exchange.services.odata.model.types.MessageCollection;
import com.microsoft.office365.api.MailClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;


public class MailTests extends TestGroup {

	public MailTests() {
		super("Sharepoint Lists tests");

		this.addTest(createMailClient("Get MailClient", true));
	}

	private TestCase createMailClient(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					MailClient client = ApplicationContext.getMailClient();

					MessageCollection messages = client.getMessages("Inbox");

					if (messages.size() == 0) {
						throw new Exception("Expected at least one email");
					}

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