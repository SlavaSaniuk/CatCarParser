package by.bsac.office.files;

import by.bsac.assertions.Asserts;
import by.bsac.composite.Info;
import by.bsac.composite.Informational;
import by.bsac.core.Processable;
import by.bsac.office.WordUtils;
import by.bsac.web.PartItem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartItemWordDocument implements Processable<XWPFDocument> {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItemWordDocument.class);
    // Class variables:
    private final XWPFDocument document = WordUtils.createWordDocument(); // Word document;
    private final Info info; // Info object with all information;

    public PartItemWordDocument(Info a_info) {
        LOGGER.debug("Construct new PartItemWordDocument instance;");

        // Check if a_info has PartItem object type:
        if (a_info.getObjectType() != PartItem.class) throw new Asserts.RuntimeAssertionException("Specified [a_info] document hasn't PartItem object type;");

        // Map arguments:
        this.info = a_info;
    }


    @Override
    public XWPFDocument process() {

        // Return this word document:
        return this.document;
    }


}
