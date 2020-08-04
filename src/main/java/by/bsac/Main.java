package by.bsac;

import by.bsac.configuration.SeleniumConfiguration;
import by.bsac.scripts.MainScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String GECKO_DRIVER_PATH = "/home/slava/geckodriver";

    public static void main(String[] args) {

        // Set gecko driver path:
        LOGGER.info(String.format("Set gecko driver path to [%s] value;", GECKO_DRIVER_PATH));
        SeleniumConfiguration.setPath(GECKO_DRIVER_PATH);

        // Run main parser script:
        MainScript.main();

    }

}
