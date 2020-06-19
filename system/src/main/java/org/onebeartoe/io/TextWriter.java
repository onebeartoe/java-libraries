
package org.onebeartoe.io;

import java.io.File;
import java.io.IOException;

public interface TextWriter 
{
    /**
     * This method writes text to the specified File, overwriting any existing 
     * content. 
     * 
     * @param outfile
     * @param text
     * @throws IOException 
     */
    void writeText(File outfile, String text) throws IOException;

    void writeText(File outfile, String text, boolean append) throws IOException;
}
