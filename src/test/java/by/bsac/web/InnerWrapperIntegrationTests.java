package by.bsac.web;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.scripts.SetInnerRowsWrappersAttr;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InnerWrapperIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(InnerWrapperIntegrationTests.class);
    private static final String TEST_URL = "http://www.catcar.info/toyota/?l=bWFya2V0PT1ldXJvfHxjYXRhbG9nPT0xNjI1MTB8fG1vZGlmbmFtZT09WlJFMTUxTC1BRUdOS1d8fGVuZ2luZT09MVpSRkV8fG1vZENvZGUxPT0zNTlXfHxtb2RDb2RlMj09WjBFfHxtb2RDb2RlMz09RHx8bW9kZWxubz09MDA5fHxQcm9kUGVyaW9kPT0yMDA2MTEyMDA4MTJ8fHJlYz09QTF8fHN0PT03MHx8c3RzPT17IjEwIjoiXHUwNDIwXHUwNDRiXHUwNDNkXHUwNDNlXHUwNDNhIiwiNjAiOiJDT1JPTExBIFNFRCAgICAoSlBQKVwvSlROQlY1NkUyMDM1MTUyNzciLCI3MCI6IjExLTA0IFx1MDQxM1x1MDQxZVx1MDQxYlx1MDQxZVx1MDQxMlx1MDQxYVx1MDQxMCBcdTA0MTFcdTA0MWJcdTA0MWVcdTA0MWFcdTA0MTAifXx8UHJvZERhdGU9PTIwMDgwNnx8bW9kX2luZm8wPT1OfHxtb2RfaW5mbzE9PU1UTXx8bW9kX2luZm8yPT01RlN8fG1vZF9pbmZvMz09TEhEfHxtb2RfaW5mbzQ9PUhUV0N8fGdyb3VwX2lkPT0xMTA0fHxwYWdlNzA9PTA%3D";
    private static final boolean close_after_each_test = true;
    private static final boolean close_after_all_test = true;

    @BeforeEach
    void beforeEach() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TEST_URL);
    }

    @BeforeAll
    static void beforeAll() {
        //WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        //DRIVER.get(TEST_URL);
    }

    @AfterEach
    void afterEach() {
        if (close_after_each_test) SeleniumConfiguration.close();
    }

    @AfterAll
    static void afterAll() {
        if (close_after_all_test) SeleniumConfiguration.close();
    }

    @Test
    void setInnerRowWrapperAttr_wrapperAttrIsString_shouldSetAttr() {
        ArticlesTable table = new ArticlesTable();
        List<InnerWrapper> wrappers = table.getInnerWrappers();

        Assertions.assertEquals(table.getPointerRows().size(), wrappers.size());

        wrappers.forEach(wrapper -> wrapper.setInnerRowWrapperAttr(table.getPointerRows().get(wrappers.indexOf(wrapper)).getRowText()));

        wrappers.forEach(wrapper -> {
            String attr = wrapper.getRowElement().getAttribute(SetInnerRowsWrappersAttr.ATTRIBUTE_NAME);

            Assertions.assertNotNull(attr);
            Assertions.assertFalse(attr.isEmpty());
            LOGGER.debug(String.format("Attribute[%s] has value: %s", SetInnerRowsWrappersAttr.ATTRIBUTE_NAME, attr));
        });
    }

    @Test
    void isDisplayed_notDisplayedInnerWrappers_shouldReturnFalse() {
        ArticlesTable table = new ArticlesTable();

        table.getInnerWrappers().forEach(wrapper -> {
            Assertions.assertFalse(wrapper.isDisplayed());
        });

    }

    @Test
    void getInnerTable_innerTableExist_shouldReturnInnerTable() {

        ArticlesTable table = new ArticlesTable();
        table.parseTable();

        InnersTable inner = table.getInnerWrappers().get(0).getInnerTable();

        Assertions.assertNotNull(inner);
        LOGGER.debug(String.format("Pointer row[%s] -> inners table[%s];", table.getPointerRows().get(0), inner));

    }

}
