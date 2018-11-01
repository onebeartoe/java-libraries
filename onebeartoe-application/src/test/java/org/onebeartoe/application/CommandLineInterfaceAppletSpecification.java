
package org.onebeartoe.application;

import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class CommandLineInterfaceAppletSpecification 
{
    CommandLineInterfaceApplet implementation;
    
    public CommandLineInterfaceAppletSpecification()
    {
        implementation = new CommandLineInterfaceApplet();
    }
    
    @Test(groups = {"unit"})
    public void buildOptions()
    {
        implementation.buildOptions();
    }
    
    @Test(groups = {"unit"})
    public void execute() throws Exception
    {
        String [] args = {};
        
        implementation.execute(args);
    }
}
