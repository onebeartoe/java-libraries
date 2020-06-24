
package org.onebeartoe.web.utilities.jsp;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import org.onebeartoe.io.TextFileWriter;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class StreamedJspSeederService implements JspSeederService
{
    private final JspTemplates templates = new JspTemplates();
    
    private String dotDotSlashes(String childDirectoryPath)
    {
        String [] strs = childDirectoryPath.split("/");
        
        StringBuilder dotSlashes = new StringBuilder();
        
        Stream.of(strs).forEach(s -> {dotSlashes.append("../"); });
        
        return dotSlashes.toString();
    }
    
    private void interpolateBottom(File webinfChildDir, String dotDotSlashes, JspSeedReport report) throws IOException
    {
        File webinfBottom = new File(webinfChildDir, "bottom.jsp");
        
        String content = templates.loadBottom();
     
        String fullPath = webinfChildDir.getAbsolutePath();
        
        // this string should be part of every path to the bottom.jsp
        String target = "WEB-INF/jsp/";
        
        int index = fullPath.indexOf(target);
        
        int start = index + target.length();
        
        String subpath = fullPath.substring(start);
        
        int textStart = subpath.lastIndexOf("/");
        
        textStart = textStart == -1 ? 0 : textStart + 1;
        
        String text = subpath.substring(textStart);
        
        String anchor = "<%= request.getContextPath() %>" + "/" + subpath + "/";
        
        String link = "<a href=\"" + anchor + "\" >" + text + "</a> ";
        
        content = "\n\n" + link + "\n\n" + content;
        
        report.webinfBottom = seed(webinfBottom, content);        
    }
    
    /**
     * this is the public facing index file
     */
    private String interpolateIndex(File webRoot, String childDirectoryPath, JspSeedReport report) throws IOException
    {
        File indexDirectoy = new File(webRoot, childDirectoryPath);
        indexDirectoy.mkdirs();
        
        File index = new File(indexDirectoy, "index.jsp");
        String content = templates.loadIndex();
        content = content.replaceAll("--subpath--", childDirectoryPath);
        
        String dotDotSlashes = dotDotSlashes(childDirectoryPath);
        
        content = content.replaceAll("--dot-dot-slash--", dotDotSlashes);
        report.index = seed(index, content);

        return dotDotSlashes;
    }
    
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
                
//                boolean saved = 
                        tfw.writeText(seefFile, content);
                
//                if(saved)
                {
                    result.type = SeedResults.CREATED;
                    result.details = seefFile.getAbsolutePath();
                }
  //              else
                {
//                    throw new Exception("The file could not be saved");
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
        JspSeedReport report = new JspSeedReport();
                
        String dotDotSlashes = interpolateIndex(webRoot, childDirectoryPath, report);
                
        File webinfJspDir = new File(webRoot, "WEB-INF/jsp/");
        webinfJspDir.mkdirs();
        
        File webinfChildDir = new File(webinfJspDir, childDirectoryPath);
        webinfChildDir.mkdirs();
        
        // this is the index under the WEB-INF/ directory
        File webinfIndex = new File(webinfChildDir, "index.jsp");
        String content = templates.loadWebinfIndex();
        report.webinfIndex = seed(webinfIndex, content);
        
        File webinfProperties = new File(webinfChildDir, "properties.jsp");
        content = templates.loadProperites();
        report.webinfProperties = seed(webinfProperties, content);
        
        interpolateBottom(webinfChildDir, dotDotSlashes, report);
                
        return report;
    }
}
