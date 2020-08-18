package by.bsac.web;

import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.configuration.TableConstants;
import by.bsac.core.Initializable;
import by.bsac.core.Parseable;
import by.bsac.scripts.MoveToElementScript;
import by.bsac.scripts.ScriptsProcessor;
import by.bsac.web.html.Table;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticlesTable extends Table implements Parseable<ArticlesTable>, Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticlesTable.class);
    @Getter
    private List<PointerRow> pointer_rows = new ArrayList<>();
    @Getter
    private List<InnerWrapper> inner_wrappers = new ArrayList<>();
    private Map<PointerRow, InnersTable> pointer_inners_map;

    public ArticlesTable() {
        super(SeleniumConfiguration.getDriver().findElement(By.className("table")));

        this.initialize();

    }

    public ArticlesTable(WebElement a_table_element) {
        super(a_table_element);

        // Init table properties:
        this.initialize();
    }

    @Override
    public void parseTable() {
        LOGGER.debug("Try to parse table;");

        // Set inner wrappers attribute:
        this.inner_wrappers.forEach(wrapper -> wrapper.setInnerRowWrapperAttr(this.pointer_rows.get(this.inner_wrappers.indexOf(wrapper)).getRowText()));

        // Click on pointer rows:
        this.pointer_rows.forEach(row -> {
            ScriptsProcessor.process(MoveToElementScript.getInstance(), row.getRowElement());
            row.click();
            try {
                Thread.sleep(TableConstants.POINTER_ROW_CLICK_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public ArticlesTable parse() {

        if (SeleniumConfiguration.TESTS_FLAG) return this;

        LOGGER.trace("Start to parse this ArticlesTable object;");
        this.parseTable();

        LOGGER.trace("End of parse this ArticlesTable object");
        return this;
    }

    @Override
    public void initialize() {
        LOGGER.trace("Start to initialize this ArticlesTable object;");

        // Set table title ( first row ):
        super.setTableTitle(0);

        // Initialize pointer rows list:
        this._initPointerRows();

        // Initialize inners wrappers list:
        this._initInnerWrappers();

        LOGGER.trace("End of initialization this ArticlesTable object;");
    }

    private void _initPointerRows() {
        LOGGER.trace(String.format("Start to init pointer_rows list of articles table[%s]", this.toString()));
        super.getTableContent().forEach(row -> {
            if (PointerRow.isPointerRow(row)) this.pointer_rows.add( new PointerRow(row.getRowElement()));
        });
    }

    private void _initInnerWrappers() {
        LOGGER.debug(String.format("Start to init pointer_rows list of articles table[%s]", this.toString()));
        super.getTableContent().forEach(row -> {
            if (!PointerRow.isPointerRow(row)) this.inner_wrappers.add( new InnerWrapper(row.getRowElement()));
        });
    }

}
