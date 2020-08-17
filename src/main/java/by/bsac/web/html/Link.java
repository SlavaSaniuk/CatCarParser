package by.bsac.web.html;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Link {

    public static final String TAG = "a";
    public static final String HREF_ATTRIBUTE = "href";

    private WebElement link_element;

    public Link(WebElement a_link_element) {

        // Map parameters:
        this.link_element = a_link_element;
    }

    public void transfer(WebDriver driver) {

        // Get "href" attr:
        String href = this.link_element.getAttribute(Link.HREF_ATTRIBUTE);

        // Transfer to url:
        driver.get(href);
    }

    public void click() {
        // Click on element:
        this.link_element.click();
    }

    public boolean isLink() {
        // Check if link element has a 'a' tag:
        return this.link_element.getTagName().equals(Link.TAG);
    }
}
