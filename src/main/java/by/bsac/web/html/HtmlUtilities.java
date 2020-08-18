package by.bsac.web.html;

import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class HtmlUtilities {

    public static List<String> getClassNames(WebElement a_element) {

        String class_attr = a_element.getAttribute("class");
        return Arrays.asList(class_attr.split(" "));

    }

    public static String describeWebElement(WebElement a_element) {
        return String.format("WebElement[tag: [%s], text: [%s], classes: [%s]], displayed: [%s]",
                a_element.getTagName(), a_element.getText(), HtmlUtilities.getClassNames(a_element), a_element.isDisplayed());
    }
}
