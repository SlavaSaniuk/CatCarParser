package by.bsac.scripts;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.ArticlesTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SetInnerRowsWrappersAttrIntegrationTest {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(SetInnerRowsWrappersAttrIntegrationTest.class);
    private static final String TEST_URL = "http://www.catcar.info/toyota/?l=bWFya2V0PT1ldXJvfHxjYXRhbG9nPT0xNjI1MTB8fG1vZGlmbmFtZT09WlJFMTUxTC1BRUdOS1d8fGVuZ2luZT09MVpSRkV8fG1vZENvZGUxPT0zNTlXfHxtb2RDb2RlMj09WjBFfHxtb2RDb2RlMz09RHx8bW9kZWxubz09MDA5fHxQcm9kUGVyaW9kPT0yMDA2MTEyMDA4MTJ8fHJlYz09QTF8fHN0PT03MHx8c3RzPT17IjEwIjoiXHUwNDIwXHUwNDRiXHUwNDNkXHUwNDNlXHUwNDNhIiwiNjAiOiJDT1JPTExBIFNFRCAgICAoSlBQKVwvSlROQlY1NkUyMDM1MTUyNzciLCI3MCI6IjExLTA0IFx1MDQxM1x1MDQxZVx1MDQxYlx1MDQxZVx1MDQxMlx1MDQxYVx1MDQxMCBcdTA0MTFcdTA0MWJcdTA0MWVcdTA0MWFcdTA0MTAifXx8UHJvZERhdGU9PTIwMDgwNnx8bW9kX2luZm8wPT1OfHxtb2RfaW5mbzE9PU1UTXx8bW9kX2luZm8yPT01RlN8fG1vZF9pbmZvMz09TEhEfHxtb2RfaW5mbzQ9PUhUV0N8fGdyb3VwX2lkPT0xMTA0fHxwYWdlNzA9PTA%3D";

    @BeforeAll
    static void beforeAll() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TEST_URL);
    }

    @Test
    void process_tableContainsInnerRows_shouldSetCustomAttribute() {
        ScriptsProcessor.process(SetInnerRowsWrappersAttr.SCRIPT);
    }

    @Test
    void process_fromJava_shouldSetCustomAttr() {

        ArticlesTable table = new ArticlesTable();

        Assertions.assertEquals(table.getPointerRows().size(), table.getInnerWrappers().size());

        table.getInnerWrappers().forEach(wrapper -> {
            SetInnerRowsWrappersAttr script = new SetInnerRowsWrappersAttr();
            ScriptsProcessor.process(script, wrapper.getRowElement(), SetInnerRowsWrappersAttr.ATTRIBUTE_NAME, table.getPointerRows().get(table.getInnerWrappers().indexOf(wrapper)).getRowText());
        });

        table.getInnerWrappers().forEach(wrapper -> {
            Assertions.assertFalse(wrapper.getRowElement().getAttribute(SetInnerRowsWrappersAttr.ATTRIBUTE_NAME).isEmpty());
            LOGGER.debug("Attribute name: " +wrapper.getRowElement().getAttribute(SetInnerRowsWrappersAttr.ATTRIBUTE_NAME));
        });

    }

}
