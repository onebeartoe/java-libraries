
package org.onebeartoe.poi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

/**
 * This class provides utility methods for working with MS Word (.docx) documents.
 */
public class PoiDocuments 
{
    
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
    private void setTabStop(XWPFParagraph oParagraph, STTabJc.Enum oSTTabJc, BigInteger oPos) {
        CTP oCTP = oParagraph.getCTP();
        CTPPr oPPr = oCTP.getPPr();
        if (oPPr == null) {
            oPPr = oCTP.addNewPPr();
        }

        CTTabs oTabs = oPPr.getTabs();
        if (oTabs == null) {
            oTabs = oPPr.addNewTabs();
        }

        CTTabStop oTabStop = oTabs.addNewTab();
        oTabStop.setVal(oSTTabJc);
        oTabStop.setPos(oPos);
    }    
}
