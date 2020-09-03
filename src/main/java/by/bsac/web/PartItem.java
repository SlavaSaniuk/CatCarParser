package by.bsac.web;

import by.bsac.composite.Informational;
import by.bsac.composite.infos.Info;
import by.bsac.composite.infos.PartItemInfo;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.core.Initializable;
import by.bsac.core.Linked;
import by.bsac.core.Parseable;
import by.bsac.web.html.Link;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PartItem implements Linked, Parseable<PartItem>, Initializable, Informational {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItem.class);

    @Getter
    private List<ArticleTabLink> article_tabs_links; // List of article tabs links;
    @Getter
    private final Link linked; // Link to this element;
    @Getter
    private List<ArticleTab> articles_tabs; // Articles tabs list (available only after parsing);

    @Getter
    private String item_title; // Part item title;
    private static final String ITEM_TITLE_WRAPPER_CLASS_NAME = "breadcrumbs__item"; // Class name of item title wrapper;

    public PartItem(String a_url) {
        LOGGER.trace("Construct new PartItem object via URL string;");

        // Set linked:
        this.linked = newLinked(a_url);

        // Initialize:
        this.initialize();

    }

    @Override
    public PartItem parse() {
        LOGGER.trace("Start to parse this PartItem object;");

        // Load part item page:
        SeleniumConfiguration.getDriver().get(this.linked.getHrefValue());

        // parse articles tabs:
        this._parseArticlesTabs();

        LOGGER.trace("End of parse this PartItem object;");
        return this;
    }

    private void _parseArticlesTabs() {
        LOGGER.trace("Start to parse article tabs;");

        // Check if article tabs already parsed:
        if (this.articles_tabs == null) this.articles_tabs = new ArrayList<>();

        // Add article tabs to list:
        this.article_tabs_links.forEach(tab_link -> {
            // Construct new ArticleTab object:
            ArticleTab tab = new ArticleTab(tab_link.getHrefValue());

            // Parse ArticleTab object
            tab.parse();

            this.articles_tabs.add(new ArticleTab(tab_link.getHrefValue()));
        });

        LOGGER.trace("End of parse article tabs;");
    }

    @Override
    public void initialize() {
        LOGGER.trace("Start to initialize PartItem object;");

        // Navigate to part item page:
        SeleniumConfiguration.getDriver().get(this.linked.getHrefValue());

        // Initialize item title:
        this._initializeItemTitle();

        // Initialize article tabs links:
        this._initializeArticleTabsLinks();

        // Navigate to back
        SeleniumConfiguration.getDriver().get(this.linked.getLinkHome());

        LOGGER.trace("End of initialize PartItem object;");
    }

    private void _initializeItemTitle() {
        LOGGER.trace("Start to initialize PartItem title;");

        WebElement wrapper = SeleniumConfiguration.getDriver().findElements(By.className(PartItem.ITEM_TITLE_WRAPPER_CLASS_NAME)).get(3);

        WebElement title_element = wrapper.findElements(By.tagName("span")).get(1);

        this.item_title = title_element.getText();
    }

    /*
        Initialize article tabs links. Get links object from PartItem page and construct new ArticleTabLink objects;
     */
    private void _initializeArticleTabsLinks() {
        LOGGER.trace("Start to parse article tabs links;");

        // Check if article_tabs_links is initialized
        if (this.article_tabs_links == null) this.article_tabs_links = new ArrayList<>();

        // Get article tabs links wrappers:
        List<WebElement> wrappers_elements = SeleniumConfiguration.getDriver().findElements(By.className(ArticleTabLink.ARTICLE_TAB_LINK_CLASS));

        // Construct new article tabs links:
        wrappers_elements.forEach(element -> this.article_tabs_links.add(new ArticleTabLink(element.findElement(By.tagName(Link.TAG)))));

        LOGGER.trace("End of initialize article tabs links;");
    }

    @Override
    public Info getInfo() {

        // Create info:
        PartItemInfo info = new PartItemInfo();

        // Set info title:
        info.setPartItemTitle(this.item_title);

        return info;
    }
}
