package by.bsac.scripts;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClickOnElementScript implements JavascriptFromJava {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ClickOnElementScript.class);
    private static final String SCRIPT = "arguments[0].click();";
    private static final ClickOnElementScript INSTANCE = new ClickOnElementScript();

    private ClickOnElementScript() {}

    public static ClickOnElementScript getInstance() {
        return ClickOnElementScript.INSTANCE;
    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object... args) {
        LOGGER.debug(String.format("Run [%s] script;", ClickOnElementScript.class.getCanonicalName()));

        if (args[0] instanceof WebElement)
            ScriptsProcessor.process(ClickOnElementScript.SCRIPT, args[0]);
        else {
            LOGGER.error(String.format("Specified parameter is not [%s] object;", WebElement.class.getCanonicalName()));
            throw new IllegalArgumentException(String.format("Specified parameter is not [%s] object;", WebElement.class.getCanonicalName()));
        }
    }
}
