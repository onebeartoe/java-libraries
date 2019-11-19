
package org.onebeartoe.application.io.streams;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.onebeartoe.application.duration.DurationService;
import org.onebeartoe.application.logging.SysoutLoggerFactory;

/**
 * The application 
 * @author Roberto Marquez
 */
public class PositionalAwkette
{
    private static final String INPUT_DIRECTORY = "inputDirectory";
    
    private final String NOT_MODE = "notMode";
    
    private static final String REMOVE_SPECIAL_CHARACTER_FILENAMES = "removeSpecialCharacterFilenames";
        
    private final String REPLACEMENT_STRING = "replacmentString";

    private Options buildOptions()
    {
        Option notMode = Option.builder()
                                .longOpt(NOT_MODE)
                                .desc("In ' not mode', all positions are printed except those listed in the command line args.")
                                .build();
        
        Options options = new Options();
        options.addOption(notMode);
        
        return options;
    }
    
    public static void main(String [] args) throws IOException
    {
        Logger logger = SysoutLoggerFactory.getLogger( PositionalAwkette.class.getName() );
        
        PositionalAwkette findette = new PositionalAwkette();
        Options options = findette.buildOptions();
        
        try
        {
            RunProfile runProfile = findette.parseRunProfile(args, options);

            Instant start = Instant.now();

            PositionalAwketteService movetteService = new PositionalAwketteService();
            movetteService.serviceRequest(runProfile);

            Instant end = Instant.now();
        
            DurationService durationService = new DurationService();
            String message = "Awkette ran for " + durationService.durationMessage(start, end) + "\n";
            logger.info(message);
        }
        catch(ParseException uoe)
        {
            uoe.printStackTrace(System.err);
                        
            String usage = "\n" + "java -jar findette.jar " + "--" + INPUT_DIRECTORY + " <input-directory>" + 
                           " --" + REMOVE_SPECIAL_CHARACTER_FILENAMES + " <special-char>" + "\n";
            
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(usage, options);
        } 
    }

    private RunProfile parseRunProfile(final String[] args, Options options) throws ParseException
    {
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);
        
        RunProfile runProfile = new RunProfile();

        runProfile.inpath = cl.getOptionValue(INPUT_DIRECTORY);

        runProfile.mode = RunMode.REGULAR;

        if( cl.hasOption(NOT_MODE) )
        {
            runProfile.mode = RunMode.NOT_MODE;
        }

        List<String> remainingArgs = cl.getArgList();
        if(remainingArgs.size() > 0)
        {
            boolean notMode = false;
            
            for(String a : remainingArgs)
            {
                String s = a.replace("$", "");
                
                Integer i = Integer.valueOf(s);
                
                runProfile.positionals.add(i);
            }
            
            remainingArgs.forEach(a -> 
            {

            });
        }
        
        return runProfile;
    }

    class RunProfile
    {
        String inpath;
        
        List<Integer> positionals;
        
        RunMode mode;
        
        public RunProfile()
        {
            positionals = new ArrayList();
        }
    }
    
    enum RunMode
    {
        NOT_MODE,
        REGULAR
    }
}
