
package org.onebeartoe.application.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import org.onebeartoe.application.DesktopApplication;

/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public abstract class JavafxApplication extends DesktopApplication
{
//    private String id;
    
    /**
     * 
     * @param id 
     */
    public JavafxApplication(String id)
    {
        super(id);
//        this.id = id;
    }
    
    public WindowProperties currentConfiguration(Stage stage)
    {
        wp.locationX = (int) stage.getX();
        wp.locationY = (int) stage.getY();

        wp.width = (int) stage.getWidth();
        wp.height = (int) stage.getHeight();
        
        return wp;
    }

    /**
     * This method updates the JavaFx GUI with the properties in the wp object
     */
    public void restoreWindowProperties(final WindowProperties wp, final Stage stage)// throws FileNotFoundException, IOException, ClassNotFoundException
    {        
        stage.setWidth(wp.width);
        stage.setHeight(wp.height);
                
        stage.setX(wp.locationX);
        stage.setY(wp.locationY);
    }
    
    /**
     * careful, here
     * @param stage 
     */
    public void restoreWindowProperties(Stage stage)
    {
        try
        {
            wp = loadWindowProperties();
// careful, here            
//            wp.id = applicationId;
            restoreWindowProperties(wp, stage);
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        if(wp == null)
        {
            // Prvoide default values if someting goes wrong with retoring the 
            // persisted values.
            wp = new WindowProperties();

//            wp.id = id;
//            wp.applicationName = getClass().getSimpleName();
            
            // use the default values
            wp.width = defaultWidth();
            wp.height = defaultHeight();
            
            wp.locationX = defaultX();
            wp.locationY = defaultY();
            
            restoreWindowProperties(wp, stage);
        }
    }

    public void setApplicationId(String id)
    {
        this.id = id;
    }
}