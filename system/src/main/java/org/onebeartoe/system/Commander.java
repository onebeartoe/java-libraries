
package org.onebeartoe.system;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
/**
 * @deprecated where is this even used?
 * @author Roberto Marquez
 */
public class Commander
{
    private String command;
	
    private List<String> stdout;

    private List<String> stderr;

//    static final String defaultFile = "errors.txt";
    
    public Commander()
    {
        this("no-opp");
    }

    /**
     * @deprecated This constructor is deprecated so as to not use one command 
     * per Commander object for one command.  
     * 
     * The Commander.java class should handle multiple different commands.
     * 
     * @param command 
     */
    public Commander(String command)
    {
            this.command = command;
            stdout = new ArrayList();
            stderr = new ArrayList();
    }
	
    public int execute() throws IOException, InterruptedException 
    {
        boolean useWindowsCmd = false;
        
        String os = System.getProperty("os.name");
        if( os.contains("Windows") && ! command.toLowerCase().startsWith("cmd /c") && useWindowsCmd)
        {
                command = "cmd /C " + command;				
        }

        System.out.println("executing: " + command);

        String[] split = command.split("\\s+");
        List<String> commandAndArgs = Arrays.asList(split);
        
        System.out.println("commandAndArgs["
                                + commandAndArgs.size()
                                + "] = " 
                                + commandAndArgs);
        
        ProcessBuilder builder = new ProcessBuilder(commandAndArgs);
//        ProcessBuilder builder = new ProcessBuilder(command, "push", "terue", "sss");
        
        Process jobProcess = builder.start();
        
//        Runtime runtime = Runtime.getRuntime();	
//        Process jobProcess = runtime.exec(command);
                
        int waitValue = jobProcess.waitFor();


        // read the output from the command
        InputStream instream = jobProcess.getInputStream();
        InputStreamReader insteamReader = new InputStreamReader(instream);
        BufferedReader stdInput = new BufferedReader(insteamReader);		
        String s = null;

        while ((s = stdInput.readLine()) != null) 
        {
            stdout.add(s);
        }
                
        // read any errors from the attempted command
        InputStream is = jobProcess.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader stdError = new BufferedReader(isr);
        while ((s = stdError.readLine()) != null) 
        {
            stderr.add(s);
        }

//int waitValue = jobProcess.waitFor();        
        
        int exitCode = jobProcess.exitValue();

        return exitCode;
   }
 
   public int executeCommand(String command) throws IOException, InterruptedException 
   {
       this.command = command;
               
       int exitValue = execute();
       
       return exitValue;        
   }
   
   public List<String> getStderr() 
   {
            return stderr;
    }

    public List<String> getStdout() 
    {
            return stdout;
    }
   
//   static boolean checkLF;  // A kludge to take care of the fact that text files
                            // on different files can have different formats.  Lines can end
                            // with either a carriage return, or a line feed, or a carriage
                            // return followed by a line feed.  This variable is used by the
                            // following subroutine so that it can remember to throw away
                            // a line feed that follows a carriage return, rather than
                            // treat it as an empty line.
   
   /**
    * This subroutine reads one line from the input stream, in.
    * If the end-of-stream has been reached, null is returned.
    * (Null is also returned if an input error occurs.)
    * @param in
    * @return 
    */
//   static String readLine(InputStream in) 
//   {
//      try 
//      {
//         int ch = in.read();
//         if (checkLF && ch == '\n')
//            ch = in.read();
//         if (ch == -1)
//            return null;
//         StringBuffer b = new StringBuffer();
//         while (ch != -1 && ch != '\r' && ch != '\n') 
//         {
//            b.append( (char)ch );
//            ch = in.read();
//         }
//         return b.toString();
//      }
//      catch (IOException e) 
//      {
//         return null;
//      }
//   }
}
