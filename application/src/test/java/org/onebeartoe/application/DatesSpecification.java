
package org.onebeartoe.application;

import java.util.Date;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 */
public class DatesSpecification
{
    private Dates implementation;
    
    @Test
    public void shortFilesystemDate()
    {
        int month = 12 - 1;
        int day = 17;
        int year = 2000;
        
        Date date = new Date(year, month, day);
        
        String actual = implementation.shortFilesystemDate(date);
        
        String expected = "2000-12-17";
        
        assertEquals(actual, expected);
    }
}
