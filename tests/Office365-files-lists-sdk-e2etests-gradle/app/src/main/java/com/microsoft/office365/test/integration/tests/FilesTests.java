package com.microsoft.office365.test.integration.tests;

import java.util.List;
import java.util.UUID;

import com.microsoft.office365.Constants;
import com.microsoft.office365.files.FileClient;
import com.microsoft.office365.files.FileSystemItem;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;

public class FilesTests extends TestGroup {
	public FilesTests() {
		super("Sharepoint Files tests");
		// Disabled tests will work in future api versions. Default libraries are not fully supported yet
		this.addTest(canAccessFilesDefaultLibrary("Can access default files library", false));
		this.addTest(canGetFilesFromDefaultLibrary("Check existing file in default library", false));
		this.addTest(canGetFilesFromSpecificLibrary("Check from a specific library", true));
		this.addTest(canGetFilePropertiesFromGivenLibAndPath("Get props from lib and path", true));
		this.addTest(canGetSpecificProperty("Get specific property from path in default library", false));
		this.addTest(canGetSpecificPropertyFromLib("Get specific property from lib and path", true));
		this.addTest(canGetFileFromDefaultLib("Can get file from default lib", false));
		this.addTest(canGetFileFromLibAndPath("Can get file from lib and path", true));
		this.addTest(canCreateFolderInDefaultDocLib("Can create folder in default doc lib", false));
		this.addTest(canCreateFolderInLibAndFolder("Can create folder inside lib", true));
		this.addTest(canCreateFilesInDefaultDocLib("Can create file in default doc lib", false));
		this.addTest(canCreateFilesInLibAndPath("Can create file in specific lib", true));
		this.addTest(canCreateFilesWithContentInDefaultLib("Can create files with content default doc lib", false));
		this.addTest(canCreateFilesWithContentInLibAndPath("Can create files with content in lib and path", true));
		this.addTest(canDeleteFileInDefaultLib("Can delete file in default lib", false));
		this.addTest(canDeleteFileInLibAndFolder("Can delete file in lib and folder", true));
		this.addTest(canGetChildrenFolderFromPath("Can get children from path", true));
		this.addTest(canMoveFile("Can move file in default lib", false));
		this.addTest(canMoveFileInLib("Can move file in lib", true));
		this.addTest(canCopyFile("Can copy file in default lib", false));
		this.addTest(canCopyFileInLib("Can copy file in lib", true));
	}

