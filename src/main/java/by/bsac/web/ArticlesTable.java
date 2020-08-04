package by.bsac.web;

import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.web.html.Table;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ArticlesTable extends Table {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticlesTable.class);
    @Getter
    private List<PointerRow> pointer_rows = new ArrayList<>();

    public ArticlesTable() {
        super(SeleniumConfiguration.getDriver().findElement(By.className("table")));

        // Init table properties:
        this.initPointerRows();
    }

    public ArticlesTable(WebElement a_table_element) {
        super(a_table_element);

        // Init table properties:
        this.initPointerRows();
    }

    private void initPointerRows() {
        LOGGER.debug(String.format("Start to init pointer_rows list of articles table[%s]", this.toString()));
        super.getRows().forEach(row -> {
            if (PointerRow.isPointerRow(row)) this.pointer_rows.add( new PointerRow(row.getRowElement()));
        });

    }
}
