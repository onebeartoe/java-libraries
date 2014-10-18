
package org.onebeartoe.system;

/**
 * @author Roberto Marquez
 */
public class Sleeper 
{
    public static void sleepo(long duration)
    {
        try 
        {
            Thread.sleep(duration);
        } 
        catch (InterruptedException ex) 
        {
            System.err.println("could not sleep");
        }                
    }
}
