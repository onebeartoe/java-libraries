
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
}
