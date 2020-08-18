package by.bsac.web.html;

import by.bsac.core.Initializable;
import by.bsac.core.Velidable;
import by.bsac.core.WebElementWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Link implements Velidable, Initializable, WebElementWrapper {

    public static final String TAG = "a";
    public static final String HREF_ATTRIBUTE = "href";

    private String href_value; // "href" attribute value;

    private final WebElement link_element;

    public Link(WebElement a_link_element) {

        // Map parameters:
        this.link_element = a_link_element;

        // Initialize:
        this.initialize();
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
    }

    private void _initializeHrefAttr() {
        // Set "href" attribute:
        this.href_value = this.link_element.getAttribute(Link.HREF_ATTRIBUTE);
    }
}
