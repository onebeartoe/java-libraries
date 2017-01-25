
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.onebeartoe.io.TextFileWriter;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class StreamedJspSeederService implements JspSeederService
{
    private SeedResult seed(File seefFile, String content)
    {
        SeedResult result = new SeedResult();
        
        if(seefFile.exists())
        {
            result.type = SeedResults.NOT_CREATED_EXISTED;
            result.details = seefFile.getAbsolutePath();
        }
        else
        {
            try 
            {
                TextFileWriter tfw = new TextFileWriter();
                boolean saved = tfw.writeText(seefFile, content);
                
                if(saved)
                {
                    result.type = SeedResults.CREATED;
                    result.details = seefFile.getAbsolutePath();
                }
                else
                {
                    throw new Exception("The file could not be saved");
                }
            } 
            catch (Exception ex) 
            {
                result.type = SeedResults.NOT_CREATED_ERRORED;
                result.details = "Error with " + seefFile.getAbsolutePath();
                result.error = Optional.of(ex);
            }
        }
        
        return result;
    }
    
    @Override
    public JspSeedReport seedIndex(File webRoot, String childDirectoryPath) throws IOException
    {
        JspTemplates templates = new JspTemplates();
        JspSeedReport report = new JspSeedReport();
                
        File indexDirectoy = new File(webRoot, childDirectoryPath);
        indexDirectoy.mkdirs();
        
        File index = new File(indexDirectoy, "index.jsp");
        String content = templates.loadIndex();
        report.index = seed(index, content);
        
        File webinfJspDir = new File(webRoot, "WEB-INF/jsp/");
        webinfJspDir.mkdirs();
        
        File webinfChildDir = new File(webinfJspDir, childDirectoryPath);
        webinfChildDir.mkdirs();
        
        File webinfIndex = new File(webinfChildDir, "index.jsp");
        content = templates.loadWebinfIndex();
        report.webinfIndex = seed(webinfIndex, content);
        
        File webinfProperties = new File(webinfChildDir, "properties.jsp");
        content = templates.loadProperites();
        report.webinfProperties = seed(webinfProperties, content);
        
        File webinfBottom = new File(webinfChildDir, "bottom.jsp");
        content = templates.loadBottom();
        report.webinfBottom = seed(webinfBottom, content);
                
        return report;
    }
}
