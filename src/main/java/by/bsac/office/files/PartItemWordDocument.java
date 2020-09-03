package by.bsac.office.files;

import by.bsac.assertions.Asserts;
import by.bsac.composite.infos.Info;
import by.bsac.composite.infos.PartItemInfo;
import by.bsac.core.Initializable;
import by.bsac.core.Processable;
import by.bsac.office.WordUtils;
import by.bsac.web.PartItem;
import lombok.Getter;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartItemWordDocument implements Processable<XWPFDocument>, Initializable {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItemWordDocument.class);
    // Class variables:
    @Getter
    private final XWPFDocument document = WordUtils.createWordDocument(); // Word document;
    @Getter
    private XWPFParagraph page_title;
    private final PartItemInfo info; // Info object with all information;

    public PartItemWordDocument(Info a_info) {
        LOGGER.debug("Construct new PartItemWordDocument instance;");

        // Check if a_info has PartItem object type:
        if (a_info.getObjectType() != PartItem.class) throw new Asserts.RuntimeAssertionException("Specified [a_info] document hasn't PartItem object type;");

        // Map arguments:
        this.info = (PartItemInfo) a_info;

        // Initialize:
        this.initialize();
    }


    @Override
    public XWPFDocument process() {

        // Return this word document:
        return this.document;
    }

    @Override
    public void initialize() {
        LOGGER.trace("Start to initialize this PartItemWordDocument instance;");

        // Page title:
        this._initializePageTitle();
    }

    private void _initializePageTitle() {
        LOGGER.trace("Initialize PartItem page title;");

        // Create paragraph:
        this.page_title = this.document.createParagraph();
        this.page_title.setAlignment(ParagraphAlignment.CENTER);

        // Set title text:
        XWPFRun run = this.page_title.createRun();
        run.setText(this.info.getPartItemTitle());
        run.setBold(true);
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
    }
}
