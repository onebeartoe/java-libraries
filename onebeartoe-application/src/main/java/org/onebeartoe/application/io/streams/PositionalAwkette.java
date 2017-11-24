
package org.onebeartoe.application.io.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.onebeartoe.application.duration.DurationService;

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
//        Option inputDirectory = Option.builder()
//                                      .required()
//                                      .hasArg()
//                                      .longOpt(INPUT_DIRECTORY)
//                                      .build();
//                
        Option notMode = Option.builder()
                                            .longOpt(NOT_MODE)
                                            .desc("In ' not mode', all positions are printed except those listed in the command line args.")
                                            .build();
//        
//        Option removeSpecialCharacterFilenames = Option.builder()
//                                                    .required()
//                                                    .longOpt(REMOVE_SPECIAL_CHARACTER_FILENAMES)
//                                                    .hasArg(true)
//                                                    .desc("Specifiying this paramter put the application in replace character mode and a" 
//                                                            + "The argument is the String target to be replaced.")
//                                                    .build();
        
        Options options = new Options();
//        options.addOption(inputDirectory);
//        options.addOption(justPrintRenameCommands);
        options.addOption(notMode);
        
        return options;
    }
    
    public static void main(String [] args) throws IOException
    {
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
            String message = durationService.durationMessage(start, end);
            System.out.println();
            System.out.println(message);
        }
        catch(ParseException uoe)
        {
            uoe.printStackTrace();
            
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

//        String s = cl.getOptionValue(REMOVE_SPECIAL_CHARACTER_FILENAMES);
//        if(s.length() != 1)
//        {
//            String message = "The value of the " + REMOVE_SPECIAL_CHARACTER_FILENAMES + " shold be a single character.";
//
//            throw new ParseException(message);
//        }
        
//        runProfile.specialCharTarget = s.charAt(0);
//     
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
