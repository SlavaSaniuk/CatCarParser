package by.bsac.composite;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.ArticlesTable;
import by.bsac.web.PartItemIntegrationTests;
import by.bsac.web.PointerRow;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CompositionsIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(CompositionsIntegrationTests.class);

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
        CompositionsIntegrationTests.ARTICLES_TABLE = new ArticlesTable();
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
    void getText_pointerRow_shouldReturnTextOfPointerRow() {

        List<PointerRow> p_rows = ARTICLES_TABLE.getPointerRows();

        p_rows.forEach(p_row -> {
            RowInfo info = (RowInfo) p_row.getInfo();
            Assertions.assertNotNull(info.getText());
            Assertions.assertNotEquals(0, info.getText().length());
            LOGGER.debug("Pointer row info: " +info);
        });
    }

    @Test
    void getText_innerRow_shouldReturnTextOfInnersRows() {

        ARTICLES_TABLE.getPointerInnersMap().forEach((p_row, i_tbl) -> {
            if (i_tbl.getInfo().getType() == Info.InfoType.TABLE) {
                TableInfo tbl_info = new TableInfo(i_tbl);
                tbl_info.getInfos().forEach(info -> {
                    Assertions.assertNotNull(info);
                    LOGGER.debug("Inners info: " +info);
                });
            }
        });

    }

    @Test
    void getInfo_innersTable_shouldReturnTableInfo() {
        ARTICLES_TABLE.getPointerInnersMap().forEach((p_row, i_tbl) -> {
            if (i_tbl.getInfo().getType() == Info.InfoType.TABLE) {
                TableInfo tbl_info = new TableInfo(i_tbl);

                Assertions.assertEquals(Info.InfoType.TABLE, tbl_info.getType());
                LOGGER.debug("Table info:");

                tbl_info.getInfos().forEach(info -> {
                    Assertions.assertNotNull(info);
                    LOGGER.debug("Inners info: " +info);
                });
            }
        });
    }
}
