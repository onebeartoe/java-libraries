
package org.onebeartoe.application.io.streams;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class PositionalAwketteSpecification 
{
    private PositionalAwkette implementation;
    
    private Logger logger;
    
    public PositionalAwketteSpecification()
    {
        logger = Logger.getLogger(getClass().getName());
        
        implementation = new PositionalAwkette();
    }
    
    @Test(groups = {"unit"})
    public void buildOptions()
    {
        String [] args = {"--notMode"};
        try 
        {
            PositionalAwkette.main(args);
        } 
        catch (IOException ex) 
        {
            String message = "IO Error";
            logger.log(Level.SEVERE, message, ex);
        }
    }
}
