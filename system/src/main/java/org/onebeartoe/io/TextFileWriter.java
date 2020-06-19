
package org.onebeartoe.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Roberto Marquez
 */
public class TextFileWriter implements TextWriter
{
    /**
     * By default this method writes the test to the beginning of the file. Any
     * text already in the file is overwritten.
     */
    public void writeText(File outfile, String text) throws IOException
    {
        writeText(outfile, text, false);
    }

    /**
     * This method writes text to a file.  If the value of append is true the 
     * appended to the existing file otherwise the file is overwritten.
     */
    public void writeText(File outfile, String text, boolean append) throws IOException
    {
//        boolean saved = true;
        try(FileWriter file = new FileWriter(outfile, append);
            PrintWriter index = new PrintWriter(file);)
        {
            
            index.print(text);
        } 
//        catch(FileNotFoundException fnfe)
//        {
//            saved = false;
//            fnfe.printStackTrace();
//        }
//        catch (IOException ioe)
//        {
//            ioe.printStackTrace();
//            
//            saved = false;
//        }
        
//        return saved;
    }
}
