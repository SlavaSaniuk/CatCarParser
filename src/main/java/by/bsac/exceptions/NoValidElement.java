package by.bsac.exceptions;


import by.bsac.web.html.HtmlUtilities;
import org.openqa.selenium.WebElement;

public class NoValidElement extends RuntimeException {

    public NoValidElement() {
        super("Element is not valid;");
    }

    public NoValidElement(WebElement a_element) {
        super(String.format("Not valid element[%s];", HtmlUtilities.describeWebElement(a_element)));
    }
}
