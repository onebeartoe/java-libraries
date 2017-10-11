
package org.onebeartoe.application.ui;

import java.io.File;
import javafx.scene.control.Alert;
   
/**
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class JavaFxGuiServices implements GraphicalUserInterfaceServices
{

    @Override
    public void infoMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void infoMessage(String message, String title) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information Alert");
        alert.setContentText(message);

        alert.show();
    }

    @Override
    public void infoMessage(Object message, String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File selectSaveAsFile(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File selectDirectory(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewBrowser(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
