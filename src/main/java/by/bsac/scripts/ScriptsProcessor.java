package by.bsac.scripts;

import by.bsac.configuration.SeleniumConfiguration;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptsProcessor {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptsProcessor.class);
    private static final ScriptsProcessor INSTANCE = new ScriptsProcessor();

    private ScriptsProcessor() {}

    public static void process(String a_script) {
        JavascriptExecutor executor = (JavascriptExecutor) SeleniumConfiguration.getDriver();
        executor.executeScript(a_script);
    }

    public static void process(String a_script, Object... args) {
        JavascriptExecutor executor = (JavascriptExecutor) SeleniumConfiguration.getDriver();
        executor.executeScript(a_script, args);
    }

    public static void process(JavascriptFromJava a_script, Object... args) {
        a_script.run(args);
    }

}
