package by.bsac.composite.infos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public abstract class BaseInfo implements Info {

    @Setter
    private String info_text;
    @Setter
    protected InfoType info_type;
    @Getter
    protected Class<?> object_type;

    @Override
    public String getText() {
        return this.info_text;
    }

    @Override
    public InfoType getType() {
        return this.info_type;
    }
}
