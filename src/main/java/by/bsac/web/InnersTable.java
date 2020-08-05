package by.bsac.web;

import by.bsac.web.html.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InnersTable extends Table {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(InnersTable.class);
    private static final String INNERS_TABLE_CLASS_NAME = "inner-part";

    public InnersTable(InnerWrapper a_wrapper) {
        super(a_wrapper.getRowElement().findElement(By.className(InnersTable.INNERS_TABLE_CLASS_NAME)));
    }

    public InnersTable(WebElement a_table_element) {
        super(a_table_element);
    }

    @Override
    public void parseTable() {

    }
}
