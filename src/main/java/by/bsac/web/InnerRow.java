package by.bsac.web;

import by.bsac.composite.infos.Info;
import by.bsac.composite.Informational;
import by.bsac.composite.RowInfo;
import by.bsac.web.html.Row;
import org.openqa.selenium.WebElement;

public class InnerRow extends Row implements Informational {


    public InnerRow(WebElement a_row_element) {
        super(a_row_element);
    }

    @Override
    public Info getInfo() {
        return new RowInfo(this.getRowText());
    }
}
