
package org.onebeartoe.application.ui;

import javafx.stage.Stage;
import org.onebeartoe.application.DesktopApplication;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class JavafxApplication extends DesktopApplication
{
    public WindowProperties currentConfiguration(Stage stage)
    {
        WindowProperties wp = new WindowProperties();
        
        wp.locationX = (int) stage.getX();
        wp.locationY = (int) stage.getY();

        wp.width = (int) stage.getWidth();
        wp.height = (int) stage.getHeight();
        
        return wp;
    }    
    
    
//    @Override
    public void restoreWindowProperties(final WindowProperties wp, final Stage stage)// throws FileNotFoundException, IOException, ClassNotFoundException
    {        
//        WindowProperties wp = null;     
//        String applicationId = wp.applicationName;
        
//        wp = loadWindowProperties(applicationId);
  
        // update the gui
        
        stage.setWidth(wp.width);
        stage.setHeight(wp.height);
                
        stage.setX(wp.locationX);
        stage.setY(wp.locationY);
    }        
}