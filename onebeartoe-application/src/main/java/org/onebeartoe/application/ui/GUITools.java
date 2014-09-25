
package org.onebeartoe.application.ui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.onebeartoe.application.filesystem.FileSelectionMethods;

/**
 * Copyright 2000 Roberto Marquez
 * This file is part of onebeartoe API for Java.
 * @author Roberto Marquez
 */
public class GUITools
{
    @Deprecated
    public static final int FILES_ONLY = 1;

    @Deprecated
    public static final int DIRECTORIES_ONLY = 2;

    public static String getString(String message)
    {
        return JOptionPane.showInputDialog(message);
    }

    public static TitledBorder factoryLineBorder(String title)
    {

        TitledBorder border = factoryLineBorder(title, Color.BLUE);

        return border;
    }

    public static TitledBorder factoryLineBorder(String title, Color color)
    {
        Border lineBorder = BorderFactory.createLineBorder(color, 3);
        TitledBorder border = new TitledBorder(lineBorder, title);

        return border;
    }

    /**
     * This method takes any java.awt.Component and returns a BufferedImage
     * object representing the gui componet's screen shot.
     *
     * @param gui
     * @return
     */
    static public BufferedImage screenShot(Component gui) throws AWTException
    {
        Rectangle screenRect = mapComponentToRectangle(gui);
        Robot bot = new Robot();
        return bot.createScreenCapture(screenRect);
    }

    static private Rectangle mapComponentToRectangle(Component gui)
    {
        Rectangle r = new Rectangle(gui.getLocationOnScreen());
        r.setSize(gui.getWidth(), gui.getHeight());
        return r;
    }

    /**
     * @deprecated use an implementation of the GraphicalUserInterfaceServices
     * interface
     * @param message
     */
    public static void infoMessage(String message)
    {
        GraphicalUserInterfaceServices guiServices = new SwingServices();
        guiServices.infoMessage(message);
//		infoMessage(message, "Message:");
    }

    public static void inputDialog(String title, Component inputComponent)
    {
        JOptionPane.showMessageDialog(null, inputComponent, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @deprecated use an implementation of the GraphicalUserInterfaceServices
     * interface
     * @param message
     * @param title
     */
    public static void infoMessage(String message, String title)
    {
        GraphicalUserInterfaceServices guiServices = new SwingServices();
        guiServices.infoMessage(message, title);
    }

    public static int yesNoQuestion(String question)
    {
        return JOptionPane.showConfirmDialog(null, question, "Question:", JOptionPane.YES_NO_OPTION);
    }

    public static File selectFile(int mode)
    {
        String title;
        if (mode == FILES_ONLY)
        {
            title = "Select a File";
        } else
        {   // mode == DIRECTORIES_ONLY
            title = "Select a Directory";
        }

        return selectFile(mode, title);
    }

    private static File currentDirectory = new File(System.getProperty("user.home"));

    public static File selectFile(int mode, String title)
    {
        FileSelectionMethods selectionMode;
        if (mode == FILES_ONLY)
        {
            selectionMode = FileSelectionMethods.SINGLE_FILE;
        } else if (mode == DIRECTORIES_ONLY)
        {
            selectionMode = FileSelectionMethods.SINGLE_DIRECTORY;
        } else
        {
            selectionMode = FileSelectionMethods.SINGLE_FILE;
        }

        File file = selectFile(selectionMode, title);

        return file;
    }

    public static File selectFile(FileSelectionMethods mode, String title)
    {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(currentDirectory);

        fileChooser.setDialogTitle(title);

        if (mode == FileSelectionMethods.MULTIPLE_FILES)
        {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        } else if (mode == FileSelectionMethods.MULTIPLE_DIRECTORIES || mode == FileSelectionMethods.SINGLE_DIRECTORY)
        {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        int result = fileChooser.showOpenDialog(null);

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

    public static File selectFile()
    {
        return selectFile(FILES_ONLY);
    }

    public static File selectFile(String title)
    {
        return selectFile(FILES_ONLY, title);
    }

    public static File selectDirectory()
    {
        return selectFile(DIRECTORIES_ONLY);
    }

    public static File selectDirectory(String title)
    {
        return selectFile(DIRECTORIES_ONLY, title);
    }

}
