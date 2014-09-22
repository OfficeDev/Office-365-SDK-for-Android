/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.office365.test.integration.tests;

import static com.microsoft.office365.QueryOperations.*;

import java.util.List;
import java.util.UUID;

import com.microsoft.office365.lists.SPList;
import com.microsoft.office365.lists.SPListField;
import com.microsoft.office365.lists.SPListItem;
import com.microsoft.office365.lists.SharepointListsClient;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.ExpectedValueException;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

public class ListsTests extends TestGroup {

	public ListsTests() {
		super("Sharepoint Lists tests");

		this.addTest(createWebTitleTest("Get Web Title", true));
		this.addTest(createGetListsTest("Get site lists", true));
		this.addTest(createGetListTest("Get single list", true));
		this.addTest(createRoundtripListItemTest("Insert, update, delete list item", true));
		this.addTest(createColumnsForDefaultViewTest("Columns for default view", true));
		this.addTest(createListFieldsTest("All list fields", true));

	}

	private TestCase createListFieldsTest(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					SharepointListsClient client = ApplicationContext.getListsClient();

					String listName = ApplicationContext.getTestListName();

					List<SPListField> fields = client.getListFields(listName, startsWith("Title", "T")).get();

					if (fields.size() == 0) {
						throw new Exception("Expected at least one field");
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

	private TestCase createColumnsForDefaultViewTest(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					SharepointListsClient client = ApplicationContext.getListsClient();

					String listName = ApplicationContext.getTestListName();

					List<String> columnNames = client.getColumnsFromDefaultView(listName).get();

					if (columnNames.size() == 0) {
						throw new Exception("Expected at least one column");
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

	private TestCase createRoundtripListItemTest(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					SharepointListsClient client = ApplicationContext.getListsClient();

					String listName = ApplicationContext.getTestListName();
					SPList list = client.getList(listName).get();

					SPListItem newItem = new SPListItem();

					String title1 = UUID.randomUUID().toString();
					newItem.setData("Title", title1);

					client.insertListItem(newItem, list).get();

					List<SPListItem> existingItems = client.getListItems(listName, field("Title").eq().val(title1))
							.get();

					if (existingItems.size() != 1) {
						throw new Exception("Expected 1 list item");
					}

					SPListItem existingItem = existingItems.get(0);

					String title2 = UUID.randomUUID().toString();
					existingItem.setData("Title", title2);

					client.updateListItem(existingItem, list).get();

					existingItems = client.getListItems(listName, field("Title").eq().val(title2)).get();

					if (existingItems.size() != 1) {
						throw new Exception("Expected 1 list item");
					}

					SPListItem itemToDelete = existingItems.get(0);

					client.deleteListItem(itemToDelete, listName).get();

					existingItems = client.getListItems(listName, field("Title").eq().val(title2)).get();

					if (existingItems.size() != 0) {
						throw new Exception("Expected 0 list items");
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

	private TestCase createGetListTest(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					SharepointListsClient client = ApplicationContext.getListsClient();

					String listName = ApplicationContext.getTestListName();
					SPList list = client.getList(listName).get();

					// validations

					if (!list.getTitle().equals(list.getTitle())) {
						createResultFromException(result, new ExpectedValueException(listName, list.getTitle()));
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

	private TestCase createGetListsTest(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					SharepointListsClient client = ApplicationContext.getListsClient();

					String listName = ApplicationContext.getTestListName();
					String firstChar = listName.substring(0, 1);
					List<SPList> lists = client.getLists(startsWith("Title", firstChar)).get();

					// validations

					if (lists.size() == 0) {
						createResultFromException(result, new Exception("At least one list expected"));
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

	private TestCase createWebTitleTest(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					SharepointListsClient client = ApplicationContext.getListsClient();
					String title = client.getWebTitle().get();

					// validations

					if (title == null || title.length() == 0) {
						createResultFromException(result, new Exception("Title expected"));
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
