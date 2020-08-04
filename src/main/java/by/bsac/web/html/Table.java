package by.bsac.web.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private WebElement table_element;
    private List<Row> rows;

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
}
