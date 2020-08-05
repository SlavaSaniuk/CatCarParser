package by.bsac.scripts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveToElementScript implements JavascriptFromJava {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveToElementScript.class);
    private static final String SCRIPT = "arguments[0].scrollIntoView(true);";
    private static final MoveToElementScript INSTANCE = new MoveToElementScript();

    private MoveToElementScript() {}

    public static MoveToElementScript getInstance() {
        return MoveToElementScript.INSTANCE;
    }

    @Override
    public void run() {

    }


    @Override
    public void run(Object... args) {
        LOGGER.debug(String.format("Run [%s] script;", MoveToElementScript.class.getCanonicalName()));
        ScriptsProcessor.process(MoveToElementScript.SCRIPT, args);
    }
}
