
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
    
    public boolean seemsLikeMac()
    {
        boolean isMac = false;
        
        String os = System.getProperty("os.name").toLowerCase();
        if( os.contains("mac") )
        {
            isMac = true;
        }
        
        return isMac;
    }
    
    public boolean seemsLikeLinux()
    {
        return !seemsLikeMac() && !seemsLikeMsWindows();
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
