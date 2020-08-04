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
    private static final String TEST_URL = "http://www.catcar.info/toyota/?l=bWFya2V0PT1ldXJvfHxjYXRhbG9nPT0xNjI1MTB8fG1vZGlmbmFtZT09WlJFMTUxTC1BRUdOS1d8fGVuZ2luZT09MVpSRkV8fG1vZENvZGUxPT0zNTlXfHxtb2RDb2RlMj09WjBFfHxtb2RDb2RlMz09RHx8bW9kZWxubz09MDA5fHxQcm9kUGVyaW9kPT0yMDA2MTEyMDA4MTJ8fHJlYz09QTF8fHN0PT03MHx8c3RzPT17IjEwIjoiXHUwNDIwXHUwNDRiXHUwNDNkXHUwNDNlXHUwNDNhIiwiNjAiOiJDT1JPTExBIFNFRCAgICAoSlBQKVwvSlROQlY1NkUyMDM1MTUyNzciLCI3MCI6IjExLTA0IFx1MDQxM1x1MDQxZVx1MDQxYlx1MDQxZVx1MDQxMlx1MDQxYVx1MDQxMCBcdTA0MTFcdTA0MWJcdTA0MWVcdTA0MWFcdTA0MTAifXx8UHJvZERhdGU9PTIwMDgwNnx8bW9kX2luZm8wPT1OfHxtb2RfaW5mbzE9PU1UTXx8bW9kX2luZm8yPT01RlN8fG1vZF9pbmZvMz09TEhEfHxtb2RfaW5mbzQ9PUhUV0N8fGdyb3VwX2lkPT0xMTA0fHxwYWdlNzA9PTA%3D";

    @BeforeAll
    static void beforeAll() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TEST_URL);
    }

    @AfterAll
    static void afterEach() {
        SeleniumConfiguration.close();
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
}
