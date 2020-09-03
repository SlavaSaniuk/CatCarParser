package by.bsac.office;

import by.bsac.TestsConstants;
import by.bsac.composite.CompositionsIntegrationTests;
import by.bsac.composite.RowInfo;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.utils.FilesUtilities;
import by.bsac.web.ArticlesTable;
import by.bsac.web.PointerRow;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class MicrosoftOfficeIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MicrosoftOfficeIntegrationTests.class);

    private static final boolean CLOSE_AFTER_EACH_TEST = false;
    private static final boolean CLOSE_AFTER_ALL_TEST = true;
    private static ArticlesTable ARTICLES_TABLE;

    @BeforeEach
    void beforeEach() {

    }

    @BeforeAll
    static void beforeAll() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);

        // Parse articles table:
        SeleniumConfiguration.TESTS_FLAG = false;
        MicrosoftOfficeIntegrationTests.ARTICLES_TABLE = new ArticlesTable();
        ARTICLES_TABLE.parse();
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

    @Test
    void newXWPFDocument_createNefFile_shouldCreateNewDocxFile() throws IOException {

        final XWPFDocument document = new XWPFDocument();
        final String file_path = "/home/slava";
        final String file_name = this.getClass().getSimpleName() +".docx";

        File word_file = FilesUtilities.createFile(file_path, file_name);
        Assertions.assertTrue(word_file.exists());

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(this.getClass().getSimpleName() +" test file.");

        document.write(new FileOutputStream(word_file));

        Assertions.assertNotNull(word_file);
        document.close();
    }

    @Test
    void createTable_bewFile_shouldCreateNewDocxFile() throws IOException {

        final XWPFDocument document = new XWPFDocument();
        final String file_path = "/home/slava";
        final String file_name = "TablesTests" +".docx";

        File word_file = FilesUtilities.createFile(file_path, file_name);
        Assertions.assertTrue(word_file.exists());

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("TableTests" +" test file.");


        XWPFTable table = document.createTable();
        XWPFTableRow title_row = table.getRow(0);
        title_row.getCell(0).setText("Article");
        title_row.addNewTableCell().setText("Name of part");

        List<PointerRow> p_rows = ARTICLES_TABLE.getPointerRows();

        p_rows.forEach(p_row -> {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(p_row.getRowCells().get(0).getCellText());
            row.getCell(1).setText(p_row.getRowCells().get(1).getCellText());
        });

        document.write(new FileOutputStream(word_file));

        Assertions.assertNotNull(word_file);
        document.close();
    }
}