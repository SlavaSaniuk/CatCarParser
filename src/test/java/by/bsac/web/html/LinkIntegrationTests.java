package by.bsac.web.html;

import by.bsac.TestsConstants;
import by.bsac.configuration.SeleniumConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LinkIntegrationTests {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkIntegrationTests.class);
    private static final boolean CLOSE_AFTER_EACH_TEST = true;
    private static final boolean CLOSE_AFTER_ALL_TEST = false;

    private Link test_link;

    @BeforeEach
    void beforeEach() {
        WebDriver DRIVER = SeleniumConfiguration.setPath(TestsConstants.GECKO_PATH);
        DRIVER.get(TestsConstants.TEST_URL);
    }

    @BeforeAll
    static void beforeAll() { }

    @AfterEach
    void afterEach() {
        if (CLOSE_AFTER_EACH_TEST) SeleniumConfiguration.close();

        // Reset test link:
        this.test_link = null;
    }

    @AfterAll
    static void afterAll() {
        if (CLOSE_AFTER_ALL_TEST) SeleniumConfiguration.close();
    }

    @Test
    void transfer_anyHrefLocation_shouldTransferToHrefLocation() {

        final String old_url = SeleniumConfiguration.getDriver().getCurrentUrl(); // Start url;

        // Construct new link element:
        Link second_link = this._setTestLink();

        // Transfer to new location:
        second_link.transfer(SeleniumConfiguration.getDriver());

        final String current_url = SeleniumConfiguration.getDriver().getCurrentUrl(); // finish url;

        Assertions.assertNotEquals(old_url, current_url);
        LOGGER.debug("Start url: " +old_url);
        LOGGER.debug("Finish url: " +current_url);

    }

    @Test
    void isLink_webElementIsLink_shouldReturnTrue() {

        // Get link element:
        Link link = this._setTestLink();

        Assertions.assertTrue(link.isLink());
    }

    @Test
    void click_clickOnLink_shouldTransferToNewLocation() {

        final String old_url = SeleniumConfiguration.getDriver().getCurrentUrl(); // Start url;

        // Get link and click on them:
        Link link = this._setTestLink();
        link.click();

        final String current_url = SeleniumConfiguration.getDriver().getCurrentUrl(); // finish url;
        Assertions.assertNotEquals(old_url, current_url);

    }


    private Link _setTestLink() {
        // Get link wrappers elements:
        List<WebElement> elements = SeleniumConfiguration.getDriver().findElements(By.className("tabs__item"));

        // Construct new link element:
        this.test_link = new Link(elements.get(elements.size()-1).findElement(By.tagName(Link.TAG)));
        return this.test_link;
    }


}
