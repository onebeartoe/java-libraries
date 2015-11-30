
package org.onebeartoe.network.http.file.transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Roberto Marquez
 */
public abstract class DynamicFileHttpHandler extends LocalFileHttpHandler
{
    @Override
    protected void writeText(OutputStream os, File localFile) throws IOException
    {
        Path path = localFile.toPath();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8))
        {
            List<String> lines = reader.lines()
                                    .map( s -> s.replace("a", "A") )
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
                                        Logger.getLogger(DynamicFileHttpHandler.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                  }
                            );

            os.close();
        }

    }
}
