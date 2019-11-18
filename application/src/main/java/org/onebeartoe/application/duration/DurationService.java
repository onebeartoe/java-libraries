
package org.onebeartoe.application.duration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Roberto Marquez
 */
public class DurationService
{
//TODO: add a prepend option to this and update 3D-modeling to use it    
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
