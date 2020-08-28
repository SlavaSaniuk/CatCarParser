package by.bsac.composite;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowInfo implements Info{

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(RowInfo.class);
    // Class variables:
    private final InfoType info_type = InfoType.TEXT;
    @Setter
    private String info_text; // Text string;

    // Constructor:
    public RowInfo(String a_text) {
        LOGGER.trace("Construct new RowInfo instance;");

        // Map arguments:
        this.info_text = a_text;
    }


    @Override
    public String getText() {
        LOGGER.trace("Return row info text;");
        return this.info_text;
    }

    @Override
    public InfoType getType() {
        LOGGER.trace("Return row info type;");
        return this.info_type;
    }

    @Override
    public String toString() {
        return this.info_text;
    }
}
