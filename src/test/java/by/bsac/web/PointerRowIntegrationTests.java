package by.bsac.web;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.scripts.MoveToElementScript;
import by.bsac.scripts.ScriptsProcessor;
import by.bsac.web.html.HtmlUtilities;
import by.bsac.web.html.Row;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PointerRowIntegrationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointerRowIntegrationTests.class);
    private static final String TEST_URL = "http://www.catcar.info/toyota/?l=bWFya2V0PT1ldXJvfHxjYXRhbG9nPT0xNjI1MTB8fG1vZGlmbmFtZT09WlJFMTUxTC1BRUdOS1d8fGVuZ2luZT09MVpSRkV8fG1vZENvZGUxPT0zNTlXfHxtb2RDb2RlMj09WjBFfHxtb2RDb2RlMz09RHx8bW9kZWxubz09MDA5fHxQcm9kUGVyaW9kPT0yMDA2MTEyMDA4MTJ8fHJlYz09QTF8fHN0PT03MHx8c3RzPT17IjEwIjoiXHUwNDIwXHUwNDRiXHUwNDNkXHUwNDNlXHUwNDNhIiwiNjAiOiJDT1JPTExBIFNFRCAgICAoSlBQKVwvSlROQlY1NkUyMDM1MTUyNzciLCI3MCI6IjExLTA0IFx1MDQxM1x1MDQxZVx1MDQxYlx1MDQxZVx1MDQxMlx1MDQxYVx1MDQxMCBcdTA0MTFcdTA0MWJcdTA0MWVcdTA0MWFcdTA0MTAifXx8UHJvZERhdGU9PTIwMDgwNnx8bW9kX2luZm8wPT1OfHxtb2RfaW5mbzE9PU1UTXx8bW9kX2luZm8yPT01RlN8fG1vZF9pbmZvMz09TEhEfHxtb2RfaW5mbzQ9PUhUV0N8fGdyb3VwX2lkPT0xMTA0fHxwYWdlNzA9PTA%3D";

    @BeforeAll
    static void beforeAll() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TEST_URL);
    }

    @AfterAll
    static void afterAll() {
        SeleniumConfiguration.close();
    }

    @Test
    void isPointerRow_rowsArray_shouldPassTest() {

        ArticlesTable table = new ArticlesTable();
        List<PointerRow> table_rows = table.getPointerRows();

        table_rows.forEach(row -> {
            Assertions.assertTrue(PointerRow.isPointerRow(row));
            LOGGER.debug("Pointer row: " +row);
        });

    }

    @Test
    void click_simulateClick_shouldLoadOnClickScript() {

        ArticlesTable table = new ArticlesTable();
        PointerRow p_rows = table.getPointerRows().get(0);

        ScriptsProcessor.process(MoveToElementScript.getInstance(), p_rows.getRowElement());
        p_rows.click();
    }
}