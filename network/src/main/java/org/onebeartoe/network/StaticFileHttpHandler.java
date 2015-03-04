
package org.onebeartoe.network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is an HttpHandler to serve static content (files) from the local filesystem.
 * 
 * @author Roberto Marquez
 */
public abstract class StaticFileHttpHandler implements HttpHandler
{
    protected Logger logger;
    
    public StaticFileHttpHandler()
    {
        String name = getClass().getName();
        logger = Logger.getLogger(name);
    }
    
    protected abstract String getRootPath();
    
    @Override    
    public void handle(HttpExchange t) throws IOException
    {
        logger.log(Level.INFO, "static file handler request: " + t.getRequestURI());
        
        String root = getRootPath();
        
        URI uri = t.getRequestURI();
        String request = uri.getPath();
        String urlPrefix = t.getHttpContext().getPath() + "/";
        request = request.replaceFirst(urlPrefix, "");
        
        String path = root + request;
        File f = new File(path);
        File file = f.getCanonicalFile();
        if (!file.getPath().startsWith(root))
        {
            logger.log(Level.INFO, "forbidden request: " + t.getRequestURI());
            logger.log(Level.INFO, "forbidden    file: " + file.getAbsolutePath() );
            
            logger.log(Level.INFO, "compared paths - root: " + root);
            logger.log(Level.INFO, "compared paths - file: " + file.getPath() );
                    
            // Suspected path traversal attack: reject with 403 error.
            String response = "403 (Forbidden)\n";
            t.sendResponseHeaders(403, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } 
        else if (!file.isFile())
        {
            logger.log(Level.INFO, "file not found    request: " + t.getRequestURI());
            logger.log(Level.INFO, "file not found translated: " + file.getAbsolutePath() );
            
            // Object does not exist or is not a file: reject with 404 error.
            String response = "404 (Not Found)\n";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } 
        else
        {
            logger.log(Level.INFO, "sending static file for request: " + t.getRequestURI());
            
            // Object exists and is a file: accept with response code 200.
            t.sendResponseHeaders(200, 0);
            OutputStream os = t.getResponseBody();
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0)
            {
                os.write(buffer, 0, count);
            }
            fs.close();
            os.close();
        }
    }
}