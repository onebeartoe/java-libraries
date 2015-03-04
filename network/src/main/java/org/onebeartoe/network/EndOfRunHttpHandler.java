
package org.onebeartoe.network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * @author Roberto Marquez
 */
public class EndOfRunHttpHandler extends TextHttpHandler 
{
    private HttpServer server;
    
    public EndOfRunHttpHandler(HttpServer server)
    {
        this.server = server;
    }
    
    @Override
    protected String getHttpText(HttpExchange he) 
    {
        try 
        {
            System.out.println("stopping the http server");
            
            // wait time is in seconds
            int maxWaitTime = 5;
            server.stop(maxWaitTime);

            System.out.println("http server stopped");

            Thread.sleep(maxWaitTime + 1000);

            System.exit(0);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            return "end of run approaching...";
        }
    }
}