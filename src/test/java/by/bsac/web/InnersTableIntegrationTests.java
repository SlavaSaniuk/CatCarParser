package by.bsac.web;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InnersTableIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(InnersTableIntegrationTests.class);
    private static final boolean CLOSE_AFTER_EACH_TEST = true;
    private static final boolean CLOSE_AFTER_ALL_TEST = true;

    @BeforeEach
    void beforeEach() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);
    }

    @BeforeAll
    static void beforeAll() {
        //WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        //DRIVER.get(TEST_URL);
    }

    @AfterEach
    void afterEach() {
        if (CLOSE_AFTER_EACH_TEST) SeleniumConfiguration.close();
    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();
    }

    @Test
    void getInnerRows_innerTableExist_shouldReturnListOfInnerRows() {

        // Create article table and parse it:
        ArticlesTable table = new ArticlesTable();
        table.parseTable();

        // Get inners tables:
        List<InnersTable> inners_tables = new ArrayList<>();
        table.getInnerWrappers().forEach(wrapper -> inners_tables.add(wrapper.getInnerTable()));
        Assertions.assertNotEquals(0, inners_tables.size());

        // Get inner rows for each table:
        inners_tables.forEach(in_table -> {
            List<InnerRow> rows = in_table.getInnerRows();

            Assertions.assertNotNull(rows);
            Assertions.assertNotEquals(0, rows.size());

            LOGGER.debug(String.format("Pointer [%s] with values: {\n %s \n}", table.getPointerRows().get(inners_tables.indexOf(in_table)), rows));
        });
    }

}