	private TestCase canCopyFile(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String fileName = UUID.randomUUID().toString() + ".txt";
					FileSystemItem targetFolder = client.createFolder(
							UUID.randomUUID().toString()).get();
					FileSystemItem fileToCopy = client.createFile(fileName)
							.get();

					String sourcePath = fileToCopy.getName();
					String destinationPath = targetFolder.getName() + "/"
							+ fileToCopy.getName();
					client.copy(sourcePath, destinationPath, true).get();

					client.getFileSystemItem(destinationPath).get();

					// Delete test folder and file
					client.delete(targetFolder.getName());
					client.delete(fileName);
					
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
	
	private TestCase canCopyFileInLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String fileName = UUID.randomUUID().toString() + ".txt";
					String lib = ApplicationContext.getTestDocumentListName();
					FileSystemItem targetFolder = client.createFolder(
							UUID.randomUUID().toString(), lib).get();
					FileSystemItem fileToCopy = client.createFile(fileName, lib)
							.get();

					String sourcePath = fileToCopy.getName();
					String destinationPath = targetFolder.getName() + "/"
							+ fileToCopy.getName();
					client.copy(sourcePath, destinationPath, true, lib).get();

					client.getFileSystemItem(destinationPath, lib).get();

					// Delete test folder and file
					client.delete(targetFolder.getName(), lib);
					client.delete(fileName, lib);
					
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

	private TestCase canMoveFile(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String fileName = UUID.randomUUID().toString() + ".txt";
					FileSystemItem targetFolder = client.createFolder(
							UUID.randomUUID().toString()).get();
					FileSystemItem fileToCopy = client.createFile(fileName)
							.get();

					String sourcePath = fileToCopy.getName();
					String destinationPath = targetFolder.getName() + "/"
							+ fileToCopy.getName();
					client.move(sourcePath, destinationPath, true).get();

					client.getFileSystemItem(destinationPath).get();

					// Delete test folder 
					client.delete(targetFolder.getName());
					
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

	private TestCase canMoveFileInLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String lib = ApplicationContext.getTestDocumentListName();
					String fileName = UUID.randomUUID().toString() + ".txt";
					FileSystemItem targetFolder = client.createFolder(
							UUID.randomUUID().toString(), lib).get();
					FileSystemItem fileToCopy = client.createFile(fileName, lib)
							.get();

					String sourcePath = fileToCopy.getName();
					String destinationPath = targetFolder.getName() + "/"
							+ fileToCopy.getName();
					client.move(sourcePath, destinationPath, true, lib).get();

					client.getFileSystemItem(destinationPath, lib).get();

					// Delete test folder 
					client.delete(targetFolder.getName(), lib);
					
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
	
	private TestCase canGetChildrenFolderFromPath(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String lib = ApplicationContext.getTestDocumentListName();
					List<FileSystemItem> fileInfo = client.getFileSystemItems(
							"", lib).get();
					if (fileInfo == null) {
						throw new Exception("Cannot list children folders");
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

	private TestCase canDeleteFileInLibAndFolder(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someFile = UUID.randomUUID().toString() + ".foo";
					String lib = ApplicationContext.getTestDocumentListName();
					FileSystemItem fileInfo = client.createFile(someFile, lib)
							.get();
					if (fileInfo == null) {
						throw new Exception(
								"Cannot create the file we want to erase");
					}
					client.delete(fileInfo.getName(), lib).get();

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

	private TestCase canDeleteFileInDefaultLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someFile = UUID.randomUUID().toString() + ".foo";
					FileSystemItem fileInfo = client.createFile(someFile).get();
					if (fileInfo == null) {
						throw new Exception(
								"Cannot create the file we want to erase");
					}

					client.delete(fileInfo.getName()).get();
				
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

	private TestCase canCreateFilesWithContentInDefaultLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someFile = UUID.randomUUID().toString() + ".foo";
					byte[] content = "some content"
							.getBytes(Constants.UTF8_NAME);
					FileSystemItem fileInfo = client.createFile(someFile, true,
							content).get();
					if (fileInfo == null) {
						throw new Exception("Expected file information");
					}
					
					// Delete test folder
					client.delete(someFile);
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

	private TestCase canCreateFilesWithContentInLibAndPath(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String lib = ApplicationContext.getTestDocumentListName();
					String folderName = UUID.randomUUID().toString();
					client.createFolder(folderName, lib).get();
					String someFile = folderName + "\\"
							+ UUID.randomUUID().toString() + ".fo1";
					byte[] content = "some content"
							.getBytes(Constants.UTF8_NAME);

					FileSystemItem fileInfo = client.createFile(someFile, lib,
							true, content).get();
					String path = (String) fileInfo.getData("Id");
					FileSystemItem stored = client.getFileSystemItem(path, lib)
							.get();

					if (stored == null) {
						throw new Exception("Expected folder information");
					}
					
					//Delete test file
					client.delete(folderName, lib);
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

	private TestCase canCreateFilesInLibAndPath(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someFile = UUID.randomUUID().toString() + ".foo";
					String path = ApplicationContext.getTestDocumentListName();
					FileSystemItem fileInfo = client.createFile(someFile, path)
							.get();
					if (fileInfo == null) {
						throw new Exception("Expected folder information");
					}
					
					// Delete test file
					client.delete(someFile, path);
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

	private TestCase canCreateFilesInDefaultDocLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someFile = UUID.randomUUID().toString() + ".foo";
					FileSystemItem fileInfo = client.createFile(someFile).get();
					if (fileInfo == null) {
						throw new Exception("Expected folder information");
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

	private TestCase canCreateFolderInLibAndFolder(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);
					
					FileClient client = ApplicationContext.getFileClient();
					String someFolder = UUID.randomUUID().toString();
					String docLib = ApplicationContext.getTestDocumentListName();
					FileSystemItem folderInfo = client.createFolder(someFolder,
							docLib).get();
					if (folderInfo == null) {
						throw new Exception("Expected folder information");
					}
					
					// Delete test folder
					client.delete(someFolder,docLib).get();
					
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

	private TestCase canCreateFolderInDefaultDocLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someFolder = UUID.randomUUID().toString();
					FileSystemItem folderInfo = client.createFolder(someFolder)
							.get();
					if (folderInfo == null) {
						throw new Exception("Expected folder information");
					}

					// Delete test folder
					client.delete(someFolder);
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

	private TestCase canGetSpecificProperty(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();

					String path = UUID.randomUUID().toString() + ".txt";

					client.createFile(path).get();

					String propertyName = "Name";
					Object property = client.getProperty(propertyName, path)
							.get();
					if (property == null) {
						throw new Exception("Expected at least one file");
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

	private TestCase canGetSpecificPropertyFromLib(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String library = ApplicationContext.getTestDocumentListName();
					String folder = UUID.randomUUID().toString();
					client.createFolder(folder, library).get();

					String propertyName = "Name";
					Object property = client.getProperty(propertyName, folder,
							library).get();
					if (property == null) {
						throw new Exception("Expected at least one file");
					}
					
					// Delete test folder
					client.delete(folder, library);
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

	private TestCase canAccessFilesDefaultLibrary(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();

					List<FileSystemItem> files = client.getFileSystemItems()
							.get();

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

	private TestCase canGetFilesFromDefaultLibrary(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String fileName = UUID.randomUUID().toString() + ".txt";
					client.createFile(fileName).get();

					List<FileSystemItem> files = client.getFileSystemItems()
							.get();
					if (files.size() == 0) {
						throw new Exception("Expected at least one file");
					}

					// Delete test file
					client.delete(fileName);
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

	private TestCase canGetFilesFromSpecificLibrary(String name, boolean enabled) {

		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String someLibrary = ApplicationContext.getTestDocumentListName();

					String fileName = UUID.randomUUID().toString() + ".txt";
					client.createFile(fileName,	someLibrary).get();

					List<FileSystemItem> files = client.getFileSystemItems(
							null, someLibrary).get();
					if (files.size() == 0) {
						throw new Exception("Expected at least one file");
					}

					// Delete test file
					client.delete(fileName, someLibrary);
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

	private TestCase canGetFilePropertiesFromGivenLibAndPath(String name, boolean enabled) {

		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String folderName = UUID.randomUUID().toString();
					String docLib = ApplicationContext.getTestDocumentListName();
					client.createFolder(folderName, docLib).get();

					List<FileSystemItem> files = client.getFileSystemItems(
							folderName, docLib).get();
					if (files == null) {
						throw new Exception("Expected folder information");
					}

					// Delete test folder
					client.delete(folderName, docLib);
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

	private TestCase canGetFileFromDefaultLib(String name, boolean enabled) {

		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);

					FileClient client = ApplicationContext.getFileClient();
					String fileName = UUID.randomUUID().toString() + ".txt";
					client.createFile(fileName).get();
					byte[] file = client.getFile(fileName).get();
					if (file == null) {
						throw new Exception("Expected at least one file");
					}

					// Delete test file
					client.delete(fileName);
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

	private TestCase canGetFileFromLibAndPath(String name, boolean enabled) {
		TestCase test = new TestCase() {

			@Override
			public TestResult executeTest() {
				try {
					TestResult result = new TestResult();
					result.setStatus(TestStatus.Passed);
					result.setTestCase(this);
					String docLib = ApplicationContext.getTestDocumentListName();
					FileClient client = ApplicationContext.getFileClient();
					String folder = UUID.randomUUID().toString();
					client.createFolder(folder, docLib).get();
					String fileName = UUID.randomUUID().toString() + ".txt";
					String path = folder + "/" + fileName;

					client.createFile(path, docLib).get();
					byte[] file = client.getFile(path, docLib).get();
					if (file == null) {
						throw new Exception("Expected at least one file");
					}

					// Delete test folder
					client.delete(folder, docLib);
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
