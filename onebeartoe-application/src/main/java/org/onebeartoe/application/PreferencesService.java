
package org.onebeartoe.application;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;

/**
 * @author rmarquez
 */
public interface PreferencesService
{
    String get(String key, String defaultValue);
    
    Dimension restoreWindowDimension() throws Exception;
    
    Point restoreWindowLocation() throws Exception;
    
    void saveWindowPreferences(JFrame window);
}
