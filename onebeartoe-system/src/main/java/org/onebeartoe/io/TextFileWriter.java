
package org.onebeartoe.io;

import java.io.File;
import java.io.FileWriter;
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
    public boolean writeText(File f, String text)
    {
        return writeText(f, text, false);
    }

    /**
     * If the value of append is true the text will be add to the end of the
     * file. if the value is false the text is add the begining of the file.
     */
    public boolean writeText(File f, String text, boolean append)
    {
        try
        {
            FileWriter file = new FileWriter(f, append);
            PrintWriter pic_index = new PrintWriter(file);
            pic_index.println(text);
            pic_index.close();

            return true;
        } 
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
            return false;
        }
    }

}
