
package org.onebeartoe.network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.onebeartoe.io.TextFileReader;

/**
 * This class is used to send the contents of text files located on the 
 * classpath.  For performance reasons, it is not intended to transfer huge 
 * text files.
 * @author Roberto Marquez
 */
public class ClasspathResourceHttpHandler implements HttpHandler
{
    protected Logger logger;
    
    private TextFileReader textFileReader;
    
    public ClasspathResourceHttpHandler()
    {
        String name = getClass().getName();
        logger = Logger.getLogger(name);
        
        textFileReader = new TextFileReader();
    }
    
    @Override    
    public void handle(HttpExchange t) throws IOException //throws IOException
    {
        logger.log(Level.INFO, "static file handler request: " + t.getRequestURI());

        URI uri = t.getRequestURI();
        String request = uri.getPath();
        String urlPrefix = t.getHttpContext().getPath();// + "/";
        request = request.replaceFirst(urlPrefix, "");
        
        String root = "/";
        String path = root + request;
        
        final String defaultMessage = "could not load resource: " + path;

        String text = defaultMessage;
        try
        {
            List<String> lines = textFileReader.readTextLinesFromClasspath(path);
            
            text = String.join("\n", lines);
        }
        catch (IOException ex)
        {            
            logger.log(Level.SEVERE, text, ex);
        }

        if ( text.equals(defaultMessage) )
        {
            logger.log(Level.INFO, "resource not found    request: " + t.getRequestURI());
            logger.log(Level.INFO, "resource not found translated: " + path);
            
            // Object does not exist or is not a file: reject with 404 error.
            String response = "404 (Not Found)\n";
            t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } 
        else
        {
            logger.log(Level.INFO, "sending class path resouce for request: " + t.getRequestURI());
            
            // the resource exists, accept with response code 200 and send file contents
            t.sendResponseHeaders(200, 0);
            
            final byte[] buffer = text.getBytes();
            
            OutputStream os = t.getResponseBody();
            os.write(buffer);
            os.close();
        }
    }
}
