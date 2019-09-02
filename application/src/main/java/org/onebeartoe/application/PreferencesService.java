
package org.onebeartoe.application;

import java.util.prefs.BackingStoreException;

/**
 * @author rmarquez
 */
public interface PreferencesService
{
    String get(Enum type, String defaultValue);
    
    String get(String key, String defaultValue);
    
    String restoreProperty(String key);
    
//    Dimension restoreWindowDimension() throws Exception;
    
//    Point restoreWindowLocation() throws Exception;
    
    void saveProperty(String key, String value) throws BackingStoreException;
    
//    void saveWindowPreferences(JFrame window);
}
