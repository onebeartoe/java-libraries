
package org.onebeartoe.application;

import java.awt.Point;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import org.onebeartoe.pixel.preferences.PixelPreferencesKeys;

/**
 * @author Roberto Marquez
 */
public class JavaPreferencesService implements PreferencesService
{   
    private Preferences preferences;
    
    public JavaPreferencesService(Class c)//DesktopApplication application)
    {
	preferences = Preferences.userNodeForPackage(c);
    }

    @Override
    public String get(Enum type, String defaultValue) 
    {
        String name = type.name();
        
        String value = get(name, defaultValue);
        
        return value;
    }    
    
    public String get(String key, String defaultValue) 
    {	
	String value = preferences.get(key, defaultValue);
	
	return value;
    }        

    @Override
    public String restoreProperty(String key)
    {
        String prop = get(key, null);
        
        return prop;
    }
    
//    public Dimension restoreWindowDimension() 
//    {	
////todo: remove the hard coding.   wait, is this needed here?
//	String key = PixelPreferencesKeys.windowWidth;
//	int width = preferences.getInt(key, application.DEFAULT_WIDTH);
//	
//	key = PixelPreferencesKeys.windowHeight;
//	int height = preferences.getInt(key, application.DEFAULT_HEIGHT);
//	
//	Dimension demension = new Dimension(width, height);
//	
//	return demension;
//    }
    
//    @Override
//    public Point restoreWindowLocation() throws Exception
//    {
////todo: remove the hard coding.   wait, is this needed here?        
//	int errorValue = -1;
//	String key = PixelPreferencesKeys.windowX;
//	int x = preferences.getInt(key, errorValue);
//
//	key = PixelPreferencesKeys.windowY;
//	int y = preferences.getInt(key, errorValue);
//	
//	if(x == errorValue || y == errorValue)
//	{
//	    // The window location hasn't been saved, yet.
//	    
//	    throw new Exception();
//	}
//	
//	Point point = new Point(x,y);
//	
//	return point;
//    }    

    @Override
    public void saveProperty(String key, String value) throws BackingStoreException
    {
        preferences.put(key, value);
        preferences.flush();
    }    
    
    @Override
    public void saveWindowPreferences(JFrame window)
    {
	int x = window.getX();
	String key = PixelPreferencesKeys.windowX;
	preferences.putInt(key, x);
	
	int y = window.getY();
	key = PixelPreferencesKeys.windowY;
	preferences.putInt(key, y);
	
	int width = window.getWidth();	
	key = PixelPreferencesKeys.windowWidth;
	preferences.putInt(key, width);
	
	int height = window.getHeight();
	key = PixelPreferencesKeys.windowHeight;
	preferences.putInt(key, height);
    }
}