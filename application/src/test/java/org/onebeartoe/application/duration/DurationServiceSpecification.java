
package org.onebeartoe.application.duration;

import java.time.Duration;
import java.time.Instant;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 * These tests verify the specification of the DurationService class.
 */
public class DurationServiceSpecification
{    
    @Test
    public void durationMessage_noPrependText()
    {
        DurationService implementation = new DurationService();
        
        Instant start = Instant.now();
        Instant end = start.plus( Duration.ofMinutes(1) );
        
        String acutal = implementation.durationMessage(start, end);
        
        String expected = "1 minutes 0 seconds.";
        
        assertTrue( acutal.equals(expected) );
    }
    
    @Test
    public void durationMessage_prependText()
    {
        DurationService implementation = new DurationService();
     
        implementation.setPrependText("The test suite ran ");
        
        Instant start = Instant.now();
        Instant end = start.plus( Duration.ofSeconds(10) );
        
        String acutal = implementation.durationMessage(start, end);
        
        String expected = "The test suite ran 0 minutes 10 seconds.";
        
        assertEquals(acutal, expected);
    }
}
