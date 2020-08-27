package by.bsac.web;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.html.Link;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class PartItemIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItemIntegrationTests.class);
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
    void getArticleTabsLinks_twoTabs_shouldReturnTabsLinks() {

        PartItem item = new PartItem(SeleniumConfiguration.getDriver().getCurrentUrl());
        List<ArticleTabLink> links = item.getArticleTabsLinks();

        Assertions.assertEquals(2, links.size());

        links.forEach(link -> LOGGER.debug(link.toString()));

    }

    @Test
    void parse_twoArticleTable_shouldReturnTwoArticleTabs() {

        PartItem item = new PartItem(SeleniumConfiguration.getDriver().getCurrentUrl());
        item.parse();

        List<ArticleTab> tabs = item.getArticlesTabs();

        Assertions.assertEquals(2, tabs.size());
        tabs.forEach(tab -> LOGGER.debug("Article tab: " +tab));

    }

    @Test
    void getArticlesTabs_twoArticlesTabs_shouldReturnTwoArticlesTabs() {

        // Disable selenium test flag:
        SeleniumConfiguration.TESTS_FLAG = false;

        PartItem item = new PartItem(SeleniumConfiguration.getDriver().getCurrentUrl());
        item.parse();

        List<ArticleTab> tabs = item.getArticlesTabs();
        Assertions.assertEquals(2, tabs.size());

        // Parse article table:
        tabs.forEach(tab -> {

            LOGGER.debug(String.format("Parse ArticleTab[%d]", tabs.indexOf(tab) +1));
            ArticlesTable table = tab.getArticleTable();
            Assertions.assertNotNull(table);

            LOGGER.debug(String.format("Article table: %s;", table));

            Map<PointerRow, InnersTable> prow_itable = table.getPointerInnersMap();
            prow_itable.forEach((p_row, i_table) -> {
                LOGGER.debug("" +p_row);
                i_table.getInnerRows().forEach(i_row -> LOGGER.debug("\t" + i_row));
            });

        });


    }

}
