
package org.onebeartoe.system;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roberto Marquez
 */
public class Filesystem 
{
    public static String systimeToFilename()
    {
        Date today;
        String output;
        SimpleDateFormat formatter;

        // yyyy.MM.dd G 'at' hh:mm:ss z 	2009.06.30 AD at 08:29:36 PDT
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        formatter = new SimpleDateFormat(pattern);//, currentLocale);
        today = new Date();
        output = formatter.format(today);
        
        return output;
    }
}
