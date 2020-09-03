package by.bsac.office;

import by.bsac.annotations.any.Utility;
import by.bsac.interfaces.PrivateClass;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Utility
public class WordUtils implements PrivateClass {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(WordUtils.class);

    private WordUtils() throws AssertionError {
        // Disable feature to construct instances of this class:
        this.assertionError();
    }

    public static XWPFDocument createWordDocument() {
        return new XWPFDocument();
    }

    public static XWPFTable createTable(XWPFDocument a_document) {
        return a_document.createTable();
    }


}
