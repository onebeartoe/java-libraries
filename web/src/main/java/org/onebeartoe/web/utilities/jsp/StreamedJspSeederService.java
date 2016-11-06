
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class StreamedJspSeederService implements JspSeederService
{
    private SeedResult seed(File index)
    {
        SeedResult result = new SeedResult();
        
        if(index.exists())
        {
            result.type = SeedResults.NOT_CREATED_EXISTED;
            result.details = index.getAbsolutePath();
        }
        else
        {
            try 
            {        
                index.createNewFile();
                
                result.type = SeedResults.CREATED;
                result.details = index.getAbsolutePath();
            } 
            catch (Exception ex) 
            {
                result.type = SeedResults.NOT_CREATED_ERRORED;
                result.details = "Error with " + index.getAbsolutePath();
                result.error = Optional.of(ex);
            }
        }
        
        return result;
    }
    
    @Override
    public JspSeedReport seedIndex(File webRoot, String childDirectoryPath) throws IOException
    {
        JspSeedReport report = new JspSeedReport();
                
        File indexDirectoy = new File(webRoot, childDirectoryPath);
        indexDirectoy.mkdirs();
        
        File index = new File(indexDirectoy, "index.jsp");
        report.index = seed(index);
        
        File webinfJspDir = new File(webRoot, "WEB-INF/jsp/");
        webinfJspDir.mkdirs();
        
        File webinfChildDir = new File(webinfJspDir, childDirectoryPath);
        webinfChildDir.mkdirs();
        
        File webinfIndex = new File(webinfChildDir, "index.jsp");
        report.webinfIndex = seed(webinfIndex);
        
        File webinfProperties = new File(webinfChildDir, "properties.jsp");
        report.webinfProperties = seed(webinfProperties);
        
        File webinfBottom = new File(webinfChildDir, "bottom.jsp");
        report.webinfBottom = seed(webinfBottom);
                
        return report;
    }
}
