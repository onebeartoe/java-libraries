
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
    public boolean writeText(File outfile, String text)
    {
        return writeText(outfile, text, false);
    }

    /**
     * If the value of append is true the text will be add to the end of the
     * file. if the value is false the text is add the begining of the file.
     */
    public boolean writeText(File outfile, String text, boolean append)
    {
        boolean saved = true;
        try
        {
            FileWriter file = new FileWriter(outfile, append);
            PrintWriter pic_index = new PrintWriter(file);
            pic_index.println(text);
            pic_index.close();
        } 
        catch(FileNotFoundException fnfe)
        {
            saved = false;
            fnfe.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            
            saved = false;
        }
        
        return saved;
    }
}