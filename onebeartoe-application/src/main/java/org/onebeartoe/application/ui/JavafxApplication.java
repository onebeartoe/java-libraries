
package org.onebeartoe.application.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.onebeartoe.application.DesktopApplication;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public interface JavafxApplication extends DesktopApplication
{
    @Override
    default WindowProperties restoreWindowProperties(String applicationId) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        
        
        WindowProperties wp = null;
        
        try 
        {
            wp = restoreWindowProperties(applicationId);
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
        if(wp == null)
        {
            wp = new WindowProperties();
            
            wp.id = getClass().getName();
            wp.applicationName = getClass().getSimpleName();
            
            // use the default values
            wp.width = 573;
            wp.height = 114;
            
            wp.locationX = 50;
            wp.locationY = 100;
        }                
                
                
        return wp;
    }        
}