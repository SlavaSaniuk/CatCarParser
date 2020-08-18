package by.bsac.web;

import by.bsac.core.Velidable;
import by.bsac.web.html.HtmlUtilities;
import by.bsac.web.html.Link;
import org.openqa.selenium.WebElement;

public class ArticleTabLink extends Link implements Velidable {

    public static final String ARTICLE_TAB_LINK_CLASS = "tabs__item";

    public ArticleTabLink(WebElement a_link_element) {
        super(a_link_element);

        // Check if
    }

    @Override
    public boolean isValid() {

        // Check if link:
        if (!super.isValid()) return false;

        // Check if contains tab link class name:
        return HtmlUtilities.getClassNames(super.getWebElement()).stream().anyMatch(clazz -> clazz.equals(ArticleTabLink.ARTICLE_TAB_LINK_CLASS));
    }
}
