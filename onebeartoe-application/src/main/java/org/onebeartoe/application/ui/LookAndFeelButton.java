
package org.onebeartoe.application.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author Roberto Marquez
 * Using this button will change the look and feel.
 * Created: June 24th 2003
 */
public class LookAndFeelButton extends JButton implements ActionListener
{
    static final long serialVersionUID = 3L;

    private int current_look = 0;
    
    private transient UIManager.LookAndFeelInfo looks[];
    
    private Component container;

    // testing objects
    int apCount;
    
    boolean testing;

    // Constructor
    public LookAndFeelButton(String text, Component container)
    {
        super(text);
        
        looks = UIManager.getInstalledLookAndFeels();
        
        this.container = container;
        
        addActionListener(this);

        apCount = 0;
        testing = false;
    }

    private void changeTheLookAndFeel(int value)
    {
        try
        {
            // get the installed look-and-feel information
            UIManager.setLookAndFeel(looks[current_look].getClassName());
            SwingUtilities.updateComponentTreeUI(container);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        current_look++;

        if (current_look == looks.length)
        {
            current_look = 0;
        }

        changeTheLookAndFeel(current_look);
        apCount++;

        if (testing)
        {
            System.out.println("actionPerformed() call: " + apCount);
        }
    }

    /**
     * main() calls a JFrame to the screen with the our button on it
     */
    public static void main(String args[])
    {
        JFrame app = new JFrame();
        LookAndFeelButton button = new LookAndFeelButton("Change Look", app);
        app.getContentPane().add(button);
        app.setLocation(50, 75);
        app.setSize(200, 200);
        app.setVisible(true);
//      app.show();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         app.addWindowListener(
         new WindowAdapter() {
         public void windowClosing( WindowEvent e )
         {
         System.exit( 0 );
         }
         }
         );
         */
    }

    public void setTesting(boolean value)
    {
        testing = value;
    }

    public int getActionPerformedCount()
    {
        return apCount;
    }
}
