
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import org.junit.Test;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class StreamedJspSeederServiceTest 
{
    private final File outputDir = new File("target/test-data");
    
    private StreamedJspSeederService seederService;
    
    public StreamedJspSeederServiceTest()
    {
        seederService = new StreamedJspSeederService();
        
        System.out.println("out from streammed jsp seeder");

        File pwd = new File(".");
        System.out.println("pwd: " + pwd.getAbsolutePath() );
    }
    
    @Test
    public void seedIndex() throws Exception
    {
// TODO: Make sure the files do not already exist.
        
        String urlPath = "imaging/gimp/cropping";
        
        seederService.seedIndex(outputDir, urlPath);
        
        String indexPath = urlPath + "/index.jsp";
        File index = new File(outputDir, indexPath);
        System.out.println("expecting: " + index.getAbsolutePath() );
        assert( index.exists() );
        
        File webinfDir = new File(outputDir, "WEB-INF/jsp/");
        
        String bottomPath = urlPath + "/bottom.jsp";
        File bottom = new File(webinfDir, bottomPath);
        System.out.println("expecting: " + bottom.getAbsolutePath() );
        assert( bottom.exists() );
                
        File webinfIndex = new File(webinfDir, indexPath);
        System.out.println("expecting: " + webinfIndex.getAbsolutePath() );
        assert( webinfIndex.exists() );
                
        String propertiesPath = urlPath + "/properties.jsp";
        File properties = new File(webinfDir, propertiesPath);
        System.out.println("expecting: " + properties.getAbsolutePath() );
        assert( properties.exists() );
    }
}
