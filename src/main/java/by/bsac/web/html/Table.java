package by.bsac.web.html;

import by.bsac.core.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.nio.file.LinkOption;
import java.util.*;

public class Table {

    private WebElement table_element;
    private List<Row> rows;
    private List<Row> table_content;
    private Row table_title;

    public Table(WebElement a_table_element) {
        Asserts.notNull(a_table_element, "Specified table element is null;");

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

    public void parseTable() {};

    private int _getTableCellsNumber() {

        // Return number of cells of table title:
        if (this.table_title != null)
            return this.table_title.getRowCells().size();

        // Return number of cells of first row:
        return this.getTableContent().get(0).getRowCells().size();

    }

    @Override
    public String toString() {
        return String.format("Table{Rows[%d], Cells[%d], Title[%s]}",
                this.getRows().size(), this._getTableCellsNumber(),
                this.table_title != null ? this.table_title.getRowText() : "Not defined table title");
    }
}
