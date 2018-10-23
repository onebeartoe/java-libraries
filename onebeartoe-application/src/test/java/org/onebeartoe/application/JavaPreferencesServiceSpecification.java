
package org.onebeartoe.application;

import javax.swing.JFrame;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class JavaPreferencesServiceSpecification 
{
    private JavaPreferencesService implementation;
    
    public JavaPreferencesServiceSpecification()
    {
        implementation = new JavaPreferencesService( getClass() );
    }
    
//    @Test(groups = {"unit"})
//    public void saveWindowPreferences()
//    {
//        JFrame jframe = new JFrame();
//        
//        implementation.saveWindowPreferences(jframe);
//        
//        String key = PreferencesKeys.WINDOW_X;
//        String s = implementation.get(key, "");
//        int x = Integer.parseInt(s);
//        
//        assert(x == 3);
//    }
}
