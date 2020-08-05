package by.bsac.web;

import by.bsac.scripts.JavascriptFromJava;
import by.bsac.scripts.ScriptsProcessor;
import by.bsac.scripts.SetInnerRowsWrappersAttr;
import by.bsac.web.html.Row;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InnerWrapper extends Row {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(InnerWrapper.class);
    private static final JavascriptFromJava SET_ATTR_SCRIPT = new SetInnerRowsWrappersAttr();

    public InnerWrapper(WebElement a_row_element) {
        super(a_row_element);
    }

    public void setInnerRowWrapperAttr(Object value) {
        ScriptsProcessor.process(InnerWrapper.SET_ATTR_SCRIPT, super.getRowElement(), SetInnerRowsWrappersAttr.ATTRIBUTE_NAME, value);
    }
}
