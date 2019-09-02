
package org.onebeartoe.system;

import java.io.InputStream;
import java.util.List;

/**
 * @author Roberto Marquez
 */
public class CommandResults 
{
    public int exitCode;
        
    public String processedStdOut;
    
    public String processedStdErr;
    
    public InputStream stdOut;
    
    public InputStream stdErr;    
    
    @Override
    @Deprecated
    /**
     * @Deprecated where is this used.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(processedStdOut + System.lineSeparator());

        sb.append(processedStdErr + System.lineSeparator());
        
        return sb.toString();
    }
}
