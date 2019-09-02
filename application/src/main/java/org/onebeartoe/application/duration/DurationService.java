
package org.onebeartoe.application.duration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Roberto Marquez
 */
public class DurationService
{
    public String durationMessage(Instant start, Instant end)
    {
        ChronoUnit units = ChronoUnit.SECONDS;
        long duration = units.between(start,end);
        long minutes = duration / 60;
        long seconds = duration % 60;
        String message = "The test suite ran " + minutes + " minutes " + seconds + " seconds.";
                
        return message;
    }    
}
