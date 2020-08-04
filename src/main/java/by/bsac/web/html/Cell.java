package by.bsac.web.html;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;

public class Cell {

    @Getter
    private WebElement cell_element;
    @Setter
    private String cell_text = null;

    public Cell(WebElement a_cell_element) {

        // Maps parameters:
        this.cell_element = a_cell_element;

        // Initialize cell properties:
        this.cell_text = getCellText();
    }

    public String getCellText() {
        // Check if cell text already initialized:
        if (this.cell_text != null) return this.cell_text;

        return this.cell_element.getText();
    }


}
