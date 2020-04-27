
package org.onebeartoe.application;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class services a default implementation.
 */
public class AppletService 
{
    protected Logger logger;
    
    public AppletService()
    {
        String name = getClass().getSimpleName();
        
        logger = Logger.getLogger(name);
    }
    
    public void serviceRequest(RunProfile runProfile) throws Exception
    {
        logger.log(Level.INFO, "The default service only prints this message, {0}", runProfile.toString());
    }
}
