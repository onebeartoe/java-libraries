
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class StreamedJspSeederService implements JspSeederService
{

    
    @Override
    public boolean seedIndex(File webRoot, String childDirectoryPath) throws IOException
    {
        boolean indexed = true;
                
        File indexDirectoy = new File(webRoot, childDirectoryPath);
        indexDirectoy.mkdirs();
        
        File index = new File(indexDirectoy, "index.jsp");
        if(index.exists())
        {
            String message = "the file exists already: " + index.getAbsolutePath();
            throw new IOException(message);
        }
        index.createNewFile();
        
        File webinfDir = new File(webRoot, "WEB-INF/");
        webinfDir.mkdirs();
        
        File webinfChildDir = new File(webinfDir, childDirectoryPath);
        webinfChildDir.mkdirs();
        
        File webinfIndex = new File(webinfChildDir, "index.jsp");
        if(webinfIndex.exists())
        {
            String message = "the file exists already: " + webinfIndex.getAbsolutePath();
            throw new IOException(message);
        }        
        webinfIndex.createNewFile();
        
        File webinfProperties = new File(webinfChildDir, "properties.jsp");
        if(webinfProperties.exists())
        {
            String message = "the file exists already: " + webinfProperties.getAbsolutePath();
            throw new IOException(message);
        }        
        webinfProperties.createNewFile();
        
        File webinfBottom = new File(webinfChildDir, "bottom.jsp");
        if(webinfBottom.exists())
        {
            String message = "the file exists already: " + webinfBottom.getAbsolutePath();
            throw new IOException(message);
        }        
        webinfBottom.createNewFile();
                
        return indexed;
    }
    
}
