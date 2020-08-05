package by.bsac.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Selenium configuration.
 */
public class SeleniumConfiguration {


    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumConfiguration.class);
    private static final SeleniumConfiguration INSTANCE = new SeleniumConfiguration();
    private static WebDriver driver = null;
    private static boolean path_flag = false;
    private static final String GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver";

    private SeleniumConfiguration() {}

    /**
     * Gets instance.
     *
     * @return instance - Current singleton instance;
     */
    public static SeleniumConfiguration getInstance() {
        return SeleniumConfiguration.INSTANCE;
    }

    /**
     * Sets path.
     * Set path to gecko driver property;
     * @param a_path - path to gecko driver;
     * @return {@link WebDriver} Selenium firefox driver instance;
     */
    public static WebDriver setPath(String a_path) {
        // Set system property:
        LOGGER.debug(String.format("Set gecko driver path to [%s] system path;", a_path));
        System.setProperty(GECKO_DRIVER_PROPERTY, a_path);

        // Set path flag to true:
        SeleniumConfiguration.path_flag = true;

        // Return driver instance:
        return SeleniumConfiguration.getDriver();
    }


    /**
     * Is path installed boolean.
     * Check whether user is set gecko driver path property;
     * @return the boolean
     */
    public static boolean isPathInstalled() {
        return SeleniumConfiguration.path_flag;
    }

    /**
     * Gets driver.
     * @return driver - {@link WebDriver} Selenium firefox driver instance;
     */
    public static WebDriver getDriver() {

        // Check if path is initialized
        if (SeleniumConfiguration.isPathInstalled()) {

            // Check if driver instance already created:
            if (SeleniumConfiguration.driver != null) return SeleniumConfiguration.driver;

            // Create new firefox driver:
            SeleniumConfiguration.driver = new FirefoxDriver();
            return SeleniumConfiguration.driver;

        }else {
            LOGGER.warn(String.format("Gecko driver path property [%s] is not initialized. Please, set that;", GECKO_DRIVER_PROPERTY));
            return null;
        }

    }

    /**
     * Close.
     * Close Selenium firefox driver instance;
     */
    public static void close() {
        // Close driver instance:
        LOGGER.debug("Try to close selenium firefox driver instance;");
        if (SeleniumConfiguration.getDriver() != null) {
            SeleniumConfiguration.getDriver().close();
            SeleniumConfiguration.driver = null;
        }
        else {
            LOGGER.warn(String.format("Selenium firefox driver is not initialized. Please set [%s] property;", GECKO_DRIVER_PROPERTY));
        }
    }



}
