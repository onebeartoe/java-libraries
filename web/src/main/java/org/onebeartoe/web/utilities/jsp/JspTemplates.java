
package org.onebeartoe.web.utilities.jsp;

import java.io.IOException;
import org.onebeartoe.io.TextFileReader;
import org.onebeartoe.io.buffered.BufferedTextFileReader;

public class JspTemplates
{
    public String loadBottom() throws IOException
    {
        String text = loadText("bottom.jsp");
        
        return text;
    }
            
    public String loadIndex() throws IOException
    {
        String text = loadText("index.jsp");
        
        return text;
    }
    
    public String loadProperites() throws IOException
    {
        String text = loadText("properties.jsp");
        
        return text;
    }
    
    public String loadText(String filename) throws IOException
    {
        TextFileReader tfr = new BufferedTextFileReader();
        String text = tfr.readTextFromClasspath("/" + filename);
        
        return text;
    }
    
    public String loadWebinfIndex() throws IOException
    {
        String text = loadText("webinfIndex.jsp");
        
        return text;
    }
}