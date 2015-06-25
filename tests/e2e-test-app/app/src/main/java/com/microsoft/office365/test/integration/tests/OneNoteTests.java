package com.microsoft.office365.test.integration.tests;

import android.util.Log;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.microsoft.office365.test.integration.ApplicationContext;
import com.microsoft.office365.test.integration.R;
import com.microsoft.office365.test.integration.framework.TestCase;
import com.microsoft.office365.test.integration.framework.TestGroup;
import com.microsoft.office365.test.integration.framework.TestResult;
import com.microsoft.office365.test.integration.framework.TestStatus;
import com.microsoft.onenote.api.Notebook;
import com.microsoft.onenote.api.Page;
import com.microsoft.onenote.api.PatchActionType;
import com.microsoft.onenote.api.PatchContentCommand;
import com.microsoft.onenote.api.PatchInsertPosition;
import com.microsoft.onenote.api.Section;
import com.microsoft.onenote.api.SectionGroup;
import com.microsoft.onenote.api.fetchers.OneNoteApiClient;
import com.microsoft.services.odata.CalendarSerializer;
import com.microsoft.services.odata.MultiPartElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class OneNoteTests extends TestGroup {

    public OneNoteTests() {
        super("OneNote tests");

        this.addTest(canGetNotebooks("Can get notebooks", true));
        this.addTest(canGetNotebooksById("Can get notebooks by id", true));
        this.addTest(canCreateNotebooks("Can create notebooks", true));

        this.addTest(canGetSections("Can get sections", true));
        this.addTest(canGetSectionById("Can get section by id", true));
        this.addTest(canCreateSection("Can create section", true));
        this.addTest(canGetSectionGroups("Can get section groups", true));

        this.addTest(canGetPageContent("Can get page content", true));
        this.addTest(canGetPages("Can get pages", true));
        this.addTest(canGetPagesFromSection("Can get pages from section", true));
        this.addTest(canGetPagesById("Can get pages by id", true));
        this.addTest(canCreateSimplePage("Can create simple page", true));
        this.addTest(canCreateSimplePageInSection("Can create simple page in section", true));
        this.addTest(canCreatePageWithEmbeddedWebImage("Can create page with embedded web image", true));
        this.addTest(canCreatePageWithAttachment("Can create page with attachment", true));
        this.addTest(canCreatePageWithNoteTags("Can create page with note tags", true));
        this.addTest(canCreatePageWithImage("Can create page with image", true));
        this.addTest(canPatchPage("Can patch page", true));


    }

    private TestCase canGetNotebooks(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();
                    //Act
                    List<Notebook> notebooks = client.getMe().getNote().getNotebooks().read().get();

                    //Assert
                    if (notebooks != null && notebooks.size() > 0)
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

    private TestCase canGetNotebooksById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();
                    //Act
                    List<Notebook> notebooks = client.getMe().getNote().getNotebooks().read().get();

                    //Assert
                    if (notebooks != null && notebooks.size() > 0){
                        Notebook notebook = client.getMe().getNote().getNotebooks().getById(notebooks.get(0).getId()).read().get();
                        if(notebook != null && notebook.getName().equals(notebooks.get(0).getName())) {
                            result.setStatus(TestStatus.Passed);
                        }
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

    private TestCase canCreateNotebooks(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    Log.d("ApplicationContext", "About to use client");
                    List<Notebook> notebooks = client.getMe().getNote().getNotebooks().filter("name eq 'Test notebook'").read().get();

                    if(notebooks.isEmpty()){
                        //Prepare
                        Notebook newNotebook = new Notebook();
                        newNotebook.setName("Test notebook");

                        //Act
                        Notebook added = client.getMe().getNote().getNotebooks().add(newNotebook).get();

                        //Assert
                        if (added != null && added.getName().equals(newNotebook.getName()))
                            result.setStatus(TestStatus.Passed);
                    }else
                    {
                        result.setStatus(TestStatus.Passed);
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

    private TestCase canGetSections(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();
                    //Act
                    List<Section> sections = client.getMe().getNote().getSections().read().get();

                    //Assert
                    if (sections != null)
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

    private TestCase canGetSectionById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();
                    //Prepare
                    List<Section> sections = client.getMe().getNote().getSections().read().get();

                    if(sections.isEmpty()) {
                        return result;
                    }

                    //Act
                    Section section = client.getMe().getNote().getSections().getById(sections.get(0).getId()).read().get();

                    //Assert
                    if (section != null)
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

    private TestCase canCreateSection(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    //Prepare
                    Section newSection = new Section();
                    newSection.setName(UUID.randomUUID().toString());

                    List<Notebook> notebooks = client.getMe().getNote().getNotebooks().filter("name eq 'Test notebook'").read().get();
                    if(notebooks.isEmpty())
                    {
                        return result;
                    }

                    //Act
                    Section addedSection = client.getMe().getNote().getNotebooks()
                            .getById(notebooks.get(0).getId())
                            .getSections().add(newSection).get();

                    //Assert
                    if (addedSection != null && addedSection.getName().equals(newSection.getName()))
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

    private TestCase canGetSectionGroups(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();
                    //Act
                    List<SectionGroup> sectionGroups = client.getMe().getNote().getSectionGroups().read().get();

                    //Assert
                    if (sectionGroups != null)
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

    private TestCase canGetPages(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    //Act
                    List<Page> pages = client.getMe().getNote().getPages().top(3).read().get();


                    //Assert
                    if (pages != null && pages.size()<4)
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


    private TestCase canGetPagesFromSection(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    //Prepare
                    List<Section> sections = client.getMe().getNote().getSections().top(1).read().get();

                    if(sections!= null && sections.size() >0) {
                        //Act
                        List<Page> pages = client.getMe().getNote().getSections()
                                .getById(sections.get(0).getId())
                                .getPages()
                                .top(3)
                                .read()
                                .get();


                        //Assert
                        if (pages != null && pages.size() < 4)
                            result.setStatus(TestStatus.Passed);

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

    private TestCase canGetPagesById(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    //Prepare
                    List<Page> pages = client.getMe().getNote().getPages().top(1).read().get();

                    if(pages != null && !pages.isEmpty()) {

                        //Act
                        Page singlePage = client.getMe().getNote().getPages().getById(pages.get(0).getId()).read().get();

                        //Assert
                        if (singlePage != null && singlePage.getId().equals(pages.get(0).getId()))
                            result.setStatus(TestStatus.Passed);
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

    private TestCase canGetPageContent(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Failed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    //Prepare
                    List<Page> pages = client.getMe().getNote().getPages().top(1).read().get();

                    //Act
                    byte[] content = client.getMe().getNote().getPages().getById(pages.get(0).getId()).getContent().get();

                    //Assert
                    if (content != null && content.length > 0)
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

    private TestCase canCreateSimplePage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);

                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();

                    String simpleHtml = "<html>" +
                        "<head>" +
                        "<title>A simple page created from basic HTML-formatted text</title>" +
                        "<meta name=\"created\" content=\"" + getFormattedDate() + "\" />" +
                        "</head>" +
                        "<body>" +
                        "<p>This is a page that just contains some simple <i>formatted</i> <b>text</b></p>" +
                        "<p>Here is a <a href=\"http://www.microsoft.com\">link</a></p>" +
                        "</body>" +
                        "</html>";

                    MultiPartElement m1 = new MultiPartElement("Presentation", simpleHtml);

                    multipartElements.add(m1);
                    //client.getpages().add(multipartElements).get();

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

    private TestCase canCreateSimplePageInSection(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);
                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();

                    String simpleHtml = "<html>" +
                            "<head>" +
                            "<title>A simple page created from basic HTML-formatted text</title>" +
                            "<meta name=\"created\" content=\"" + getFormattedDate() + "\" />" +
                            "</head>" +
                            "<body>" +
                            "<p>This is a page that just contains some simple <i>formatted</i> <b>text</b></p>" +
                            "<p>Here is a <a href=\"http://www.microsoft.com\">link</a></p>" +
                            "</body>" +
                            "</html>";

                    MultiPartElement m1 = new MultiPartElement("Presentation", simpleHtml);

                    multipartElements.add(m1);

                    List<Section> sections = client.getMe().getNote().getSections().top(1).read().get();
                    if(sections != null && !sections.isEmpty()){
//                        client.getsections()
//                                .getById(sections.get(0).getid())
//                                .getPages()
//                                .add(multipartElements).get();

                    }else{
                        result.setStatus(TestStatus.Failed);
                        result.setException(new Exception("Can't get section to add page"));
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

    private TestCase canCreatePageWithEmbeddedWebImage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);

                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();

                    String embeddedPartName = "embedded1";
                    String embeddedWebPage =
                            "<html>" +
                            "<head>" +
                            "<title>An embedded webpage</title>" +
                            "</head>" +
                            "<body>" +
                            "<h1>This is a screen grab of a web page</h1>" +
                            "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam vehicula magna quis mauris accumsan, nec imperdiet nisi tempus. Suspendisse potenti. " +
                            "Duis vel nulla sit amet turpis venenatis elementum. Cras laoreet quis nisi et sagittis. Donec euismod at tortor ut porta. Duis libero urna, viverra id " +
                            "aliquam in, ornare sed orci. Pellentesque condimentum gravida felis, sed pulvinar erat suscipit sit amet. Nulla id felis quis sem blandit dapibus. Ut " +
                            "viverra auctor nisi ac egestas. Quisque ac neque nec velit fringilla sagittis porttitor sit amet quam.</p>" +
                            "</body>" +
                            "</html>";

                    String simpleHtml = "<html>" +
                            "<head>" +
                            "<title>A page created with an image of an html page on it</title>" +
                            "<meta name=\"created\" content=\"2015-03-10T08:04:01.6878944-07:00\" />" +
                            "</head>" +
                            "<body>" +
                            "<h1>This is a page with an image of an html page on it.</h1>" +
                            "<img data-render-src=\"name:" + embeddedPartName +
                            "\" alt=\"A website screen grab\" />" +
                            "</body>" +
                            "</html>";



                    MultiPartElement m1 = new MultiPartElement("Presentation", simpleHtml);
                    MultiPartElement m2 = new MultiPartElement(embeddedPartName,embeddedWebPage);

                    multipartElements.add(m1);
                    multipartElements.add(m2);
                    //client.getpages().add(multipartElements);

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

    private TestCase canCreatePageWithAttachment(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);

                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();

                    String attachmentPartName = "attachment1";
                    String pageHtml = "<html>" +
                            "<head>" +
                            "<title>A page created with a file attachment</title>" +
                            "<meta name=\"created\" content=\"" + getFormattedDate() + "\" />" +
                            "</head>" +
                            "<body>" +
                            "<h1>This is a page with a text file attachment</h1>" +
                            "<object data-attachment=\"dummyFile.txt\" data=\"name:" +
                            attachmentPartName + "\" />" +
                            "</body>" +
                            "</html>";

                    java.io.File file = ApplicationContext.createTempFile(2);
                    byte[] attachmentBytes = Files.toByteArray(file);

                    MultiPartElement m1 = new MultiPartElement("Presentation", pageHtml);
                    MultiPartElement m2 = new MultiPartElement(attachmentPartName,"text/plain",attachmentBytes);

                    multipartElements.add(m1);
                    multipartElements.add(m2);
                    //client.getpages().add(multipartElements);

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

    private TestCase canCreatePageWithNoteTags(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);

                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();


                    String simpleHtml = "<html>" +
								"<head>" +
								"<title data-tag=\"to-do:completed\">A page created with note tags</title>" +
								"<meta name=\"created\" content=\"" + getFormattedDate() + "\" />" +
								"</head>" +
								"<body>" +
								"<h1 data-tag=\"important\">Paragraphs with predefined note tags</h1>" +
								"<p data-tag=\"to-do\">Paragraph with note tag to-do (data-tag=\"to-do\")</p>" +
								"<p data-tag=\"important\">Paragraph with note tag important (data-tag=\"important\")</p>" +
								"<p data-tag=\"question\">Paragraph with note tag question (data-tag=\"question\")</p>" +
								"<p data-tag=\"definition\">Paragraph with note tag definition (data-tag=\"definition\")</p>" +
								"<p data-tag=\"highlight\">Paragraph with note tag highlight (data-tag=\"contact\")</p>" +
								"<p data-tag=\"contact\">Paragraph with note tag contact (data-tag=\"contact\")</p>" +
								"<p data-tag=\"address\">Paragraph with note tag address (data-tag=\"address\")</p>" +
								"<p data-tag=\"phone-number\">Paragraph with note tag phone-number (data-tag=\"phone-number\")</p>" +
								"<p data-tag=\"web-site-to-visit\">Paragraph with note tag web-site-to-visit (data-tag=\"web-site-to-visit\")</p>" +
								"<p data-tag=\"idea\">Paragraph with note tag idea (data-tag=\"idea\")</p>" +
								"<p data-tag=\"password\">Paragraph with note tag password (data-tag=\"critical\")</p>" +
								"<p data-tag=\"critical\">Paragraph with note tag critical (data-tag=\"project-a\")</p>" +
								"<p data-tag=\"project-a\">Paragraph with note tag project-a (data-tag=\"project-b\")</p>" +
								"<p data-tag=\"project-b\">Paragraph with note tag project-b (data-tag=\"remember-for-later\")</p>" +
								"<p data-tag=\"remember-for-later\">Paragraph with note tag remember-for-later (data-tag=\"remember-for-later\")</p>" +
								"<p data-tag=\"movie-to-see\">Paragraph with note tag movie-to-see (data-tag=\"movie-to-see\")</p>" +
								"<p data-tag=\"book-to-read\">Paragraph with note tag book-to-read (data-tag=\"book-to-read\")</p>" +
								"<p data-tag=\"music-to-listen-to\">Paragraph with note tag music-to-listen-to (data-tag=\"music-to-listen-to\")</p>" +
								"<p data-tag=\"source-for-article\">Paragraph with note tag source-for-article (data-tag=\"source-for-article\")</p>" +
								"<p data-tag=\"remember-for-blog\">Paragraph with note tag remember-for-blog (data-tag=\"remember-for-blog\")</p>" +
								"<p data-tag=\"discuss-with-person-a\">Paragraph with note tag discuss-with-person-a (data-tag=\"discuss-with-person-a\")</p>" +
								"<p data-tag=\"discuss-with-person-b\">Paragraph with note tag discuss-with-person-b (data-tag=\"discuss-with-person-a\")</p>" +
								"<p data-tag=\"discuss-with-manager\">Paragraph with note tag discuss-with-manager (data-tag=\"discuss-with-manager\")</p>" +
								"<p data-tag=\"send-in-email\">Paragraph with note tag send-in-email (data-tag=\"send-in-email\")</p>" +
								"<p data-tag=\"schedule-meeting\">Paragraph with note tag schedule-meeting (data-tag=\"schedule-meeting\")</p>" +
								"<p data-tag=\"call-back\">Paragraph with note tag call-back (data-tag=\"call-back\")</p>" +
								"<p data-tag=\"to-do-priority-1\">Paragraph with note tag to-do-priority-1 (data-tag=\"to-do-priority-1\")</p>" +
								"<p data-tag=\"to-do-priority-2\">Paragraph with note tag to-do-priority-2 (data-tag=\"to-do-priority-2\")</p>" +
								"<p data-tag=\"client-request\">Paragraph with note tag client-request (data-tag=\"client-request\")</p>" +
								"<br/>" +
								"<p style=\"font-size: 16px; font-family: Calibri, sans-serif\">Paragraphs with note tag status</p>" +
								"<p data-tag=\"to-do:completed\">Paragraph with note tag status completed</p>" +
								"<p data-tag=\"call-back:completed\">Paragraph with note tag status completed</p>" +
								"<br/>" +
								"<p style=\"font-size: 16px; font-family: Calibri, sans-serif\">Paragraph with multiple note tags</p>" +
								"<p data-tag=\"critical, question\">Paragraph with two note tags</p>" +
								"<p data-tag=\"password, send-in-email\">Multiple note tags</p>" +
								"<h1>List Item with a note tag</h1>" +
								"<li data-tag=\"to-do\" id=\"todoitem2\">Build a todo app with OneNote APIs</li>" +
								"<p style=\"font-size: 16px; font-family: Calibri, sans-serif\">Image with note tag</p>" +
								"<img data-tag=\"important\" src=\"http://placecorgi.com/300\" />" +
								"</body>" +
								"</html>";


                    MultiPartElement m1 = new MultiPartElement("Presentation", simpleHtml);

                    multipartElements.add(m1);
                    //client.getpages().add(multipartElements);

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

    private TestCase canCreatePageWithImage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);

                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();

                    String imagePartName = "sampleImage1";
                    String simpleHtml = "<html>" +
                            "<head>" +
                            "<title>A simple page created with an image on it</title>" +
                            "<meta name=\"created\" content=\"" + getFormattedDate() + "\" />" +
                            "</head>" +
                            "<body>" +
                            "<h1>This is a page with an image on it</h1>" +
                            "<img src=\"name:" + imagePartName +
                            "\" alt=\"A beautiful logo\"/>" +
                            "</body>" +
                            "</html>";


                    InputStream stream = ApplicationContext.getResource(R.drawable.office);
                    byte[] bytes = ByteStreams.toByteArray(stream);
                    MultiPartElement m1 = new MultiPartElement("Presentation", simpleHtml);
                    MultiPartElement m2 = new MultiPartElement(imagePartName, "image/png", bytes);

                    multipartElements.add(m1);
                    multipartElements.add(m2);
                    //client.getpages().add(multipartElements);

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


    private TestCase canPatchPage(String name, boolean enabled) {
        TestCase test = new TestCase() {

            @Override
            public TestResult executeTest() {
                try {
                    TestResult result = new TestResult();
                    result.setStatus(TestStatus.Passed);

                    result.setTestCase(this);

                    OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

                    String pageTitle = "Page " + UUID.randomUUID().toString();
                    createPageWithTitle(pageTitle);

                    List<Page> pages = client.getMe().getNote().getPages().filter("title eq '" + pageTitle + "'").read().get();

                    if(pages != null && !pages.isEmpty()){
                        ArrayList<PatchContentCommand> commands = new ArrayList<PatchContentCommand>();

                        PatchContentCommand c1 = new PatchContentCommand();
                        c1.setAction(PatchActionType.Append);
                        c1.setPosition(PatchInsertPosition.Before);
                        c1.setTarget("body");
                        c1.setContent("<p>New trailing content</p>");

                        commands.add(c1);
                        client.getMe().getNote().getPages().getById(pages.get(0).getId()).getOperations().patchContent(commands).get();
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

    private boolean createPageWithTitle(String pageTitle){
        OneNoteApiClient client = ApplicationContext.getOneNoteApiClient();

        List<MultiPartElement> multipartElements = new ArrayList<MultiPartElement>();

        String simpleHtml = "<html>" +
                "<head>" +
                "<title>" + pageTitle +"</title>" +
                "<meta name=\"created\" content=\"" + getFormattedDate() + "\" />" +
                "</head>" +
                "<body>" +
                "<p>This is a page that just contains some simple <i>formatted</i> <b>text</b></p>" +
                "<p>Here is a <a href=\"http://www.microsoft.com\">link</a></p>" +
                "</body>" +
                "</html>";

        MultiPartElement m1 = new MultiPartElement("Presentation", simpleHtml);

        multipartElements.add(m1);
        try {
            //client.getme().getNote().getPages().add(multipartElements).get();
            return true;
        } catch (Throwable t){
            return false;
        }

    }

    private String getFormattedDate(){
        java.util.Calendar dateGt = GregorianCalendar.getInstance();
        String formatted = CalendarSerializer.serialize(dateGt);
        return formatted;
    }
}
