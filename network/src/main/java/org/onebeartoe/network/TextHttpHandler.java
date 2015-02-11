
package org.onebeartoe.network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Roberto Marquez
 */
public abstract class TextHttpHandler implements HttpHandler
{    
    protected abstract String getHttpText(HttpExchange exchange);

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {            
        String response = getHttpText(exchange);
        
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }        
}
