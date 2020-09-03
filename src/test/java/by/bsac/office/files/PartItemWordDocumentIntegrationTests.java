package by.bsac.office.files;

import by.bsac.TestsConstants;
import by.bsac.composite.infos.Info;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.office.MicrosoftOfficeIntegrationTests;
import by.bsac.utils.FilesUtilities;
import by.bsac.web.ArticlesTable;
import by.bsac.web.PartItem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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

    private static final boolean CLOSE_AFTER_EACH_TEST = false;
    private static final boolean CLOSE_AFTER_ALL_TEST = true;

    @BeforeEach
    void beforeEach() {

    }

    @BeforeAll
    static void beforeAll() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);
    }

    @AfterEach
    void afterEach() {
        if (CLOSE_AFTER_EACH_TEST) {
            SeleniumConfiguration.TESTS_FLAG = true;
            SeleniumConfiguration.close();
        }
    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();
    }

    private void _writeWordDocumentToFile(XWPFDocument a_document, String a_file_name) throws IOException {

        File file = FilesUtilities.createFile(TestsConstants.WORD_LOCATION, a_file_name);

        try(FileOutputStream fos = new FileOutputStream(file)) {
            a_document.write(fos);
        }
    }

    @Test
    void process_partItemPage_shouldSetPageTitle() throws IOException {

        PartItem item = new PartItem(SeleniumConfiguration.getDriver().getCurrentUrl());

        Info info = item.getInfo();

        PartItemWordDocument doc = new PartItemWordDocument(info);

        XWPFParagraph paragraph = doc.getPageTitle();

        Assertions.assertNotNull(paragraph);
        Assertions.assertFalse(paragraph.getText().isEmpty());

        this._writeWordDocumentToFile(doc.getDocument(), "PartItemTitle.docx");
        doc.getDocument().close();
    }


}
