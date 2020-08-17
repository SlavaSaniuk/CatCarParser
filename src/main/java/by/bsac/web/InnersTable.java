package by.bsac.web;

import by.bsac.web.html.Table;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InnersTable extends Table {

    private List<InnerRow> inner_rows;

    public InnersTable(WebElement a_table_element) {
        super(a_table_element);
    }

    public List<InnerRow> getInnerRows() {

        // Check if inner rows already initialized:
        if (this.inner_rows != null) return this.inner_rows;

        // Initialize inner rows:
        return this._getInnerRows();
    }

    public List<InnerRow> _getInnerRows() {

        // Initialize inner rows list:
        if(this.inner_rows != null) this.inner_rows = new ArrayList<>();

        // Add inner rows:
        super.getRows().forEach(row -> this.inner_rows.add(new InnerRow(row.getRowElement())));

        return this.inner_rows;
    }


    @Override
    public void parseTable() {

    }
}
