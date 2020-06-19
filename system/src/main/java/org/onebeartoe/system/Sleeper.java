
package org.onebeartoe.system;

/**
 * @author Roberto Marquez
 */
public class Sleeper 
{
    public static void sleepo(long durationInMillis)
    {
        try 
        {
            Thread.sleep(durationInMillis);
        } 
        catch (InterruptedException ex) 
        {
            System.err.println("could not sleep");
            
            Thread.currentThread().interrupt();
        }                
    }
}
