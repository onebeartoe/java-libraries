
package org.onebeartoe.web.utilities.jsp;

import java.util.Optional;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 * 
 */
public class SeedResult 
{
    public SeedResults type;
    
    public String details;
    
    public Optional<Exception> error;
    
    public SeedResult()
    {
        error = Optional.empty();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
                        
        sb.append( type.name() );
        sb.append(": ");
        sb.append("\n");
        sb.append(details);
        sb.append("\n");
        
        if( error.isPresent() )
        {
            String message = error.get().getMessage();
            sb.append(message);
            sb.append("\n");
        }
        
        return sb.toString();
    }    
}
