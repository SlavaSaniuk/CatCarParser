package by.bsac.composite.infos;

public interface Info {

    enum InfoType{ TEXT, TABLE, PART_ITEM, ARTICLE_TAB}

    String getText();

    InfoType getType();

    Class<?> getObjectType();
}
