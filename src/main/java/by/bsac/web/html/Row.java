package by.bsac.web.html;

import by.bsac.configuration.TableConstants;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Row {

    @Getter
    private WebElement row_element;
    @Getter
    private List<Cell> row_cells = new ArrayList<>();

    public Row(WebElement a_row_element) {

        // Maps parameters:
        this.row_element = a_row_element;

        // Init row properties:
        this.initRowCells();
    }

    public String getRowText() {
        StringBuilder sb = new StringBuilder("");

        this.row_cells.forEach(cell -> {
            if (sb.length() == 0) sb.append(cell.getCellText());
            else sb.append(TableConstants.TABLE_CELL_SEPARATOR).append(cell.getCellText());
        });

        return sb.toString();
    }

    private void initRowCells() {

        List<WebElement> cells_elements = this.row_element.findElements(By.tagName("td"));
        cells_elements.forEach(element -> this.row_cells.add(new Cell(element)));
    }

    @Override
    public String toString() {
        return String.format("Row{%s}", this.getRowText());
    }
}
