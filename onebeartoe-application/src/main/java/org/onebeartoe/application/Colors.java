
package org.onebeartoe.application;

import static java.lang.System.err;
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
    public static Map<String, Color> list()
    {
        Map<String, Color> colors = new HashMap();
//TODO: lambda and stream this!        
        final Field[] fields = Color.class.getFields(); // only want public
        for (final Field field : fields)
        {
            if (field.getType() == Color.class)
            {
                try
                {
                    final Color color = (Color) field.get(null);
                    final String colorName = field.getName();
                    
                    colors.put(colorName, color);                    
                }
                catch (IllegalAccessException illegalAccessEx)
                {
                    err.println(
                            "Securty Manager does not allow access of field '"
                            + field.getName() + "'.");
                }
            }
        }
        
        return colors;
    }
    
    public static Color valueOf(String targetColorName)
    {
        Color color = null;
        
//TODO: lambda and stream this!        
        final Field[] fields = Color.class.getFields(); // only want public
        
        for (final Field field : fields)
        {
            if (field.getType() == Color.class)
            {
                try
                {
                    final Color currentColor = (Color) field.get(null);
                    final String colorName = field.getName();
                    
                    if( colorName.equals(targetColorName) )
                    {
                        color = currentColor;
                        
                        break;  // stop iterating once the target color is found
                    }
                }
                catch (IllegalAccessException illegalAccessEx)
                {
                    String message = "Securty Manager does not allow access of field '"
                            + field.getName() + "' - " + illegalAccessEx.getMessage();
                    
                    err.println(message);
                }
            }
        }
        
        return color;
    }    
}
