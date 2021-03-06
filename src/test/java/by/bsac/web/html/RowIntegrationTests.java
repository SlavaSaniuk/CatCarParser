package by.bsac.web.html;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.ArticlesTable;
import by.bsac.web.PointerRow;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RowIntegrationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(RowIntegrationTests.class);
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
    void getRowCells_rowContainsCell_shouldReturnArrayOfCells() {

        ArticlesTable table = new ArticlesTable();
        PointerRow row = table.getPointerRows().get(0);
        List<Cell> cells = row.getRowCells();

        Assertions.assertNotNull(cells);
        Assertions.assertFalse(cells.isEmpty());

        StringBuilder sb = new StringBuilder();
        cells.forEach(cell -> sb.append(" ").append(cell.getCellText()));

        LOGGER.debug("Row text: " +sb.toString());

    }

    @Test
    void getRowText_rowContainsText_shouldReturnTextString() {
        ArticlesTable table = new ArticlesTable();
        List<PointerRow> rows = table.getPointerRows();

        rows.forEach(row -> {
            String text = row.getRowText();

            Assertions.assertNotNull(text);
            Assertions.assertFalse(text.isEmpty());

            LOGGER.debug(row.toString());
        });
    }
}
