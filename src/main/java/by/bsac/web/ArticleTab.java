package by.bsac.web;

import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.core.Initializable;
import by.bsac.web.html.Link;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ArticleTab implements Initializable {

    private ArticlesTable article_table;
    @Getter
    private List<ArticleTabLink> article_tabs_links;

    public ArticleTab() {
        // Initialize:
        this.initialize();
    }


    @Override
    public void initialize() {

        // Initialize article tabs links:
        this._initializeArticleTableLinks();

    }

    private void _initializeArticleTableLinks() {

        // Check if article_tabs_links is initialized
        if (this.article_tabs_links == null) this.article_tabs_links = new ArrayList<>();

        // Get article tabs links wrappers:
        List<WebElement> wrappers_elements = SeleniumConfiguration.getDriver().findElements(By.className(ArticleTabLink.ARTICLE_TAB_LINK_CLASS));

        // Construct new article tabs links:
        wrappers_elements.forEach(element -> this.article_tabs_links.add(new ArticleTabLink(element.findElement(By.tagName(Link.TAG)))));
    }
}
