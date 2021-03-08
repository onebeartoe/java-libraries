
package org.onebeartoe.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

/**
 *
 */
public class PoiDocumentsSpecification 
{
    private PoiDocuments implementation = new PoiDocuments();
    
    @Test
    public void edgeAlignedText() throws IOException
    {
        File outFile = new File("target/poi/edge-aligned.docx");
        
        OutputStream outStream = new FileOutputStream(outFile);

        String leftText = "left text poi docs";

        String rightText = "right text poi docs";
        
        XWPFDocument document = buildDeftAndRightAlignedTextOnTheSameLine(leftText, rightText);
        
        implementation.writeDocument(outStream, document);
        
        assertTrue( outFile.exists() );
        
        assertTrue( outFile.length() > 0);
        
        System.out.println("document path = " + outFile.getAbsolutePath() );
        
        XWPFDocument filesystemDocument = new XWPFDocument(
                Files.newInputStream( outFile.toPath() ) );
        
        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(filesystemDocument);
            
        String docText = xwpfWordExtractor.getText();
        
        System.out.println("docText = " + docText);

        assertTrue( docText.contains(leftText) );
        
        assertTrue( docText.contains(rightText) );
                   
        String actual = docText.replace(leftText, "");        
        System.out.println("acutal 1 = " + actual);
        
        actual = actual.replace(rightText, "");                
        System.out.println("acutal 2 = " + actual);
        
        assertTrue(actual.trim().isEmpty() );       
    }
    
    private XWPFDocument buildDeftAndRightAlignedTextOnTheSameLine(String leftText, String rightText)
    {
        XWPFDocument doc = new XWPFDocument();
        
        // This version uses TabStops to have two text items on the same line, but
        // with separate left and right alignment.
        XWPFParagraph paragraph = doc.createParagraph();

        implementation.edgeAlignedText(paragraph, leftText, rightText);
        
        return doc;
    }
}
