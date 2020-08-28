package by.bsac.composite;

public interface Info {

    enum InfoType{ TEXT, TABLE }

    String getText();

    InfoType getType();
}
