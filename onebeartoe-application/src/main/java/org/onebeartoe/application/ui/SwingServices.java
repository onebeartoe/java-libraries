
package org.onebeartoe.application.ui;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Roberto Marquez
 */
public class SwingServices implements GraphicalUserInterfaceServices
{

    File currentDirectory;

    JFileChooser fileChooser;

    public SwingServices()
    {
        currentDirectory = new File(System.getProperty("user.home"));
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(currentDirectory);
    }

    public void infoMessage(String message)
    {
        infoMessage(message, "Message:");
    }

    public void infoMessage(String message, String title)
    {
        Object obj = (Object) message;
        infoMessage(obj, title);
    }

    public void infoMessage(Object message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public File selectSaveAsFile(String title)
    {
        fileChooser.setDialogTitle(title);
        int result = fileChooser.showSaveDialog(null);

        File file;

        // user clicked Cancel button on dialog
        if (result == JFileChooser.CANCEL_OPTION)
        {
            file = null;
        } else
        {
            file = fileChooser.getSelectedFile();
            currentDirectory = fileChooser.getCurrentDirectory();
        }

        return file;
    }

    @Override
    public void viewBrowser(String url)
    {
        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            URI uri;
            try
            {
                uri = new URI(url);
                desktop.browse(uri);
            } catch (Exception e)
            {
                e.printStackTrace();
                String message = "Visit this URL to see the web interface: " + url;
                String title = "The browser could not be opened.";
                infoMessage(message, title);
            }
        }
    }

    @Override
    public File selectDirectory(String title)
    {
        File dir = GUITools.selectDirectory(title);

        return dir;
    }

}
