
package org.onebeartoe.network.http.file.transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * @author Roberto Marquez
 */
public abstract class DynamicFileHttpHandler extends LocalFileHttpHandler
{
    protected Map<String, TextReplacement> textReplacements;
    
//TODO: Move this to the bottom
    public interface TextReplacement
    {
        String operation(String key, String original);
    }
    
    public DynamicFileHttpHandler()
    {
        textReplacements = new HashMap();
    }

    protected void addTextReplacer(String key, TextReplacement textReplacer)
    {
        textReplacements.put(key, textReplacer);
    }
    
    @Override
    protected void writeText(OutputStream os, File localFile) throws IOException
    {
        Path path = localFile.toPath();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8))
        {
            List<String> lines = reader.lines()
                                    .map( s -> {
                                                    Set<String> keySet = textReplacements.keySet();
                                                    for(String key : keySet)
                                                    {
                                                        TextReplacement replacer = textReplacements.get(key);
                                                        s = replacer.operation(key, s); //s.replace("REPLACE_ME_TEXT", "--This is replaced text---");
                                                    }
                                                    
                                                    return s;
                                                } )
                                    .collect( Collectors.toList() );
            
            lines.stream()
                    .forEach(s -> 
                                  {
                                    try
                                    {
                                        os.write( s.getBytes() );
                                    }
                                    catch (IOException ex)
                                    {
                                        logger.log(Level.SEVERE, null, ex);
                                    }
                                  }
                            );

            os.close();
        }

    }
}
