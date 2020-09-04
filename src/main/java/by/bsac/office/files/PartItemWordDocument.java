package by.bsac.office.files;

import by.bsac.assertions.Asserts;
import by.bsac.composite.infos.ArticleTabInfo;
import by.bsac.composite.infos.Info;
import by.bsac.composite.infos.PartItemInfo;
import by.bsac.core.Initializable;
import by.bsac.core.Processable;
import by.bsac.office.WordUtils;
import by.bsac.web.ArticleTab;
import by.bsac.web.PartItem;
import by.bsac.web.html.Row;
import lombok.Getter;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public class PartItemWordDocument implements Processable<XWPFDocument>, Initializable {

    //Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PartItemWordDocument.class);
    // Class variables:
    @Getter
    private final XWPFDocument document = WordUtils.createWordDocument(); // Word document;
    @Getter
    private XWPFParagraph page_title; // Document title;
    @Getter
    private XWPFHeader document_header; // Document header;
    @Getter
    private final List<XWPFTable> articles_tables = new ArrayList<>();

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

        // Process ArticlesTabs:
        this._processArticlesTabs();

        // Return this word document:
        return this.document;
    }

    @Override
    public void initialize() {
        LOGGER.trace("Start to initialize this PartItemWordDocument instance;");

        // Page title:
        this._initializePageTitle();

        // Document header:
        this._initializeDocumentHeader();

        // Articles tabs:
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

    private void _initializeDocumentHeader() {

        // Create header:
        this.document_header = this.document.createHeader(HeaderFooterType.DEFAULT);
        XWPFParagraph paragraph = this.document_header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun run = paragraph.createRun();
        run.setText(this.page_title.getText());
        run.setFontSize(11);
        run.setFontFamily("TimesNewRoman");

    }

    private void _processArticlesTabs() {

        // Iterate about child articles tabs infos:
        this.info.getTabsInfos().forEach((child_info) -> {

            // Check if child_info is ArticleTabInfo instance:
            if (child_info.getObjectType() != ArticleTab.class)
                throw new Asserts.RuntimeAssertionException("Child info is not ArticleTabs info");
            ArticleTabInfo tab_info = (ArticleTabInfo) child_info;

            // Construct new word table:
            XWPFTable table = this.document.createTable();

            // Add content to table:
            // Add pointer rows:
            tab_info.getPointerRows().forEach((p_row) -> {

                // Create word row:
                XWPFTableRow pointer_row = table.createRow();

                // Iterate about pointer row cells:
                for (int i=0; i < p_row.getRowCells().size(); i++) {
                    if (i==0) pointer_row.getCell(0).setText(p_row.getRowCells().get(i).getCellText());
                    else pointer_row.createCell().setText(p_row.getRowCells().get(i).getCellText());
                }
            });

            // Put table to ArticlesTables list:
            this.articles_tables.add(table);
        });
    }



}
