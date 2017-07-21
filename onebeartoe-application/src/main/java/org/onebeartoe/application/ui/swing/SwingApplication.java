
package org.onebeartoe.application.ui.swing;

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
    
    public WindowProperties currentConfiguration(JFrame stage)
    {
        WindowProperties wp = new WindowProperties();
        
        wp.locationX = (int) stage.getX();
        wp.locationY = (int) stage.getY();

        wp.width = (int) stage.getWidth();
        wp.height = (int) stage.getHeight();
        
        return wp;
    }
    
    /**
     * This method updates the JavaFx GUI with the properties in the wp object
     */
    public void restoreWindowProperties(final WindowProperties wp, final JFrame stage)
    {        
        stage.setSize(wp.width, wp.height);
                
        stage.setLocation(wp.locationX ,wp.locationY);
    }    
}
