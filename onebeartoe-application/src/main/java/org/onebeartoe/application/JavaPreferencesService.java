
package org.onebeartoe.application;

import java.awt.Dimension;
import java.awt.Point;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import org.onebeartoe.pixel.preferences.PixelPreferencesKeys;

/**
 * @author rmarquez
 */
public class JavaPreferencesService implements PreferencesService
{   
    private Preferences preferences;
    
    private DesktopApplication application;
    
    public JavaPreferencesService(DesktopApplication application)
    {
        this.application = application;
        Class c = this.application.getClass();        
        
	preferences = Preferences.userNodeForPackage(c);
    }

    public String get(String key, String defaultValue) 
    {	
	String value = preferences.get(key, defaultValue);
	
	return value;
    }        

    @Override
    public String restoreProperty(String key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Dimension restoreWindowDimension() 
    {	
	String key = PixelPreferencesKeys.windowWidth;
	int width = preferences.getInt(key, application.DEFAULT_WIDTH);
	
	key = PixelPreferencesKeys.windowHeight;
	int height = preferences.getInt(key, application.DEFAULT_HEIGHT);
	
	Dimension demension = new Dimension(width, height);
	
	return demension;
    }
    
    @Override
    public Point restoreWindowLocation() throws Exception
    {
	int errorValue = -1;
	String key = PixelPreferencesKeys.windowX;
	int x = preferences.getInt(key, errorValue);

	key = PixelPreferencesKeys.windowY;
	int y = preferences.getInt(key, errorValue);
	
	if(x == errorValue || y == errorValue)
	{
	    // The window location hasn't been saved, yet.
	    
	    throw new Exception();
	}
	
	Point point = new Point(x,y);
	
	return point;
    }    

    @Override
    public void saveProperty(String key, String value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
