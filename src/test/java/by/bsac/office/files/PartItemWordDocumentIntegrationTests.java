package by.bsac.office.files;

import by.bsac.TestsConstants;
import by.bsac.composite.infos.Info;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.office.MicrosoftOfficeIntegrationTests;
import by.bsac.utils.FilesUtilities;
import by.bsac.web.ArticlesTable;
import by.bsac.web.PartItem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PartItemWordDocumentIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItemWordDocumentIntegrationTests.class);

    private static final boolean CLOSE_AFTER_EACH_TEST = true;
    private static final boolean CLOSE_AFTER_ALL_TEST = true;
    private static PartItemWordDocument DOCUMENT;

    @BeforeEach
    void beforeEach() {

    }

    @BeforeAll
    static void beforeAll() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);

        //PartItem item = new PartItem(SeleniumConfiguration.getDriver().getCurrentUrl());
        //Info info = item.getInfo();
        //PartItemWordDocumentIntegrationTests.DOCUMENT = new PartItemWordDocument(info);
    }

    @AfterEach
    void afterEach() {
        if (CLOSE_AFTER_EACH_TEST) {
            SeleniumConfiguration.TESTS_FLAG = true;
        }
    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();

        //try {
            //DOCUMENT.getDocument().close();
        //} catch (IOException e) {
            //e.printStackTrace();
       // }
    }

    private void _writeWordDocumentToFile(XWPFDocument a_document, String a_file_name) throws IOException {

        File file = FilesUtilities.createFile(TestsConstants.WORD_LOCATION, a_file_name);

        try(FileOutputStream fos = new FileOutputStream(file)) {
            a_document.write(fos);
        }
    }

    @Test
    void process_partItemPage_shouldSetPageTitle() throws IOException {


        XWPFParagraph paragraph = DOCUMENT.getPageTitle();

        Assertions.assertNotNull(paragraph);
        Assertions.assertFalse(paragraph.getText().isEmpty());

        this._writeWordDocumentToFile(DOCUMENT.getDocument(), "PartItemTitle.docx");
    }

    @Test
    void process_partItemPage_shouldSetDocumentHeader() throws IOException {

        XWPFHeader header = DOCUMENT.getDocumentHeader();

        Assertions.assertNotNull(header);
        Assertions.assertFalse(header.getText().isEmpty());

        this._writeWordDocumentToFile(DOCUMENT.getDocument(), "PartItemHeader.docx");
    }

    @Test
    @Disabled
    void process_partItemPage_shouldReturnArticlesTableList() throws IOException {

        SeleniumConfiguration.TESTS_FLAG = false;

        PartItem item = new PartItem(SeleniumConfiguration.getDriver().getCurrentUrl());
        Info info = item.getInfo();

        PartItemWordDocument document = new PartItemWordDocument(info);
        document.process();

        Assertions.assertNotNull(document.getArticlesTables());
        Assertions.assertEquals(2, document.getArticlesTables().size());

        this._writeWordDocumentToFile(document.getDocument(), "PartItemArticlesTables.docx");
    }
    



}
