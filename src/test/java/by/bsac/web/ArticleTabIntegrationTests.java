package by.bsac.web;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.html.Link;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArticleTabIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleTabIntegrationTests.class);
    private static final boolean CLOSE_AFTER_EACH_TEST = true;
    private static final boolean CLOSE_AFTER_ALL_TEST = false;

    @BeforeEach
    void beforeEach() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);
    }

    @BeforeAll
    static void beforeAll() { }

    @AfterEach
    void afterEach() {        if (CLOSE_AFTER_EACH_TEST) SeleniumConfiguration.close();    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();
    }

    @Test
    void parse_articlesTableExist_shouldReturnParsedArticlesTable() {

        ArticleTab tab = new ArticleTab(SeleniumConfiguration.getDriver().getCurrentUrl());
        tab.parse();

        ArticlesTable table = tab.getArticleTable();
        Assertions.assertNotNull(table);

        table.getPointerRows().forEach(row -> LOGGER.debug("Pointer row text: " +row.getRowText()));
    }

}
