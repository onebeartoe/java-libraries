
package org.onebeartoe.application.io.streams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.onebeartoe.application.io.streams.PositionalAwkette.RunProfile;

/**
 * @author Roberto Marquez
 */
public class PositionalAwketteService
{
    public void serviceRequest(RunProfile runProfile)
    {
        switch (runProfile.mode)
        {
            case NOT_MODE:
            {
                BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
                br.lines().forEach( l -> 
                {
                    String [] split = l.split("\\s+");
                    
                    for(int i=0; i<split.length; i++)
                    {
                        if( runProfile.positionals.contains(i) )
                        {
                            // do not print
                        }
                        else
                        {
                            System.out.print(split[i]);
                            
                            if(i != split.length - 1)
                            {
                                System.out.print(' ');
                            }
                        }
                    }
                    
                    System.out.println();
                });
                
                System.out.println("end");
                
                break;
            }
            default:
            {
                throw new AssertionError();
            }
        }
    }
}
