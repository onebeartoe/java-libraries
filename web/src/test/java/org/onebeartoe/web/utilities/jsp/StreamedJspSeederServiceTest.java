
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import org.onebeartoe.io.TextFileReader;
import org.onebeartoe.io.buffered.BufferedTextFileReader;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class StreamedJspSeederServiceTest 
{
    private final String urlPath = "imaging/gimp/cropping";
    
    private final File outputDir = new File("target/test-data");
    
    private final String jspPath = "WEB-INF/jsp/";
    
    private final File webinfDir = new File(outputDir, jspPath);
    
    private StreamedJspSeederService implementation;
    
    public StreamedJspSeederServiceTest()
    {
        implementation = new StreamedJspSeederService();
        
        System.out.println("out from streammed jsp seeder");

        File pwd = new File(".");
        System.out.println("pwd: " + pwd.getAbsolutePath() );
    }
    
    private void assertBottom()
    {
        String bottomPath = urlPath + "/bottom.jsp";
        File bottom = new File(webinfDir, bottomPath);
        System.out.println("expecting: " + bottom.getAbsolutePath() );
        assert( bottom.exists() );
        
        String expectedPath = urlPath;

        TextFileReader tfr = new BufferedTextFileReader();
        String content = tfr.readText( bottom.getAbsolutePath() );

        assertTrue( content.contains(expectedPath) );
    }

    @Test
//    @Test(groups = {"unit"})
    public void seedIndex() throws Exception
    {
// TODO: Make sure the files do not already exist.
        
        implementation.seedIndex(outputDir, urlPath);
        
        String indexPath = urlPath + "/index.jsp";
        File index = new File(outputDir, indexPath);
        System.out.println("expecting: " + index.getAbsolutePath() );
        assert( index.exists() );

        assertBottom();
        
        File webinfIndex = new File(webinfDir, indexPath);
        System.out.println("expecting: " + webinfIndex.getAbsolutePath() );
        assert( webinfIndex.exists() );
                
        String propertiesPath = urlPath + "/properties.jsp";
        File properties = new File(webinfDir, propertiesPath);
        System.out.println("expecting: " + properties.getAbsolutePath() );
        assert( properties.exists() );
    }
}
