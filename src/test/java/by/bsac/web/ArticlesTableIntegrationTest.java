package by.bsac.web;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.html.Row;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArticlesTableIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticlesTableIntegrationTest.class);
    private static final boolean CLOSE_AFTER_EACH_TEST = true;
    private static final boolean CLOSE_AFTER_ALL_TEST = true;

    @BeforeEach
    void beforeEach() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);
    }

    @BeforeAll
    static void beforeAll() { }

    @AfterEach
    void afterEach() {
        if (CLOSE_AFTER_EACH_TEST) {
            SeleniumConfiguration.TESTS_FLAG = true;
            SeleniumConfiguration.close();
        };
    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();
    }

    @Test
    void getRows_tableContainsRows_shouldReturnListOfRows() {
        ArticlesTable table = new ArticlesTable();

        List<Row> table_rows = table.getRows();
        Assert.assertNotNull(table);
        Assert.assertNotNull(table_rows);
        Assert.assertNotEquals(0, table_rows.size());

        table_rows.forEach(row -> LOGGER.debug("Table row: " +row));
    }

    @Test
    void getPointerRows_tableContainsPointerRows_shouldReturnListOfPointerRows() {

        ArticlesTable table = new ArticlesTable();
        List<PointerRow> pointer_rows = table.getPointerRows();

        Assertions.assertNotNull(pointer_rows);
        Assertions.assertNotEquals(0, pointer_rows.size());

        pointer_rows.forEach(row -> LOGGER.debug("Pointer row: " +row));
    }

    @Test
    void getInnerWrappers_tableContainsInnerWrappers_shouldReturnInnerWrappers() {

        ArticlesTable table = new ArticlesTable();
        List<InnerWrapper> inner_wrappers = table.getInnerWrappers();

        Assertions.assertNotNull(inner_wrappers);
        Assertions.assertNotEquals(0, inner_wrappers.size());

        inner_wrappers.forEach(row -> LOGGER.debug("Inner wrapper: " +row));
    }

    @Test
    void parseTable_realTable_shouldParseTable() {

        SeleniumConfiguration.TESTS_FLAG = false;

        ArticlesTable table = new ArticlesTable();
        table.parse();

        Assertions.assertNotNull(table.getPointerInnersMap());
    }

    @Test
    void getPointerInnersMap_mapping_shouldReturnMap() {

        SeleniumConfiguration.TESTS_FLAG = false;

        ArticlesTable table = new ArticlesTable();
        table.parse();

        Assertions.assertNotNull(table.getPointerInnersMap());
        Assertions.assertNotEquals(0, table.getPointerInnersMap().size());

        table.getPointerInnersMap().forEach((key, val) -> {
            LOGGER.debug(String.format("Pointer rows[%s]:", key));
            val.getInnerRows().forEach(i_row -> LOGGER.debug(String.format("\t Inner row[%s]", i_row)));
        });
    }
}
