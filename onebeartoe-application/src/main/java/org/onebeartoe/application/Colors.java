
package org.onebeartoe.application;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * This is a utility class that lists the built-in Colors that
 * JavaFX provides.
 * 
 * The original code if from: http://www.javaworld.com/article/2074533/core-java/viewing-javafx-2-standard-colors.html
 * 
 * @author Roberto Marquez
 */
public class Colors
{
    public static Map<String, Color> list() throws IllegalAccessException
    {
        Map<String, Color> colors = new HashMap();

        final Field[] fields = Color.class.getFields(); // only want public
        
        for (final Field field : fields)
        {
            if (field.getType() == Color.class)
            {
                final Color color = (Color) field.get(null);
                final String colorName = field.getName();

                colors.put(colorName, color);
            }
        }
        
        return colors;
    }
    
    public static Color valueOf(String targetColorName) throws IllegalArgumentException, IllegalAccessException
    {
        Color color = null;
        
        final Field[] fields = Color.class.getFields(); // only want public
        
        for (final Field field : fields)
        {
            if (field.getType() == Color.class)
            {
                final Color currentColor = (Color) field.get(null);
                final String colorName = field.getName();

                if( colorName.equals(targetColorName) )
                {
                    color = currentColor;

                    break;  // stop iterating once the target color is found
                }
            }
        }
        
        return color;
    }    
}
