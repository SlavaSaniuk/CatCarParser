package by.bsac.web.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.LinkOption;
import java.util.*;

public abstract class Table {

    private WebElement table_element;
    private List<Row> rows;
    private List<Row> table_content;
    private Row table_title;

    public Table(WebElement a_table_element) {

        // Maps parameters:
        this.table_element = a_table_element;

        // Initialize this table properties:
        this.rows = this.getRows();

    }

    public List<Row> getRows() {

        // Check if row list already initialized:
        if (rows == null) {

            // Initialize rows list:
            List<WebElement> rows_elements = this.table_element.findElements(By.tagName("tr"));
            List<Row> rows_list = new ArrayList<>();
            rows_elements.forEach(element -> rows_list.add(new Row(element)));
            return rows_list;
        }

        // Return table rows:
        return this.rows;
    }

    public void setTableTitle(int a_number) {
        this.table_title = this.getRows().get(a_number);
    }

    public Row getTableTitle() {
        return this.table_title;
    }

    public List<Row> getTableContent() {
        List<Row> table_content = this.getRows();
        table_content.remove(this.table_title);
        return table_content;
    }

    public abstract void parseTable();
}
