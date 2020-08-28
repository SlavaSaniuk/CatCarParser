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
        }
    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();
    }

    @Test
    void getText_pointerRow_shouldReturnTextOfPointerRow() {

        ArticlesTable table = new ArticlesTable();
        List<PointerRow> p_rows = table.getPointerRows();

        p_rows.forEach(p_row -> {
            RowInfo info = (RowInfo) p_row.getInfo();
            Assertions.assertNotNull(info.getText());
            Assertions.assertNotEquals(0, info.getText().length());
            LOGGER.debug("Pointer row info: " +info);
        });

    }
}
