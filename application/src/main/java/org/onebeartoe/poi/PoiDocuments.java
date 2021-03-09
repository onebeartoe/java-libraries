
package org.onebeartoe.poi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

/**
 * This class provides utility methods for working with MS Word (.docx) documents.
 */
public class PoiDocuments 
{
    public void addBreak(XWPFDocument document) 
    {
        XWPFParagraph p4 = document.createParagraph();

        XWPFRun r4 = p4.createRun();
        
        r4.addBreak();
    }
    
    public void edgeAlignedText(XWPFParagraph paragraph, String leftText, String rightText)
    {
        XWPFRun tmpRun = paragraph.createRun();
        tmpRun.setText(leftText);
        tmpRun.addTab();
//tmpRun.setSmallCaps(true);

// uncomment for 3 columns            
//            tmpRun.setText("JAN2");
//            tmpRun.addTab();
        tmpRun.setText(rightText);
// uncomment for 3 columns            
//            BigInteger pos1 = BigInteger.valueOf(4500);
//            setTabStop(paragraph, STTabJc.Enum.forString("center"), pos1);
        BigInteger pos2 = BigInteger.valueOf(9000);
        setTabStop(paragraph, STTabJc.Enum.forString("right"), pos2);        
    }
    
    
    
    /**
     * This version uses a table to make it appear that two text items are 
     * on the same line with separate left and right alignment.  
     * But really the the text items are in separate table cells.      
     * 
     * @param targetParagraph
     * @param leftText
     * @param rightText 
     */
    public void edgeAlignedText(XWPFDocument document,
                                ParagraphProfile leftTextProile,
                                ParagraphProfile rightTextProile)
//                                XWPFParagraph leftText, XWPFParagraph rightText)
    {
        XWPFTable table = document.createTable();

        //Creating first Row
        XWPFTableRow row1 = table.getRow(0);
        
        table.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
        table.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
        table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
        table.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "");
        
//        XWPFParagraph leftText = document.createParagraph();
//        XWPFRun leftRun = leftText.createRun();
//        leftRun.setText(leftTextProile.text);


        
        XWPFParagraph leftText = row1.getCell(0).addParagraph(); //setParagraph(leftText);
//        row1.getCell(0).setParagraph(leftText);
//        row1.getCell(0).setText("Table - Left TExt");
        
        XWPFRun leftRun = leftText.createRun();
        leftRun.setBold( leftTextProile.bold);
        leftRun.setSmallCaps(leftTextProile.smallCaps);
        leftRun.setText(leftTextProile.text);


//        XWPFParagraph rightText = document.createParagraph();
//        XWPFRun rightRun = rightText.createRun();
//        rightRun.setText(rightTextProile.text);                
//        XWPFDocument tableDoc = new XWPFDocument();
//        XWPFParagraph rightTextPara = tableDoc.createParagraph();
//        rightTextPara.setAlignment(ParagraphAlignment.RIGHT);



        XWPFParagraph rightText = row1.createCell().addParagraph();
//        XWPFParagraph rightText = row1.addNewTableCell().addParagraph();
        XWPFRun rightRun = rightText.createRun();
        rightRun.setText(rightTextProile.text);          
        rightRun.setBold( rightTextProile.bold);
        rightRun.setSmallCaps(rightTextProile.smallCaps);
        rightText.setAlignment(ParagraphAlignment.RIGHT);



//        XWPFRun rightTextRun = rightTextPara.createRun();
//        rightTextRun.setText("Table - Right Text");

//        XWPFParagraph rightText = row1.addNewTableCell().addParagraph();
//        row1.addNewTableCell().setParagraph(rightText);
//        row1.addNewTableCell().setParagraph(rightTextPara);        
    }

    public void smallCapsCase(XWPFParagraph paragraph, String text, boolean bold)
    {
        XWPFRun run = paragraph.createRun();
        
        run.setSmallCaps(true);
        
        run.setBold(bold);
        
        run.setText(text);
    }
    
    public void unorderedListViaNumId(XWPFDocument document)
    {
        String [] fruits = {"Apple", "Banana", "Mango"};
        // for each item a paragraph is created and the Style and NumId is set
        for (int i = 0; i < fruits.length; i++) {
          
            XWPFParagraph p = document.createParagraph();
            p.setStyle("ListParagraph");
            // 2 prints bullet as of this code and 1 would print numbers
            p.setNumID(BigInteger.valueOf(2));

            XWPFRun r = p.createRun();
            r.setText(fruits[i]);
        }        
    }
    
    public void unorderedList(XWPFDocument doc1, List<String> varios) 
    {
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
        CTLevelText addNewLvlText = cTLvl.addNewLvlText();
    //    addNewLvlText.setVal("•");
        addNewLvlText.setVal("•");

        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        XWPFNumbering numbering = doc1.createNumbering();
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(abstractNumID);

        for (String item : varios){
            XWPFParagraph bulletedPara = doc1.createParagraph();
            XWPFRun run = bulletedPara.createRun();
            run.setFontFamily("Arial");
            run.setFontSize(10);
            run.setText(item);
            bulletedPara.setNumID(numID);
        }
    }
    
    public void writeDocument(OutputStream outStream, XWPFDocument document) 
            throws FileNotFoundException, IOException 
    {
        // save the docs
        
        document.write(outStream);       
        
        outStream.close();
    }    

    /**
     * This TabStop allows for two text items on the same line. One is left aligned
     * and the other is right aligned, on the same line.
     * 
     * Code example is from this StackOverflow answer:
     * 
     *      https://stackoverflow.com/a/33942296/803890
     * 
     * @param oParagraph
     * @param oSTTabJc
     * @param oPos 
     */
    private void setTabStop(XWPFParagraph oParagraph, STTabJc.Enum oSTTabJc, BigInteger oPos) 
    {
        CTP oCTP = oParagraph.getCTP();
        CTPPr oPPr = oCTP.getPPr();
        if (oPPr == null) 
        {
            oPPr = oCTP.addNewPPr();
        }

        CTTabs oTabs = oPPr.getTabs();
        if (oTabs == null) 
        {
            oTabs = oPPr.addNewTabs();
        }

        CTTabStop oTabStop = oTabs.addNewTab();
        oTabStop.setVal(oSTTabJc);
        oTabStop.setPos(oPos);
    }
}
