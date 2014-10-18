
package org.onebeartoe.application;

/**
 * @author Roberto Marquez
 */
public class Enums 
{
    public static String stringToEnumName(String s)
    {
        String name = s.trim().toUpperCase();
        name = name.replace("-", "_");
        
        return name;
    }
}
