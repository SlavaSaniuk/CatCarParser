package by.bsac.composite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
public abstract class BaseInfo implements Info {

    @Setter
    private String info_text;
    @Setter
    private InfoType info_type;
    @Getter @Setter
    private Class<?> object_type;

    @Override
    public String getText() {
        return this.info_text;
    }

    @Override
    public InfoType getType() {
        return this.info_type;
    }
}
