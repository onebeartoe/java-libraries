
package org.onebeartoe.system.command.imagemagick;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.onebeartoe.system.command.SystemCommand;
import org.onebeartoe.system.command.SystemCommandProfile;

/**
 * This system command represents the ImageMagick compare command.  It calls it in this 
 * format: 
 * 
 *        compare -metric RMSE pyramid-baseline.png pyramid-proposed-baseline.png NULL:
 * 
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class Compare extends SystemCommand
{
    private final static double DEFAULT_ERROR_THRESHOLD = 2.5;
    
    private double errorThreshold;
    
    private Logger logger;

    public Compare(String path1, String path2, double errorThreshold)
    {
        String name = getClass().getName(); 
        logger = Logger.getLogger(name);
        
        this.errorThreshold = errorThreshold;
        profile = new SystemCommandProfile();
        
        String systemCommand = "compare -metric RMSE " + path1 + " " + path2 + " NULL:";
        
        String [] strs = systemCommand.split("\\s+");
        List <String> commandAndArgs = Arrays.asList(strs);
        profile.commandAndArgs = commandAndArgs;
        
        profile.processStdErr = true;
        profile.processStdOut = true;        
    }
    
    public Compare(String path1, String path2)
    {
        this(path1, path2, DEFAULT_ERROR_THRESHOLD);
    }    
    
/**
     * This is the error allowance for the image comparison.
     *
     * Any comparison difference (the value given between the parenthesis by the
     * compare command) greater than this value is treated as an error.
     */
    @Override
    protected String processStdErr(InputStream is)
    {
        String stderr = super.processStdErr(is);
        
        int begin = stderr.lastIndexOf("(") + 1;
        int end  =  stderr.lastIndexOf(")");
        
        if(begin < 0 || end < 0)
        {
            String message = "The substring look up for the comparison percentage failed.  " +
                                "Is the ImageMagic command available?";
            
            message += "\n\n" + stderr;
            logger.log(Level.SEVERE, message);
        }
        
        String s = stderr.substring(begin, end);
        
        float f = Float.valueOf(s);
        
        // move it to an integer representation of a percetage
        f = f * 100.0f;
        
        // check if the error percentage is below the threshold
        if(f <= errorThreshold)
        {
            // set the exit code to success
            results.exitCode = 0;
        }
        else
        {
            results.exitCode = (int) f;
            
            // the diff is not good
            System.err.println();
            System.err.println();
            System.err.println("The comparison is not identical for:");
            profile.commandAndArgs.forEach( c ->
            {
                System.err.print(c + " ");   
            });
            System.err.println();
        }
        
        return stderr;
    }
}
