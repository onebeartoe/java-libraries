
package org.onebeartoe.network.http.file.transfer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roberto Marquez
 */
public abstract class LocalFileHttpHandler implements HttpHandler
{
    protected Logger logger;
    
    protected abstract String getRootPath();
    
    protected abstract void writeText(OutputStream os, File localFile) throws IOException;
        
    public LocalFileHttpHandler()
    {
        String name = getClass().getName();
        logger = Logger.getLogger(name);
    }
    
    @Override    
    public void handle(HttpExchange t) throws IOException
    {
        logger.log(Level.INFO, "static file handler request: " + t.getRequestURI());
        
        String root = getRootPath();
        root = root.replace("\\","/");
        
        URI uri = t.getRequestURI();
        String request = uri.getPath();
        String urlPrefix = t.getHttpContext().getPath() + "/";
        request = request.replaceFirst(urlPrefix, "");
        
        String path = root + request;
        path = path.replace("\\", "/");
        
        File f = new File(path);
        File file = f.getCanonicalFile();
        
        String requestedPath = file.getPath().replace("\\","/");
        
        if (!requestedPath.startsWith(root))
        {
            String forbiddenMessage = "forbidden request: " + t.getRequestURI() + "\n"
                                        + "forbidden    file: " + file.getAbsolutePath();
            logger.log(Level.INFO, forbiddenMessage);
            
            String compareMessage = "compared paths - root: " + root + "\n"
                                    + "compared paths - file: " + file.getPath();
            logger.log(Level.INFO, compareMessage);
                    
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
            
            writeText(os, file);
        }
    }    
}
