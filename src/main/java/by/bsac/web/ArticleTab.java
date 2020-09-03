package by.bsac.web;

import by.bsac.composite.Informational;
import by.bsac.composite.infos.ArticleTabInfo;
import by.bsac.composite.infos.Info;
import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.core.Linked;
import by.bsac.core.Parseable;
import by.bsac.web.html.Link;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleTab implements Linked, Parseable<ArticleTab>, Informational {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleTab.class);

    private ArticlesTable article_table; // ArticleTable in this tab;
    @Getter
    private boolean parsed_flag = false; // Parsed flag;
    @Getter
    private final Link linked; // Link to this element;

    public ArticleTab(String a_url) {
        LOGGER.trace("Construct new ArticleTab object;");

        // Construct linked object:
        this.linked = newLinked(a_url);

    }


    @Override
    public ArticleTab parse() {
        LOGGER.trace("Start to parse this ArticleTab object;");

        // Navigate to this tab:
        SeleniumConfiguration.getDriver().get(this.linked.getHrefValue());

        // Parse article table:
        this._parseArticlesTable();

        LOGGER.trace("End of parse this ArticleTab object;");
        this.parsed_flag = true;
        return this;
    }

    private void _parseArticlesTable() {
        LOGGER.trace("Start to parse ArticlesTable in this tab;");
        this.article_table = new ArticlesTable();
        this.article_table.parse();
        LOGGER.trace("End of parsing ArticlesTable in this tab;");
    }

    public ArticlesTable getArticleTable() {

        if (!this.parsed_flag) this.parse();
        return this.article_table;

    }

    @Override
    public Info getInfo() {
        // Parse this article tab instance:
        this.parse();

        // Construct new Info:
        final ArticleTabInfo info = new ArticleTabInfo();

        // Add pointer row to info object:
        this.article_table.getPointerRows().forEach((row) -> info.getPointerRows().add(row));

        return info;
    }
}
