package by.bsac.composite;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowInfo extends BaseInfo{

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(RowInfo.class);
    // Class variables:
    @Setter
    private String info_text; // Text string;

    // Constructor:
    public RowInfo(String a_text) {
        super();
        LOGGER.trace("Construct new RowInfo instance;");

        // Map arguments:
        this.info_text = a_text;
        super.setInfoType(InfoType.TEXT);
    }


    @Override
    public String getText() {
        LOGGER.trace("Return row info text;");
        return this.info_text;
    }

    @Override
    public String toString() {
        return this.info_text;
    }
}
