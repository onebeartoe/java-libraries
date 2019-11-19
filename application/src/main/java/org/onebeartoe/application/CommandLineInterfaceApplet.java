
package org.onebeartoe.application;

import java.io.IOException;
import java.time.Instant;
import java.util.logging.Logger;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.onebeartoe.application.duration.DurationService;

/**
 * @author Roberto Marquez
 */
public class CommandLineInterfaceApplet 
{
    protected Logger logger;
    
    public CommandLineInterfaceApplet()
    {
        String name = getClass().getSimpleName();
        
        logger = Logger.getLogger(name);
    }
    
    public Options buildOptions()
    {
        return new Options();
    }
    
    public void execute(String [] args) throws Exception
    {
        Options options = buildOptions();
        
        try
        {
            RunProfile runProfile = parseRunProfile(args, options);

            Instant start = Instant.now();

            AppletService appletService = getService();
            appletService.serviceRequest(runProfile);

            Instant end = Instant.now();
        
            DurationService durationService = new DurationService();
            String message = "The application ran for " + durationService.durationMessage(start, end) + "\n";
            logger.info(message);
        }
        catch(ParseException uoe)
        {
            String message = "An error occured: " + uoe.getMessage();
            logger.severe(message);
            
            String usage = getUsage();
            
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(usage, options);
        }         
    }
    
    public static void main(String [] args) throws IOException, Exception
    {
        CommandLineInterfaceApplet app = new CommandLineInterfaceApplet();
        app.execute(args);
    }

    protected RunProfile parseRunProfile(final String[] args, Options options) throws ParseException
    {
        if(args == null)
        {
            throw new ParseException("The arguments cannot be null.");
        }
        
        return new RunProfile();
    }
    
    protected String getUsage()
    {
        return "No usage documentation is available.";
    }

    protected AppletService getService() 
    {
        return new AppletService();
    }
}
