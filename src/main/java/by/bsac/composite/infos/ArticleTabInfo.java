package by.bsac.composite.infos;

import by.bsac.web.ArticleTab;
import by.bsac.web.html.Row;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ArticleTabInfo extends BaseInfo {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleTabInfo.class);

    @Getter
    private final List<Row> pointer_rows = new ArrayList<>();

    public ArticleTabInfo() {
        super();
        LOGGER.trace("Construct new ArticleTabInfo instance;");

        // Set super properties:
        super.info_type = InfoType.ARTICLE_TAB;
        super.object_type = ArticleTab.class;
    }




}
