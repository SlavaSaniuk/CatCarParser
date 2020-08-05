package by.bsac.scripts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetInnerRowsWrappersAttr implements JavascriptFromJava{

    //Logger
    public static final Logger LOGGER = LoggerFactory.getLogger(SetInnerRowsWrappersAttr.class);
    public static final String SCRIPT = "function tableReader_parseMainTable(){var e=document.getElementsByClassName(\"table\")[0];return console.log(\"Table: \",e),tableReader_getTableRows(e)}function tableReader_getTableRows(e){var t=e.rows.length;console.log(\"Rows count: \",t);for(var a=[],r=0;r<t;r++)a.push(e.getElementsByTagName(\"tr\")[r]);return a}function tableReader_isPointerRow(e){return!!e.classList.contains(\"pointer\")}function tableReader_getInnerRowsWrappers(e){for(var t=[],a=0;a<e.length;a++)tableReader_isPointerRow(e[a])||t.push(e[a]);return t}function tableReader_setAttribute(e){for(var t=0;t<e.length;t++)e[t].setAttribute(custom_attribute_name,t)}var custom_attribute_name=\"p-row\";function main(){var e=tableReader_parseMainTable();e.shift(),tableReader_setAttribute(tableReader_getInnerRowsWrappers(e))}main();";
    public static final String JAVA_SCRIPT = "arguments[0].setAttribute(arguments[1],arguments[2])";
    public static final String ATTRIBUTE_NAME="pointer-row";

    public static void executeJsFromJava(String a_script, Object... args) {};

    @Override
    public void run() {

    }

    @Override
    public void run(Object... args) {
        ScriptsProcessor.process(JAVA_SCRIPT, args);
    }

}
