
package org.onebeartoe.system.command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.onebeartoe.system.CommandResults;

/**
 * This class represents the execution of one single command.
 * 
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class SystemCommand 
//public abstract class SystemCommand 
{
    protected SystemCommandProfile profile;

//TODO: IT DOES NOT LOOK LIKE THIS SHOULD BE A CLASS MEMBER!    
    protected CommandResults results;
    
    public CommandResults execute() throws Exception
    {
        ProcessBuilder builder = new ProcessBuilder(profile.commandAndArgs);
        
        Process jobProcess = builder.start();                
        int exitCode = jobProcess.waitFor();
        
        results = new CommandResults();
        
        results.exitCode = exitCode;

        InputStream es = jobProcess.getErrorStream();
        if(profile.processStdErr)
        {
            results.processedStdErr = processStdErr(es);
        }
        else
        {
            results.stdErr = es;
        }
        
        InputStream is = jobProcess.getInputStream();
        if(profile.processStdOut)
        {
            results.processedStdOut = processStdOut(is);
        }
        else
        {
            results.stdOut = is;
        }

        return results;
    }
    
    protected String processStdErr(InputStream errStream)
    {
        String stderr = new BufferedReader( new InputStreamReader(errStream))
                                  .lines()
                                  .collect(Collectors.joining("\n"));
        
        return stderr;
    }
    
    protected String processStdOut(InputStream is)
    {
        String stdout = new BufferedReader( new InputStreamReader(is))
                      .lines()
                      .collect(Collectors.joining("\n"));
                    
        return stdout;
    }
    
    public void setCommandProfile(SystemCommandProfile profile)
    {
        this.profile = profile;
    }
}
