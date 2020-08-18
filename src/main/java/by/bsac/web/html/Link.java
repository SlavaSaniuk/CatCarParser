package by.bsac.web.html;

import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.core.Initializable;
import by.bsac.core.Validable;
import by.bsac.core.WebElementWrapper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Link implements Validable, Initializable, WebElementWrapper {

    public static final Logger LOGGER = LoggerFactory.getLogger(Link.class);
    public static final String TAG = "a";
    public static final String HREF_ATTRIBUTE = "href";

    @Getter
    private String href_value; // "href" attribute value;
    @Getter
    private String link_home; // "href" attribute were link is exist;

    private WebElement link_element;

    public Link(WebElement a_link_element) {

        // Map parameters:
        this.link_element = a_link_element;

        // Initialize:
        this.initialize();
    }

    public Link(String a_href) {
        LOGGER.trace("Construct new Link object with href string value;");

        // Map parameters:
        this.href_value = a_href;

        // Initialize:
        this._initializeLinkHome();
    }

    public void transfer(WebDriver driver) {

        // Transfer to url:
        driver.get(this.href_value);
    }

    public void click() {
        // Click on element:
        this.link_element.click();
    }

    public boolean isLink() {
        // Check if link element has a 'a' tag:
        return this.link_element.getTagName().equals(Link.TAG);
    }

    @Override
    public boolean isValid() {
        return this.isLink();
    }

    @Override
    public WebElement getWebElement() {
        return this.link_element;
    }

    @Override
    public String toString() {
        return String.format("Link[%s]", this.href_value);
    }

    @Override
    public void initialize() {
        // Initialize href attribute:
        this._initializeHrefAttr();
        // Initialize "link_home" property:
        this._initializeLinkHome();
    }

    private void _initializeHrefAttr() {
        // Set "href" attribute:
        this.href_value = this.link_element.getAttribute(Link.HREF_ATTRIBUTE);
    }

    private void _initializeLinkHome() {
        LOGGER.trace("Start to initialize [link_home] attribute;");

        // Set "link_home" property:
        this.link_home = SeleniumConfiguration.getDriver().getCurrentUrl();

        LOGGER.trace("End of initialization [link_home] attribute;");
    }
}
