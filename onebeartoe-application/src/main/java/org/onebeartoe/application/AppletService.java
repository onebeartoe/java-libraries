
package org.onebeartoe.application;

import java.util.logging.Logger;

/**
 * @author Roberto Marquez
 */
public class AppletService 
{
    protected Logger logger;
    
    public AppletService()
    {
        String name = getClass().getSimpleName();
        
        logger = Logger.getLogger(name);
    }
    
    public void serviceRequest(RunProfile runProfile)// throws Exception
    {
        logger.info("The default service only prints this message, " + runProfile.toString() );
    }
}
