
package org.onebeartoe.network.http.file.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.onebeartoe.network.http.file.transfer.LocalFileHttpHandler;

/**
 * This is an HttpHandler to serve static content (files) from the local filesystem.
 * 
 * @author Roberto Marquez
 */
public abstract class StaticFileHttpHandler extends LocalFileHttpHandler
{    
    public void writeText(OutputStream os, File file) throws IOException
    {
        try (FileInputStream fs = new FileInputStream(file);)
        {
        final byte[] buffer = new byte[0x10000];
        int count = 0;
        while ((count = fs.read(buffer)) >= 0)
        {
            os.write(buffer, 0, count);
        }
        fs.close();
        os.close();

        logger.info("static file written");
        }
    }
}
