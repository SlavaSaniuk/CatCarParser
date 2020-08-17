package by.bsac.web;

import by.bsac.scripts.JavascriptFromJava;
import by.bsac.scripts.ScriptsProcessor;
import by.bsac.scripts.SetInnerRowsWrappersAttr;
import by.bsac.web.html.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InnerWrapper extends Row {

    //Logger
    private static final JavascriptFromJava SET_ATTR_SCRIPT = new SetInnerRowsWrappersAttr();
    private InnersTable inner_table; // Inner table element in wrapper;

    public InnerWrapper(WebElement a_row_element) {
        super(a_row_element);
    }

    public void setInnerRowWrapperAttr(Object value) {
        ScriptsProcessor.process(InnerWrapper.SET_ATTR_SCRIPT, super.getRowElement(), SetInnerRowsWrappersAttr.ATTRIBUTE_NAME, value);
    }

    public boolean isDisplayed() {
        return super.getRowElement().getAttribute("style").isEmpty();
    }

    public InnersTable getInnerTable() {

        // Check if inner table already initialized:
        if (this.inner_table != null) return this.inner_table;

        // Initialize inner table:
        return this._getInnerTable();
    }

    private InnersTable _getInnerTable() {
        // Initialize inner table:
        WebElement table_element = super.getRowElement().findElement(By.tagName("table"));
        this.inner_table = new InnersTable(table_element);

        return this.inner_table;
    }
}
