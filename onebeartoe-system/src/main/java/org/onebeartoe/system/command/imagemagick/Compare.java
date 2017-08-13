
package org.onebeartoe.system.command.imagemagick;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.onebeartoe.system.CommandResults;
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
    public Compare(String path1, String path2)
    {
        profile = new SystemCommandProfile();
        
        String systemCommand = "compare -metric RMSE " + path1 + " " + path2 + " NULL:";
        
        String [] strs = systemCommand.split("\\s+");
        List <String> commandAndArgs = Arrays.asList(strs);        
        profile.commandAndArgs = commandAndArgs;
        
        profile.processStdErr = true;
        profile.processStdOut = true;        
    }
    
    @Override
    protected String processStdErr(InputStream is)
    {
        String stderr = super.processStdErr(is);
        
        System.err.println("Examining stderr reveals: " + stderr);
        
        int begin = stderr.lastIndexOf("(");
        int end  =  stderr.lastIndexOf(")");
        
        String s = stderr.substring(begin, end);
        
        System.err.println("string value is: " + s);
        
        float f = Float.valueOf(s);
        
        int i = (int) f;
        
        results.exitCode = i;
        
        if(results.exitCode != 0)
        {
            // the diff is not good

            System.err.println("The comparison is not identical for:");
            profile.commandAndArgs.forEach( c ->
            {
                System.err.print(c + " ");   
            });
        }
        
        return stderr;
    }
    
    @Override
    protected String processStdOut(InputStream is)
    {
        String stdout = super.processStdOut(is);
        
        System.out.println("examining the stdout reveals: " + stdout);
        
        return stdout;
    }
}
