package by.bsac.composite.infos;

import by.bsac.web.PartItem;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PartItemInfo extends BaseInfo {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItemInfo.class);

    // Class variables:
    @Getter
    private final List<Info> tabs_infos = new ArrayList<>();

    @Getter @Setter
    private String part_item_title;

    public PartItemInfo() {
        super();
        LOGGER.trace("Construct new PartItemObject instance;");

        // Set super parameters:
        super.info_type = InfoType.PART_ITEM;
        super.object_type = PartItem.class;

    }







}
