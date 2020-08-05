package by.bsac.scripts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainScript {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainScript.class); // Logger instance;
    private static final MainScript INSTANCE = new MainScript();

    private MainScript() {}

    public static MainScript getInstance() {
        LOGGER.debug(String.format("Return singleton instance of [%s] class;", MainScript.class.getCanonicalName()));
        return MainScript.INSTANCE;
    }

    public static void main() {
        LOGGER.info("Start to execute main parser script;");
        MainScript.getInstance().run();
    }

    public void run() {

    }
}
