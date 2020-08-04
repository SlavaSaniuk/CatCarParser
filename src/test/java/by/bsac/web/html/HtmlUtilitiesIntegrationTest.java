package by.bsac.web.html;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.ArticlesTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HtmlUtilitiesIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtilitiesIntegrationTest.class);
    private static final String TEST_URL = "http://www.catcar.info/toyota/?l=bWFya2V0PT1ldXJvfHxjYXRhbG9nPT0xNjI1MTB8fG1vZGlmbmFtZT09WlJFMTUxTC1BRUdOS1d8fGVuZ2luZT09MVpSRkV8fG1vZENvZGUxPT0zNTlXfHxtb2RDb2RlMj09WjBFfHxtb2RDb2RlMz09RHx8bW9kZWxubz09MDA5fHxQcm9kUGVyaW9kPT0yMDA2MTEyMDA4MTJ8fHJlYz09QTF8fHN0PT03MHx8c3RzPT17IjEwIjoiXHUwNDIwXHUwNDRiXHUwNDNkXHUwNDNlXHUwNDNhIiwiNjAiOiJDT1JPTExBIFNFRCAgICAoSlBQKVwvSlROQlY1NkUyMDM1MTUyNzciLCI3MCI6IjExLTA0IFx1MDQxM1x1MDQxZVx1MDQxYlx1MDQxZVx1MDQxMlx1MDQxYVx1MDQxMCBcdTA0MTFcdTA0MWJcdTA0MWVcdTA0MWFcdTA0MTAifXx8UHJvZERhdGU9PTIwMDgwNnx8bW9kX2luZm8wPT1OfHxtb2RfaW5mbzE9PU1UTXx8bW9kX2luZm8yPT01RlN8fG1vZF9pbmZvMz09TEhEfHxtb2RfaW5mbzQ9PUhUV0N8fGdyb3VwX2lkPT0xMTA0fHxwYWdlNzA9PTA%3D";

    @BeforeEach
    public void beforeEach() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TEST_URL);
    }

    @AfterEach
    public void afterEach() {
        SeleniumConfiguration.close();
    }

    @Test
    void getClassNames_elementsContainsClasses_shouldReturnClassNamesList() {

        ArticlesTable table = new ArticlesTable();
        List<Row> table_rows = table.getRows();

        // Remove first(title) row:
        table_rows.remove(0);

        for (int i=0; i<table_rows.size(); i=i+2) {
            List<String> class_names = HtmlUtilities.getClassNames(table_rows.get(i).getRowElement());
            Assertions.assertNotNull(class_names);
            Assertions.assertNotEquals(0, class_names.size());

            LOGGER.debug(String.format("Row [%s] contains classes: %s;", table_rows.get(i), class_names));
        }
    }

}
