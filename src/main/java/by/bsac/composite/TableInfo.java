package by.bsac.composite;

import by.bsac.web.html.Table;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TableInfo extends BaseInfo {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(TableInfo.class);
    @Getter
    private final List<Info> infos = new ArrayList<>();

    public TableInfo(Table a_table) {
        super();
        a_table.getTableContent().forEach(row -> this.infos.add(new RowInfo(row.getRowText())));
        super.setInfoType(InfoType.TABLE);
    }

    @Override
    public String getText() {
        return "It's a table info";
    }

}
