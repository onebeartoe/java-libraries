/*

 */
package org.onebeartoe.system.command;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class Diff extends SystemCommand
{
    public Diff(String path1, String path2)
    {
        profile = new SystemCommandProfile();
        
        String systemCommand = "diff " + path1 + " " + path2;
        String [] strs = systemCommand.split("\\s+");
        List <String> commandAndArgs = Arrays.asList(strs);        
        profile.commandAndArgs = commandAndArgs;
        
        profile.processStdErr = true;
        profile.processStdOut = true;
    }
}
