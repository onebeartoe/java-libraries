
package org.onebeartoe.ui;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.onebeartoe.application.ui.LookAndFeelButton;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class LookAndFeelButtonSpecification 
{
    private LookAndFeelButton implementation;
    
    public LookAndFeelButtonSpecification()
    {
        JFrame container = new JFrame();
        String text = "Start Fun";
        implementation = new LookAndFeelButton(text, container);
    }
    
    @Test(groups = {"unit"})
    public void actionPerformed()
    {
        ActionEvent ae = new ActionEvent(this, 0, "command");
        implementation.actionPerformed(ae);
    }
}