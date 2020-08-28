package by.bsac.web;

import by.bsac.composite.Info;
import by.bsac.composite.Informational;
import by.bsac.composite.RowInfo;
import by.bsac.scripts.ClickOnElementScript;
import by.bsac.scripts.ScriptsProcessor;
import by.bsac.web.html.HtmlUtilities;
import by.bsac.web.html.Row;
import org.openqa.selenium.WebElement;

import java.util.List;


public class PointerRow extends Row implements Informational {
    public static final String POINTER_ROW_CLASS_NAME = "pointer";

    public PointerRow(WebElement a_row_element) {
        super(a_row_element);
    }

    public static boolean isPointerRow(Row a_row) {
        List<String> class_names = HtmlUtilities.getClassNames(a_row.getRowElement());
        return class_names.contains(POINTER_ROW_CLASS_NAME);
    }

    public void click() {
        ScriptsProcessor.process(ClickOnElementScript.getInstance(), super.getRowElement());
    }

    @Override
    public Info getInfo() {
        return new RowInfo(this.getRowText());
    }
}
