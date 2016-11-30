
package org.onebeartoe.system;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class OperatingSystem 
{
    public String currentUserHome()
    {
        String homeDirectory = System.getProperty("user.home");
        
        return homeDirectory;
    }
    
    public boolean seemsLikeMsWindows()
    {
        boolean windows = false;
        
        String os = System.getProperty("os.name");
        if( os.contains("Windows") )
        {
            windows = true;
        }
        
        return windows;
    }
}
