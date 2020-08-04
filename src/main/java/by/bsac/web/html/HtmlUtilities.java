package by.bsac.web.html;

import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class HtmlUtilities {

    public static List<String> getClassNames(WebElement a_element) {

        String class_attr = a_element.getAttribute("class");
        return Arrays.asList(class_attr.split(" "));

    }
}
