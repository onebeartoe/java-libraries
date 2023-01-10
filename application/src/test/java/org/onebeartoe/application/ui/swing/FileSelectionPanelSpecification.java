
package org.onebeartoe.application.ui.swing;

import java.awt.event.ActionListener;
import org.onebeartoe.application.filesystem.FileSelectionMethods;
import org.onebeartoe.filesystem.FileType;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 */
public class FileSelectionPanelSpecification
{
//    FileSelectionPanel implementation;
    
    @Test
    public void constructor_withTitle()
    {
        boolean showRecursive = false;
        
        ActionListener preferenceSaveListener = null;
        
        String expectedLabel = "Select the location of the WAR's 'webapp' directory.";
        
        FileSelectionPanel implementation = new FileSelectionPanel(FileType.IMAGE, 
                                                    FileSelectionMethods.SINGLE_DIRECTORY, 
                                                    showRecursive, 
                                                    preferenceSaveListener,
                                                    expectedLabel);
        
        String actual = implementation.getSourceDirectoryLabel();
        
        assertEquals(expectedLabel, actual);
    }    
}
