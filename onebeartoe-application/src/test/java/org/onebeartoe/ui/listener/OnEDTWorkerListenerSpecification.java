
package org.onebeartoe.ui.listener;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class OnEDTWorkerListenerSpecification 
{
    private OnEDTWorkerListener implementation;
    
    public OnEDTWorkerListenerSpecification()
    {
        JFrame parent = new JFrame();
        implementation = new OnEDTWorkerListener(parent);
    }
    
    @Test(groups = {"unit"})
    public void actionPerformed()
    {
        ActionEvent e = new ActionEvent(this, 0, "command");
        
        implementation.actionPerformed(e);                
    }
}
