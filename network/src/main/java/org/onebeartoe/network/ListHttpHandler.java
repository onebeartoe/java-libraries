
package org.onebeartoe.network;

import com.sun.net.httpserver.HttpExchange;
import java.util.List;

/**
 * @author Roberto Marquez
 */
public abstract class ListHttpHandler extends TextHttpHandler
{
    protected String delinimator = "\n" + "-+-" + "\n";
    
    @Override
    protected String getHttpText(HttpExchange t)
    {
        StringBuilder response = new StringBuilder();
        
        List<String> list = getList();
        for(String name : list)
        {
            response.append(name);
            response.append(delinimator);
        }

        return response.toString();
    }
    
    protected abstract List<String> getList();
}
