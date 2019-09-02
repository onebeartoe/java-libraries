
package org.onebeartoe.application.ui.swing;

import java.io.IOException;
import javax.swing.JFrame;
import org.onebeartoe.application.DesktopApplication;
import org.onebeartoe.application.ui.WindowProperties;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public abstract class SwingApplication extends DesktopApplication
{
    public SwingApplication(String id) 
    {
        super(id);
    }
    
    public WindowProperties setCurrentConfiguration(JFrame stage)
    {
        wp.locationX = (int) stage.getX();
        wp.locationY = (int) stage.getY();

        wp.width = (int) stage.getWidth();
        wp.height = (int) stage.getHeight();
        
        return wp;
    }
    
    /**
     * This method updates the Swing GUI with the properties in the wp object
     */
    private void restoreWindowProperties(final WindowProperties wp, final JFrame stage)
    {        
        stage.setSize(wp.width, wp.height);
                
        stage.setLocation(wp.locationX ,wp.locationY);
    }
    
    /**
     * This method provides default values if something goes wrong with restoring the 
     * persisted values.
     * 
     * @param stage 
     */
    public void restoreWindowProperties(JFrame stage)
    {
        boolean loadError = false;
        try
        {
            wp = loadWindowProperties();
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            loadError = true;
            ex.printStackTrace();
        }

        if(loadError)
        {
            // use the default values
            wp.width = defaultWidth();
            wp.height = defaultHeight();
            
	    // center it
	    stage.setLocationRelativeTo(null); 		            
            
            wp.locationX = (int) stage.getLocation().getX();
            wp.locationY = (int) stage.getLocation().getY();
        }
        
        restoreWindowProperties(wp, stage);
    }    
}
