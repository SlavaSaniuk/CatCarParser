package by.bsac.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficeConfiguration {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(OfficeConfiguration.class);

    public enum ProcessMode {BY_PART_ITEM, BY_ITEM_GROUP, ONE_FILE}
}
