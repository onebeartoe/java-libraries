
package org.onebeartoe.application.ui;

import java.io.File;

/**
 * @author Roberto Marquez
 */
public interface GraphicalUserInterfaceServices
{
    void infoMessage(String message);

    void infoMessage(String message, String title);

    void infoMessage(Object message, String title);

    File selectSaveAsFile(String title);

    File selectDirectory(String title);

    void viewBrowser(String url);
}
